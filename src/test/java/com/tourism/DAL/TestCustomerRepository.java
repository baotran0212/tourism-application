package com.tourism.DAL;

import junit.framework.TestCase;

public class TestCustomerRepository extends TestCase {
	CustomerRepository customerRepos = new CustomerRepository();
	
	public void testFindAll() {
		customerRepos.findAll().forEach(System.out::println);
	}
}
