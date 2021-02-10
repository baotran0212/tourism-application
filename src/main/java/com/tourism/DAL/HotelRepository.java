package com.tourism.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tourism.DTO.Hotel;
import com.tourism.DTO.TouristGroup;

public class HotelRepository implements Repositories<Hotel, Long>{
	Connector connector = new MysqlConnector();
	@Override
	public Hotel save(Hotel entity) {
		List<Hotel> hotels = new ArrayList<Hotel>();
		hotels.add(entity);
		return saveAll(hotels).get(0);
	}

	@Override
	public List<Hotel> saveAll(Iterable<Hotel> entities) {
		List<Long> ids = new ArrayList<Long>();
		entities.forEach(e -> {
			if (findById(e.getId()).isPresent()) {
				StringBuilder updateQuery = new StringBuilder("UPDATE hotel SET ");
				updateQuery.append("name = \"" + e.getName() + "\", ");
				updateQuery.append("price = \"" +e.getPrice() + "\", ");
				updateQuery.append("address1 = \""+e.getAddress1() + "\", ");
				updateQuery.append("address2 = \"" +e.getAddress2() + "\", ");
				updateQuery.append("address3 = \"" + e.getAddress3() + "\", ");
				updateQuery.append("street = \"" + e.getStreet() + "\", ");
				updateQuery.append("WHERE id = \"" + e.getId() + "\" ;");
				this.connector.executeUpdate(updateQuery.toString());
			} else {
				StringBuilder insertQuery = new StringBuilder(
						"INSERT INTO hotel(`name`, `price`, `address1`, `address2`, `address3`, `street`) VALUES ");
				insertQuery.append("( \"" + e.getName() + "\", ");
				insertQuery.append("\"" + e.getPrice() + "\", ");
				insertQuery.append("\"" + e.getAddress1() + "\", ");
				insertQuery.append("\"" + e.getAddress2() + "\", ");
				insertQuery.append("\"" + e.getAddress3() + "\", ");
				insertQuery.append("\""+e.getStreet() + "\"); ");
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
//			// Save tourist groups 
//			e.getTouristGroups().forEach(tg -> {
//				tg.setHotels(new ArrayList<Hotel>());
//				tg = new TouristGroupRepository().save(tg);
//				connector.executeUpdate(
//						"INSERT INTO tourist_group_hotel (`tourist_group_id`, `hotel_id`) VALUES ( \""
//						+tg.getId() + "\", \"" + e.getId() + "\" );");
//			});
			ids.add(e.getId());
		});
		return findAllById(ids);
	}

	@Override
	public Optional<Hotel> findById(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		List<Hotel> objs = findAllById(ids);
		return objs.isEmpty() ? Optional.empty() : Optional.ofNullable(objs.get(0)); 
	}

	@Override
	public List<Hotel> findAll() {
		List<Hotel> hotels = new ArrayList<Hotel>();
			ResultSet rsHotel = this.connector.executeQuery(
					"SELECT * FROM hotel  ;");
			try {
			while (rsHotel!=null && rsHotel.next()) {
				Hotel h = new Hotel();
				h.setId(Long.valueOf(rsHotel.getLong("id")));
				h.setName(rsHotel.getString("name"));
				h.setPrice(Double.valueOf(rsHotel.getDouble("price")));
				h.setAddress1(rsHotel.getString("address1"));
				h.setAddress2(rsHotel.getString("address2"));
				h.setAddress3(rsHotel.getString("address3"));
				h.setStreet(rsHotel.getString("street"));
//				//Set tourist groups
//				if(h.getTouristGroups() == null) {
//					ResultSet rsTG = this.connector.executeQuery(
//							"SELECT temp.tourist_group_id as id FROM tourist_group_hotel temp WHERE temp.hotel_id = \""
//							+ h.getId() + "\" ;");
//					List<Long> idTGs = new ArrayList<Long>();
//					while(rsTG != null && rsTG.next()) {
//						idTGs.add(Long.valueOf(rsTG.getLong("id")));
//					}
//					h.setTouristGroups(new TouristGroupRepository().findAllById(idTGs));
//				}
				hotels.add(h);
			}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		return hotels;
	}

	@Override
	public List<Hotel> findAllById(Iterable<Long> ids) {
		List<Hotel> hotels = new ArrayList<Hotel>();
		ids.forEach(id -> {
			ResultSet rsHotel = this.connector.executeQuery(
					"SELECT * FROM hotel WHERE id = \"" + id + "\" ;");
			try {
			while (rsHotel!=null && rsHotel.next()) {
				Hotel h = new Hotel();
				h.setId(Long.valueOf(rsHotel.getLong("id")));
				h.setName(rsHotel.getString("name"));
				h.setPrice(Double.valueOf(rsHotel.getDouble("price")));
				h.setAddress1(rsHotel.getString("address1"));
				h.setAddress2(rsHotel.getString("address2"));
				h.setAddress3(rsHotel.getString("address3"));
				h.setStreet(rsHotel.getString("street"));
//				//Set tourist groups
//				if(h.getTouristGroups() == null) {
//					ResultSet rsTG = this.connector.executeQuery(
//							"SELECT temp.tourist_group_id as id FROM tourist_group_hotel temp WHERE temp.hotel_id = \""
//							+ h.getId() + "\" ;");
//					List<Long> idTGs = new ArrayList<Long>();
//					while(rsTG != null && rsTG.next()) {
//						idTGs.add(Long.valueOf(rsTG.getLong("id")));
//					}
//					h.setTouristGroups(new TouristGroupRepository().findAllById(idTGs));
//				}
				hotels.add(h);
			}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return hotels;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Hotel entity) {
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
	public void deleteAll(Iterable<? extends Hotel> entities) {
		// TODO Auto-generated method stub
		
	}
	
}
