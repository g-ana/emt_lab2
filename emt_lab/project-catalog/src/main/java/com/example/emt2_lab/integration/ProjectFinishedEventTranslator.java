package com.example.emt2_lab.integration;

import com.example.emt2_lab.domain.event.ProjectFinishedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectFinishedEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    public ProjectFinishedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper=objectMapper;
    }

    public boolean supports(StoredDomainEvent remoteEvent) {
        return remoteEvent.domainEventClassName().equals(
                "com.example.emt2_lab,project-catalog.domain.event.ProjectFinishedEvent");
    }

    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainObject(this.objectMapper, ProjectFinishedEvent.class));
    }
}
