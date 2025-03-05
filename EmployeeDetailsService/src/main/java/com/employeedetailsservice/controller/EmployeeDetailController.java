package com.employeedetailsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeedetailsservice.model.EmployeeResponse;
import com.employeedetailsservice.service.EmployeeDetailService;

@RestController
@RequestMapping("/EmployeeDetails")
public class EmployeeDetailController {
	
	@Autowired
	private EmployeeDetailService employeeDetailService;
	

	@GetMapping
	public ResponseEntity<List<EmployeeResponse>> getAllEmployeeDetail(){
		
		List<EmployeeResponse> employees = employeeDetailService.getAllEmployees();

        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if no employees found
        }

        return ResponseEntity.ok(employees);
	}

}
