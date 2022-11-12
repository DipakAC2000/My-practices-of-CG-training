package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Employee;
import com.springboot.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	/*
	 * Insert data in employee: create Employee
	 * @parameter (employee)
	 * @return employee
	 */
	@PostMapping("/employee")
	public Employee insertEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	/*
	 * Fetch all records
	 */
	@GetMapping("/employee")
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();	
	}
	
	/*
	 * Fetch record by ID
	 */
	@GetMapping("/employee/{id}") //localhost:8181/employee/4
	public Employee getEmployee(@PathVariable("id") long id) {
		return employeeRepository.getById(id);
	}
	
	/*
	 * Update Existing EmployeeRecord
	 */
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable("id") long id,@RequestBody Employee employeeNew) {
		/*
		 * Step1: Take the ID of the employee that has to be updated: id
		 * step2: Go to the DB and fetch the record for this id : empDB
		 * step3: Read the new Employee details/values from the user : employeeNew
		 * step4: update empDB with new values: empDB <-- employeeNew
		 */
		
		Employee empDB = employeeRepository.getById(id); //empDB{id:1,____}
		
		if(employeeNew.getName() != null)
			empDB.setName(employeeNew.getName());
		if(employeeNew.getAge() != 0)
			empDB.setAge(employeeNew.getAge());
		if(employeeNew.getCity() != null)
			empDB.setCity(employeeNew.getCity());
		if(employeeNew.getSalary() != 0)
			empDB.setSalary(employeeNew.getSalary());
		if(employeeNew.getEmail() != null)
			empDB.setEmail(employeeNew.getEmail());
		
		return employeeRepository.save(empDB);
	}
	
	/*
	 * Delete the record by id
	 */
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable("id") long id) {
		employeeRepository.deleteById(id);
	}
	
	/*
	 * Fetch list of employees having salary>70000
	 */
	@GetMapping("/employee/salary")
	public void getEmployeeBySalary(@RequestParam("salary") double salary) {
		
	}
	
	/*
	 * Fetch list of employee by city
	 * Passing request parameter in URL: localhost:8181/employee/city?city=london
	 */
	@GetMapping("/employee/city")
	public List<Employee> getEmployeeBYCity(@RequestParam("city") String city) {
		List<Employee> list = employeeRepository.findByCity(city);
		return list;
	}


}

/*
 * fetch: GET
 * insert: POST
 * update: PUT
 * delete: DELETE
 */
