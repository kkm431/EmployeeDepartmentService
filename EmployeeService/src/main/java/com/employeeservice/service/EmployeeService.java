package com.employeeservice.service;

import java.util.List;

import com.employeeservice.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(Long id);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
	List<EmployeeDTO> createEmployees(List<EmployeeDTO> employeeDTOs);
}
