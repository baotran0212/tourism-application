package com.tourism.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Customer
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  private String id;
  private String name;
  private String identityCard;
  private String address;
  private String gender;
  private String phoneNumber;

  public static void main(String[] args) {
    Customer c = new Customer();
  }
}
