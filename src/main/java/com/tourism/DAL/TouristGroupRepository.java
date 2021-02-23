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

import com.tourism.ProjectProperties;
import com.tourism.DTO.Customer;
import com.tourism.DTO.Employee;
import com.tourism.DTO.Hotel;
import com.tourism.DTO.TourPosition;
import com.tourism.DTO.Tour;
import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.Resources;

/**
 * TouristGroupDAL
 */
public class TouristGroupRepository implements Repositories<TouristGroup, Long> {
	Connector connector = new MysqlConnector();
	TourPositionRepository tourPositionRepos =  new TourPositionRepository();
	CustomerRepository customerRepos = new CustomerRepository();
	HotelRepository hotelRepos = new HotelRepository();
	TourRepository tourRepos = new TourRepository();
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
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
			if (findById(e.getId()).isPresent()) {
				StringBuilder updateQuery = new StringBuilder("UPDATE tourist_group SET ");
				updateQuery.append("tour_id = \"" + e.getTourId() + "\", ");
				updateQuery.append("name = \"" + e.getName() + "\", ");
				updateQuery.append("depature_date = \"" + Resources.simpleDateFormat.format(e.getDepatureDate()) + "\", ");
				updateQuery.append("end_date = \"" + Resources.simpleDateFormat.format(e.getEndDate()) + "\", ");
				updateQuery.append("description = \"" + e.getDescription() + "\", ");
				updateQuery.append("food_price = " + e.getFoodPrice() + ", ");
				updateQuery.append("transport_price = " + e.getTransportPrice() + ", ");
				updateQuery.append("hotel_price = " + e.getHotelPrice() + ", ");
				updateQuery.append("other_price = " + e.getOtherPrice() + ", ");
				updateQuery.append("status = \"" + e.getStatus() + "\" ");
				updateQuery.append("WHERE id = \"" + e.getId() + "\" ;");
				logger.info(updateQuery.toString());
				this.connector.executeUpdate(updateQuery.toString());
			} else {
				StringBuilder insertQuery = new StringBuilder(
						"INSERT INTO tourist_group(`tour_id`, `name`, `depature_date`, `end_date`, `description`, `food_price`, `transport_price`, `hotel_price`, `other_price`, `status`) VALUES ");
				insertQuery.append("( \"" + e.getTourId() + "\", ");
				insertQuery.append("\"" + e.getName() + "\", ");
				insertQuery.append("\"" + Resources.simpleDateFormat.format(e.getDepatureDate()) + "\", ");
				insertQuery.append("\"" + Resources.simpleDateFormat.format(e.getEndDate()) + "\", ");
				insertQuery.append("\"" + e.getDescription() + "\", ");
				insertQuery.append("\"" + e.getFoodPrice() + "\", ");
				insertQuery.append("\"" + e.getTransportPrice() + "\", ");
				insertQuery.append("\"" + e.getHotelPrice() + "\", ");
				insertQuery.append("\"" + e.getOtherPrice() + "\", ");
				insertQuery.append("\"" + e.getStatus() + "\"); ");
				connector.executeUpdate(insertQuery.toString());
				ResultSet returnedResultSet = connector
						.executeQuery("SELECT * FROM tourist_group ORDER BY `id` DESC LIMIT 1");
				try {
					while (returnedResultSet.next()) {
						e.setId(Long.valueOf(returnedResultSet.getLong("id")));
					}
				} catch (Exception e1) {
				}
			}
			ids.add(e.getId());
		});
		return findAllById(ids);
	}
	
	public void saveAllRelationShip(Iterable<TouristGroup> touristGroups) {
		List<String> insertTouristGroupCustomer = new ArrayList<String>();
		StringBuilder deleteTouristGroupCustomer = new StringBuilder(); 
		List<String> insertTouristGroupHotel = new ArrayList<String>();
		StringBuilder deleteTouristGroupHotel = new StringBuilder();
		StringBuilder deleteTourPosition = new StringBuilder();
		touristGroups.forEach(touristGroup->{
//			if(touristGroup.getCustomers() != null && !touristGroup.getCustomers().isEmpty()) {
				deleteTouristGroupCustomer.append("DELETE FROM tourist_group_customer WHERE"
						+" tourist_group_id = \""+ touristGroup.getId() + "\" ");
				touristGroup.getCustomers().forEach(customer->{
					insertTouristGroupCustomer.add(" INSERT INTO tourist_group_customer(tourist_group_id, customer_id) VALUES ( \""+ touristGroup.getId() + "\", \"" + customer.getId() + "\"); ");
					deleteTouristGroupCustomer.append(" AND customer_id <> \"" +customer.getId() + "\" ");
				});
				deleteTouristGroupCustomer.append("; ");
				insertTouristGroupCustomer.forEach(query->connector.executeUpdate(query));
				connector.executeUpdate(deleteTouristGroupCustomer.toString());
//			}
			
//			if(touristGroup.getHotels() != null && !touristGroup.getHotels().isEmpty()) {
				deleteTouristGroupHotel.append("DELETE FROM tourist_group_hotel WHERE"
						+ " tourist_group_id = \"" + touristGroup.getId() +"\" ");
				touristGroup.getHotels().forEach(hotel ->{
					insertTouristGroupHotel.add(" INSERT INTO tourist_group_hotel(tourist_group_id, hotel_id) VALUES ( \""+ touristGroup.getId() +"\", \"" + hotel.getId() +"\" ); ");
					deleteTouristGroupHotel.append(" AND hotel_id <> \"" + hotel.getId() + "\" ");
				});
				deleteTouristGroupHotel.append(";");
				insertTouristGroupHotel.forEach(query->connector.executeUpdate(query));
				connector.executeUpdate(deleteTouristGroupHotel.toString());
//			}
			
//			if(touristGroup.getTourPositions() != null && !touristGroup.getTourPositions().isEmpty()) {
				deleteTourPosition.append("DELETE FROM position_in_tour WHERE "
						+ " tourist_group_id = \"" + touristGroup.getId() + "\" ");
				touristGroup.getTourPositions().forEach(tourPosition ->{
					deleteTourPosition.append(" AND id <> \"" + tourPosition.getId() + "\" ");
				});
				deleteTourPosition.append("; ");
				tourPositionRepos.saveAll(touristGroup.getTourPositions());
				connector.executeUpdate(deleteTourPosition.toString());
//			}
		});
	}
	
	@Override
	public Optional<TouristGroup> findById(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		List<TouristGroup> objs = findAllById(ids);
		return objs.isEmpty() ? Optional.empty() : Optional.ofNullable(objs.get(0));
	}
	
	public Optional<TouristGroup> findByIdNotDeleted(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		List<TouristGroup> objs = findAllByIdNotDeleted(ids);
		return objs.isEmpty() ? Optional.empty() : Optional.ofNullable(objs.get(0));
	}
	
	@Override
	public List<TouristGroup> findAll() {
		ResultSet rsTG = this.connector.executeQuery("SELECT * FROM tourist_group ;");
		return extractResultSet(rsTG);
	}
	
	public List<TouristGroup> findAllNotDeleted() {
		ResultSet rsTG = this.connector.executeQuery("SELECT * FROM tourist_group WHERE status <> \"deleted\" ;");
		return extractResultSet(rsTG);
	}
	
	@Override
	public List<TouristGroup> findAllById(Iterable<Long> ids) {
		if(!ids.iterator().hasNext())
			return new ArrayList<TouristGroup>();
		StringBuilder query = new StringBuilder("SELECT * FROM tourist_group WHERE ");
		ids.forEach(id -> {
			query.append("id=\"" + id +"\" OR ");
		});
		ResultSet rs = connector.executeQuery(query.substring(0, query.lastIndexOf("OR")));
		return extractResultSet(rs);
	}
	
	public List<TouristGroup> findAllByIdNotDeleted(Iterable<Long> ids) {
		if(!ids.iterator().hasNext())
			return new ArrayList<TouristGroup>();
		StringBuilder query = new StringBuilder("SELECT * FROM tourist_group WHERE status <> \"deleted\" AND ");
		ids.forEach(id -> {
			query.append("id=\"" + id +"\" OR ");
		});
		ResultSet rs = connector.executeQuery(query.substring(0, query.lastIndexOf("OR")));
		return extractResultSet(rs);
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
	}

	@Override
	public void deleteAll(Iterable<? extends TouristGroup> entities) {
		List<Long> ids = new ArrayList<Long>();
		entities.forEach(e -> {
			ids.add(e.getId());
		});
		deleteAllById(ids);
	}
	
	public List<TouristGroup> extractResultSet(ResultSet rs){
		List<TouristGroup> touristGroups = new ArrayList<TouristGroup>();
		try {
			while (rs !=null && rs.next()) {
				TouristGroup tg = new TouristGroup();
				tg.setId(Long.valueOf(rs.getLong("id")));
				tg.setTourId(Long.valueOf(rs.getLong("tour_id")));
				tg.setName(rs.getString("name"));
				tg.setDepatureDate(rs.getDate("depature_date"));
				tg.setEndDate(rs.getDate("end_date"));
				tg.setDescription(rs.getString("description"));
				tg.setFoodPrice(Double.valueOf(rs.getDouble("food_price")));
				tg.setTransportPrice(Double.valueOf(rs.getDouble("transport_price")));
				tg.setHotelPrice(Double.valueOf(rs.getDouble("hotel_price")));
				tg.setOtherPrice(Double.valueOf(rs.getDouble("other_price")));
				tg.setStatus(rs.getString("status"));
				touristGroups.add(tg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return touristGroups;
	}
	
	public List<TouristGroup> findAllByCustomerId(Long id){
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		return findAllByCustomerIds(ids);
	}
	
	public List<TouristGroup> findAllByCustomerIds(List<Long> ids){
		List<Long> idTGs = new ArrayList<Long>();
		StringBuilder query = new StringBuilder("SELECT temp.tourist_group_id as id FROM tourist_group_customer temp WHERE ");
		ids.forEach(cusId->{
			query.append(" temp.customer_id =  " + cusId + " OR ");
		});
		ResultSet rs = this.connector.executeQuery(query.substring(0, query.lastIndexOf("OR")));
		try {
			while(rs != null && rs.next()) {
				idTGs.add(Long.valueOf(rs.getLong("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findAllById(ids);
	}
	
	public List<TouristGroup> findAllByTourId(Long id){
		StringBuilder query = new StringBuilder(
				"SELECT * FROM tourist_group tg WHERE tg.tour_id = \"");
		query.append(id + "\" ;");
		ResultSet rs = connector.executeQuery(query.toString());
		return extractResultSet(rs);
	}
	
	public List<TouristGroup> findAllByHotelId(Long id){
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		return findAllByHotelIds(ids);
	}
	public List<TouristGroup> findAllByHotelIds(Iterable<Long> ids){
		List<Long> idTGs = new ArrayList<Long>();
		StringBuilder query = new StringBuilder("SELECT temp.tourist_group_id as id FROM tourist_group_hotel temp WHERE ");
		ids.forEach(hotelId ->{
			query.append(" temp.hotel_id = \"" + hotelId + "\" OR ");
		});
		ResultSet rs = this.connector.executeQuery(query.substring(0, query.lastIndexOf("OR")));
		try {
			while(rs != null && rs.next()) {
				idTGs.add(Long.valueOf(rs.getLong("id")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return findAllById(ids);
	}
	public List<TouristGroup> findAllLike(TouristGroup TG){
		StringBuilder query = new StringBuilder("SELECT * FROM tourist_group WHERE status <> \"deleted\" AND ");
		if( TG.getId() !=null )
			query.append(" id = \""+TG.getId() + "\" OR ");
		if( !TG.getName().equals(""))
			query.append(" name LIKE \"%"+ TG.getName() +"%\" OR " );
		if( TG.getDepatureDate() != null)
			query.append("depature_date = \"" + Resources.simpleDateFormat.format(TG.getDepatureDate()) + "\" OR ");
		if( TG.getEndDate() !=null)
			query.append("end_date = \"" + Resources.simpleDateFormat.format(TG.getEndDate()) + "\" OR ");
		if( TG.getTourId() != null)
			query.append("tour_id = \"" + TG.getTourId() + "\" OR ");
		if( TG.getStatus() != null)
			query.append("status = \"" + TG.getStatus() + "\" OR");
		System.out.println(query.toString());
		return extractResultSet(connector.executeQuery(
				query.substring(0,
						query.lastIndexOf("OR") != -1 ? query.lastIndexOf("OR") : query.lastIndexOf("AND"))));
	}
	public static void main(String[] args) {

	}

	@Override
	public boolean testPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return false;
	}
}
