package com.denn.edd.axon.service;

import com.denn.edd.axon.command.CreateAccountCommand;
import com.denn.edd.axon.command.CreditMoneyCommand;
import com.denn.edd.axon.command.DebitMoneyCommand;
import com.denn.edd.axon.dto.AccountCreateDTO;
import com.denn.edd.axon.dto.MoneyCreditDTO;
import com.denn.edd.axon.dto.MoneyDebitDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {
        return commandGateway .send(new CreateAccountCommand(UUID.randomUUID().toString(), accountCreateDTO.getStartingBalance(), accountCreateDTO.getCurrency()));
    }

    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
        return commandGateway.send(new CreditMoneyCommand(accountNumber, moneyCreditDTO.getCreditAmount(), moneyCreditDTO.getCurrency()));
    }

    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
        return commandGateway.send(new DebitMoneyCommand(accountNumber, moneyDebitDTO.getDebitAmount(), moneyDebitDTO.getCurrency()));
    }
}
