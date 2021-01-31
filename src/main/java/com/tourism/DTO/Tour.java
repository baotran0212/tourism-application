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
 * Tour
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
public class Tour {

	protected Long id;
	protected Long typeId;
	@NonNull
	protected String name;
	@NonNull
	protected String description;
	@NonNull
	protected Double price;
	protected Type type = new Type();
	List<TouristGroup> touristGroups = new ArrayList<TouristGroup>();
	List<Location> locations = new ArrayList<Location>();
}
