package com.tourism;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.tourism.DAL.TourRepository;
import com.tourism.DAL.TouristGroupRepository;
import com.tourism.DTO.Customer;
import com.tourism.DTO.Tour;
import com.tourism.DTO.TouristGroup;

import junit.framework.TestCase;

public class TestTouristGroupRepository extends TestCase{
	public TestTouristGroupRepository(String name) {
		super(name);
	}
	
	public void testInsertTouristGroup() {
		TouristGroupRepository touristGroupRepos = new TouristGroupRepository();
		
		TouristGroup tg = new TouristGroup("name", new Date(), new Date(), "description",
				Double.valueOf(300000), Double.valueOf(50000), Double.valueOf(130000), Double.valueOf(12300000));
		Tour tour = new Tour("tour name", "tour description", Double.valueOf(500));
		Customer customer = new Customer(null,"customer name", "311842023", "customer address", "male", "340545345");
		Customer customer2 = new Customer(null, "customer2 name", "3525445", "customer2 address", "female", "453463456");
		tg.setTour(tour);
		tg.setCustomers(new ArrayList<Customer>(Arrays.asList(new Customer[] {customer,customer2})));
		tg = touristGroupRepos.save(tg);
		
		assertNotNull("Customer not null", tg.getCustomers().get(0));
		assertNotNull("Customer 2 not null", tg.getCustomers().get(1));
		assertNotNull("Tour not null", tg.getTour());
	}
	
	public void testNullInName() {
		
	}
}