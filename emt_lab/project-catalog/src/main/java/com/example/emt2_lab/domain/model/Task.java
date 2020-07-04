package com.example.emt2_lab.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

/* @NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor */
@Getter
@Setter
@Entity
@Table(name="tasks")
@Where(clause = "deleted=false")
public class Task extends AbstractEntity<TaskId> {

    @EmbeddedId
    private TaskId id;

    @Version
    private Long version;

    @NonNull
    @Column(name="name", nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(name="start_date")
    /* @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent */
    private LocalDateTime startDate;

    @Column(name="deadline")
    /* @Temporal(TemporalType.TIMESTAMP)
    @FutureOrPresent */
    private LocalDateTime deadline;

    @NonNull
    @Column(name="description", nullable = false, length = 5000)
    @JsonProperty("description")
    private String description;

    @NonNull
    @Column(name="hours", nullable = false)
    @JsonProperty("hours")
    private Integer hours;

    @NonNull
    @Column(name="price", nullable = false)
    @JsonProperty("price")
    private Money price;

    @Column(name="finished")
    private boolean finished;

    @Column(name="deleted")
    private boolean deleted;

    @ManyToOne(cascade = CascadeType.ALL)
    private Project project;

    private Task() {
        this.deleted=false;
        this.finished=false;
        this.startDate=LocalDateTime.now();
    }

    @JsonCreator
    public Task(@NonNull String name, @NonNull String description, @NonNull Integer hours,
                @NonNull Money price, @NonNull LocalDateTime deadline) {
        this.name=name;
        this.description=description;
        this.hours=hours;
        this.price=price;
        this.deadline=deadline;
    }

    @NonNull
    public TaskId id() {
        return this.id;
    }

    @NonNull
    public String name() {
        return this.name;
    }

    @NonNull
    public String description() {
        return this.description;
    }

    @NonNull
    public Integer hours() {
        return this.hours;
    }

    @NonNull
    public Money price() {
        return this.price;
    }

    public Project project() {
        return this.project;
    }

    @JsonValue
    public String toString() {
        return String.format("%s - %s, %d, %s", this.name, this.description, this.hours, this.price);
    }
}
