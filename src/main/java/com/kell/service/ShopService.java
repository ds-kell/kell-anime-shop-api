package com.kell.service;


import com.kell.webapp.dto.ShopInformationDto;

public interface ShopService {
    ShopInformationDto getShopInformation(Integer shopId);

//    void updateShopInformation(Integer shopId);
}
