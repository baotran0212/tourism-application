package com.tourism.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.tourism.DTO.Tour;

/**
 * TourRepository
 */
public class TourRepository implements Repositories<Tour, Long> {
	Connector connector = new MysqlConnector();
	Logger logger = Logger.getLogger(this.getClass().getName());
  @Override
  public Tour save(Tour entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Tour> saveAll(Iterable<Tour> entities) {
	  List<Long> ids = new ArrayList<Long>();
		entities.forEach(e -> {
			Long returnedId = null;
			e.setTypeId(e.getType().getId());
			if (findById(e.getId()).isPresent()) {
				StringBuilder updateQuery = new StringBuilder("UPDATE tour SET ");
				updateQuery.append("type_id = \"" + e.getTypeId() + "\", ");
				updateQuery.append("name = \"" + e.getName() + "\", ");
				updateQuery.append("description = \"" + e.getDescription() + "\", ");
				updateQuery.append("price = \"" + e.getPrice() + "\", ");
				updateQuery.append("WHERE id = \"" + e.getId() + "\" ;");
				logger.info(updateQuery.toString());
				this.connector.executeUpdate(updateQuery.toString());
				new TypeRepository().save(e.getType());
				new TouristGroupRepository().saveAll(e.getTouristGroups());
				new LocationRepository().saveAll(e.getLocations()).forEach(location->{
					connector.executeUpdate(
							"INSERT INTO tour_location (`tour_id`, `location_id`) VALUES (\""
							+e.getId() +"\", \""+location.getId() + "\" );");
				});
				returnedId = e.getId();
			} else {
				StringBuilder insertQuery = new StringBuilder(
						"INSERT INTO tourist_group(`type_id`, `name`, `description`, `price`) VALUES ");
				insertQuery.append("( \"" + e.getTypeId() + "\", ");
				insertQuery.append("\"" + e.getName() + "\", ");
				insertQuery.append("\"" + e.getDescription() + "\", ");
				insertQuery.append("\"" + e.getPrice() + "\" ); ");
				connector.executeUpdate(insertQuery.toString());
				new TypeRepository().save(e.getType());
				new TouristGroupRepository().saveAll(e.getTouristGroups());
				new LocationRepository().saveAll(e.getLocations()).forEach(location->{
					connector.executeUpdate(
							"INSERT INTO tour_location (`tour_id`, `location_id`) VALUES (\""
							+e.getId() +"\", \""+location.getId() + "\" );");
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
  public Optional<Tour> findById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Tour> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Tour> findAllById(Iterable<Long> ids) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public long count() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void delete(Tour entity) {
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
  public void deleteAll(Iterable<? extends Tour> entities) {
    // TODO Auto-generated method stub

  }

@Override
public List<Tour> loadAllRelationship(Iterable<Tour> entities) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Optional<Tour> loadRelationshipById(Long id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Optional<Tour> loadRelationship(Tour entity) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Tour> saveAllRelationship(Iterable<Tour> entities) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Tour saveRelationship(Tour entity) {
	// TODO Auto-generated method stub
	return null;
}

}
