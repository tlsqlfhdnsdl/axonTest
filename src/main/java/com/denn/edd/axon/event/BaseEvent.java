package com.denn.edd.axon.event;

public class BaseEvent<T> {
    public final T id;
    public final Class eventType = this.getClass();

    public <R extends BaseEvent> BaseEvent(T id) {
        this.id = id;
    }
}
