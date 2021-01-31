package com.tourism.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.tourism.DTO.Customer;
import com.tourism.DTO.Customer;

/**
 * Customer
 */
public class CustomerRepository implements Repositories<Customer, Long> {
	Logger logger = Logger.getLogger(this.getClass().getName());
	Connector connector = new MysqlConnector();

	@Override
	public Customer save(Customer entity) {
		List<Customer> cus = new ArrayList<Customer>();
		cus.add(entity);
		return saveAll(cus).get(0);
	}

	@Override
	public List<Customer> saveAll(Iterable<Customer> entities) {
		List<Long> ids = new ArrayList<Long>();
		entities.forEach(e -> {
			Long returnedId = null;
			if (findById(e.getId()).isPresent()) {
				StringBuilder updateQuery = new StringBuilder("UPDATE customer SET ");
				updateQuery.append("name = \"" + e.getName() + "\", ");
				updateQuery.append("identity_card = \"" + e.getIdentityCard() + "\", ");
				updateQuery.append("address = \"" + e.getAddress() + "\", ");
				updateQuery.append("gender = \"" + e.getGender() + "\", ");
				updateQuery.append("phone_number = \"" + e.getPhoneNumber() + "\"");
				updateQuery.append(" WHERE id = \"" + e.getId() + "\" ;");
				logger.info(updateQuery.toString());
				this.connector.executeUpdate(updateQuery.toString());
				new TouristGroupRepository().saveAll(e.getTouristGroups()).forEach(tg->{
					connector.executeUpdate(
							"INSERT INTO tourist_group_customer (`tourist_group_id`,`customer_id`) VALUES ("
							+e.getId() + "\", \""+tg.getId() + "\" );");
				});
				returnedId = e.getId();
			} else {
				StringBuilder insertQuery = new StringBuilder(
						"INSERT INTO customer(`name`, `identity_card`, `address`, `gender`, `phone_number`) VALUES ");
				insertQuery.append("( \"" + e.getName() + "\", ");
				insertQuery.append("\"" + e.getIdentityCard() + "\", ");
				insertQuery.append("\"" + e.getAddress() + "\", ");
				insertQuery.append(" \"" + e.getGender() + "\", ");
				insertQuery.append("\"" + e.getPhoneNumber() + "\") ");
				connector.executeUpdate(insertQuery.toString());
				new TouristGroupRepository().saveAll(e.getTouristGroups()).forEach(tg->{
					connector.executeUpdate(
							"INSERT INTO tourist_group_customer (`tourist_group_id`,`customer_id`) VALUES ("
							+e.getId() + "\", \""+tg.getId() + "\" );");
				});
				ResultSet returnedResutSet = connector
						.executeQuery("SELECT * FROM customer ORDER BY `id` DESC LIMIT 1");
				try {
					while (returnedResutSet != null && returnedResutSet.next()) {
						returnedId = Long.valueOf(returnedResutSet.getString("id"));
						logger.info(returnedId.toString());
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			ids.add(returnedId);
		});
		return findAllById(ids);
	}

	@Override
	public Optional<Customer> findById(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		List<Customer> customers = findAllById(ids);
		System.out.println(customers);
		return Optional.ofNullable(customers.isEmpty() ? null : customers.get(0));
	}

	@Override
	public List<Customer> findAll() {
		ResultSet rs = connector.executeQuery("SELECT * FROM customer");
		List<Customer> customers = new ArrayList<Customer>();
		try {
			while (rs.next()) {
				Customer tg = new Customer(Long.valueOf(rs.getLong("id")), rs.getString("name"),
						rs.getString("identity_card"), rs.getString("address"), rs.getString("gender"),
						rs.getString("phone_number"));
				customers.add(tg);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return customers;
	}

	@Override
	public List<Customer> findAllById(Iterable<Long> ids) {
		List<Customer> customers = new ArrayList<Customer>();
		StringBuilder query = new StringBuilder("SELECT * FROM customer where ");
		ids.forEach(id -> {
			query.append(" id = " + id + " OR");
		});
		ResultSet rs = this.connector.executeQuery(query.substring(0, query.length() - 2));
		try {
			while (rs != null && rs.next()) {
				Customer tg = new Customer(Long.valueOf(rs.getString("id")), rs.getString("name"),
						rs.getString("identity_card"),
						rs.getString("address"),
						rs.getString("gender"),
						rs.getString("phone_number"));
				customers.add(tg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Customer entity) {
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
	  StringBuilder query = new StringBuilder("DELETE FROM customer WHERE ");
	  ids.forEach(id->{
		  query.append(" id = "+id+" OR");
	  });
	  this.connector.executeUpdate(query.substring(0, query.length()-2));
	  System.out.println(query.substring(0, query.length()-2));
	}

	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		List<Long> ids = new ArrayList<Long>();
		entities.forEach(e -> {
			ids.add(e.getId());
		});
		deleteAllById(ids);
	}

	@Override
	public List<Customer> loadAllRelationship(Iterable<Customer> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customer> loadRelationshipById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customer> loadRelationship(Customer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> saveAllRelationship(Iterable<Customer> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer saveRelationship(Customer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		CustomerRepository customerRepository = new CustomerRepository();
		/* TEST SAVE */
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer(Long.valueOf(17), "Long", "311098490", "Nguyen Trai", "nam", "09123455"));
		customers.add(new Customer(Long.valueOf(18), "Vu", "311098490", "Nguyen Trai", "nam", "09123455"));
		customers = customerRepository.findAll();
		System.out.println(customers);
		// TEST FIND
		List<Long> ids = new ArrayList<Long>();
		ids.add(Long.valueOf(1));
		// System.out.println(!customerRepository.findAllById(ids).isEmpty()?customerRepository.findAllById(ids).get(0):null);
	}
}
