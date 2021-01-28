package com.tourism.DAL;

import java.util.Optional;

/**
 * Repositories
 */
public interface Repositories<T, ID> {
  T save(T entity);

  Iterable<T> saveAll(Iterable<T> entities);

  Optional<T> findById(ID id);

  Iterable<T> findAll();

  Iterable<T> findAllById(Iterable<ID> ids);

  long count();

  void delete(T entity);

  void deleteById(ID id);

  void deleteAll();

  void deleteAllById(Iterable<? extends ID> ids);

  void deleteAll(Iterable<? extends T> entities);
}
