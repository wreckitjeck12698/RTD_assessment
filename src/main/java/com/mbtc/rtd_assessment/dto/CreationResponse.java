package com.mbtc.rtd_assessment.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreationResponse {
    private Long customerNumber;
    private Integer transactionStatusCode;
    private String transactionStatusDescription;
}