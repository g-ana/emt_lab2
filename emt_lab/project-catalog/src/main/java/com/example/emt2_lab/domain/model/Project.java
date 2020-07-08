package com.example.emt2_lab.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
// import org.dom4j.tree.AbstractEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* @NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor */
@Getter
@Setter
@Entity
@Table("projects")
@Where(clause = "deleted=false")
public class Project extends AbstractEntity<ProjectId> {

    @EmbeddedId
    private ProjectId id;

    @Version
    private Long version;

    @NonNull
    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    private String name;

    @NonNull
    @Column(name = "description", nullable = false, length = 5000)
    @JsonProperty("description")
    private String description;

    @Column(name = "start_date")
    /* @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent */
    private LocalDateTime startDate;

    @Column(name="deadline")
    /* @Temporal(TemporalType.TIMESTAMP)
    @FutureOrPresent */
    private LocalDateTime deadline;

    @Column(name = "finished")
    private boolean finished;

    @Column(name="payed")
    private boolean payed;

    @Column(name="deleted")
    private boolean deleted;

    @NonNull
    @Column(name="price", nullable = false)
    @JsonProperty("price")
    @Embedded
    private Money price;

    @OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Task> tasks;

    @ManyToMany(mappedBy = "projects", cascade = CascadeType.ALL)
    private Set<Client> clients;

    @ManyToMany(mappedBy = "projects", cascade = CascadeType.ALL)
    private Set<Employee> employees;

    @ManyToOne
    private Employee manager;

    private Project() {
        this.deleted=false;
        this.finished=false;
        this.payed=false;
        this.startDate= LocalDateTime.now();
        this.tasks=new HashSet<Task>();
        this.clients=new HashSet<Client>();
    }

    @JsonCreator
    public Project(@NonNull String name, @NonNull String description,
                   @NonNull Money price, @NonNull LocalDateTime deadline) {
        this.deleted=false;
        this.finished=false;
        this.payed=false;
        this.startDate=LocalDateTime.now();
        this.name=name;
        this.description=description;
        this.price=price;
        this.deadline=deadline;
        this.tasks=new HashSet<Task>();
        this.clients=new HashSet<Client>();
    }

    @NonNull
    public ProjectId id() {
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
    public boolean isFinished() {
        return this.finished;
    }

    @NonNull
    public boolean isPayed() {
        return this.payed;
    }

    @NonNull
    public Money price() {
        return this.price;
    }

    @NonNull
    public Employee manager() {
        return this.manager;
    }

    @NonNull
    public Set<Task> tasks() {
        return this.tasks;
    }

    @NonNull
    public Set<Client> clients() {
        return this.clients;
    }

    @NonNull
    public Set<Employee> employees() {
        return this.employees;
    }

    @NonNull
    public Task addTask(@NonNull Task task) {
        Objects.requireNonNull(task, "Task must not be null. ");
        this.tasks.add(task);
        return task;
    }

    @NonNull
    public Employee addEmployee(@NonNull Employee employee) {
        Objects.requireNonNull(employee, "Employee must not be null. ");
        this.employees.add(employee);
        return employee;
    }

    @NonNull
    public Client addClient(@NonNull Client client) {
        Objects.requireNonNull(client, "Client must not be null. ");
        this.clients.add(client);
        return client;
    }

    @JsonValue
    public String toString() {
        return String.format("%s - %s, %s", this.name, this.description, this.price.toString());
    }

}
