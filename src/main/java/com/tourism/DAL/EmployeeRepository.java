package com.tourism.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.tourism.DTO.Employee;
import com.tourism.DTO.TouristGroup;

/**
 * EmployeeRepository
 */
public class EmployeeRepository implements Repositories<Employee, Long> {
	private Connector connector = new MysqlConnector();
	Logger logger = Logger.getLogger(this.getClass().getName());
  @Override
  public Employee save(Employee entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
	public List<Employee> saveAll(Iterable<Employee> entities) {
		List<Long> ids = new ArrayList<Long>();
		entities.forEach(e -> {
			if (findById(e.getId()).isPresent()) {
				StringBuilder updateQuery = new StringBuilder("UPDATE employee SET ");
				updateQuery.append("name = \"" + e.getName() + "\", ");
				updateQuery.append("indentity_card = \"" + e.getIdentityCard() + "\", ");
				updateQuery.append("address1 = \"" + e.getAddress1() + "\", ");
				updateQuery.append("address2 = \"" +  e.getAddress2() + "\", ");
				updateQuery.append("address3 = \"" + e.getAddress3() + "\", ");
				updateQuery.append("gender = \"" + e.getGender() + "\", ");
				updateQuery.append("phone_number = \"" + e.getPhoneNumber() + "\" ");
				updateQuery.append("WHERE id = \"" + e.getId() + "\" ;");
				this.connector.executeUpdate(updateQuery.toString());
			} else {
				StringBuilder insertQuery = new StringBuilder(
						"INSERT INTO employee(`name`, `identity_card`, `address1`, `address2`, `address3`, `gender`, `phone_number`) VALUES ");
				insertQuery.append("( \"" + e.getName() + "\", ");
				insertQuery.append("\"" + e.getIdentityCard() + "\", ");
				insertQuery.append("\"" + e.getAddress1() + "\", ");
				insertQuery.append("\"" + e.getAddress2() + "\", ");
				insertQuery.append("\"" + e.getAddress3() + "\", ");
				insertQuery.append("\"" + e.getGender() + "\", ");
				insertQuery.append("\"" + e.getPhoneNumber() + "\" ); ");
				connector.executeUpdate(insertQuery.toString());
				ResultSet returnedResultSet = connector
						.executeQuery("SELECT * FROM employee ORDER BY `id` DESC LIMIT 1");
				try {
					while (returnedResultSet != null && returnedResultSet.next()) {
						e.setId(Long.valueOf(returnedResultSet.getLong("id")));
					}
				} catch (Exception e1) {
				}
			}
			e.getTouristGroups().forEach(tg ->{
				tg = new TouristGroupRepository().save(tg);
				TouristGroup tgTemp = tg;
				tg.getRoleTours().forEach(roleTour->{
					roleTour.setEmployees(new ArrayList<Employee>());
					roleTour = new RoleTourRepository().save(roleTour);
					connector.executeUpdate(
							"INSERT INTO tourist_group_role_tour_employee (`tourist_group_id`, `role_tour_id`, `employee_id`) VALUES (\""
							+tgTemp.getId() +"\", \"" + roleTour.getId() +"\", \""+e.getId() + "\" );");
				});
			});
			ids.add(e.getId());
		});
		return findAllById(ids);
	}

  @Override
  public Optional<Employee> findById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Employee> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Employee> findAllById(Iterable<Long> ids) {
	List<Employee> employees = new ArrayList<Employee>();
    ids.forEach(id->{
    	ResultSet rsEmp = this.connector.executeQuery("SELECT * FROM employee WHERE id = \"" +id+ "\" ;");
    	try {
    		while(rsEmp.next()) {
    			Employee emp = new Employee();
    			emp.setId(Long.valueOf(rsEmp.getLong("id")));
    			emp.setName(rsEmp.getString("name"));
    			emp.setIdentityCard(rsEmp.getString("identity_card"));
    			emp.setAddress1(rsEmp.getString("address1"));
    			emp.setAddress2(rsEmp.getString("address2"));
    			emp.setAddress3(rsEmp.getString("address3"));
    			emp.setGender(rsEmp.getString("gender"));
    			emp.setPhoneNumber(rsEmp.getString("phone_number"));
    			//Set tourist group for employee
    			ResultSet rsTouristGroups = this.connector.executeQuery(
    					"SELECT tg.id FROM tourist_group tg, tourist_group_role_tour_employee temp WHERE tg.id=temp.tourist_group_id AND temp.employee_id="
    					+emp.getId() + " GROUP BY tg.id");
    			List<Long> idTouristGroups = new ArrayList<Long>();
    			while(rsTouristGroups.next() && rsTouristGroups!=null) {
    				idTouristGroups.add(Long.valueOf(rsTouristGroups.getLong("id")));
    			}
    			emp.setTouristGroups(new TouristGroupRepository().findAllById(idTouristGroups));
    			employees.add(emp);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    });
    return employees;
  }

  @Override
  public long count() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void delete(Employee entity) {
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
  public void deleteAll(Iterable<? extends Employee> entities) {
    // TODO Auto-generated method stub

  }

@Override
public List<Employee> loadAllRelationship(Iterable<Employee> entities) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Optional<Employee> loadRelationshipById(Long id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Optional<Employee> loadRelationship(Employee entity) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Employee> saveAllRelationship(Iterable<Employee> entities) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Employee saveRelationship(Employee entity) {
	// TODO Auto-generated method stub
	return null;
}

}
