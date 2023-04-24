package com.kell.repository;

import com.kell.model.ShopInfor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<ShopInfor, Integer> {
    Optional<ShopInfor> getStoreById(Integer id);
}
