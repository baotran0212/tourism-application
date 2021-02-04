package com.tourism.DTO;

import java.util.ArrayList;
import java.util.List;

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
	protected String address1;
	@NonNull
	protected String address2;
	@NonNull
	protected String address3;
	@NonNull
	protected String gender;
	@NonNull
	protected String phoneNumber;
	@NonNull
	protected String status;
	protected List<TourPosition> tourPositions;
	public static void main(String[] args) {
	}
}
