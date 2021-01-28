package com.tourism.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Location
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Location {

  private Long id;
  private String name;
  private String address;
  private String coordinates;
}
