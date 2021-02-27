package com.tourism.BUS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.tourism.DAL.EmployeeRepository;
import com.tourism.DAL.TouristGroupRepository;
import com.tourism.DTO.Employee;
import com.tourism.DTO.TouristGroup;

public class AnalysisController {
	EmployeeRepository employeeRepository;
	TouristGroupRepository touristGroupRepository;
	
	public AnalysisController() {
		employeeRepository = new EmployeeRepository();
		touristGroupRepository = new TouristGroupRepository();
	}
	
	public List<Employee> getEmployeeActivity(Date startDate, Date endDate){
		List<Employee> employees = new ArrayList<>();
		
		employees = employeeRepository.findAll();
		employees.forEach(emp -> {
			List<TouristGroup> TGs = touristGroupRepository.findAllByEmployeeId(emp.getId());
			TGs.removeIf(TG -> (TG.getDepatureDate().before(startDate) || TG.getDepatureDate().after(endDate)));
			System.out.println("TGs Size:"+TGs.size());
			emp.setTouristGroups(TGs);
		});
		System.out.println(employees);
		return employees;
	}
}
