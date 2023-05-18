package com.kell.webapp.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data

public class DeliveryAddressReq {
    @NotBlank
    private String receiver;
    @NotBlank

    private String phoneNumber;
    @NotBlank

    private String province;
    @NotBlank

    private String district;
    @NotBlank

    private String ward;
    @NotBlank

    private String specificAddress;

    private String content;
    private Boolean defaultAddress;
}
