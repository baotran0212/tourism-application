package com.tourism.BUS;

import java.util.List;

import com.tourism.DAL.HotelRepository;
import com.tourism.DAL.TouristGroupRepository;
import com.tourism.DTO.Hotel;

public class HotelController {
	HotelRepository hotelRepository;
	TouristGroupRepository touristGroupRepository;
	
	public HotelController() {
		hotelRepository = new HotelRepository();
		touristGroupRepository = new TouristGroupRepository();
	}
	
	public List<Hotel> getAll(){
		List<Hotel> hotels;
		hotels = hotelRepository.findAll();
		hotels.forEach(hotel -> {
			hotel.setTouristGroups(touristGroupRepository.findAllByHotelId(hotel.getId()));
		});
		return hotels;
	}
	
	public Hotel getById(Long id) {
		Hotel hotel = new Hotel();
		hotel = hotelRepository.findById(id).get();
		hotel.setTouristGroups(touristGroupRepository.findAllByHotelId(hotel.getId()));
		return hotel;
	}
}
