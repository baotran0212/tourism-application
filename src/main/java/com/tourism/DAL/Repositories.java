package com.tourism.DAL;

import java.util.List;
import java.util.Optional;

/**
 * Repositories
 */
public interface Repositories<T, ID> {
  T save(T entity);

  List<T> saveAll(Iterable<T> entities);

  Optional<T> findById(ID id);

  List<T> findAll();

  List<T> findAllById(Iterable<ID> ids);

  long count();

  void delete(T entity);

  void deleteById(ID id);

  void deleteAll();

  void deleteAllById(Iterable<? extends ID> ids);

  void deleteAll(Iterable<? extends T> entities);
  
  List<T> loadAllRelationship(Iterable<T> entities);
  
  Optional<T> loadRelationshipById(ID id);
  
  Optional<T> loadRelationship(T entity);
  
  List<T> saveAllRelationship(Iterable<T> entities);
  
  T saveRelationship(T entity);
}
