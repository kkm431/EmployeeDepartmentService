package com.employeeDepartment.service;

import java.util.List;

import com.employeeDepartment.dto.DepartmentDTO;
import com.employeeDepartment.model.Department;

public interface DepartmentService {
	List<Department> getAllDepartments();
    Department getDepartmentById(Long id);
    Department createDepartment(DepartmentDTO departmentDTO);
    Department updateDepartment(Long id, DepartmentDTO updatedDepartmentDTO);
    void deleteDepartment(Long id);
}
