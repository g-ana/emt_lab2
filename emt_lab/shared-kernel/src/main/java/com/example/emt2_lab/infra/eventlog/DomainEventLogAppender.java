package com.example.emt2_lab.infra.eventlog;

import com.example.emt2_lab.domain.base.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
// @AllArgsConstructor
public class DomainEventLogAppender {

    private final DomainEventLogService domainEventLogService;

    public DomainEventLogAppender(DomainEventLogService domainEventLogService) {
        this.domainEventLogService=domainEventLogService;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onDomainEvent(@NonNull DomainEvent domainEvent) {
        this.domainEventLogService.append(domainEvent);
    }
}
