package com.example.emt2_lab.domain.model;

import com.example.emt2_lab.domain.base.DomainObjectId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode(callSuper = true)
/* @NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor */
public class ProjectId extends DomainObjectId {

    private String id;

    private ProjectId() {
        super("");
    }

    public ProjectId(@NonNull String id) {
        super(id);
    }
}
