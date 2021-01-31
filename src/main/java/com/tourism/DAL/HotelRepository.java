package com.tourism.DAL;

import java.util.List;
import java.util.Optional;

import com.tourism.DTO.Hotel;

public class HotelRepository implements Repositories<Hotel, Long>{

	@Override
	public Hotel save(Hotel entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> saveAll(Iterable<Hotel> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Hotel> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Hotel entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Hotel> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Hotel> loadAllRelationship(Iterable<Hotel> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Hotel> loadRelationshipById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Hotel> loadRelationship(Hotel entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> saveAllRelationship(Iterable<Hotel> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hotel saveRelationship(Hotel entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
