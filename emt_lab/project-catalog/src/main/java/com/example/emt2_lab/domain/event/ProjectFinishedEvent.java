package com.example.emt2_lab.domain.event;

import com.example.emt2_lab.domain.model.ProjectId;
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
public class ProjectFinishedEvent implements DomainEvent {

    @NonNull
    @JsonProperty("projectId")
    private ProjectId projectId;

    @NonNull
    @JsonProperty("occurredOn")
    private Instant occurredOn;

    @JsonCreator
    public ProjectFinishedEvent(@NonNull @JsonProperty("projectId") ProjectId projectId,
                                @NonNull @JsonProperty("occurredOn") Instant occurredOn) {
        this.projectId=Objects.requireNonNull(projectId, "ProjectId must not be null. ");
        this.occurredOn=Objects.requireNonNull(occurredOn, "OccurredOn must not be null. ");
    }

    @NonNull
    public ProjectId projectId() {
        return this.projectId;
    }

    @NonNull
    public Instant occurredOn() {
        return this.occurredOn;
    }
}
