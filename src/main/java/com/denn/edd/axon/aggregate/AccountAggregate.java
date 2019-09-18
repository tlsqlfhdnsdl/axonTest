package com.denn.edd.axon.aggregate;

import com.denn.edd.axon.command.CreateAccountCommand;
import com.denn.edd.axon.command.CreditMoneyCommand;
import com.denn.edd.axon.command.DebitMoneyCommand;
import com.denn.edd.axon.event.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@NoArgsConstructor
@Aggregate
@Getter
public class AccountAggregate {

    @AggregateIdentifier
    private String id;

    private double accountBalance;

    private String currency;

    private String status;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand){
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.id, createAccountCommand.accountBalance, createAccountCommand.currency));
    }

    @CommandHandler
    protected void on(CreditMoneyCommand creditMoneyCommand){
        AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.id, creditMoneyCommand.creditAmount, creditMoneyCommand.currency));
    }

    @CommandHandler
    protected void on(DebitMoneyCommand debitMoneyCommand){
        AggregateLifecycle.apply(new MoneyDebitedEvent(debitMoneyCommand.id, debitMoneyCommand.debitAmount, debitMoneyCommand.currency));
    }

    @EventSourcingHandler
    protected void on(AccountCreatedEvent accountCreatedEvent){
        this.id = accountCreatedEvent.id;
        this.accountBalance = accountCreatedEvent.accountBalance;
        this.currency = accountCreatedEvent.currency;
        this.status = String.valueOf(AccountAggregateStatus.CREATED);

        AggregateLifecycle.apply(new AccountActivatedEvent(this.id, AccountAggregateStatus.ACTIVATED));
    }

    @EventSourcingHandler
    protected void on(AccountActivatedEvent accountActivatedEvent){
        this.status = String.valueOf(accountActivatedEvent.status);
    }

    @EventSourcingHandler
    protected void on(MoneyCreditedEvent moneyCreditedEvent){

        if (this.accountBalance < 0 & (this.accountBalance + moneyCreditedEvent.creditAmount) >= 0){
            AggregateLifecycle.apply(new AccountActivatedEvent(this.id, AccountAggregateStatus.ACTIVATED));
        }

        this.accountBalance += moneyCreditedEvent.creditAmount;
    }

    @EventSourcingHandler
    protected void on(MoneyDebitedEvent moneyDebitedEvent){

        if (this.accountBalance >= 0 & (this.accountBalance - moneyDebitedEvent.debitAmount) < 0){
            AggregateLifecycle.apply(new AccountHeldEvent(this.id, AccountAggregateStatus.HOLD));
        }

        this.accountBalance -= moneyDebitedEvent.debitAmount;
    }

    @EventSourcingHandler
    protected void on(AccountHeldEvent accountHeldEvent){
        this.status = String.valueOf(accountHeldEvent.status);
    }
}
