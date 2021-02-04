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
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(entity);
		return saveAll(emps).get(0);
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
				updateQuery.append("address2 = \"" + e.getAddress2() + "\", ");
				updateQuery.append("address3 = \"" + e.getAddress3() + "\", ");
				updateQuery.append("gender = \"" + e.getGender() + "\", ");
				updateQuery.append("phone_number = \"" + e.getPhoneNumber() + "\" ");
				updateQuery.append("status = \"" + e.getStatus() + "\" ");
				updateQuery.append("WHERE id = \"" + e.getId() + "\" ;");
				this.connector.executeUpdate(updateQuery.toString());
			} else {
				StringBuilder insertQuery = new StringBuilder(
						"INSERT INTO employee(`name`, `identity_card`, `address1`, `address2`, `address3`, `gender`, `phone_number`, `status`) VALUES ");
				insertQuery.append("( \"" + e.getName() + "\", ");
				insertQuery.append("\"" + e.getIdentityCard() + "\", ");
				insertQuery.append("\"" + e.getAddress1() + "\", ");
				insertQuery.append("\"" + e.getAddress2() + "\", ");
				insertQuery.append("\"" + e.getAddress3() + "\", ");
				insertQuery.append("\"" + e.getGender() + "\", ");
				insertQuery.append("\"" + e.getPhoneNumber() + "\" ");
				insertQuery.append("\"" + e.getStatus() + "\" ); ");
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
			// Save position_in_tour
			e.getTourPositions().forEach(tourPosition -> {
				tourPosition.setEmployeeId(e.getId());
				tourPosition.setEmployee(new Employee());
				new TourPositionRepository().save(tourPosition);
			});
			ids.add(e.getId());
		});
		return findAllById(ids);
	}

	@Override
	public Optional<Employee> findById(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		return Optional.ofNullable(findAllById(ids).get(0));
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = new ArrayList<Employee>();
		ResultSet rsEmp = this.connector.executeQuery("SELECT * FROM employee ;");
		try {
			while (rsEmp.next()) {
				Employee emp = new Employee();
				emp.setId(Long.valueOf(rsEmp.getLong("id")));
				emp.setName(rsEmp.getString("name"));
				emp.setIdentityCard(rsEmp.getString("identity_card"));
				emp.setAddress1(rsEmp.getString("address1"));
				emp.setAddress2(rsEmp.getString("address2"));
				emp.setAddress3(rsEmp.getString("address3"));
				emp.setGender(rsEmp.getString("gender"));
				emp.setPhoneNumber(rsEmp.getString("phone_number"));
				emp.setStatus(rsEmp.getString("status"));
				// Set position_in_tour
				if (emp.getTourPositions() == null) {
					ResultSet rsTourPosition = this.connector.executeQuery(
							"SELECT temp.position_id as id FROM position_in_tour temp WHERE temp.employee_id="
									+ emp.getId() + " GROUP BY temp.position_id");
					List<Long> idTourPositions = new ArrayList<Long>();
					while (rsTourPosition != null && rsTourPosition.next()) {
						idTourPositions.add(Long.valueOf(rsTourPosition.getString("id")));
					}
					emp.setTourPositions(new TourPositionRepository().findAllById(idTourPositions));
				}
				employees.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public List<Employee> findAllById(Iterable<Long> ids) {
		List<Employee> employees = new ArrayList<Employee>();
		ids.forEach(id -> {
			ResultSet rsEmp = this.connector.executeQuery("SELECT * FROM employee WHERE id = \"" + id + "\" ;");
			try {
				while (rsEmp.next()) {
					Employee emp = new Employee();
					emp.setId(Long.valueOf(rsEmp.getLong("id")));
					emp.setName(rsEmp.getString("name"));
					emp.setIdentityCard(rsEmp.getString("identity_card"));
					emp.setAddress1(rsEmp.getString("address1"));
					emp.setAddress2(rsEmp.getString("address2"));
					emp.setAddress3(rsEmp.getString("address3"));
					emp.setGender(rsEmp.getString("gender"));
					emp.setPhoneNumber(rsEmp.getString("phone_number"));
					emp.setStatus(rsEmp.getString("status"));
					// Set position_in_tour
					if (emp.getTourPositions() == null) {
						ResultSet rsTourPosition = this.connector.executeQuery(
								"SELECT temp.position_id as id FROM position_in_tour temp WHERE temp.employee_id="
										+ emp.getId() + " GROUP BY temp.position_id");
						List<Long> idTourPositions = new ArrayList<Long>();
						while (rsTourPosition != null && rsTourPosition.next()) {
							idTourPositions.add(Long.valueOf(rsTourPosition.getString("id")));
						}
						emp.setTourPositions(new TourPositionRepository().findAllById(idTourPositions));
					}
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

}
