package com.example.emt2_lab.domain.geo;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonCreator;
import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonValue;
import com.example.emt2_lab.domain.base.ValueObject;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
@MappedSuperclass
/* @Getter
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor */
public class Address implements ValueObject {

    @NonNull
    @JsonProperty("address")
    @Column(name="address")
    private String address;

    @NonNull
    @JsonProperty("city")
    @Column(name = "city", nullable = false)
    @Embedded
    private CityName city;

    @NonNull
    @JsonProperty("country")
    @Column(name = "country", nullable = false)
    @Enumerated(EnumType.STRING)
    private Country country;

    @SuppressWarnings("unused")
    protected Address() {

    }

    @JsonCreator
    public Address(@NonNull String address, @NonNull CityName city, @NonNull Country country) {
        this.address=Objects.requireNonNull(address, "Address must not be null. ");
        this.city=Objects.requireNonNull(city, "City must not be null. ");
        this.country=Objects.requireNonNull(country, "Country must not be null. ");
    }

    public String address() {
        return this.address;
    }

    public CityName city() {
        return this.city;
    }

    public Country country() {
        return this.country;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || this.getClass()!=object.getClass()) {
            return false;
        }
        Address address=(Address) object;
        return Objects.equals(this.address, address.address)
                && Objects.equals(this.city, address.city)
                && this.country==address.country;
    }

    public int hashCode() {
        return Objects.hash(this.address, this.city, this.country);
    }

    @JsonValue
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(this.address);
        sb.append(", ");
        sb.append(this.city);
        sb.append(", ");
        sb.append(this.country);
        return sb.toString();
    }
}
