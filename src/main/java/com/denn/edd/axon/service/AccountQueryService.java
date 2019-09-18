package com.denn.edd.axon.service;

import com.denn.edd.axon.query.entity.AccountQueryEntity;
//import com.denn.edd.axon.query.repository.AccountRepository;
import com.denn.edd.axon.query.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountQueryService {

    private final EventStore eventStore;
    private final AccountRepository accountRepository;


    public List<Object> listEventsForAccount(String accountNumber) {
        return eventStore.readEvents(accountNumber)
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }

    public AccountQueryEntity getAccount(String accountNumber) {
        return accountRepository.findById(accountNumber).get();
    }
}