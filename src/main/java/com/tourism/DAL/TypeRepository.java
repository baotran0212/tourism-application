package com.tourism.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tourism.DTO.Tour;
import com.tourism.DTO.TourPosition;
import com.tourism.DTO.TouristGroup;
import com.tourism.DTO.Type;

public class TypeRepository implements Repositories<Type, Long> {
	Connector connector = new MysqlConnector();
	@Override
	public Type save(Type entity) {
		List<Type> types= new ArrayList<Type>();
		types.add(entity);
		return saveAll(types).get(0);
	}

	@Override
	public List<Type> saveAll(Iterable<Type> entities) {
		 List<Long> ids = new ArrayList<Long>();
		 entities.forEach(e->{
			 if(findById(e.getId()).isPresent()) {
				 StringBuilder updateQuery = new StringBuilder(
						 "UPDATE type_of_tour SET");
				 updateQuery.append("name = \"" +e.getName() + "\" " );
				 updateQuery.append("WHERE id =\"" + e.getId() + "\" ;");
				connector.executeUpdate(updateQuery.toString()); 
			 } else {
				 StringBuilder insertQuery = new StringBuilder(
						 "INSERT INTO type_of_tour(`name`) VALUES");
				 insertQuery.append("( \"" + e.getName() + "\" ); ");
				 connector.executeUpdate(insertQuery.toString());
				 ResultSet returnedResultSet = connector.executeQuery(
						 "SELECT * FROM type_of_tour ORDER BY `id` DESC LIMIT 1");
				 try {
					while(returnedResultSet != null && returnedResultSet.next()) {
						e.setId(Long.valueOf(returnedResultSet.getLong("id")));
					}
				} catch (Exception e2) {
				}
			 }
//			 // Set tours
//			 e.getTours().forEach(tour ->{
//				 tour.setType(new Type());
//				 tour.setTypeId(e.getId());
//				 tour = new TourRepository().save(tour);
//			 });
			 ids.add(e.getId());
		 });
		 return findAllById(ids);
	}

	@Override
	public Optional<Type> findById(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		List<Type> objs = findAllById(ids);
		return objs.isEmpty() ? Optional.empty() : Optional.ofNullable(objs.get(0)); 
	}

	@Override
	public List<Type> findAll() {
		List<Type> types = new ArrayList<Type>();
		ResultSet rsType = this.connector.executeQuery("SELECT * FROM type_of_tour ;");
		try {
			while (rsType != null && rsType.next()) {
				Type type = new Type();
				type.setId(Long.valueOf(rsType.getLong("id")));
				type.setName(rsType.getString("name"));
//				// Set tours
//				if (type.getTours() == null) {
//					ResultSet rsTour = connector
//							.executeQuery("SELECT tour.id FROM tour WHERE tour.type_id = \"" + type.getId() + "\" ;");
//					List<Long> idTours = new ArrayList<Long>();
//					while (rsTour != null && rsTour.next()) {
//						idTours.add(Long.valueOf(rsTour.getLong("id")));
//					}
//					type.setTours(new TourRepository().findAllById(idTours));
//				}
				types.add(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return types;
	}

	@Override
	public List<Type> findAllById(Iterable<Long> ids) {
		List<Type> types = new ArrayList<Type>();
		ids.forEach(id -> {
			ResultSet rsType = this.connector.executeQuery(
					"SELECT * FROM type_of_tour WHERE id = \"" + id + "\" ;");
			try {
				while (rsType !=null && rsType.next()) {
					Type type = new Type();
					type.setId(Long.valueOf(rsType.getLong("id")));
					type.setName(rsType.getString("name"));
//					// Set tours
//					if(type.getTours() == null) {
//						ResultSet rsTour = connector.executeQuery(
//								"SELECT tour.id FROM tour WHERE tour.type_id = \""+type.getId() + "\" ;");
//						List<Long> idTours = new ArrayList<Long>();
//						while(rsTour!=null && rsTour.next()) {
//							idTours.add(Long.valueOf(rsTour.getLong("id")));
//						}
//						type.setTours(new TourRepository().findAllById(idTours));
//					}
					types.add(type);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return types;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Type entity) {
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
	public void deleteAll(Iterable<? extends Type> entities) {
		// TODO Auto-generated method stub
		
	}
}
