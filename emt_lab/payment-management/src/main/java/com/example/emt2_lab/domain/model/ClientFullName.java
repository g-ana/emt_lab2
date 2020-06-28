package com.example.emt2_lab.domain.model;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonCreator;
import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonValue;
import com.example.emt2_lab.domain.base.ValueObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;

/* @NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode
@Data */
@Embeddable
public class ClientFullName implements ValueObject {

    @NonNull
    @JsonProperty("firstName")
    private String firstName;

    @NonNull
    @JsonProperty("lastName")
    private String lastName;

    private ClientFullName() {
        this.firstName="";
        this.lastName="";
    }

    @JsonCreator
    public ClientFullName(@NonNull @JsonProperty("firstName") String firstName,
                          @NonNull @JsonProperty("lastName") String lastName) {
        this.firstName=firstName;
        this.lastName=lastName;
    }

    @NonNull
    public String firstName() {
        return this.firstName;
    }

    @NonNull
    public String lastName() {
        return this.lastName;
    }

    @NonNull
    @JsonValue
    public String fullName() {
        return this.toString();
    }

    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null || this.getClass()!=object.getClass()) return false;
        ClientFullName clientFullName=(ClientFullName) object;
        return (Objects.equals(this.firstName, clientFullName.firstName) &&
                Objects.equals(this.lastName, clientFullName.lastName));
    }

    public int hashCode() {
        return Objects.hash(this.firstName, this.lastName);
    }

    @JsonValue
    public String toString() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}
