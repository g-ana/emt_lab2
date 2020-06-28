package com.example.emt2_lab.domain.base;

import com.example.emt2_lab.infra.eventlog.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {

    List<StoredDomainEvent> events();
}
