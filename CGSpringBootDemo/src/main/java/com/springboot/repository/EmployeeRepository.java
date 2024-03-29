package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>  {

	List<Employee> findByCity(String city);

}
