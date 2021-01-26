package com.tourism.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TouristGroup
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TouristGroup {

  private String id;
  private String name;
  private Date depatureDate;
  private Date endDate;
  private String description;
  private Double foodPrice;
  private Double transportPrice;
  private Double hotelPrice;
  private Double otherPrice;
}
