package com.kell.service;


import com.kell.webapp.dto.ProductDetailDto;
import com.kell.webapp.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDetailDto>getProductDetailsByProductId(Integer productId);

    List<ProductDto> getProducts();

    List<ProductDto> getProductPaging();
}
