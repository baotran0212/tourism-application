package com.tourism.DAL;

import java.util.Optional;

import com.tourism.DTO.Employee;

/**
 * EmployeeRepository
 */
public class EmployeeRepository implements Repositories<Employee, Long> {

  @Override
  public Employee save(Employee entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterable<Employee> saveAll(Iterable<Employee> entities) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<Employee> findById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterable<Employee> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterable<Employee> findAllById(Iterable<Long> ids) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public long count() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void delete(Employee entity) {
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
  public void deleteAll(Iterable<? extends Employee> entities) {
    // TODO Auto-generated method stub

  }

}
