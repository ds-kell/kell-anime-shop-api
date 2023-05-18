package com.kell.webapp.dto;

import com.kell.model.Account;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class DeliveryAddressDto {
    private Integer id;
    private String receiver;
    private String phoneNumber;
    private String province;
    private String district;
    private String ward;
    private String specificAddress;
    private String content;
    private Boolean defaultAddress;
}
