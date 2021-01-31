package com.tourism.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * Hotel
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hotel {

  protected Long id;
  @NonNull
  protected String name;
  @NonNull
  protected String price;
  @NonNull
  protected String address;
  protected List<TouristGroup> touristGroups = new ArrayList<TouristGroup>();
}
