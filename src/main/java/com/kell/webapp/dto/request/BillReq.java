package com.kell.webapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillReq {
    private String paymentMethod;
//    private String descriptionPay;
//    private List<Integer> productCartIds;
    private float totalAmount;
    private Integer addressId;
    private Integer shippingServiceId;
}
