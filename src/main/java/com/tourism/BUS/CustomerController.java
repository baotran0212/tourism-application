package com.tourism.BUS;

import java.util.List;
import java.util.Optional;

import com.tourism.DAL.CustomerRepository;
import com.tourism.DAL.TouristGroupRepository;
import com.tourism.DTO.Customer;

public class CustomerController {
	CustomerRepository customerRepository;
	TouristGroupRepository touristGroupRepository;

	public CustomerController() {
		customerRepository = new CustomerRepository();
		touristGroupRepository = new TouristGroupRepository();
	}
	
	public List<Customer> getAll(){
		List<Customer> customers;
		customers = customerRepository.findAll();
		customers.forEach(customer->{
			customer.setTouristGroups(touristGroupRepository.findAllByCustomerId(customer.getId()));
		});;
		return customers;
	}
	
	public Customer getById(Long id) {
		Customer customer = new Customer();
		customer = customerRepository.findById(id).get();
		customer.setTouristGroups(touristGroupRepository.findAllByCustomerId(customer.getId()));
		return customer;
	}
}
