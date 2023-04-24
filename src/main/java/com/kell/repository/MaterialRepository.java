package com.kell.repository;

import com.kell.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    boolean findByName(String name);

}
