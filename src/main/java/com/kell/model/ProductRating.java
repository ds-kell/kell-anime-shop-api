package com.kell.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_rating")
public class ProductRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "comment")
    @Type(type = "text")
    private String comment;
    @Column(name = "star")
    private Integer star;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "product_bill_id")
    private ProductBill productBill;
}
