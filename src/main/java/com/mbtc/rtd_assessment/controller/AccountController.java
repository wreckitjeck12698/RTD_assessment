package com.mbtc.rtd_assessment.controller;

import com.mbtc.rtd_assessment.dto.AccountCreationRequest;
import com.mbtc.rtd_assessment.dto.CreationResponse;
import com.mbtc.rtd_assessment.dto.InquiryResponse;
import com.mbtc.rtd_assessment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<CreationResponse> createAccount(@Valid @RequestBody AccountCreationRequest request) {
        CreationResponse response = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<InquiryResponse> getAccount(@PathVariable Long customerNumber) {
        InquiryResponse response = accountService.getCustomerDetails(customerNumber);

        if (response.getTransactionStatusCode() == 401) {
            return ResponseEntity.status(401).body(response);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}