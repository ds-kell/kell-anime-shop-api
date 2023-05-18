package com.kell.webapp.rest.user;


import com.kell.service.CartService;
import com.kell.webapp.dto.request.AddToCartReq;
import com.kell.webapp.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/cart")
@RequiredArgsConstructor
@CrossOrigin

public class CartResource {
    private final CartService cartService;

    @PostMapping("/add-product")
    public ResponseEntity<?> addToCart(@RequestBody AddToCartReq addToCartReq) {
        cartService.addToCart(addToCartReq);
        return ResponseUtils.created();
    }

    @GetMapping("/product-in-cart")
    public ResponseEntity<?> getProductInCart() {
        return ResponseUtils.ok(cartService.getProductInCart());
    }

    @DeleteMapping("/{productDetailId}")
    public ResponseEntity<?> removeProductCart(@PathVariable Integer productDetailId){
        cartService.removeProductCart(productDetailId);
        return ResponseUtils.ok("Removed");
    }

}
