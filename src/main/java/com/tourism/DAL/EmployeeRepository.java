package com.tourism.DAL;

import java.util.List;
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
  public List<Employee> saveAll(Iterable<Employee> entities) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<Employee> findById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Employee> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Employee> findAllById(Iterable<Long> ids) {
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

@Override
public List<Employee> loadAllRelationship(Iterable<Employee> entities) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Optional<Employee> loadRelationshipById(Long id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Optional<Employee> loadRelationship(Employee entity) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Employee> saveAllRelationship(Iterable<Employee> entities) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Employee saveRelationship(Employee entity) {
	// TODO Auto-generated method stub
	return null;
}

}
