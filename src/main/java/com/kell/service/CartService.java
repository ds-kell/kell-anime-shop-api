package com.kell.service;


import com.kell.webapp.dto.ProductCartDto;
import com.kell.webapp.dto.request.AddToCartReq;

import java.util.List;

public interface CartService {
    void addToCart(AddToCartReq addToCartReq);

    List<ProductCartDto> getProductInCart();

    void removeProductCart(Integer productDetailId);
}
