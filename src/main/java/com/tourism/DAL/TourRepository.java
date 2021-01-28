package com.tourism.DAL;

import java.util.Optional;

import com.tourism.DTO.Tour;

/**
 * TourRepository
 */
public class TourRepository implements Repositories<Tour, Long> {

  @Override
  public Tour save(Tour entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterable<Tour> saveAll(Iterable<Tour> entities) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<Tour> findById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterable<Tour> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterable<Tour> findAllById(Iterable<Long> ids) {
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

}
