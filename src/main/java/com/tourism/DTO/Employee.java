package com.tourism.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Employee
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

  private Long id;
  private String name;
  private String identityCard;
  private String address;
  private String gender;
  private String phoneNumber;
}
