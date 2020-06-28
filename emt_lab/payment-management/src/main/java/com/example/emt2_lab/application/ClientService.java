package com.example.emt2_lab.application;

import com.example.emt2_lab.domain.event.PaymentFinishedEvent;
import com.example.emt2_lab.domain.model.Client;
import com.example.emt2_lab.domain.model.ClientId;
import com.example.emt2_lab.domain.repository.ClientRepository;
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
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository=clientRepository;
    }

    @NonNull
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @NonNull
    public Optional<Client> findById(@NonNull ClientId clientId) {
        Objects.requireNonNull(clientId, "ClientId must not be null. ");
        return this.clientRepository.findById(clientId);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void onPaymentFinishedEvent(PaymentFinishedEvent event) {

    }
}
