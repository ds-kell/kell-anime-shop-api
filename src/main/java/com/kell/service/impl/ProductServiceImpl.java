package com.kell.service.impl;


import com.kell.model.Product;
import com.kell.repository.ProductDetailRepository;
import com.kell.repository.ProductRepository;
import com.kell.service.ProductService;
import com.kell.service.utils.MappingHelper;
import com.kell.webapp.dto.ProductDetailDto;
import com.kell.webapp.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final MappingHelper mappingHelper;
    private final ProductDetailRepository productDetailRepository;
    @Override
    @Transactional
    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(e -> mappingHelper.map(e, ProductDto.class))
                .collect(Collectors.toList());
    }
}
