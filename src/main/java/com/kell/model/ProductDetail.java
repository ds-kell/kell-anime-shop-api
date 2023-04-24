package com.kell.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;
    private float price;
    private int count;
    private String des;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
