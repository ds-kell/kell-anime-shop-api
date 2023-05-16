package com.kell.repository;

import com.kell.model.ShopInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<ShopInformation, Integer> {
    Optional<ShopInformation> getShopInformationById(Integer id);
}
