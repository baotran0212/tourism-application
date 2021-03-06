package com.tourism.BUS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.tourism.DAL.CustomerRepository;
import com.tourism.DAL.EmployeeRepository;
import com.tourism.DAL.TourRepository;
import com.tourism.DAL.TouristGroupCostRepository;
import com.tourism.DAL.TouristGroupRepository;
import com.tourism.DTO.Employee;
import com.tourism.DTO.Tour;
import com.tourism.DTO.TouristGroup;
import com.tourism.DTO.TouristGroupCost;
import com.tourism.DTO.TouristGroupCostItem;

public class AnalysisController {
	EmployeeRepository employeeRepository;
	TouristGroupRepository touristGroupRepository;
	TourRepository tourRepository;
	
	public AnalysisController() {
		employeeRepository = new EmployeeRepository();
		touristGroupRepository = new TouristGroupRepository();
		tourRepository = new TourRepository();
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

	public List<Object[]> getTourOperations() {
		List<Object[]> objectRows = new ArrayList<Object[]>();
		List<Tour> tours = tourRepository.findAll();
		tours.forEach(tour ->{
			List<TouristGroup> touristGroups = touristGroupRepository.findAllByTourId(tour.getId(), false); 
			int touristGroupCount = touristGroups.size();
			int totalRevenue = 0;
			int totalCost = 0;
			int interest = 0;
			for(TouristGroup TG: touristGroups) {
				totalRevenue += TG.getRevenue();
				List<TouristGroupCost> TGCosts = new TouristGroupCostRepository().findAllByTouristGroupId(TG.getId());
				for(TouristGroupCost cost :TGCosts) {
					totalCost+=cost.getTotalPrice();
				}
			}
			interest = totalRevenue - totalCost;
			objectRows.add(new Object[] {tour.getId(), tour.getName(), touristGroupCount, totalRevenue, totalCost, interest});
		});
		return objectRows;
	}
	
	public List<Object[]> getTourOperationDetails(Long tourId){
		List<Object[]> objectRows = new ArrayList<Object[]>();
		Tour tour = tourRepository.findById(tourId).get();
		List<TouristGroup> TGs = touristGroupRepository.findAllByTourId(tourId, false);
		TGs.forEach(TG->{
			int totalCost = 0;
			List<TouristGroupCost> TGCosts = new TouristGroupCostRepository().findAllByTouristGroupId(TG.getId());
			for(TouristGroupCost cost :TGCosts) {
				totalCost+=cost.getTotalPrice();
			}
			TG.setCustomers(new CustomerRepository().findAllByTouristGroupId(TG.getId()));
			objectRows.add(new Object[] {
					TG.getId(), 
					TG.getName(), 
					TG.getCustomers().size(),
					TG.getRevenue()/TG.getCustomers().size(),
					TG.getRevenue(),
					totalCost,
					TG.getRevenue()-totalCost
					});
		});
		return objectRows;
	}
}
