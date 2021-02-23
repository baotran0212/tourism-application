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
<<<<<<< HEAD

	protected Long id;
	protected Long typeId;
	@NonNull
	protected String name;
	@NonNull
	protected String description;
	@NonNull
	protected Double price;
	@NonNull
	protected String status;
	protected Type type ;
	protected String picture;
=======
	Long id;
	Long typeId;
	String name;
	String description;
	String status;
	String image;
	Type type ;
>>>>>>> 1710f019e689fb3ee19e89c9b0807a74eac7c986
	List<TouristGroup> touristGroups ;
	List<Location> locations;
	List<TourCost> tourCosts;
}
