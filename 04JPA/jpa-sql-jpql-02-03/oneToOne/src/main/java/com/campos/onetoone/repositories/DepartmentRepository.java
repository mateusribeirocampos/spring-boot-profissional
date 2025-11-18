package com.campos.onetoone.repositories;

import com.campos.onetoone.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
