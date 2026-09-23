package com.mbtc.rtd_assessment.service;

import com.mbtc.rtd_assessment.dto.AccountCreationRequest;
import com.mbtc.rtd_assessment.dto.AccountDto;
import com.mbtc.rtd_assessment.dto.CreationResponse;
import com.mbtc.rtd_assessment.dto.InquiryResponse;
import com.mbtc.rtd_assessment.entity.Account;
import com.mbtc.rtd_assessment.entity.Customer;
import com.mbtc.rtd_assessment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private CustomerRepository customerRepository;

    public CreationResponse createAccount(AccountCreationRequest request) {
        Customer customer = new Customer();
        customer.setCustomerName(request.getCustomerName());
        customer.setCustomerMobile(request.getCustomerMobile());
        customer.setCustomerEmail(request.getCustomerEmail());
        customer.setAddress1(request.getAddress1());
        customer.setAddress2(request.getAddress2());

        Account account = new Account();
        account.setAccountType(request.getAccountType());
        account.setAvailableBalance(BigDecimal.ZERO);
        account.setCustomer(customer);

        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        customer.setAccounts(accounts);

        Customer savedCustomer = customerRepository.save(customer);

        CreationResponse response = new CreationResponse();
        response.setCustomerNumber(savedCustomer.getCustomerNumber());
        response.setTransactionStatusCode(201);
        response.setTransactionStatusDescription("Customer account created");
        return response;
    }

    public InquiryResponse getCustomerDetails(Long customerNumber) {
        Optional<Customer> customerOpt = customerRepository.findById(customerNumber);
        InquiryResponse response = new InquiryResponse();

        if (!customerOpt.isPresent()) {
            response.setTransactionStatusCode(401);
            response.setTransactionStatusDescription("Customer not found");
            return response;
        }

        Customer customer = customerOpt.get();
        response.setCustomerNumber(customer.getCustomerNumber());
        response.setCustomerName(customer.getCustomerName());
        response.setCustomerMobile(customer.getCustomerMobile());
        response.setCustomerEmail(customer.getCustomerEmail());
        response.setAddress1(customer.getAddress1());
        response.setAddress2(customer.getAddress2());

        List<AccountDto> accountDtos = customer.getAccounts().stream().map(acc -> {
            AccountDto dto = new AccountDto();
            dto.setAccountNumber(acc.getAccountNumber());
            dto.setAccountType(acc.getAccountType().name().equals("S") ? "Savings" : "Checking");
            dto.setAvailableBalance(acc.getAvailableBalance());
            return dto;
        }).collect(Collectors.toList());

        response.setSavings(accountDtos);
        response.setTransactionStatusCode(302);
        response.setTransactionStatusDescription("Customer Account found");
        return response;
    }
}