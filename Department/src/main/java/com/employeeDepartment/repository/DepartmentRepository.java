package com.employeeDepartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeeDepartment.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {}
