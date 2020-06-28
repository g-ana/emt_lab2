package com.example.emt2_lab.domain.repository;

import com.example.emt2_lab.domain.model.Project;
import com.example.emt2_lab.domain.model.ProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, ProjectId> {
}
