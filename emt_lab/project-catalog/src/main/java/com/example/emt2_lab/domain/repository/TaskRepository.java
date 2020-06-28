package com.example.emt2_lab.domain.repository;

import com.example.emt2_lab.domain.model.Task;
import com.example.emt2_lab.domain.model.TaskId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, TaskId> {
}
