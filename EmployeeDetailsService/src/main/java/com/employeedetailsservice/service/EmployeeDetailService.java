package com.employeedetailsservice.service;

//package com.employeedetailsservice.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;

import com.employeedetailsservice.model.Department;
import com.employeedetailsservice.model.EmployeeModel;
import com.employeedetailsservice.model.EmployeeResponse;

@Service
public class EmployeeDetailService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<EmployeeResponse> getAllEmployees() {
        // Fetch all employees from the EmployeeService registered in Eureka
        List<EmployeeModel> employees = webClientBuilder.baseUrl("http://EmployeeService")
                .build()
                .get()
                .uri("/api/employees")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(EmployeeModel.class)
                .collectList()
                .block();

        // Fetch all departments from the DepartmentService registered in Eureka
        Map<Long, String> departmentMap = webClientBuilder.baseUrl("http://DepartmentService")
                .build()
                .get()
                .uri("/api/departments")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Department.class)
                .collectList()
                .block()
                .stream()
                .collect(Collectors.toMap(Department::getDeptId, Department::getDeptName));

        // Map employees to their respective department names
        return employees.stream()
                .map(emp -> new EmployeeResponse(
                        emp.getEmployeeId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmail(),
                        departmentMap.getOrDefault(emp.getDepartmentId(), "Unknown Department")
                ))
                .collect(Collectors.toList());
    }
}
