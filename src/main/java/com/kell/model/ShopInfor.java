package com.kell.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "shopinfor")
public class ShopInfor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    @OneToMany(mappedBy = "shopInfor", cascade = CascadeType.ALL)
    private List<ShopImage> shopImages;
    @Type(type = "text")
    private String description;
}
