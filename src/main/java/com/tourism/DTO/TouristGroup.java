package com.tourism.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * TouristGroup
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
public class TouristGroup {
	protected Long id;
	protected Long tourId;
	@NonNull
	protected String name;
	@NonNull
	protected Date depatureDate;
	@NonNull
	protected Date endDate;
	@NonNull
	protected String description;
	@NonNull
	protected Double foodPrice;
	@NonNull
	protected Double transportPrice;
	@NonNull
	protected Double hotelPrice;
	@NonNull
	protected Double otherPrice;
	protected List<Customer> customers = new ArrayList<Customer>();
	protected Tour tour = new Tour();
	protected List<RoleTour> roleTours = new ArrayList<RoleTour>();
	protected List<Hotel> hotels = new ArrayList<Hotel>();

	public static void main(String[] args) {
	}
}
