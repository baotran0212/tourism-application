package com.tourism.DTO;

public class SearchObject {
	StringBuilder searchQuery;
	
	public SearchObject(String initQuery) {
		this.searchQuery = new StringBuilder(initQuery);
	}
	
	public SearchObject andEqual(String field, String values) {
		this.searchQuery.append(" AND " + field + " = \"" +values + "\" ");
		return this;
	}
}
