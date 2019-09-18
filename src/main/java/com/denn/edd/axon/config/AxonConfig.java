package com.denn.edd.axon.config;

import com.denn.edd.axon.aggregate.AccountAggregate;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository(EventStore eventStore){
        return EventSourcingRepository.builder(AccountAggregate.class).eventStore(eventStore).build();
    }
}
