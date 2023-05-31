package com.kell.service.impl;

import com.kell.model.Discount;
import com.kell.repository.DiscountRepository;
import com.kell.service.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    @Override
    public List<Discount> getAllDiscount() {
        return discountRepository.findAll();
    }
}
