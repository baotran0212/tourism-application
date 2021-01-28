package com.tourism.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Hotel
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hotel {

  private Long id;
  private String name;
  private String price;
  private String address;
}
