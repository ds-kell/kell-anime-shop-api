//package com.kell.service.impl;
//
//
//import com.kell.model.*;
//import com.kell.model.constants.OrderStatus;
//import com.kell.repository.*;
//import com.kell.service.BillService;
//import com.kell.service.utils.MappingHelper;
//import com.kell.webapp.dto.*;
//import com.kell.webapp.dto.request.BillCriteria;
//import com.kell.webapp.dto.request.BillReq;
//import com.kell.webapp.dto.request.BillStatusUpdateReq;
//import com.kell.webapp.exception.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class BillServiceImpl implements BillService {
//    private final ProductCartRepository productCartRepository;
//    private final BillRepository billRepository;
//    private final ProductBillRepository productBillRepository;
//    private final CartRepository cartRepository;
//    private final AccountRepository accountRepository;
//    private final DeliveryAddressRepository deliveryAddressRepository;
//    private final ShippingServiceRepository shippingServiceRepository;
//    private final MappingHelper mappingHelper;
//
//    @Override
//    public void createBillByCurrentAccount(BillReq billReq) {
//        Bill bill = mappingHelper.map(billReq, Bill.class);
//        bill.setStatus(OrderStatus.PENDING);
//        bill.setPaymentTime(new Date());
//
//        var account = getCurrentAccount();
//        bill.setAccount(account);
//
//        var addressResource = deliveryAddressRepository.findById(billReq.getAddressId())
//                .orElseThrow(() -> new EntityNotFoundException(
//                        DeliveryAddress.class.getName(), billReq.getAddressId().toString()));
//
//        DeliveryAddress address = mappingHelper.map(addressResource, DeliveryAddress.class);
////        address.setContent(addressResource.getContent());
////        address.setDistrict(addressResource.getDistrict());
////        address.setProvince(addressResource.getProvince());
////        address.setWard(addressResource.getWard());
////        address.setReceiver(addressResource.getReceiver());
////        address.setPhoneNumber(addressResource.getPhoneNumber());
//
//        deliveryAddressRepository.save(address);
//
//        bill.setDeliveryAddress(address);
//
//        var shippingService = shippingServiceRepository.findById(billReq.getShippingServiceId())
//                .orElseThrow(() -> new EntityNotFoundException(
//                        ShippingService.class.getName(), billReq.getShippingServiceId().toString()));
//        bill.setShippingService(shippingService);
//
//        var cart = getCartCurrentUser();
////        kiểm tra các sản phẩm đã check
//        var productCartChecked = productCartRepository.findByCart_IdAndChecked(cart.getId(), true);
//
//        List<ProductBill> productBills = productCartChecked
//                .stream().map(e -> {
//                    ProductBill productBill = new ProductBill();
//                    productBill.setBill(bill);
//                    productBill.setProductDetail(e.getProductDetail());
//                    productBill.setQuantity(e.getQuantity());
//                    return productBill;
//                }).collect(Collectors.toList());
//
//        billRepository.save(bill);
//        productBillRepository.saveAll(productBills);
//        productCartRepository.deleteAll(productCartChecked);
//    }
//
//    @Override
//    public List<BillDto> getBillsOfCurrentAccount() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        return billRepository.findByAccount_Username(username)
//                .stream().map(this::mapToBillDto).sorted((i1, i2) -> i2.getPaymentTime().compareTo(i1.getPaymentTime())).collect(Collectors.toList());
//    }
//
//    @Override
//    public BillDto getBillById(Integer billId) {
//        var bill = billRepository.findById(billId)
//                .orElseThrow(() -> new EntityNotFoundException(Bill.class.getName(), billId.toString()));
//        return mapToBillDto(bill);
//    }
//
////    @Override
////    public List<BillDto> getCustomBills(BillCriteria billCriteria) {
////        return billRepository.findAll(billCriteria.toSpecification()).stream()
////                .map(this::mapToBillDto).collect(Collectors.toList());
////    }
//
//    @Override
//    public void updateBillStatus(BillStatusUpdateReq billStatusUpdateReq) {
//        if (OrderStatus.LIST_STATUS.contains(billStatusUpdateReq.getStatus())) {
//            var bill = billRepository.findById(billStatusUpdateReq.getBillId())
//                    .orElseThrow(() -> new EntityNotFoundException(Bill.class.getName(),
//                            billStatusUpdateReq.getBillId().toString()));
//            bill.setStatus(billStatusUpdateReq.getStatus());
//            billRepository.save(bill);
//        }
//    }
//
//    private BillDto mapToBillDto(Bill bill) {
//        var billDto = mappingHelper.map(bill, BillDto.class);
//
//        billDto.setDeliveryAddressDto(mappingHelper.map(bill.getDeliveryAddress(), DeliveryAddressDto.class));
//        billDto.setProductBills(bill.getProductBills()
//                .stream().map(i -> {
//                    var productBillDto = mappingHelper.map(i, ProductBillDto.class);
//                    var productDetailDto = mappingHelper.map(i.getProductDetail(), ProductDetailDto.class);
//                    productDetailDto.setProductDto(mappingHelper.map(i.getProductDetail().getProduct(), ProductDto.class));
//                    productBillDto.setProductDetail(productDetailDto);
//                    return productBillDto;
//                }).collect(Collectors.toList()));
//
//        var profileDto = mappingHelper.map(bill.getAccount().getProfile(), ProfileDto.class);
//        profileDto.setAccountDto(mappingHelper.map(bill.getAccount(), AccountDto.class));
//        billDto.setProfileDto(profileDto);
//
//        return billDto;
//    }
//
//    private Account getCurrentAccount() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        return accountRepository.findOneWithAuthoritiesByUsername(username)
//                .orElseThrow(() -> new EntityNotFoundException(Account.class.getName(), username));
//    }
//
//    private Cart getCartCurrentUser() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        return cartRepository.findByAccount_Username(username)
//                .orElseGet(() -> cartRepository.save(Cart.builder()
//                        .account(accountRepository.findOneWithAuthoritiesByUsername(username)
//                                .orElseThrow(() -> new EntityNotFoundException(Account.class.getName(), username)))
//                        .build()));
//    }
//}
