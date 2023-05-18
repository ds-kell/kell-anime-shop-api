package com.kell.repository;

import com.kell.model.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Integer> {
    List<DeliveryAddress> findByAccount_Username(String username);
    Optional<DeliveryAddress> findByAccount_UsernameAndDefaultAddress(String username, boolean defaultAddress);
}
