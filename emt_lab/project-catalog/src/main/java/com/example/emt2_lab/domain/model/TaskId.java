package com.example.emt2_lab.domain.model;

import com.example.emt2_lab.domain.base.DomainObjectId;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode(callSuper = true)
/* @NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor */
public class TaskId extends DomainObjectId {

    private String id;

    private TaskId() {
        super("");
    }

    public TaskId(@NonNull String id) {
        super(id);
    }
}
