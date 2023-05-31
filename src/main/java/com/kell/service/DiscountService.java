package com.kell.service;


import com.kell.model.Discount;

import java.util.List;

public interface DiscountService {
//    void addDiscount(DiscountDto discountDto);
//    void removeDiscount(Integer discountId);
//    void updateDiscount(Integer discountId);
    List<Discount> getAllDiscount();
}
