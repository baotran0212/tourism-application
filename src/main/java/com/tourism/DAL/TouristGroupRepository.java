package com.tourism.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.tourism.DTO.Customer;
import com.tourism.DTO.Hotel;
import com.tourism.DTO.Tour;
import com.tourism.DTO.TouristGroup;

/**
 * TouristGroupDAL
 */
public class TouristGroupRepository implements Repositories<TouristGroup, Long> {
	Connector connector = new MysqlConnector();
	Logger logger = Logger.getLogger(this.getClass().getName());
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

	@Override
	public List<TouristGroup> findAll() {
		ResultSet rs = connector.executeQuery("SELECT * FROM tourist_group");
		List<TouristGroup> touristGroups = new ArrayList<TouristGroup>();
		try {
			while (rs.next() && rs != null) {
				TouristGroup tg = new TouristGroup();
				tg.setId(Long.valueOf(rs.getString("id")));
				tg.setTourId(Long.valueOf(rs.getString("tour_id")));
				tg.setName(rs.getString("name"));
				tg.setDepatureDate(rs.getDate("depature_date"));
				tg.setEndDate(rs.getDate("end_date"));
				tg.setDescription(rs.getString("description"));
				tg.setFoodPrice(Double.valueOf(rs.getDouble("food_price")));
				tg.setTransportPrice(Double.valueOf(rs.getDouble("transport_price")));
				tg.setHotelPrice(Double.valueOf(rs.getDouble("hotel_price")));
				tg.setOtherPrice(Double.valueOf(rs.getDouble("other_price")));
				touristGroups.add(tg);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return touristGroups;
	}

	@Override
	public TouristGroup save(TouristGroup entity) {
		List<TouristGroup> touristGroups = new ArrayList<TouristGroup>();
		touristGroups.add(entity);
		return saveAll(touristGroups).get(0);
	}

	@Override
	public List<TouristGroup> saveAll(Iterable<TouristGroup> entities) {
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
	public Optional<TouristGroup> findById(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		List<TouristGroup> tourisGroups = findAllById(ids);
		return Optional.ofNullable(tourisGroups.isEmpty() ? null : tourisGroups.get(0));
	}

	/*
SELECT * 
FROM tourist_group, role_tour, tourist_group_role_tour_employee
WHERE tourist_group.id = tourist_group_role_tour_employee.tourist_group_id
AND tourist_group_role_tour_employee.role_tour_id = role_tour.id
-- AND tourist_group_role_tour_employee.employee_id = employee_id
AND tourist_group.id = 1
GROUP BY tourist_group_role_tour_employee.role_tour_id
	 */
	@Override
	public List<TouristGroup> findAllById(Iterable<Long> ids) {
		List<TouristGroup> touristGroups = new ArrayList<TouristGroup>();
		StringBuilder query = new StringBuilder("SELECT * FROM tourist_group where ");
		ids.forEach(id -> {
			query.append(" id = " + id + " OR");
		});
		ResultSet rs = this.connector.executeQuery(query.substring(0, query.length() - 2));
		try {
			while (rs != null && rs.next()) {
				TouristGroup tg = new TouristGroup();
				tg.setId(Long.valueOf(rs.getString("id")));
				tg.setTourId(Long.valueOf(rs.getString("tour_id")));
				tg.setName(rs.getString("name"));
				tg.setDepatureDate(rs.getDate("depature_date"));
				tg.setEndDate(rs.getDate("end_date"));
				tg.setDescription(rs.getString("description"));
				tg.setFoodPrice(Double.valueOf(rs.getDouble("food_price")));
				tg.setTransportPrice(Double.valueOf(rs.getDouble("transport_price")));
				tg.setHotelPrice(Double.valueOf(rs.getDouble("hotel_price")));
				tg.setOtherPrice(Double.valueOf(rs.getDouble("other_price")));
				touristGroups.add(tg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return touristGroups;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(TouristGroup entity) {

	}

	@Override
	public void deleteById(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		deleteAllById(ids);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		StringBuilder query = new StringBuilder("DELETE FROM tourist_group WHERE ");
		ids.forEach(id -> {
			query.append(" id = " + id + " OR");
		});
		this.connector.executeUpdate(query.substring(0, query.length() - 2));
		System.out.println(query.substring(0, query.length() - 2));
	}

	@Override
	public void deleteAll(Iterable<? extends TouristGroup> entities) {
		List<Long> ids = new ArrayList<Long>();
		entities.forEach(e -> {
			ids.add(e.getId());
		});
		deleteAllById(ids);
	}

	@Override
	public List<TouristGroup> loadAllRelationship(Iterable<TouristGroup> entities) {
		// load Tour
		Iterator<TouristGroup> itr = entities.iterator();
		TourRepository tourRepos = new TourRepository();
		while (itr.hasNext()) {
			TouristGroup touristGroup = itr.next();
			Optional<Tour> tourOpt = tourRepos.findById(touristGroup.getTourId());
			tourOpt.ifPresent(tour -> {
				touristGroup.setTour(tour);
			});
		}
		return null;
	}

	@Override
	public Optional<TouristGroup> loadRelationshipById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TouristGroup> loadRelationship(TouristGroup entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * n-1 relationship: -Set attribute of foreign key = Table -Save 1-n
	 * ralationship:
	 */
	@Override
	public List<TouristGroup> saveAllRelationship(Iterable<TouristGroup> entities) {
		entities.forEach(e -> {
			// save Tour
			TourRepository tourRepos = new TourRepository();
			e.setTourId(e.getTour().getId());
			tourRepos.save(e.getTour());
			//
		});
		return null;
	}

	@Override
	public TouristGroup saveRelationship(TouristGroup entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		TouristGroupRepository touristGroupRepository = new TouristGroupRepository();
		List<TouristGroup> touristGroups = new ArrayList<TouristGroup>();
		/* TEST SELECT */
//		touristGroupRepository
//				.findAllById(Arrays.asList(new Long[] { Long.valueOf(4), Long.valueOf(6), Long.valueOf(5) }))
//				.forEach(System.out::println);
		/* TEST DELETE */
//		touristGroupRepository.deleteAll(touristGroups);
	}
}
