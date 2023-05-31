package com.kell.repository;

import com.kell.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}
