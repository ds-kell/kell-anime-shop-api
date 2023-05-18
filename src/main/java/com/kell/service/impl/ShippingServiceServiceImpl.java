package com.kell.service.impl;

import com.kell.repository.ShippingServiceRepository;
import com.kell.service.ShippingServiceService;
import com.kell.service.utils.MappingHelper;
import com.kell.webapp.dto.response.ShippingServiceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippingServiceServiceImpl implements ShippingServiceService {
    private final ShippingServiceRepository shippingServiceRepository;
    private final MappingHelper mappingHelper;
    @Override
    public List<ShippingServiceDto> getAllShippingService() {
        return shippingServiceRepository.findAll()
                .stream()
                .map( e -> mappingHelper.map(e, ShippingServiceDto.class))
                .collect(Collectors.toList());
    }
}
