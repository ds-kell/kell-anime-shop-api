package com.kell.repository;

import com.kell.model.Bill;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findByAccount_Username(String username);

    Collection<Object> findAll(Specification<Bill> specification, Sort sort);

    @Query(value = "SELECT * FROM bills WHERE payment_time Like %?1%", nativeQuery = true)
    List<Bill> findByPaymentTimeByYear(@Param("paymentTime") String eventDate);
}
