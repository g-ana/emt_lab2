package com.example.emt2_lab.domain.repository;

import com.example.emt2_lab.domain.model.Employee;
import com.example.emt2_lab.domain.model.EmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, EmployeeId> {
}
