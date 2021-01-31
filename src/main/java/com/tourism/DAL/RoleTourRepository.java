package com.tourism.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import com.tourism.DTO.Employee;
import com.tourism.DTO.RoleTour;
import com.tourism.DTO.TouristGroup;

public class RoleTourRepository implements Repositories<RoleTour, Long>{
	Logger logger = Logger.getLogger(this.getClass().getName());
	Connector connector = new MysqlConnector();
	@Override
	public RoleTour save(RoleTour entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleTour> saveAll(Iterable<RoleTour> entities) {
		List<Long> ids = new ArrayList<Long>();
		entities.forEach(e -> {
			Long returnedId = null;
			if (findById(e.getId()).isPresent()) {
				StringBuilder updateQuery = new StringBuilder("UPDATE role_tour SET ");
				updateQuery.append("name = \"" + e.getName() + "\" ");
				updateQuery.append("WHERE id = \"" + e.getId() + "\" ;");
				logger.info(updateQuery.toString());
				this.connector.executeUpdate(updateQuery.toString());
			} else {
				StringBuilder insertQuery = new StringBuilder(
						"INSERT INTO role_tour(`name`) VALUES ");
				insertQuery.append("( \"" + e.getName() + "\" ); ");
				connector.executeUpdate(insertQuery.toString());
				ResultSet returnedResultSet = connector
						.executeQuery("SELECT * FROM role_tour ORDER BY `id` DESC LIMIT 1");
				try {
					while (returnedResultSet != null && returnedResultSet.next()) {
						e.setId(Long.valueOf(returnedResultSet.getString("id")));
					}
				} catch (Exception e1) {
				}
			}
			e.getTouristGroup();
			e.getEmployees().forEach(emp->{
				emp.setTouristGroups(new ArrayList<TouristGroup>());
				emp = new EmployeeRepository().save(emp);
				connector.executeUpdate(
						"INSERT INTO tourist_group_role_tour_employee (`tourist_group_id`, `role_tour_id`, `employee_id`) VALUES (\""
						+ e.getTouristGroup().getId() + "\", \"" +e.getId() + "\", \"" + emp.getIdentityCard() +"\" );");
			});
			ids.add(e.getId());
		});
		return findAllById(ids);
	}

	@Override
	public Optional<RoleTour> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleTour> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleTour> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(RoleTour entity) {
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
	public void deleteAll(Iterable<? extends RoleTour> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RoleTour> loadAllRelationship(Iterable<RoleTour> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RoleTour> loadRelationshipById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RoleTour> loadRelationship(RoleTour entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleTour> saveAllRelationship(Iterable<RoleTour> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleTour saveRelationship(RoleTour entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
