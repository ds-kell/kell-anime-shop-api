package com.kell.webapp.rest.product;

import com.kell.service.impl.ProductServiceImpl;
import com.kell.webapp.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductResource {
    private final ProductServiceImpl productServiceImpl;
    @PostMapping
    public ResponseEntity<?> getProducts() {
        return ResponseUtils.ok(productServiceImpl.getProducts());
    }

}
