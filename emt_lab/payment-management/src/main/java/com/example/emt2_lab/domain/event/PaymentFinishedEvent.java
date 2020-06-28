package com.example.emt2_lab.domain.event;

import com.example.emt2_lab.domain.model.PaymentId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.DomainEvents;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;

// @AllArgsConstructor
@Getter
public class PaymentFinishedEvent implements DomainEvent {

    @NonNull
    @JsonProperty("paymentId")
    private PaymentId paymentId;

    @NonNull
    @JsonProperty("occurredOn")
    private Instant occurredOn;

    @JsonCreator
    public PaymentFinishedEvent(@NonNull @JsonProperty("paymentId") PaymentId paymentId,
                                @NonNull @JsonProperty("occurredOn") Instant occurredOn) {
        this.paymentId= Objects.requireNonNull(paymentId, "PaymentId must not be null. ");
        this.occurredOn=Objects.requireNonNull(occurredOn, "OccurredOn must not be null. ");
    }

    @NonNull
    public PaymentId paymentId() {
        return this.paymentId;
    }

    @NonNull
    public Instant occurredOn() {
        return this.occurredOn;
    }
}
