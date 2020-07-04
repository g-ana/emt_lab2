package com.example.emt2_lab.domain.financial;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonCreator;
import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonValue;
import com.example.emt2_lab.domain.base.ValueObject;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Objects;

import javax.validation.constraints.PositiveOrZero;

// @AllArgsConstructor
public class Price implements ValueObject {

    @JsonProperty("currency")
    @NonNull
    private final Currency currency;

    @JsonProperty("amount")
    // @PositiveOrZero
    private final Integer amount;

    @JsonCreator
    public Price(@NonNull @JsonProperty("currency") Currency currency, @JsonProperty("amount") Integer amount) {
        this.currency=Objects.requireNonNull(currency, "Currency must not be null. ");
        this.amount=amount;
    }

    @JsonValue
    public String toString() {
        return String.format("%s %d", this.currency, this.amount);
    }
}
