package com.example.emt2_lab.domain.financial;

import com.example.emt2_lab.domain.base.ValueObject;
import lombok.*;

import javax.persistence.Access;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;
// import java.util.Currency;

import javax.validation.constraints.PositiveOrZero;

@Embeddable
@Getter
/* @NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor */
public class Money implements ValueObject {

    @NonNull
    @Enumerated(EnumType.STRING)
    private final Currency currency;

    @NonNull
   // @PositiveOrZero
    private final Integer amount;

    private Money() {
        this.currency=null;
        this.amount=0;
    }

    public Money(@NonNull Currency currency, @NonNull Integer amount) {
        this.currency=Objects.requireNonNull(currency, "Currency must not be null. ");
        this.amount=Objects.requireNonNull(amount, "Amount must not be null. ");
    }

    public static Money valueOf(Currency currency, Integer amount) {
        return new Money(currency, amount);
    }

    public Money add(Money money) throws IllegalArgumentException {
        if (! this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies. ");
        }
        return new Money(currency, this.amount + money.amount);
    }

    public Money subtract(Money money) throws IllegalArgumentException {
        if (! this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot subtract two Money objects with different currencies. ");
        }
        return new Money(currency, this.amount - money.amount);
    }

    public Money multiply(int quantity) {
        return new Money(currency, this.amount * quantity);
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || this.getClass()!=object.getClass()) {
            return false;
        }
        Money money=(Money) object;
        return (this.currency==money.currency && this.amount.intValue()==money.amount.intValue());
    }

    public int hashCode() {
        return Objects.hash(valueOf(this.currency, this.amount));
    }

    public String toString() {
        return String.format("%s %d", this.currency, this.amount);
    }

}
