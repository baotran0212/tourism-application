package com.tourism.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Tour
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tour {

  private Long id;
  private String name;
  private String description;
  private Double price;
}
