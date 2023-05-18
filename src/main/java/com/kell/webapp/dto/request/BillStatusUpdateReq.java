package com.kell.webapp.dto.request;

import lombok.Data;

@Data
public class BillStatusUpdateReq {
    private Integer billId;
    private String status;
}
