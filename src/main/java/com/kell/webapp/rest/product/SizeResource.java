package com.kell.webapp.rest.product;

import com.kell.service.SizeService;
import com.kell.webapp.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/product/size")
@RequiredArgsConstructor
@CrossOrigin
public class SizeResource {
    private final SizeService sizeService;
    @GetMapping
    public ResponseEntity<?> getAllSize() {
        return ResponseUtils.ok(sizeService.getAllSize());
    }
}
