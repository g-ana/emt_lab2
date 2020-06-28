package com.example.emt2_lab.infra.eventlog;

import com.example.emt2_lab.domain.base.DomainEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// @AllArgsConstructor
public class DomainEventLogService {

    private final StoredDomainEventRepository storedDomainEventRepository;
    private final ObjectMapper objectMapper;

    public DomainEventLogService(StoredDomainEventRepository storedDomainEventRepository, ObjectMapper objectMapper) {
        this.storedDomainEventRepository=storedDomainEventRepository;
        this.objectMapper=objectMapper;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void append(@NonNull DomainEvent domainEvent) {
        StoredDomainEvent storedEvent=new StoredDomainEvent(domainEvent, this.objectMapper);
        this.storedDomainEventRepository.saveAndFlush(storedEvent);
    }

    @NonNull
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StoredDomainEvent> retrieveLog(Long lastProcessedId) {
        Long max=this.storedDomainEventRepository.findHighestDomainEventId();
        if (max==null) {
            max=0L;
        }
        return this.storedDomainEventRepository.findDomainEventsBetween(lastProcessedId, max);
    }
}
