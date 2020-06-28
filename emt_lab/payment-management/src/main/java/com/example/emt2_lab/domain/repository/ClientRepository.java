package com.example.emt2_lab.domain.repository;

import com.example.emt2_lab.domain.model.Client;
import com.example.emt2_lab.domain.model.ClientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, ClientId> {
}
