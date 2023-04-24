package com.kell.repository;

import com.kell.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByAccount_Username(String username);

    Optional<Cart> findById(Integer productDetailId);
}
