package com.kell.webapp.dto;

import com.kell.model.ShippingService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Integer id;
    private String status;
    private String paymentMethod;
    private String descriptionPay;
    private Float totalAmount;
    private Date paymentTime;
    private DeliveryAddressDto deliveryAddressDto;
    private ShippingService shippingService;
//    private OrderType orderType;
    private ProfileDto profileDto;
    private List<ProductBillDto> productBills;
}
