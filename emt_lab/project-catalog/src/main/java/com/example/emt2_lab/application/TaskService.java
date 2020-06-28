package com.example.emt2_lab.application;

import com.example.emt2_lab.domain.event.TaskFinishedEvent;
import com.example.emt2_lab.domain.model.Project;
import com.example.emt2_lab.domain.model.Task;
import com.example.emt2_lab.domain.model.TaskId;
import com.example.emt2_lab.domain.repository.TaskRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository=taskRepository;
    }

    @NonNull
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    @NonNull
    public Optional<Task> findById(@NonNull TaskId taskId) {
        Objects.requireNonNull(taskId, "TaskId must not be null. ");
        return this.taskRepository.findById(taskId);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onTaskFinishedEvent(TaskFinishedEvent event) {

    }
}
