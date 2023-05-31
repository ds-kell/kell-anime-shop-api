package com.kell.service.impl;


import com.kell.model.*;
import com.kell.model.constants.OrderStatus;
import com.kell.repository.*;
import com.kell.service.BillService;
import com.kell.service.utils.MappingHelper;
import com.kell.webapp.dto.*;
import com.kell.webapp.dto.request.BillCriteria;
import com.kell.webapp.dto.request.BillReq;
import com.kell.webapp.dto.request.BillStatusUpdateReq;
import com.kell.webapp.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final ProductCartRepository productCartRepository;
    private final BillRepository billRepository;
    private final ProductBillRepository productBillRepository;
    private final CartRepository cartRepository;
    private final AccountRepository accountRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final ShippingServiceRepository shippingServiceRepository;
    private final ProductDetailRepository productDetailRepository;
    private final MappingHelper mappingHelper;

    @Override
    @Transactional
    public void createBillByCurrentAccount(BillReq billReq) {
        Bill bill = mappingHelper.map(billReq, Bill.class);
        bill.setStatus(OrderStatus.PENDING);
        bill.setPaymentTime(new Date());
        var account = getCurrentAccount();
        bill.setAccount(account);
        var address = deliveryAddressRepository.findById(billReq.getDeliveryAddressId())
                .orElseThrow(() -> new EntityNotFoundException(
                        ShippingService.class.getName(), billReq.getDeliveryAddressId().toString()));
        bill.setDeliveryAddress(address);

        var shippingService = shippingServiceRepository.findById(billReq.getShippingServiceId())
                .orElseThrow(() -> new EntityNotFoundException(
                        ShippingService.class.getName(), billReq.getShippingServiceId().toString()));
        bill.setShippingService(shippingService);
        List<Integer> ids = billReq.getProductsCartId();
        var cart = getCartCurrentUser();
        List<ProductBill> productBills = new ArrayList<>();
        for (Integer id : ids) {
            ProductCart productCart = productCartRepository.findByCart_IdAndProductDetail_Id(cart.getId(), id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            ShippingService.class.getName(), id.toString()));
            ProductDetail productDetail = productCart.getProductDetail();
            ProductBill productBill = new ProductBill();
            productBill.setBill(bill);
            productBill.setQuantity(productCart.getQuantity());
            productBill.setProductDetail(productDetail);
            productDetail.setCountInStock(productDetail.getCountInStock() - productCart.getQuantity());
            productBills.add(productBill);
            productDetailRepository.save(productDetail);
        }
        billRepository.save(bill);
        productBillRepository.saveAll(productBills);
        for (Integer id : ids) {
            productCartRepository.deleteByCart_IdAndProductDetail_Id(cart.getId(), id);
        }
    }

    @Override
    @Transactional
    public List<BillDto> getBillsOfCurrentAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return billRepository.findByAccount_Username(username)
                .stream().map(this::mapToBillDto).sorted((i1, i2) -> i2.getPaymentTime().compareTo(i1.getPaymentTime())).collect(Collectors.toList());
    }

    @Override
    public BillDto getBillById(Integer billId) {
        var bill = billRepository.findById(billId)
                .orElseThrow(() -> new EntityNotFoundException(Bill.class.getName(), billId.toString()));
        return mapToBillDto(bill);
    }

    @Override
    @Transactional
    public List<BillDto> getCustomBills(BillCriteria billCriteria) {
        Specification<Bill> specification = billCriteria.toSpecification();
        Sort sort = Sort.by(Sort.Direction.DESC, "paymentTime");

        return billRepository.findAll(specification, sort).stream()
                .map(bill -> mapToBillDto((Bill) bill)).collect(Collectors.toList());

    }

    private BillDto mapToBillDto(Bill bill) {
        var billDto = mappingHelper.map(bill, BillDto.class);

        billDto.setDeliveryAddressDto(mappingHelper.map(bill.getDeliveryAddress(), DeliveryAddressDto.class));
        billDto.setProductBills(bill.getProductBills()
                .stream().map(i -> {
                    var productBillDto = mappingHelper.map(i, ProductBillDto.class);
                    var productDetailDto = mappingHelper.map(i.getProductDetail(), ProductDetailDto.class);
                    productDetailDto.setProductDto(mappingHelper.map(i.getProductDetail().getProduct(), ProductDto.class));
                    productBillDto.setProductDetail(productDetailDto);
                    return productBillDto;
                }).collect(Collectors.toList()));

        var profileDto = mappingHelper.map(bill.getAccount().getProfile(), ProfileDto.class);
        profileDto.setAccountDto(mappingHelper.map(bill.getAccount(), AccountDto.class));
        billDto.setProfileDto(profileDto);

        return billDto;
    }

    @Override
    public void updateBillStatus(BillStatusUpdateReq billStatusUpdateReq) {
        if (OrderStatus.LIST_STATUS.contains(billStatusUpdateReq.getStatus())) {
            var bill = billRepository.findById(billStatusUpdateReq.getBillId())
                    .orElseThrow(() -> new EntityNotFoundException(Bill.class.getName(),
                            billStatusUpdateReq.getBillId().toString()));
            bill.setStatus(billStatusUpdateReq.getStatus());
            billRepository.save(bill);
        }
    }


    private Account getCurrentAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return accountRepository.findOneWithAuthoritiesByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(Account.class.getName(), username));
    }

    private Cart getCartCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return cartRepository.findByAccount_Username(username)
                .orElseGet(() -> cartRepository.save(Cart.builder()
                        .account(accountRepository.findOneWithAuthoritiesByUsername(username)
                                .orElseThrow(() -> new EntityNotFoundException(Account.class.getName(), username)))
                        .build()));
    }
}
