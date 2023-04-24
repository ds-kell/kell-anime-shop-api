package com.kell.repository;

import com.kell.model.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCartRepository extends JpaRepository<ProductCart, Integer> {
    Optional<ProductCart> findByCart_IdAndAndProductDetail_Id(Integer cartId, Integer productDetailId);
    List<ProductCart> findByCart_Id(Integer cartId);
}
