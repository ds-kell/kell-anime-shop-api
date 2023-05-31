package com.kell.service;

import com.kell.model.DeliveryAddress;
import com.kell.webapp.dto.DeliveryAddressDto;
import com.kell.webapp.dto.request.DeliveryAddressReq;

import java.util.List;

public interface DeliveryAddressService {
//    DeliveryAddressDto getDefaultDeliveryAddressCurrentAccount();
    DeliveryAddressDto getDefaultDeliveryAddressCurrentAccount();

    List<DeliveryAddressDto> getAllDeliveryAddressCurrentAccount();

    void createDeliveryAddressCurrentAccount(DeliveryAddressReq deliveryAddressReq);

    void updateDeliveryAddress(Integer addressId, DeliveryAddressReq deliveryAddressReq);

    void removeDeliveryAddress(Integer addressId);

    DeliveryAddressDto getAddressCurrentAccountByDeliveryAddressId(Integer deliveryAddressId);
}
