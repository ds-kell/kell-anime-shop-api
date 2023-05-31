package com.kell.webapp.rest.product;

import com.kell.service.DiscountService;
import com.kell.webapp.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@CrossOrigin
public class DiscountResource {
    private final DiscountService discountService;
    @GetMapping("/discounts")
    public ResponseEntity<?> getDiscounts(){
        return ResponseUtils.ok(discountService.getAllDiscount());
    }
}
