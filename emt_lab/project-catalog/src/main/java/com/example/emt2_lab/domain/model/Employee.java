package com.example.emt2_lab.domain.model;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonCreator;
import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonValue;
import com.example.emt2_lab.domain.base.AbstractEntity;
import com.example.emt2_lab.domain.financial.Money;
import com.example.emt2_lab.domain.geo.Address;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="employees")
@Getter
@Setter
@Where(clause = "deleted=false")
public class Employee extends AbstractEntity<EmployeeId> {

    @EmbeddedId
    private EmployeeId id;

    @NonNull
    @Column(name="email_address", nullable = false)
    @JsonProperty("emailAddress")
    private String email;

    @NonNull
    @Column(name="telephone_number", nullable = false)
    @JsonProperty("telephoneNumber")
    private String phone;

    @NonNull
    @Column(name="deleted", nullable = false)
    private boolean deleted;

    @Embedded
    private Money tariff;

    @Embedded
    private Address address;

    @NonNull
    @Column(name="name_surname", nullable = false)
    @JsonProperty("nameSurname")
    private String name_sname;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "employees")
    private Set<Project> projects;

    private Employee() {
        this.deleted=false;
        this.email="";
        this.phone="";
        this.name_sname="";
        this.projects=new HashSet<Project>();
    }

    @JsonCreator
    public Employee(@NonNull String name_sname, @NonNull String email, @NonNull String phone) {
        this.deleted=false;
        this.name_sname=name_sname;
        this.email=email;
        this.phone=phone;
        this.projects=new HashSet<Project>();
    }

    @NonNull
    public EmployeeId id() {
        return this.id;
    }

    @NonNull
    public String email() {
        return this.email;
    }

    @NonNull
    public String phone() {
        return this.phone;
    }

    @NonNull
    public String name_sname() {
        return this.name_sname;
    }

    @NonNull
    public Set<Project> projects() {
        return this.projects;
    }

    @JsonValue
    public String toString() {
        return String.format("%s, %s, %s", this.name_sname, this.email, this.phone);
    }
}
