package com.kell.webapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillReq {
    private String paymentMethod;
    private String descriptionPay;
    private float totalAmount;
    private Integer addressId;
    private Integer shippingServiceId;
}
