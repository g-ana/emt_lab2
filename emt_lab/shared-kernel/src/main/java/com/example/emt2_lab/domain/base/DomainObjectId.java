package com.example.emt2_lab.domain.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.MappedSuperclass;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@Getter
// @AllArgsConstructor
public class DomainObjectId implements ValueObject {

    private String id;

    public DomainObjectId(String id) {
        this.id=id;
    }

    @NonNull
    public static <ID extends DomainObjectId> ID randomId(@NonNull Class<ID> idClass) throws RuntimeException {
        Objects.requireNonNull(idClass, "idClass must not be null. ");
        try {
            return idClass.getConstructor(String.class).newInstance(UUID.randomUUID().toString());
        } catch (Exception ex) {
            throw new RuntimeException("Could not create new instance of " + idClass, ex);
        }
    }
}
