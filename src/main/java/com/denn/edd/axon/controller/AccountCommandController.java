package com.denn.edd.axon.controller;

import com.denn.edd.axon.dto.AccountCreateDTO;
import com.denn.edd.axon.dto.MoneyCreditDTO;
import com.denn.edd.axon.dto.MoneyDebitDTO;
import com.denn.edd.axon.service.AccountCommandService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/bank-accounts")
@Api(value = "Account Commands", description = "Account Commands Related Endpoints", tags = "Account Commands")
public class AccountCommandController {

    private final AccountCommandService accountCommandService;

    public AccountCommandController(AccountCommandService accountCommandService) {
        this.accountCommandService = accountCommandService;
    }

    @PostMapping
    public CompletableFuture<String> createAccount(@RequestBody AccountCreateDTO accountCreateDTO){
        return accountCommandService.createAccount(accountCreateDTO);
    }

    @PutMapping(value = "/credits/{accountNumber}")
    public CompletableFuture<String> creditMoneyToAccount(@PathVariable(value = "accountNumber") String accountNumber,
                                                          @RequestBody MoneyCreditDTO moneyCreditDTO){
        return accountCommandService.creditMoneyToAccount(accountNumber, moneyCreditDTO);
    }

    @PutMapping(value = "/debits/{accountNumber}")
    public CompletableFuture<String> debitMoneyFromAccount(@PathVariable(value = "accountNumber") String accountNumber,
                                                           @RequestBody MoneyDebitDTO moneyDebitDTO){
        return accountCommandService.debitMoneyFromAccount(accountNumber, moneyDebitDTO);
    }
}