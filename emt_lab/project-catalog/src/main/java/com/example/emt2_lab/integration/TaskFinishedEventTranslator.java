package com.example.emt2_lab.integration;

import com.example.emt2_lab.domain.event.TaskFinishedEvent;
import com.example.emt2_lab.domain.model.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Optional;

@Service
public class TaskFinishedEventTranslator {

    private final ObjectMapper objectMapper;

    public TaskFinishedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper=objectMapper;
    }

    public boolean supports(StoredDomainEvent remoteEvent) {
        return remoteEvent.domainEventClassName().equals(
                "com.example.emt2_lab.project-catalog.domain.event.TaskFinishedEvent");
    }

    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(this.objectMapper, TaskFinishedEvent.class));
    }

}
