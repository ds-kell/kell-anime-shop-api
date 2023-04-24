package com.kell.repository;

import com.kell.model.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Integer> {
    List<DeliveryAddress> findByAccount_Username(String username);
    DeliveryAddress findByAccount_UsernameAndDefaultAddress(String username, boolean defaultAddress);
}
