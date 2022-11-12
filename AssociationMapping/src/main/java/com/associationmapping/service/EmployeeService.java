package com.associationmapping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.associationmapping.model.Employee;
import com.associationmapping.repository.AddressRepository;
import com.associationmapping.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	AddressRepository addressRepository;

	public String addEmployee(Employee emp) {
		Employee emp2=employeeRepository.save(emp);
		return "Inserted Succesfully with id"+":"+emp2.getEid();
	}
	
	
}
