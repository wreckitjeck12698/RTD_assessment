package com.mbtc.rtd_assessment.service;

import com.mbtc.rtd_assessment.dto.AccountCreationRequest;
import com.mbtc.rtd_assessment.dto.CreationResponse;
import com.mbtc.rtd_assessment.dto.InquiryResponse;
import com.mbtc.rtd_assessment.entity.Account;
import com.mbtc.rtd_assessment.entity.AccountType;
import com.mbtc.rtd_assessment.entity.Customer;
import com.mbtc.rtd_assessment.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void testCreateAccount_Success() {
        // 1. Setup Mock Request
        AccountCreationRequest request = new AccountCreationRequest();
        request.setCustomerName("Test User");
        request.setCustomerMobile("09081234567");
        request.setCustomerEmail("test12345@gmail.com");
        request.setAddress1("Test Address");
        request.setAccountType(AccountType.S);

        // 2. Setup Mock Database Response
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerNumber(12345678L);
        when(customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);

        // 3. Execute Service Method
        CreationResponse response = accountService.createAccount(request);

        // 4. Assert Results match Exam Criteria
        assertNotNull(response);
        assertEquals(12345678L, response.getCustomerNumber());
        assertEquals(201, response.getTransactionStatusCode());
        assertEquals("Customer account created", response.getTransactionStatusDescription());
    }

    @Test
    void testGetCustomerDetails_Success() {
        // 1. Setup Mock Database Data
        Account mockAccount = new Account();
        mockAccount.setAccountNumber(10001L);
        mockAccount.setAccountType(AccountType.S);
        mockAccount.setAvailableBalance(new BigDecimal("500.00"));

        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerNumber(12345678L);
        mockCustomer.setCustomerName("Test User");
        mockCustomer.setAccounts(Collections.singletonList(mockAccount));

        when(customerRepository.findById(12345678L)).thenReturn(Optional.of(mockCustomer));

        // 2. Execute Service Method
        InquiryResponse response = accountService.getCustomerDetails(12345678L);

        // 3. Assert Results match Exam Criteria
        assertEquals(302, response.getTransactionStatusCode());
        assertEquals("Customer Account found", response.getTransactionStatusDescription());
        assertEquals("Savings", response.getSavings().get(0).getAccountType());
    }

    @Test
    void testGetCustomerDetails_NotFound() {
        // 1. Setup Mock Database to return empty
        when(customerRepository.findById(9999L)).thenReturn(Optional.empty());

        // 2. Execute Service Method
        InquiryResponse response = accountService.getCustomerDetails(9999L);

        // 3. Assert Results match Exam Criteria for Failure
        assertEquals(401, response.getTransactionStatusCode());
        assertEquals("Customer not found", response.getTransactionStatusDescription());
    }
}