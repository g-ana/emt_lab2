package com.example.emt2_lab.infra.eventlog;

import com.example.emt2_lab.domain.base.RemoteEventLog;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

// @AllArgsConstructor
@Service
public class RemoteEventProcessor {

    private final ProcessedRemoteEventRepository processedRemoteEventRepository;
    private final Map<String, RemoteEventLogService> remoteEventLogs;
    private final Map<String, RemoteEventTranslator> remoteEventTranslators;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final TransactionTemplate transactionTemplate;

    public RemoteEventProcessor(ProcessedRemoteEventRepository processedRemoteEventRepository,
                                Map<String, RemoteEventLogService> remoteEventLogs,
                                Map<String, RemoteEventTranslator> remoteEventTranslators,
                                ApplicationEventPublisher applicationEventPublisher,
                                TransactionTemplate transactionTemplate) {
        this.processedRemoteEventRepository=processedRemoteEventRepository;
        this.remoteEventLogs=remoteEventLogs;
        this.remoteEventTranslators=remoteEventTranslators;
        this.applicationEventPublisher=applicationEventPublisher;
        this.transactionTemplate=transactionTemplate;
    }

    private Long getLastProcessedEventId(@NonNull RemoteEventLogService remoteEventLogService) {
        return this.processedRemoteEventRepository.
                findById(remoteEventLogService.source()).
                map(ProcessedRemoteEvent::lastProcessedEventId).
                orElse(0L);
    }

    private void setLastProcessedEventId(@NonNull RemoteEventLogService remoteEventLogService,
                                         Long lastProcessedEventId) {
        this.processedRemoteEventRepository.saveAndFlush(
                new ProcessedRemoteEvent(remoteEventLogService.source(), lastProcessedEventId));
    }

    private void publishEvent(@NonNull StoredDomainEvent event) {
        this.remoteEventTranslators.values().
                stream().
                filter(translator -> translator.supports(event)).
                findFirst().
                flatMap(translator -> translator.translate(event)).
                ifPresent(ApplicationEventPublisher::publishEvent);
    }

    private void processEvents(@NonNull RemoteEventLogService remoteEventLogService,
                               @NonNull List<StoredDomainEvent> events) {
        events.forEach(event -> {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    this.publishEvent(event);
                    this.setLastProcessedEventId(remoteEventLogService, event.id());
                }
            });
        });
    }

    private void processEvents(@NonNull RemoteEventLogService remoteEventLogService) {
        RemoteEventLog log=remoteEventLogService.currentLog(this.getLastProcessedEventId(remoteEventLogService));
        this.processEvents(remoteEventLogService, log.events());
    }

    @Scheduled(fixedDelay = 20000)
    public void processEvents() {
        this.remoteEventLogs.values().forEach(this::processEvents);
    }

}
