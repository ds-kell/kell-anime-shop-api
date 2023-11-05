package com.kell.webapp.rest.product;

import com.kell.service.BrandService;
import com.kell.webapp.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@CrossOrigin
public class BrandResource {
    private final BrandService brandService;

    @GetMapping("")
    public ResponseEntity<?> getAllBrands() {
        return ResponseUtils.ok(brandService.getAllBrands());
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<?> getBrandInfo(@PathVariable Integer brandId) {
        return ResponseUtils.ok(brandService.getBrandInfo(brandId));
    }
}
