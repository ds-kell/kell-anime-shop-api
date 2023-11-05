package com.kell.webapp.rest.product;

import com.kell.service.ProductService;
import com.kell.webapp.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@CrossOrigin
public class ProductResource {
    private final ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        return ResponseUtils.ok(productService.getProducts());
    }
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductDetail(@PathVariable Integer productId) {
        return ResponseUtils.ok(productService.getProductDetailsByProductId(productId));
    }
}
