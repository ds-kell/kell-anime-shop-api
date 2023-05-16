package com.kell.model;


import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    @Column(name = "date_modified")
    private Date dateModified;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;
    private float price;
    @Column(name = "count_in_stock")
    private int countInStock;
    @Column(name = "image_url")
    @Type(type = "text")
    private String imageUrl;
//    private String description;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
