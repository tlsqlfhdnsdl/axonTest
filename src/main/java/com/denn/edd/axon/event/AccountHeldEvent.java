package com.denn.edd.axon.event;

import com.denn.edd.axon.aggregate.AccountAggregateStatus;

public class AccountHeldEvent extends BaseEvent<String> {

    public final AccountAggregateStatus status;

    public AccountHeldEvent(String id, AccountAggregateStatus status) {
        super(id);
        this.status = status;
    }
}
