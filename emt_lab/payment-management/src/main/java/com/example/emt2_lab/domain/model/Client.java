package com.example.emt2_lab.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;


// @NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="clients")
public class Client extends AbstractEntity<ClientId> {

    @EmbeddedId
    private ClientId id;

    @Version
    private Long version;

    @NonNull
    @Embedded
    @JsonProperty("clientAddress")
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "billing_address", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country", nullable = false))
    })
    private ClientAddress clientAddress;

    @NonNull
    @Column(name="email_adderess", nullable = false)
    private String emailAddress;

    @NonNull
    @Column(name="telephone_number", nullable = false)
    private String telephoneNumber;

    @NonNull
    @Column(name="is_active", nullable = false)
    private boolean isActive;

    @ManyToMany(mappedBy = "projects", cascade = CascadeType.ALL)
    private Set<Project> projects;

    @ManyToMany(mappedBy = "payments", cascade = CascadeType.ALL)
    private Set<Payment> payments;

    private Client() {
        this.isActive=true;
        this.clientAddress=new ClientAddress();
        this.emailAddress="";
        this.telephoneNumber="";
        this.projects=new HashSet<Project>();
        this.payments=new HashSet<Payment>();
    }

    @JsonCreator
    public Client(@NonNull ClientAddress clientAddress, @NonNull String emailAddress, @NonNull String telephoneNumber) {
        this.isActive=true;
        this.clientAddress= Objects.requireNonNull(clientAddress, "Client address must not be null. ");
        this.emailAddress=emailAddress;
        this.telephoneNumber=telephoneNumber;
        this.projects=new HashSet<Project>();
        this.payments=new HashSet<Payment>();
    }

    @NonNull
    public ClientId id() {
        return this.id;
    }

    @NonNull
    @JsonValue
    public ClientAddress clientAddress() {
        return clientAddress;
    }

    @NonNull
    public String emailAddress() {
        return this.emailAddress;
    }

    @NonNull
    public String telephoneNumber() {
        return this.telephoneNumber;
    }
    
    public void changeStatus() {
        this.isActive=!isActive;
    }

    public Stream<Project> projects() {
        return this.projects.stream();
    }

    public Stream<Payment> payments() {
        return this.payments.stream();
    }

    public String toString() {
        return String.format("%s %s %s", this.clientAddress.toString(), this.emailAddress, this.telephoneNumber);
    }
}
