package com.kell.webapp.rest.user;


import com.kell.service.BillService;
import com.kell.webapp.dto.request.BillCriteria;
import com.kell.webapp.dto.request.BillReq;
import com.kell.webapp.dto.request.BillStatusUpdateReq;
import com.kell.webapp.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales/order")
@CrossOrigin
@RequiredArgsConstructor
public class BillResource {
    private final BillService billService;

    @PostMapping
    public ResponseEntity<?> createBillByCurrentAccount (@RequestBody BillReq billReq) {
        billService.createBillByCurrentAccount(billReq);
        return ResponseUtils.created();
    }

    @GetMapping
    public ResponseEntity<?> getBillsOfCurrentAccount () {
        return ResponseUtils.ok(billService.getBillsOfCurrentAccount());
    }

    @GetMapping("/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable Integer billId) {
        return ResponseUtils.ok(billService.getBillById(billId));
    }

    @PostMapping("/custom-bills")
    public ResponseEntity<?> getCustomBills (@RequestBody BillCriteria billCriteria) {
        return ResponseUtils.ok(billService.getCustomBills(billCriteria));
    }

    @PutMapping
    public ResponseEntity<?> updateBillStatus (@RequestBody BillStatusUpdateReq billStatusUpdateReq) {
        billService.updateBillStatus(billStatusUpdateReq);
        return ResponseUtils.ok("Updated");
    }

}
