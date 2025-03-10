package com.employeedetailsservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
	private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String departmentName;
	
}
