package com.kell.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shipping_services")
@Data
public class ShippingService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private float cost;
//    @Column(name = "time")
//    private Date time;
    @Type(type = "text")
    private String description;
}
