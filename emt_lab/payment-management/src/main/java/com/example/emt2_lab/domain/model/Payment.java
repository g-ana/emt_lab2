package com.example.emt2_lab.domain.model;

import com.example.emt2_lab.domain.base.AbstractEntity;
import com.example.emt2_lab.domain.financial.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.dom4j.tree.AbstractEntity;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.time.Instant;
import java.util.Currency;
import java.util.HashSet;
import java.util.Objects;

// @NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment extends AbstractEntity<PaymentId> {

    @EmbeddedId
    private PaymentId id;
    
    @Version
    private Long version;

    @NonNull
    @Column(name = "payed_on", nullable = false)
    @JsonProperty("payedOn")
    private Instant payedOn;

    @NonNull
    @Column(name = "price", nullable = false)
//    @Enumerated(EnumType.STRING)
    @JsonProperty("price")
    private Price price;

    @NonNull
    @Column(name = "payment_state", nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonProperty("state")
    private PaymentState state;

    @Embedded
    @JsonProperty("clientAddress")
    private ClientAddress clientAddress;

    @OneToMany(mappedBy = "payments", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Project> projects;

    public Stream<Project> projects() {
        return this.projects.stream();
    }

    public Project addProject(@NonNull Project project, int quantity) {
        Objects.requireNonNull(project, "Project must not be null. ");
        Project p=new Project(project, quantity);
        this.projects.add(p);
        return p;
    }

    public Money total() {
        return this.projects.stream().map(Project::subtotal).reduce(new Money(price.currency, 0), Money::add);
    }

    @SuppressWarnings("unused")
    private Payment() {
        super(DomainObjectId.randomId(PaymentId.class));
        this.projects=new HashSet<Project>();
        this.setState(PaymentState.RECEIVED);
    }

    public Payment(@NonNull Instant payedOn, @NonNull Price price, @NonNull ClientAddress clientAddress) {
        super(DomainObjectId.randomId(PaymentId.class));
        this.projects=new HashSet<Project>();
        this.setState(PaymentState.RECEIVED);
        this.setPayedOn(payedOn);
        this.setPrice(price);
        this.setClientAddress(clientAddress);
    }

    @NonNull
    public Instant payedOn() {
        return this.payedOn;
    }

    @NonNull
    public Price price() {
        return this.price;
    }

    @NonNull
    public PaymentState state() {
        return this.state;
    }
}
