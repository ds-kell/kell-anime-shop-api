package com.kell.webapp.rest.product;

import com.kell.service.MaterialService;
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
public class MaterialResource {
    private final MaterialService materialService;
    @GetMapping("/materials")
    public ResponseEntity<?> getMaterials(){
        return ResponseUtils.ok(materialService.getAllMaterial());
    }
}
