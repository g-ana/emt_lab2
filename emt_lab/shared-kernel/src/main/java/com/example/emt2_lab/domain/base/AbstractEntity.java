package com.example.emt2_lab.domain.base;

// import lombok.var;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.util.ProxyUtils;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@MappedSuperclass
/* @NoArgsConstructor
@AllArgsConstructor
@Data */
public abstract class AbstractEntity<ID extends DomainObjectId> implements IdentifiableDomainObject<ID> {

    @EmbeddedId
    protected ID id;

    public AbstractEntity() {

    }

    public AbstractEntity(ID id) {
        this.id=id;
    }

    public ID id() {
        return this.id;
    }

    public boolean equals(Object object) {
        if (object==this) {
            return true;
        }
        if (object==null || !this.getClass().equals(ProxyUtils.getUserClass(object))) {
            return false;
        }
        AbstractEntity<?> other=(AbstractEntity<?>) object;
        return (this.id!=null && this.id.equals(other.id));
    }

    public int hashCode() {
        return this.id==null ? super.hashCode() : this.id.hashCode();
    }

    public String toString() {
        return String.format("%s[%s]", this.getClass().getSimpleName(), this.id);
    }
}
