package com.kell.webapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillReq {
    private List<Integer> productsCartId;
    private Integer deliveryAddressId;
    private Integer shippingServiceId;
    private String paymentMethod;
}
