package com.example.emt2_lab.integration;

import com.example.emt2_lab.domain.event.PaymentFinishedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentFinishedEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    public PaymentFinishedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper=objectMapper;
    }

    public boolean supports(StoredDomainEvent remoteEvent) {
        return remoteEvent.domainEventClassName().equals(
                "com.example.emt2_lab.payment-management.domain.event.PaymentFinishedEvent");
    }

    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(this.objectMapper, PaymentFinishedEvent.class));
    }
}
