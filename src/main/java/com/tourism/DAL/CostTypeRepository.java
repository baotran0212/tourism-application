package com.tourism.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tourism.DTO.CostType;

public class CostTypeRepository implements Repositories<CostType, Long> {
	Connector connector; 
	public CostTypeRepository() {
		connector = new MysqlConnector();
	}
	@Override
	public CostType save(CostType entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CostType> saveAll(Iterable<CostType> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CostType> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CostType> findAll() {
		ResultSet rs = connector.executeQuery("SELECT * FROM cost_type");
		return extractResultSet(rs);
	}

	@Override
	public List<CostType> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(CostType entity) {
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
	public void deleteAll(Iterable<? extends CostType> entities) {
		// TODO Auto-generated method stub
		
	}
	
	private List<CostType> extractResultSet(ResultSet rs){
		List<CostType> costTypes = new ArrayList<CostType>();
		try {
			while (rs.next()){
				costTypes.add(new CostType(
						rs.getLong("id"),
						rs.getString("name"),
						rs.getString("description")));
			}
		} catch (Exception e) {
		}
		return costTypes;
	}
}
