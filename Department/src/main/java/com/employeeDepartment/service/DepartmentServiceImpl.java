package com.employeeDepartment.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.employeeDepartment.dto.DepartmentDTO;
import com.employeeDepartment.model.Department;
import com.employeeDepartment.repository.DepartmentRepository;

@Service
class DepartmentServiceImpl implements DepartmentService {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentRepository repository;

    @Override
    public List<Department> getAllDepartments() {
        logger.info("Fetching all departments");
        return repository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        logger.info("Fetching department with id: {}", id);
        return repository.findById(id).orElseThrow(() -> {
            logger.error("Department not found with id: {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        });
    }

    @Override
    public Department createDepartment(DepartmentDTO departmentDTO) {
        logger.info("Creating new department: {}", departmentDTO.getDeptName());
        Department department = new Department();
        department.setDeptId(departmentDTO.getDeptId());
        department.setDeptName(departmentDTO.getDeptName());
        return repository.save(department);
    }

    @Override
    public Department updateDepartment(Long id, DepartmentDTO updatedDepartmentDTO) {
        logger.info("Updating department with id: {}", id);
        Department existingDepartment = repository.findById(id).orElseThrow(() -> {
            logger.error("Department not found with id: {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        });
        existingDepartment.setDeptName(updatedDepartmentDTO.getDeptName());
        return repository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        logger.info("Deleting department with id: {}", id);
        Department existingDepartment = repository.findById(id).orElseThrow(() -> {
            logger.error("Department not found with id: {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        });
        repository.deleteById(id);
    }
}
