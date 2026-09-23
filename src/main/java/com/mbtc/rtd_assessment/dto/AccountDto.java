package com.mbtc.rtd_assessment.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class AccountDto {
    private Long accountNumber;
    private String accountType;
    private BigDecimal availableBalance;
}