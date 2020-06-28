package com.example.emt2_lab.domain.geo;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonCreator;
import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonValue;
import com.example.emt2_lab.domain.base.ValueObject;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
/* @NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data */
public class CityName implements ValueObject {

    @NonNull
    @Column(name="city_name", nullable = false)
    @JsonProperty("cityName")
    private String name;

    private CityName() {
        this.name="";
    }

    @JsonCreator
    public CityName(@NonNull String name) {
        this.name= Objects.requireNonNull(name, "Name must not be null. ");
    }

    public boolean equals(Object object) {
        if (object==this) {
            return true;
        }
        if (object==null || this.getClass()!=object.getClass()) {
            return false;
        }
        CityName cityName=(CityName) object;
        return Objects.equals(this.name, cityName.name);
    }

    public int hashCode() {
        return Objects.hash(this.name);
    }

    @JsonValue
    public String toString() {
        return this.name;
    }
}
