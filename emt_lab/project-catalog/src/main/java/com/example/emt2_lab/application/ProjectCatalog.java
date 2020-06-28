package com.example.emt2_lab.application;

import com.example.emt2_lab.domain.event.ProjectFinishedEvent;
import com.example.emt2_lab.domain.event.TaskFinishedEvent;
import com.example.emt2_lab.domain.model.Project;
import com.example.emt2_lab.domain.model.ProjectId;
import com.example.emt2_lab.domain.repository.ProjectRepository;
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
public class ProjectCatalog {

    private final ProjectRepository projectRepository;

    public ProjectCatalog(ProjectRepository projectRepository) {
        this.projectRepository=projectRepository;
    }

    @NonNull
    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    @NonNull
    public Optional<Project> findById(@NonNull ProjectId projectId) {
        Objects.requireNonNull(projectId, "ProjectId must not be null. ");
        return this.projectRepository.findById(projectId);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void onProjectFinishedEvent(ProjectFinishedEvent event) {

    }
}

