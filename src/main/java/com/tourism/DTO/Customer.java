package com.tourism.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Customer
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class Customer {
  private Long id;
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

  public static void main(String[] args) {
    Customer c = new Customer();
  }

}
