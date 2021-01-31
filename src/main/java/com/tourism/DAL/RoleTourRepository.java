package com.tourism.DAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tourism.DTO.RoleTour;

public class RoleTourRepository implements Repositories<RoleTour, Long>{

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
			e.setTourId(e.getTour().getId());
			if (findById(e.getId()).isPresent()) {
				StringBuilder updateQuery = new StringBuilder("UPDATE tourist_group SET ");
				updateQuery.append("tour_id = \"" + e.getTourId() + "\", ");
				updateQuery.append("name = \"" + e.getName() + "\", ");
				updateQuery.append("depature_date = \"" + formatter.format(e.getDepatureDate()) + "\", ");
				updateQuery.append("end_date = \"" + formatter.format(e.getEndDate()) + "\", ");
				updateQuery.append("description = \"" + e.getDescription() + "\", ");
				updateQuery.append("food_price = " + e.getFoodPrice() + ", ");
				updateQuery.append("transport_price = " + e.getTransportPrice() + ", ");
				updateQuery.append("hotel_price = " + e.getHotelPrice() + ", ");
				updateQuery.append("other_price = " + e.getOtherPrice() + ", ");
				updateQuery.append("WHERE id = \"" + e.getId() + "\" ;");
				logger.info(updateQuery.toString());
				this.connector.executeUpdate(updateQuery.toString());
				new TourRepository().save(e.getTour());
				new CustomerRepository().saveAll(e.getCustomers()).forEach(cus -> {
					connector.executeUpdate(
							"INSERT INTO tourist_group_customer (`tourist_group_id`, `customer_id`) VALUES (\""
									+ e.getId() + "\", \"" + cus.getId() + "\" );");
				});
				new RoleTourRepository().saveAll(e.getRoleTours()).forEach(roleTour -> {
					new EmployeeRepository().saveAll(roleTour.getEmployees()).forEach(emp ->{
						connector.executeUpdate(
								"INSERT INTO tourist_group_role_tour_employee (`tourist_group_id`, `role_tour_id`, `employee_id`) VALUES (\""
										+ e.getId() + "\", \"" + roleTour.getId() +"\", \"" + emp.getId() +"\" );");
					});
				});
				new HotelRepository().saveAll(e.getHotels()).forEach(hotel -> {
					connector.executeUpdate("INSERT INTO tourist_group_hotel (`tourist_group_id`, `hotel`) VALUES (\""
							+ e.getId() + "\", \"" + hotel.getId() + "\" );");
				});
				returnedId = e.getId();
			} else {
				StringBuilder insertQuery = new StringBuilder(
						"INSERT INTO tourist_group(`tour_id`, `name`, `depature_date`, `end_date`, `description`, `food_price`, `transport_price`, `hotel_price`, `other_price`, `tour_id`) VALUES ");
				insertQuery.append("( \"" + e.getTourId() + "\", ");
				insertQuery.append("\"" + e.getName() + "\", ");
				insertQuery.append("\"" + formatter.format(e.getDepatureDate()) + "\", ");
				insertQuery.append("\"" + formatter.format(e.getEndDate()) + "\", ");
				insertQuery.append("\"" + e.getDescription() + "\", ");
				insertQuery.append("\"" + e.getFoodPrice() + "\", ");
				insertQuery.append("\"" + e.getTransportPrice() + "\", ");
				insertQuery.append("\"" + e.getHotelPrice() + "\", ");
				insertQuery.append("\"" + e.getOtherPrice() + "\", ");
				insertQuery.append("\"" + e.getTour().getId() + "\" ); ");
				connector.executeUpdate(insertQuery.toString());
				new TourRepository().save(e.getTour());
				new CustomerRepository().saveAll(e.getCustomers()).forEach(cus -> {
					connector.executeUpdate(
							"INSERT INTO tourist_group_customer (`tourist_group_id`, `customer_id`) VALUES (\""
									+ e.getId() + "\", \"" + cus.getId() + "\" );");
				});
				new RoleTourRepository().saveAll(e.getRoleTours()).forEach(roleTour -> {
					new EmployeeRepository().saveAll(roleTour.getEmployees()).forEach(emp -> {
						connector.executeUpdate(
								"INSERT INTO tourist_group_role_tour_employee (`tourist_group_id`, `role_tour_id`, `employee_id`) VALUES (\""
										+ e.getId() + "\", \"" + roleTour.getId() + "\", \"" + emp.getId() + "\" );");
					});
				});
				new HotelRepository().saveAll(e.getHotels()).forEach(hotel -> {
					connector.executeUpdate("INSERT INTO tourist_group_hotel (`tourist_group_id`, `hotel`) VALUES (\""
							+ e.getId() + "\", \"" + hotel.getId() + "\" );");
				});
				ResultSet returnedResultSet = connector
						.executeQuery("SELECT * FROM tourist_group ORDER BY `id` DESC LIMIT 1");
				try {
					while (returnedResultSet != null && returnedResultSet.next()) {
						returnedId = Long.valueOf(returnedResultSet.getString("id"));
						logger.info(returnedId.toString());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			ids.add(returnedId);
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
