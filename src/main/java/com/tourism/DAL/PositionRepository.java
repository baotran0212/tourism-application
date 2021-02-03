package com.tourism.DAL;

import java.util.List;
import java.util.Optional;

import com.tourism.DTO.Position;

public class PositionRepository implements Repositories<Position, Long> {

	@Override
	public Position save(Position entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Position> saveAll(Iterable<Position> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Position> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Position> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Position> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Position entity) {
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
	public void deleteAll(Iterable<? extends Position> entities) {
		// TODO Auto-generated method stub
		
	}

}
