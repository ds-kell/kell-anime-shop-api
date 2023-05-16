package com.kell.webapp.rest.admin;

import com.kell.service.impl.ShopServiceImpl;
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
@RequestMapping("api/admin")
@RequiredArgsConstructor
@CrossOrigin
public class ShopResource {
    private final ShopServiceImpl shopService;
    @GetMapping("/shop")
    public ResponseEntity<?> getStoreProfile(){ return ResponseUtils.ok(shopService.getShopInformation(1));
    }

}
