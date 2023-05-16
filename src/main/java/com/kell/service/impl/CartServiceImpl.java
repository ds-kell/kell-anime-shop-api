package com.kell.service.impl;


import com.kell.model.*;
import com.kell.repository.AccountRepository;
import com.kell.repository.CartRepository;
import com.kell.repository.ProductCartRepository;
import com.kell.repository.ProductDetailRepository;
import com.kell.service.CartService;
import com.kell.service.utils.MappingHelper;
import com.kell.webapp.dto.ProductCartDto;
import com.kell.webapp.dto.ProductDetailDto;
import com.kell.webapp.dto.request.AddToCartReq;
import com.kell.webapp.exception.EntityNotFoundException;
import com.kell.webapp.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ProductCartRepository productCartRepository;
    private final ProductDetailRepository productDetailRepository;
    private final AccountRepository accountRepository;
    private final CartRepository cartRepository;
    private final MappingHelper mappingHelper;

    @Override
    @Transactional
    public void addToCart(AddToCartReq addToCartReq) {
        var cart = getCartCurrentUser();
        var productDetail = productDetailRepository.findById(addToCartReq.getProductDetailId())
                .orElseThrow(() ->
                        new EntityNotFoundException(ProductDetail.class.getName(), addToCartReq.getProductDetailId().toString()));
        var productCart = productCartRepository
                .findByCart_IdAndAndProductDetail_Id(cart.getId(), productDetail.getId())
                .orElseGet(() -> ProductCart.builder()
                        .id(new ProductCartKey(cart.getId(), productDetail.getId()))
                        .cart(cart)
                        .productDetail(productDetail)
                        .quantity(0)
                        .build());
        if (addToCartReq.getQuantity() > productDetail.getCountInStock())
            throw new ServiceException("Quantity is greater than count in stock", "");
        productCart.setQuantity(productCart.getQuantity() + addToCartReq.getQuantity());
        productCartRepository.save(productCart);
    }

    @Override
    @Transactional
    public List<ProductCartDto> getProductInCart() {
        var cart = getCartCurrentUser();
        return productCartRepository.findByCart_Id(cart.getId()).stream()
                .map(e -> {
                    var productCart = mappingHelper.map(e, ProductCartDto.class);
                    productCart.setProductDetailDto(mappingHelper.map(e.getProductDetail(), ProductDetailDto.class));
                    return productCart;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeProductCart(Integer productDetailId) {
        var cart = getCartCurrentUser();
        var productCart = productCartRepository
                .findByCart_IdAndAndProductDetail_Id(cart.getId(), productDetailId)
                .orElseThrow(() -> new ServiceException("Not found product in cart", ""));
        productCartRepository.delete(productCart);
    }
//    @Transactional
    private Cart getCartCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return cartRepository.findByAccount_Username(username)
                .orElseGet(() -> cartRepository.save(Cart.builder()
                        .account(accountRepository.findOneWithAuthoritiesByUsername(username)
                                .orElseThrow(() -> new EntityNotFoundException(Account.class.getName(), username)))
                        .build()));
    }
}
