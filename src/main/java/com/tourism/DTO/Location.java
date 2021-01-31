package com.tourism.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * Location
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Location {

	protected Long id;
	@NonNull
	protected String name;
	@NonNull
	protected String address;
	@NonNull
	protected String coordinates;
	protected List<Tour> tours = new ArrayList<Tour>();
}
