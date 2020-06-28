package com.example.emt2_lab.infra.eventlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedRemoteEventRepository extends JpaRepository<ProcessedRemoteEvent, String> {

}
