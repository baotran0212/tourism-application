package com.tourism.DAL;

import java.util.List;
import java.util.Optional;

import com.tourism.DTO.Location;

public class LocationRepository implements Repositories<Location, Long> {

	@Override
	public Location save(Location entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> saveAll(Iterable<Location> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Location> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Location entity) {
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
	public void deleteAll(Iterable<? extends Location> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Location> loadAllRelationship(Iterable<Location> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Location> loadRelationshipById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Location> loadRelationship(Location entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> saveAllRelationship(Iterable<Location> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location saveRelationship(Location entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
