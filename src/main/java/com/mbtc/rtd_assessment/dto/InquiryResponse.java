package com.mbtc.rtd_assessment.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InquiryResponse {
    private Long customerNumber;
    private String customerName;
    private String customerMobile;
    private String customerEmail;
    private String address1;
    private String address2;
    private List<AccountDto> savings;
    private Integer transactionStatusCode;
    private String transactionStatusDescription;
}