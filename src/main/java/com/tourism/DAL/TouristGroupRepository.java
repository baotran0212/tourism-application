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
import com.tourism.DTO.Employee;
import com.tourism.DTO.Hotel;
import com.tourism.DTO.TourPosition;
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
	public TouristGroup save(TouristGroup entity) {
		List<TouristGroup> touristGroups = new ArrayList<TouristGroup>();
		touristGroups.add(entity);
		return saveAll(touristGroups).get(0);
	}

	@Override
	public List<TouristGroup> saveAll(Iterable<TouristGroup> entities) {
		List<Long> ids = new ArrayList<Long>();
		entities.forEach(e -> {
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
				updateQuery.append("other_price = " + e.getOtherPrice() + " ");
				updateQuery.append("WHERE id = \"" + e.getId() + "\" ;");
				logger.info(updateQuery.toString());
				this.connector.executeUpdate(updateQuery.toString());
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
				ResultSet returnedResultSet = connector
						.executeQuery("SELECT * FROM tourist_group ORDER BY `id` DESC LIMIT 1");
				try {
					while (returnedResultSet != null && returnedResultSet.next()) {
						e.setId(Long.valueOf(returnedResultSet.getLong("id")));
					}
				} catch (Exception e1) {
				}
			}
			// Save tour positions
			e.getTourPositions().forEach(tourPosition -> {
				tourPosition.setTouristGroup(new TouristGroup());
				tourPosition.setTouristGroupId(e.getId());
				tourPosition = new TourPositionRepository().save(tourPosition);
			});
			// Save tour
			e.getTour().setTouristGroups(new ArrayList<TouristGroup>());
			e.setTour(new TourRepository().save(e.getTour()));
			e.setTourId(e.getTour().getId());
			// Save customers
			e.getCustomers().forEach(cus -> {
				cus.setTouristGroups(new ArrayList<TouristGroup>());
				cus = new CustomerRepository().save(cus);
				connector.executeUpdate(
						"INSERT INTO tourist_group_customer (`tourist_group_id`, `customer_id`) VALUES (\"" 
				+ e.getId()+ "\", \"" + cus.getId() + "\" );");
			});
			// save hotels
			e.getHotels().forEach(hotel -> {
				hotel.setTouristGroups(new ArrayList<TouristGroup>());
				hotel = new HotelRepository().save(hotel);
				connector.executeUpdate("INSERT INTO tourist_group_hotel (`tourist_group_id`, `hotel`) VALUES (\""
						+ e.getId() + "\", \"" + hotel.getId() + "\" );");
			});
			ids.add(e.getId());
		});
		return findAllById(ids);
	}

	@Override
	public Optional<TouristGroup> findById(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		return Optional.ofNullable(findAllById(ids).get(0));
	}

	@Override
	public List<TouristGroup> findAll() {
		List<TouristGroup> touristGroups = new ArrayList<TouristGroup>();
		ResultSet rsTG = this.connector.executeQuery("SELECT * FROM tourist_group ;");
		try {
			while (rsTG.next()) {
				TouristGroup tg = new TouristGroup();
				tg.setId(Long.valueOf(rsTG.getLong("id")));
				tg.setName(rsTG.getString("name"));
				tg.setDepatureDate(rsTG.getDate("depature_date"));
				tg.setEndDate(rsTG.getDate("end_date"));
				tg.setDescription(rsTG.getString("description"));
				tg.setFoodPrice(Double.valueOf(rsTG.getDouble("food_price")));
				tg.setTransportPrice(Double.valueOf(rsTG.getDouble("transport_price")));
				tg.setHotelPrice(Double.valueOf(rsTG.getDouble("hotel_price")));
				tg.setOtherPrice(Double.valueOf(rsTG.getDouble("other_price")));
				// Set tour positions
				if (tg.getTourPositions() == null) {
					ResultSet rsTourPosition = this.connector.executeQuery(
							"SELECT tp.id FROM position_in_tour tp WHERE tp.tourist_group_id = " + tg.getId());
					List<Long> idTourPositions = new ArrayList<Long>();
					while (rsTourPosition != null && rsTourPosition.next()) {
						idTourPositions.add(Long.valueOf(rsTourPosition.getLong("id")));
					}
					tg.setTourPositions(new TourPositionRepository().findAllById(idTourPositions));
				}
				// Set customers
				if (tg.getCustomers() == null) {
					ResultSet rsCustomers = this.connector.executeQuery(
							"SELECT c.id FROM customer c, tourist_group_customer temp WHERE temp.customer_id=c.id AND temp.tourist_group_id = "
									+ tg.getId() + " GROUP BY c.id");
					List<Long> idCustomers = new ArrayList<Long>();
					while (rsCustomers.next() && rsCustomers != null) {
						idCustomers.add(Long.valueOf(rsCustomers.getLong("id")));
					}
					tg.setCustomers(new CustomerRepository().findAllById(idCustomers));
				}
				// Set hotels
				if (tg.getHotels() == null) {
					ResultSet rsHotel = this.connector.executeQuery(
							"SELECT h.id FROM hotel h, tourist_group_hotel temp WHERE temp.hotel_id=h.id AND temp.tourist_group_id="
									+ tg.getId() + " GROUP BY h.id");
					List<Long> idHotels = new ArrayList<Long>();
					while (rsHotel.next()) {
						idHotels.add(Long.valueOf(rsHotel.getLong("id")));
					}
					tg.setHotels(new HotelRepository().findAllById(idHotels));
				}
				// Set tour
				if (tg.getTour() == null) {
					tg.setTour(new TourRepository().findById(tg.getTourId()).orElse(new Tour()));
				}
				touristGroups.add(tg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return touristGroups;
	}

	@Override
	public List<TouristGroup> findAllById(Iterable<Long> ids) {
		List<TouristGroup> touristGroups = new ArrayList<TouristGroup>();
		ids.forEach(id -> {
			ResultSet rsTG = this.connector.executeQuery(
					"SELECT * FROM tourist_group WHERE id = \"" + id + "\" ;");
			try {
				while (rsTG !=null && rsTG.next()) {
					TouristGroup tg = new TouristGroup();
					tg.setId(Long.valueOf(rsTG.getLong("id")));
					tg.setName(rsTG.getString("name"));
					tg.setDepatureDate(rsTG.getDate("depature_date"));
					tg.setEndDate(rsTG.getDate("end_date"));
					tg.setDescription(rsTG.getString("description"));
					tg.setFoodPrice(Double.valueOf(rsTG.getDouble("food_price")));
					tg.setTransportPrice(Double.valueOf(rsTG.getDouble("transport_price")));
					tg.setHotelPrice(Double.valueOf(rsTG.getDouble("hotel_price")));
					tg.setOtherPrice(Double.valueOf(rsTG.getDouble("other_price")));
					// Set tour positions
					if(tg.getTourPositions() == null) {
						ResultSet rsTourPosition = this.connector.executeQuery(
								"SELECT tp.id FROM position_in_tour tp WHERE tp.tourist_group_id = " + tg.getId());
						List<Long> idTourPositions = new ArrayList<Long>();
						while (rsTourPosition != null && rsTourPosition.next()) {
							idTourPositions.add(Long.valueOf(rsTourPosition.getLong("id")));
						}
						tg.setTourPositions(new TourPositionRepository().findAllById(idTourPositions));	
					}
					// Set customers
					if(tg.getCustomers() == null ) {
						ResultSet rsCustomers = this.connector.executeQuery(
								"SELECT c.id FROM customer c, tourist_group_customer temp WHERE temp.customer_id=c.id AND temp.tourist_group_id = "
										+ tg.getId() + " GROUP BY c.id");
						List<Long> idCustomers = new ArrayList<Long>();
						while (rsCustomers.next() && rsCustomers != null) {
							idCustomers.add(Long.valueOf(rsCustomers.getLong("id")));
						}
						tg.setCustomers(new CustomerRepository().findAllById(idCustomers));
					}
					// Set hotels
					if (tg.getHotels() == null) {
						ResultSet rsHotel = this.connector.executeQuery(
								"SELECT h.id FROM hotel h, tourist_group_hotel temp WHERE temp.hotel_id=h.id AND temp.tourist_group_id="
										+ tg.getId() + " GROUP BY h.id");
						List<Long> idHotels = new ArrayList<Long>();
						while (rsHotel.next()) {
							idHotels.add(Long.valueOf(rsHotel.getLong("id")));
						}
						tg.setHotels(new HotelRepository().findAllById(idHotels));
					}
					// Set tour
					if (tg.getTour() == null) {
						tg.setTour(new TourRepository().findById(tg.getTourId()).orElse(new Tour()));
					}
					touristGroups.add(tg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
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

	public static void main(String[] args) {

	}
}
