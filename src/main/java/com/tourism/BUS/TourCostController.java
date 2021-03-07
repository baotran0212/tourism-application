package com.tourism.BUS;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.tourism.DAL.TourCostRepository;
import com.tourism.DTO.TourCost;
import com.tourism.GUI.Resources;

public class TourCostController {
	TourCostRepository tourCostRepository = new TourCostRepository();
	
	public List<TourCost> getByTourIdSortFromNow(Long tourId){
		List<TourCost> tourCosts = tourCostRepository.findByTourId(tourId);
		Collections.sort(tourCosts, (a,b)->{
			try {
				return Resources.simpleDateFormat.parse(a.getPriceFromTime()).compareTo(
						new Date());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return 0;
		});
		return tourCosts;
	}
}
