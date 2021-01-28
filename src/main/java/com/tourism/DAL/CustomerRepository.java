package com.tourism.DAL;

import java.util.Optional;

import com.tourism.DTO.Customer;

/**
 * Customer
 */
public class CustomerRepository implements Repositories<Customer, Long> {

  @Override
  public <S extends Customer> S save(S entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<Customer> findById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterable<Customer> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterable<Customer> findAllById(Iterable<Long> ids) {
  	// TODO Auto-generated method stub
  	return null;
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
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteAll(Iterable<? extends Customer> entities) {
    // TODO Auto-generated method stub

  }

}
