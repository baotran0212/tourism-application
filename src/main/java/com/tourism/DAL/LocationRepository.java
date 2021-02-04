package com.tourism.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tourism.DTO.Location;
import com.tourism.DTO.TouristGroup;

public class LocationRepository implements Repositories<Location, Long> {
	Connector connector = new MysqlConnector();
	@Override
	public Location save(Location entity) {
		List<Location> locations = new ArrayList<Location>();
		locations.add(entity);
		return saveAll(locations).get(0);
	}

	@Override
	public List<Location> saveAll(Iterable<Location> entities) {
		List<Long> ids = new ArrayList<Long>();
		entities.forEach(e->{
			if(findById(e.getId()).isPresent()) {
				StringBuilder updateQuery = new StringBuilder("UPDATE location SET ");
				updateQuery.append("name = \"" + e.getName() + "\", ");
				updateQuery.append("address1 = \"" + e.getAddress1() + "\", ");
				updateQuery.append("address2 = \"" +e.getAddress2() + "\", ");
				updateQuery.append("address2 = \"" +e.getAddress3() + "\" ");
				updateQuery.append("WHERE id = \"" + e.getId() + "\" ;");
				connector.executeUpdate(updateQuery.toString());
			} else {
				StringBuilder insertQuery = new StringBuilder(
						"INSERT INTO location(`name`, `address1`, `address2`, `address3`) VALUES ");
				insertQuery.append("( \"" + e.getName() + "\", ");
				insertQuery.append("\"" + e.getAddress1() + "\", ");
				insertQuery.append("\"" + e.getAddress2() + "\", ");
				insertQuery.append("\"" + e.getAddress3() + "\" ); ");
				connector.executeUpdate(insertQuery.toString());
				ResultSet returnedResultSet = connector.executeQuery(
						"SELECT * FROM location ORDER BY `id` DESC LIMIT 1");
				try {
					while(returnedResultSet!=null && returnedResultSet.next()) {
						e.setId(Long.valueOf(returnedResultSet.getLong("id")));
					}
				} catch (Exception e2) {
				}
			}
			//Save tours
			e.getTours().forEach(tour ->{
				tour.setLocations(new ArrayList<Location>());
				tour = new TourRepository().save(tour);
				connector.executeUpdate(
						"INSERT INTO tour_location (`tour_id`, `location_id`) VALUES ( \""
						+tour.getId() + "\", \"" + e.getId() + "\" );");
			});
			ids.add(e.getId());
		});
		return findAllById(ids);
	}

	@Override
	public Optional<Location> findById(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		return Optional.ofNullable(findAllById(ids).get(0));
	}

	@Override
	public List<Location> findAll() {
		List<Location> locations = new ArrayList<Location>();
		ResultSet rsLocation = connector.executeQuery("SELECT * FROM location ;");
		try {
			while (rsLocation != null && rsLocation.next()) {
				Location location = new Location();
				location.setId(Long.valueOf(rsLocation.getLong("id")));
				location.setName(rsLocation.getString("name"));
				location.setAddress1(rsLocation.getString("address1"));
				location.setAddress2(rsLocation.getString("address2"));
				location.setAddress3(rsLocation.getString("address3"));
				// Set tours
				if (location.getTours() == null) {
					ResultSet rsTour = connector
							.executeQuery("SELECT temp.tour_id as id FROM tour_location temp WHERE temp.location = \""
									+ location.getId() + "\" GROUP BY temp.tour_id ;");
					List<Long> idTours = new ArrayList<Long>();
					while (rsTour != null && rsTour.next()) {
						idTours.add(Long.valueOf(rsTour.getString("id")));
					}
					location.setTours(new TourRepository().findAllById(idTours));
				}
				locations.add(location);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locations;
	}

	@Override
	public List<Location> findAllById(Iterable<Long> ids) {
		List<Location> locations = new ArrayList<Location>();
		ids.forEach(id->{
			ResultSet rsLocation= connector.executeQuery(
					"SELECT * FROM location WHERE id = \"" + id + "\" ;");
			try {
				while(rsLocation!=null && rsLocation.next()) {
					Location location = new Location();
					location.setId(Long.valueOf(rsLocation.getLong("id")));
					location.setName(rsLocation.getString("name"));
					location.setAddress1(rsLocation.getString("address1"));
					location.setAddress2(rsLocation.getString("address2"));
					location.setAddress3(rsLocation.getString("address3"));
					// Set tours
					if(location.getTours() == null) {
						ResultSet rsTour = connector.executeQuery(
								"SELECT temp.tour_id as id FROM tour_location temp WHERE temp.location = \""
								+location.getId() + "\" GROUP BY temp.tour_id ;");
						List<Long> idTours = new ArrayList<Long>();
						while(rsTour != null && rsTour.next() ) {
							idTours.add(Long.valueOf(rsTour.getString("id")));
						}
						location.setTours(new TourRepository().findAllById(idTours));
					}
					locations.add(location);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return locations;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Location entity) {
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
	public void deleteAll(Iterable<? extends Location> entities) {
		// TODO Auto-generated method stub
		
	}
}
