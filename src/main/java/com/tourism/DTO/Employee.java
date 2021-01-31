package com.tourism.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Employee
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
public class Employee {

  protected Long id;
  @NonNull
  private String name;
  @NonNull
  private String identityCard;
  @NonNull
  private String address;
  @NonNull
  private String gender;
  @NonNull
  private String phoneNumber;
  
}
