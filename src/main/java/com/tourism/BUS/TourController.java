package com.tourism.BUS;

import java.util.ArrayList;
import java.util.List;

import com.tourism.DAL.LocationRepository;
import com.tourism.DAL.TourRepository;
import com.tourism.DAL.TouristGroupRepository;
import com.tourism.DTO.Location;
import com.tourism.DTO.Tour;

public class TourController {
	TourRepository tourRepository = new TourRepository();
	LocationRepository locationRepository = new LocationRepository();
	TouristGroupRepository touristGroupRepository = new TouristGroupRepository();
	public List<Tour> getAll(){
		List<Tour> tours = new ArrayList<Tour>();
		tours = tourRepository.findAll();
		tours.forEach(tour->{
			tour.setLocations(locationRepository.findAllByTourId(tour.getId()));
			tour.setTouristGroups(touristGroupRepository.findAllByTourId(tour.getId()));
		});
		return tours;
	}
}
