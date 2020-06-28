package com.example.emt2_lab.application;

import com.example.emt2_lab.domain.event.PaymentStateChangedEvent;
import com.example.emt2_lab.domain.model.Payment;
import com.example.emt2_lab.domain.model.PaymentId;
import com.example.emt2_lab.domain.repository.PaymentRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class PaymentCatalog {

    private final PaymentRepository paymentRepository;

    public PaymentCatalog(PaymentRepository paymentRepository) {
        this.paymentRepository=paymentRepository;
    }

    @NonNull
    public List<Payment> findAll() {
        return this.paymentRepository.findAll();
    }

    @NonNull
    public Optional<Payment> findById(@NonNull PaymentId paymentId) {
        Objects.requireNonNull(paymentId, "PaymentId must not be null. ");
        return this.paymentRepository.findById(paymentId);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onPaymentStateChangedEvent(PaymentStateChangedEvent event) {

    }
}

