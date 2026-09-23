package com.mbtc.rtd_assessment.dto;

import com.mbtc.rtd_assessment.entity.AccountType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AccountCreationRequest {
    private String customerName;
    private String customerMobile;

    @NotBlank(message = "Email is required field")
    @Email(message = "Invalid email format")
    private String customerEmail;

    private String address1;
    private String address2;

    @NotNull(message = "Account type is required")
    private AccountType accountType;
}
