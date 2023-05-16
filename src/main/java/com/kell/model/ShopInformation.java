package com.kell.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "shop_information")
public class ShopInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Type(type = "text")
    private String description;
    @OneToMany(mappedBy = "shopInformation", cascade = CascadeType.ALL)
    private List<ShopImage> shopImages;
}
