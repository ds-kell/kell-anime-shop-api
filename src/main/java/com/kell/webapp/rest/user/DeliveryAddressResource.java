package com.kell.webapp.rest.user;


import com.kell.service.DeliveryAddressService;
import com.kell.webapp.dto.request.DeliveryAddressReq;
import com.kell.webapp.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user/address")
@CrossOrigin("*")
@RequiredArgsConstructor
public class DeliveryAddressResource {
    private final DeliveryAddressService deliveryAddressService;

    @GetMapping("/default")
    public ResponseEntity<?> getDefaultAddressCurrentAccount() {
        return ResponseUtils.ok(deliveryAddressService.getDefaultDeliveryAddressCurrentAccount());
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<?> getAddressCurrentAccountByAddressId(@PathVariable Integer addressId) {
        return ResponseUtils.ok(deliveryAddressService.getAddressCurrentAccountByDeliveryAddressId(addressId));
    }

    @GetMapping
    public ResponseEntity<?> getAllAddressCurrentAccount() {
        return ResponseUtils.ok(deliveryAddressService.getAllDeliveryAddressCurrentAccount());
    }

    @PostMapping
    public ResponseEntity<?> createAddressCurrentAccount(@RequestBody @Valid DeliveryAddressReq deliveryAddressReq){
        deliveryAddressService.createDeliveryAddressCurrentAccount(deliveryAddressReq);
        return ResponseUtils.created();
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<?> updateAddress(@PathVariable Integer addressId, @RequestBody DeliveryAddressReq deliveryAddressReq) {
        deliveryAddressService.updateDeliveryAddress(addressId, deliveryAddressReq);
        return ResponseUtils.ok("Updated");
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> removeAddress(@PathVariable Integer addressId){
        deliveryAddressService.removeDeliveryAddress(addressId);
        return ResponseUtils.ok("Removed");
    }
}
