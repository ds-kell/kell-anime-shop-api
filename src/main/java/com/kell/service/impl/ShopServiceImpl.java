package com.kell.service.impl;

import com.kell.config.mapper.ModelMapperUtils;
import com.kell.repository.ShopRepository;
import com.kell.service.ShopService;
import com.kell.service.utils.MappingHelper;
import com.kell.webapp.dto.ShopInformationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final MappingHelper mappingHelper;
    @Override
    @Transactional
    public ShopInformationDto getShopInformation(Integer shopId) {
        return shopRepository.getShopInformationById(shopId)
                .map(shopInformation -> mappingHelper.map(shopInformation, ShopInformationDto.class))
                .orElseThrow(() -> new RuntimeException("Not found profile with id: " + shopId));
    }
}
