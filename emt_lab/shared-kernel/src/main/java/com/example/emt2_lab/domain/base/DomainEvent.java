package com.example.emt2_lab.domain.base;

import org.springframework.lang.NonNull;

import java.time.Instant;

public interface DomainEvent extends DomainObject {

    @NonNull
    Instant occurredOn();
}
