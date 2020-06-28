package com.example.emt2_lab.domain.event;

import com.example.emt2_lab.domain.model.TaskId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;

// @AllArgsConstructor
@Getter
public class TaskFinishedEvent implements DomainEvent {

    @NonNull
    @JsonProperty("taskId")
    private TaskId taskId;

    @NonNull
    @JsonProperty("occurredOn")
    private Instant occurredOn;

    @JsonCreator
    public TaskFinishedEvent(@NonNull @JsonProperty("taskId") TaskId taskId,
                             @NonNull @JsonProperty("occurredOn") Instant occurredOn) {
        this.taskId= Objects.requireNonNull(taskId, "TaskId must not be null. ");
        this.occurredOn=Objects.requireNonNull(occurredOn, "OccurredOn must not be null. ");
    }

    @NonNull
    public TaskId taskId() {
        return this.taskId;
    }

    @NonNull
    public Instant occurredOn() {
        return this.occurredOn;
    }
}
