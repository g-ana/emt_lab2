package com.example.emt2_lab.domain.model;

import com.example.emt2_lab.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode(callSuper = true)
public class EmployeeId extends DomainObjectId {

    private String id;

    private EmployeeId() {
        super("");
    }

    public EmployeeId(@NonNull String id) {
        super(id);
    }
}
