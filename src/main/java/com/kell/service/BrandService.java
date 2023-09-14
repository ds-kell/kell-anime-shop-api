package com.kell.service;


import com.kell.webapp.dto.BrandDto;

import java.util.List;

public interface BrandService {
    List<BrandDto> getAllBrands();

    BrandDto getBrandInfo(Integer brandId);
}