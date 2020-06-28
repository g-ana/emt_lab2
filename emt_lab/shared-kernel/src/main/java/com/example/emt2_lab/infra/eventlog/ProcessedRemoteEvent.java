package com.example.emt2_lab.infra.eventlog;

import com.example.emt2_lab.domain.base.IdentifiableDomainObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/* @NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter */
@Entity
@Table(name="processed_remote_events")
public class ProcessedRemoteEvent implements IdentifiableDomainObject<String> {

    @Id
    @NonNull
    @Column(name="source", nullable = false)
    private String source;

    @NonNull
    @Column(name = "last_processed_event_id", nullable = false)
    private Long lastProcessedEventId;

    @SuppressWarnings("unused")
    private ProcessedRemoteEvent() {

    }

    public ProcessedRemoteEvent(@NonNull String source, @NonNull Long lastProcessedEventId) {
        this.source=Objects.requireNonNull(source, "Source must not be null. ");
        this.lastProcessedEventId=lastProcessedEventId;
    }

    @NonNull
    public String source() {
        return this.source;
    }

    @NonNull
    public Long lastProcessedEventId() {
        return this.lastProcessedEventId;
    }

    @NonNull
    public String id() {
        return this.source();
    }
}
