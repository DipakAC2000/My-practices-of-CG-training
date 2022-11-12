package com.associationmapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.associationmapping.model.Employee;
import com.associationmapping.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	//1.insert employee
	@PostMapping("/employees")
	public String insertEmployee(@RequestBody Employee emp) {
		return employeeService.addEmployee(emp);
	}
}
