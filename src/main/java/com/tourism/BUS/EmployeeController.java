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
	
	public Employee getById(Long id) {
		return employeeRepository.findById(id).get();
	}
	
	public void createEmployee(Employee employee) {
		employee.setId(null);
		employeeRepository.save(employee);
	}

	public void modifyEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}
	
}
