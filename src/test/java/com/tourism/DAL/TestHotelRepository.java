package com.tourism.DAL;

import java.util.List;
import java.util.logging.Logger;

import junit.framework.TestCase;

public class TestHotelRepository extends TestCase {
	HotelRepository hotelRepository;
	Logger logger = Logger.getLogger(getClass().getName());
	
	public TestHotelRepository() {
		hotelRepository = new HotelRepository();
	}
	
	public void testFindAllByTouristGroupId() {
		Long touristGroupId = Long.valueOf(1);
		logger.info(hotelRepository.findAllByTouristGroupId(touristGroupId).toString());
	}
}
