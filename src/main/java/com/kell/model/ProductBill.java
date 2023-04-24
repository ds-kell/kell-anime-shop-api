package com.kell.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_bill")
@Data
public class ProductBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;
    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;
}
