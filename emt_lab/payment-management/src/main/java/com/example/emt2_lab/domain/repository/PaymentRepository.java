package com.example.emt2_lab.domain.repository;

import com.example.emt2_lab.domain.model.Payment;
import com.example.emt2_lab.domain.model.PaymentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, PaymentId> {
}
