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
 * RoleTour
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class RoleTour {
	private Long id;
	@NonNull
	private String name;
	protected TouristGroup touristGroup; 
	protected List<Employee> employees = new ArrayList<Employee>();
}
