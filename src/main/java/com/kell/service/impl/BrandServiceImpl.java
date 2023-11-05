package com.kell.service.impl;

import com.kell.model.Brand;
import com.kell.repository.BrandRepository;
import com.kell.service.BrandService;
import com.kell.service.utils.MappingHelper;
import com.kell.webapp.dto.BrandDto;
import com.kell.webapp.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
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

    @Override
    public BrandDto getBrandInfo(Integer brandId) {
        val brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new EntityNotFoundException(Brand.class.getName(), brandId.toString()));
        return mappingHelper.map(brand, BrandDto.class);
    }
}
