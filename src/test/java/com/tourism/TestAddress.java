package com.tourism;

import com.tourism.service.JsonAddress;

import junit.framework.TestCase;

public class TestAddress extends TestCase {
	public TestAddress(String name) {
		super(name);
	}
	
	public void testGetAddressLv2() {
		JsonAddress addressService = new JsonAddress();
		assertNotNull(addressService);
		addressService.getAddressLv2("Thành phố Hà Nội").forEach(System.out::println);
	}
}
