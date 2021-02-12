package com.tourism.BUS;

import java.util.List;

import com.tourism.DAL.EmployeeRepository;
import com.tourism.DTO.Employee;

public class EmployeeController {
	EmployeeRepository employeeRepository;
	
	public EmployeeController() {
		employeeRepository = new EmployeeRepository();
	}
	
	public List<Employee> getAll(){
		return employeeRepository.findAll();
	}
}
