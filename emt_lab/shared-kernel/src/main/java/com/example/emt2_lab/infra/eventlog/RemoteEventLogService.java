package com.example.emt2_lab.infra.eventlog;

import com.example.emt2_lab.domain.base.RemoteEventLog;

public interface RemoteEventLogService {

    String source();

    RemoteEventLog currentLog(Long lastProcessedEventId);
}
