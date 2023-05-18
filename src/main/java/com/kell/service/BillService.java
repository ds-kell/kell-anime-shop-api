package com.kell.service;

import com.kell.webapp.dto.BillDto;
import com.kell.webapp.dto.request.BillCriteria;
import com.kell.webapp.dto.request.BillReq;
import com.kell.webapp.dto.request.BillStatusUpdateReq;

import java.util.List;

public interface BillService {
    void createBillByCurrentAccount(BillReq billReq);

    List<BillDto> getBillsOfCurrentAccount();

    BillDto getBillById(Integer billId);

    List<BillDto> getCustomBills(BillCriteria billCriteria);

    void updateBillStatus(BillStatusUpdateReq billStatusUpdateReq);
}
