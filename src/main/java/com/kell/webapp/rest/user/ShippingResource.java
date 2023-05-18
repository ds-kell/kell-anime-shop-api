package com.kell.webapp.rest.user;


import com.kell.service.ShippingServiceService;
import com.kell.webapp.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shipping-services")
@CrossOrigin
@RequiredArgsConstructor
public class ShippingResource {
    private final ShippingServiceService shippingService;

    @GetMapping
    public ResponseEntity<?> getAllShippingService(){
        return ResponseUtils.ok(shippingService.getAllShippingService());
    }
}
