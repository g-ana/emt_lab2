package com.example.emt2_lab.infra.eventlog;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonRawValue;
import com.example.emt2_lab.domain.base.DomainEvent;
import com.example.emt2_lab.infra.jackson.RawJsonDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

/* @NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Data */
@Entity
@Table(name = "event_log")
public class StoredDomainEvent {

    private static final int DOMAIN_EVENT_JSON_MAX_LENGTH=1024;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NonNull
    @Column(name="id", nullable = false)
    @JsonProperty("id")
    private Long id;

    @NonNull
    @Column(name="occurred_on", nullable = false)
    @JsonProperty("occurredOn")
    private Instant occurredOn;

    @NonNull
    @Column(name="domain_event_class_name", nullable = false)
    @JsonProperty("domainEventClass")
    private String domainEventClassName;

    @NonNull
    @Column(name="domain_event_body", nullable = false, length = DOMAIN_EVENT_JSON_MAX_LENGTH)
    @JsonProperty("domainEventBody")
    @JsonRawValue
    @JsonDeserialize(using = RawJsonDeserializer.class)
    private String domainEventBody;

    @Transient
    private Class<? extends DomainEvent> domainEventClass;

    @SuppressWarnings("unused")
    private StoredDomainEvent() {

    }

    private StoredDomainEvent(@NonNull DomainEvent domainEvent, @NonNull ObjectMapper objectMapper)
            throws IllegalArgumentException {
        Objects.requireNonNull(domainEvent, "Domain event must not be null. ");
        Objects.requireNonNull(objectMapper, "Object mapper must not be null. ");
        this.occurredOn=domainEvent.occurredOn();
        this.domainEventClass=domainEvent.getClass();
        this.domainEventClassName=domainEventClass.getName();
        try {
            this.domainEventBody=objectMapper.writeValueAsString(domainEvent);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Could not serialize domain event to JSON. ", ex);
        }
        if (this.domainEventBody.length() > DOMAIN_EVENT_JSON_MAX_LENGTH) {
            throw new IllegalArgumentException("Domain event JSON string is too long. ");
        }
    }

    public String toString() {
        return String.format("%s[id=%d, domainEventClass=%s]",
                this.getClass().getSimpleName(), this.id, this.domainEventClassName);
    }

    @NonNull
    public <T extends DomainEvent> T toDomainEvent(
            @NonNull ObjectMapper objectMapper, @NonNull Class<T> domainEventClass) throws IllegalArgumentException {
        Objects.requireNonNull(objectMapper, "Object mapper must not be null. ");
        Objects.requireNonNull(domainEventClass, "Domain event class must not be null. ");
        try {
            return objectMapper.readValue(this.domainEventBody, domainEventClass);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Could not deserialize domain event from JSON", ex);
        }
    }

    @NonNull
    public DomainEvent toDomainEvent(@NonNull ObjectMapper objectMapper) {
        return this.toDomainEvent(objectMapper, this.domainEventClass());
    }

    @NonNull
    public JsonNode toJsonNode(@NonNull ObjectMapper objectMapper) throws IllegalArgumentException {
        Objects.requireNonNull(objectMapper, "Object mapper must not be null. ");
        try {
            return objectMapper.readTree(this.domainEventBody);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Could not deserialize event from JSON", ex);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<? extends DomainEvent> lookupDomainEventClass() throws IllegalStateException {
        try {
            return (Class<? extends DomainEvent>) Class.forName(this.domainEventClassName);
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Could not load domain event class", ex);
        }
    }

    @NonNull
    public Class<? extends DomainEvent> domainEventClass() {
        if (this.domainEventClass==null) {
            this.domainEventClass=this.lookupDomainEventClass();
        }
        return this.domainEventClass;
    }

    @NonNull
    public Long id() throws IllegalStateException {
        if (this.id==null) {
            throw new IllegalStateException("The StoredDomainEvent has not been saved yet. ");
        }
        return this.id;
    }

    @NonNull
    public Instant occurredOn() {
        return this.occurredOn;
    }

    @NonNull
    public String domainEventClassName() {
        return this.domainEventClassName;
    }

    @NonNull
    public String toJsonString() {
        return this.domainEventBody;
    }
}
