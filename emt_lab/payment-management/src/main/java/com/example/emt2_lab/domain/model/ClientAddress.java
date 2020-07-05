package com.example.emt2_lab.domain.model;

import com.example.emt2_lab.domain.geo.Address;
import com.example.emt2_lab.domain.geo.CityName;
import com.example.emt2_lab.domain.geo.Country;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
/* @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Data */
public class ClientAddress extends Address {

    @NonNull
    @Column(name = "client_name", nullable = false)
    @Embedded
    private ClientFullName fullName;

    @SuppressWarnings("unused")
    protected ClientAddress() {

    }

    public ClientAddress(@NonNull ClientFullName fullName,
                         @NonNull String address, @NonNull CityName city, @NonNull Country country) {
        super(address, city, country);
        this.fullName=fullName;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || this.getClass()!=object.getClass()) {
            return false;
        }
        if (! super.equals(object)) {
            return false;
        }
        ClientAddress clientAddress=(ClientAddress) object;
        return (Objects.equals(this.fullName, clientAddress.fullName) &&
                Objects.equals(this.address(), clientAddress.address()));
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), this.fullName.hashCode());
    }

    public String toString() {
        return String.format("%s, %s", this.fullName.toString(), this.address());
    }
}
