package com.kell.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "delivery_address")
@Data
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String receiver;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String province;
    private String district;
    private String ward;
    @Column(name = "specific_address")
    private String specificAddress;
    @Type(type = "text")
    private String content;
    @Column(name = "default_address")
    private Boolean defaultAddress;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
