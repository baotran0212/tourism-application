package com.tourism.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.tourism.DTO.Position;
import com.tourism.DTO.TouristGroup;

public class PositionRepository implements Repositories<Position, Long> {
	Connector connector = new MysqlConnector();
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	@Override
	public Position save(Position entity) {
		List<Position> positions = new ArrayList<Position>();
		positions.add(entity);
		return saveAll(positions).get(0);
	}

	@Override
	public List<Position> saveAll(Iterable<Position> entities) {
//		List<Long> ids = new ArrayList<Long>();
//		entities.forEach(e->{
//			if(findById(e.getId()).isPresent()) {
//				 
//			}
//		});
		return null;
	}

	@Override
	public Optional<Position> findById(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		List<Position> objs = findAllById(ids);
		return objs.isEmpty() ? Optional.empty() : Optional.ofNullable(objs.get(0));
	}

	@Override
	public List<Position> findAll() {
		List<Position> positions = new ArrayList<Position>();
			ResultSet rsPosition = this.connector.executeQuery("SELECT * FROM position ;");
			try {
				while(rsPosition != null && rsPosition.next()) {
					Position position = new Position();
					position.setId(Long.valueOf(rsPosition.getLong("id")));
					position.setName(rsPosition.getString("name"));
					positions.add(position);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		return positions;
	}

	@Override
	public List<Position> findAllById(Iterable<Long> ids) {
		List<Position> positions = new ArrayList<Position>();
		ids.forEach(id -> {
			ResultSet rsPosition = this.connector.executeQuery("SELECT * FROM position WHERE id = \"" + id + "\" ;");
			try {
				while(rsPosition != null && rsPosition.next()) {
					Position position = new Position();
					position.setId(Long.valueOf(rsPosition.getLong("id")));
					position.setName(rsPosition.getString("name"));
					positions.add(position);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		});
		return positions;
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
