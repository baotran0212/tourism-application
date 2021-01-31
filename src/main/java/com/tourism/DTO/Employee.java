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
  protected String name;
  @NonNull
  protected String identityCard;
  @NonNull
  protected String address;
  @NonNull
  protected String gender;
  @NonNull
  protected String phoneNumber;
  
}
