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

import com.tourism.DTO.Customer;
import com.tourism.DTO.TouristGroup;

/**
 * TouristGroupDAL
 */
public class TouristGroupRepository implements Repositories<TouristGroup, Long> {
  Connector connector = new MysqlConnector();
  SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

  @Override
  public List<TouristGroup> findAll() {
    ResultSet rs = connector.executeQuery("SELECT * FROM tourist_group");
    List<TouristGroup> touristGroups = new ArrayList<TouristGroup>();
    try {
      while (rs.next()) {
    	  TouristGroup tg = new TouristGroup(
          		Long.valueOf(rs.getString("id")), 
          		rs.getString("name"), 
          		rs.getDate("depature_date"),
          		rs.getDate("end_date"),
          		rs.getString("description"),
          		Double.valueOf(rs.getDouble("food_price")),
          		Double.valueOf(rs.getString("transport_price")),
          		Double.valueOf(rs.getString("hotel_price")),
          		Double.valueOf(rs.getString("other_price")));
        touristGroups
            .add(tg);
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
    StringBuilder query = new StringBuilder("INSERT INTO tourist_group (`name`, `depature_date`, `end_date`, `description`, `food_price`, `transport_price`, `hotel_price`, `other_price`) VALUES ");
    List<Long> ids = new ArrayList<Long>();
    entities.forEach(e -> {
      query.append("( \""+e.getName() + "\", ");
      query.append("\""+ formatter.format(e.getDepatureDate()) + "\", ");
      query.append("\""+formatter.format(e.getEndDate()) + "\", ");
      query.append("\""+e.getDescription() + "\", ");
      query.append(e.getFoodPrice()+ ", ");
      query.append(e.getTransportPrice() + ", ");
      query.append(e.getHotelPrice() + ", ");
      query.append(e.getOtherPrice() + "); SELECT LAST_INSERT_ID();");
      Long rs = Long.valueOf(this.connector.executeUpdate(query.toString()));
      ids.add(rs);
    });
    return findAllById(ids);
  }

  @Override
  public Optional<TouristGroup> findById(Long id) {
    List<Long> ids = new ArrayList<Long>();
    ids.add(id);
    Optional<TouristGroup> opt;
    opt = Optional.ofNullable(findAllById(ids).get(0));
    return opt;
  }

	@Override
	public List<TouristGroup> findAllById(Iterable<Long> ids) {
		List<TouristGroup> touristGroups = new ArrayList<TouristGroup>();
		StringBuilder query = new StringBuilder("SELECT * FROM tourist_group where ");
		ids.forEach(id -> {
			query.append(" id = "+id+" OR");
		});
		ResultSet rs = this.connector.executeQuery(query.substring(0, query.length()-2));
		System.out.println(query.substring(0, query.length()-2));
		try {
			while(rs.next()) {
				TouristGroup tg = new TouristGroup(
		          		Long.valueOf(rs.getString("id")), 
		          		rs.getString("name"), 
		          		rs.getDate("depature_date"),
		          		rs.getDate("end_date"),
		          		rs.getString("description"),
		          		Double.valueOf(rs.getDouble("food_price")),
		          		Double.valueOf(rs.getString("transport_price")),
		          		Double.valueOf(rs.getString("hotel_price")),
		          		Double.valueOf(rs.getString("other_price")));
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
	  System.out.println("DELETED");
  }

  @Override
  public void deleteAll(Iterable<? extends TouristGroup> entities) {
	  List<Long> ids = new ArrayList<Long>();
	  entities.forEach(e->{
		  ids.add(e.getId());
	  });
	  deleteAllById(ids);
  }

	public static void main(String[] args) {
		TouristGroupRepository touristGroupRepository = new TouristGroupRepository();
		List<TouristGroup> touristGroups = new ArrayList<TouristGroup>();
		/* TEST INSERT */
		TouristGroup tg = new TouristGroup(new Long(123), "name", new Date(), new Date(), "description",
				new Double(300000), new Double(50000), new Double(130000), new Double(12300000));
		TouristGroup tg2 = new TouristGroup(new Long(123), "name", new Date(), new Date(), "description",
				new Double(300000), new Double(50000), new Double(130000), new Double(12300000));
		touristGroups.add(tg);
		touristGroups.add(tg2);
		/* TEST SELECT */
		touristGroupRepository
				.findAllById(Arrays.asList(new Long[] { Long.valueOf(4), Long.valueOf(6), Long.valueOf(5) }))
				.forEach(System.out::println);
		/* TEST DELETE */
		touristGroupRepository.deleteAll(touristGroups);
	}
}
