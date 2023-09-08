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
                .map(e -> {
                    var res = mappingHelper.map(e, ProductDto.class);
                    var productDetail = productDetailRepository.findFirstByProduct_Id(e.getId());
                    if(productDetail.isPresent()){
                        res.setPrice(productDetail.get().getPrice());
                    }
                    else{
                        res.setPrice(0);
                    }
                    return  res;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductDto> getProductPaging() {
        return null;
    }

    @Override
    @Transactional
    public List<ProductDetailDto> getProductDetailsByProductId(Integer productId) {
        return productDetailRepository.findByProduct_Id(productId)
                .stream().map(e -> {
                    var productDetailDto = mappingHelper.map(e, ProductDetailDto.class);
                    productDetailDto.setProductDto(mappingHelper.map(e.getProduct(), ProductDto.class));
                    return productDetailDto;
                }).collect(Collectors.toList());
    }
}
