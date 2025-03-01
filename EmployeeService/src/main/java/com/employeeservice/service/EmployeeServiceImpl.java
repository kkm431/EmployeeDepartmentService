package com.employeeservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.employeeservice.dto.EmployeeDTO;
import com.employeeservice.model.EmployeeModel;
import com.employeeservice.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee data cannot be null");
        }
        EmployeeModel employee = new EmployeeModel(
                employeeDTO.getEmployeeId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail(),
                employeeDTO.getDepartmentId()
        );
        employee = employeeRepository.save(employee);
        return mapToDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeModel employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id));
        return mapToDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        EmployeeModel employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id));

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDepartmentId(employeeDTO.getDepartmentId());
        employee = employeeRepository.save(employee);

        return mapToDTO(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO mapToDTO(EmployeeModel employee) {
        return new EmployeeDTO(
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentId()
        );
    }


	@Override
    public List<EmployeeDTO> createEmployees(List<EmployeeDTO> employeeDTOs) {
        List<EmployeeModel> employees = employeeDTOs.stream()
                .map(dto -> new EmployeeModel(
                        dto.getEmployeeId(),
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getEmail(),
                        dto.getDepartmentId()
                ))
                .collect(Collectors.toList());
        employeeRepository.saveAll(employees);
        return employeeDTOs;
    }
}
