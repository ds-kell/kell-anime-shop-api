package com.kell.service.impl;

import com.kell.model.Account;
import com.kell.model.DeliveryAddress;
import com.kell.repository.AccountRepository;
import com.kell.repository.DeliveryAddressRepository;
import com.kell.service.DeliveryAddressService;
import com.kell.service.utils.MappingHelper;
import com.kell.webapp.dto.DeliveryAddressDto;
import com.kell.webapp.dto.request.DeliveryAddressReq;
import com.kell.webapp.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final AccountRepository accountRepository;
    private final MappingHelper mappingHelper;
    @Override
    public DeliveryAddressDto getDefaultDeliveryAddressCurrentAccount() {
        var addressDefault = deliveryAddressRepository.findByAccount_UsernameAndDefaultAddress(getCurrentUsernameAccount(), true);
        if (addressDefault.isPresent())
            return mappingHelper.map(addressDefault, DeliveryAddressDto.class);
        return new DeliveryAddressDto();
    }

    @Override
    public List<DeliveryAddressDto> getAllDeliveryAddressCurrentAccount() {
        var res = mappingHelper.mapList(deliveryAddressRepository
                .findByAccount_Username(getCurrentUsernameAccount()), DeliveryAddressDto.class);
        res.sort((i1, i2) -> Boolean.compare(i2.getDefaultAddress(), i1.getDefaultAddress()));
        return res;
    }

    @Override
    public void createDeliveryAddressCurrentAccount(DeliveryAddressReq deliveryAddressReq) {
        String username = getCurrentUsernameAccount();
        Account account = accountRepository.findOneWithAuthoritiesByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(Account.class.getName(), username));
        DeliveryAddress deliveryAddress = mappingHelper.map(deliveryAddressReq, DeliveryAddress.class);
        deliveryAddress.setAccount(account);
        if (deliveryAddressRepository.count() == 0) deliveryAddress.setDefaultAddress(true);
        else if (deliveryAddressReq.getDefaultAddress().equals(true)) {
            var addressDefault = deliveryAddressRepository.findByAccount_UsernameAndDefaultAddress(
                    username, true
            );
            if (addressDefault.isPresent()) {
                addressDefault.get().setDefaultAddress(false);
                deliveryAddressRepository.save(addressDefault.get());
            }
        }
        deliveryAddressRepository.save(deliveryAddress);
    }

    @Override
    public void updateDeliveryAddress(Integer addressId, DeliveryAddressReq deliveryAddressReq) {
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException(DeliveryAddress.class.getName(), addressId.toString()));
        if (deliveryAddress.getDefaultAddress().equals(false) && deliveryAddressReq.getDefaultAddress().equals(true)) {
            var addressDefault = deliveryAddressRepository.findByAccount_UsernameAndDefaultAddress(
                    getCurrentUsernameAccount(), true
            );
            if (addressDefault.isPresent()) {
                addressDefault.get().setDefaultAddress(false);
                deliveryAddressRepository.save(addressDefault.get());
            }
            mappingHelper.mapIfSourceNotNullAndStringNotBlank(deliveryAddressReq, deliveryAddress);
            deliveryAddressRepository.save(deliveryAddress);
        } else if (deliveryAddress.getDefaultAddress().equals(true) && deliveryAddress.getDefaultAddress().equals(false)) {
            mappingHelper.mapIfSourceNotNullAndStringNotBlank(deliveryAddressReq, deliveryAddress);
            deliveryAddress.setDefaultAddress(true);
            deliveryAddressRepository.save(deliveryAddress);
        } else {
            mappingHelper.mapIfSourceNotNullAndStringNotBlank(deliveryAddressReq, deliveryAddress);
            deliveryAddressRepository.save(deliveryAddress);
        }
    }

    @Override
    public void removeDeliveryAddress(Integer addressId) {
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException(DeliveryAddress.class.getName(), addressId.toString()));
        deliveryAddressRepository.delete(deliveryAddress);
    }

    @Override
    public DeliveryAddressDto getAddressCurrentAccountByDeliveryAddressId(Integer deliveryAddressId) {
        var addressDefault = deliveryAddressRepository.findById(deliveryAddressId)
                .orElseThrow(() -> new EntityNotFoundException(DeliveryAddress.class.getName(), deliveryAddressId.toString()));
        return mappingHelper.map(addressDefault, DeliveryAddressDto.class);
    }
    private String getCurrentUsernameAccount() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
