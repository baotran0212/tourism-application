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
public class Tour {
	protected Long id;
	protected Long typeId;
	protected String name;
	protected String description;
	protected String status;
	protected Type type ;
	List<TouristGroup> touristGroups ;
	List<Location> locations;
	List<TourCost> tourCosts;
}
