package com.denn.edd.axon.event;

import com.denn.edd.axon.aggregate.AccountAggregateStatus;

public class AccountActivatedEvent extends BaseEvent<String> {

    public final AccountAggregateStatus status;

    public AccountActivatedEvent(String id, AccountAggregateStatus status) {
        super(id);
        this.status = status;
    }
}