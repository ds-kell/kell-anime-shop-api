package com.kell.repository;

import com.kell.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory_Id(Integer categoryId);

    @Query(value = "SELECT * FROM products LIMIT ?1", nativeQuery = true)
    List<Product> getProductsByNumberPage(@Param("numberProduct") int numberProduct);

}
