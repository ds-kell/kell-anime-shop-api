package com.kell.service.impl;

import com.kell.repository.BrandRepository;
import com.kell.service.BrandService;
import com.kell.service.utils.MappingHelper;
import com.kell.webapp.dto.BrandDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final MappingHelper mappingHelper;
    @Override
    public List<BrandDto> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(e -> mappingHelper.map(e, BrandDto.class))
                .collect(Collectors.toList());
    }
}
