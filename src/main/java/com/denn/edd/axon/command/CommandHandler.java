package com.denn.edd.axon.command;

import com.denn.edd.axon.event.AccountCreatedEvent;
import com.denn.edd.axon.event.MoneyCreditedEvent;
import com.denn.edd.axon.event.MoneyDebitedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@NoArgsConstructor
public class CommandHandler {
    @org.axonframework.commandhandling.CommandHandler
    public CommandHandler(CreateAccountCommand createAccountCommand){
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.id, createAccountCommand.accountBalance, createAccountCommand.currency));
    }

    @org.axonframework.commandhandling.CommandHandler
    protected void on(CreditMoneyCommand creditMoneyCommand){
        AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.id, creditMoneyCommand.creditAmount, creditMoneyCommand.currency));
    }

    @org.axonframework.commandhandling.CommandHandler
    protected void on(DebitMoneyCommand debitMoneyCommand){
        AggregateLifecycle.apply(new MoneyDebitedEvent(debitMoneyCommand.id, debitMoneyCommand.debitAmount, debitMoneyCommand.currency));
    }

}
