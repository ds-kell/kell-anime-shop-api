package com.kell.webapp.dto;

import lombok.Data;

@Data
public class ProductCartDto {
    private ProductDetailDto productDetailDto;
    private int quantity;
}
