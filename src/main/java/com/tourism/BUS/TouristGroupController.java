package com.tourism.BUS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.tourism.DAL.CustomerRepository;
import com.tourism.DAL.HotelRepository;
import com.tourism.DAL.TourPositionRepository;
import com.tourism.DAL.TourRepository;
import com.tourism.DAL.TouristGroupRepository;
import com.tourism.DTO.Customer;
import com.tourism.DTO.Employee;
import com.tourism.DTO.Position;
import com.tourism.DTO.Tour;
import com.tourism.DTO.TourPosition;
import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.frames.touristgroup.management.TouristGroupSearchPanel;

public class TouristGroupController {
	Logger logger = Logger.getLogger(getClass().getName());
	TouristGroupRepository TGRepository;
	CustomerRepository customerRepository;
	TourPositionRepository tourPositionRepository;
	TourRepository tourRepository;
	HotelRepository hotelRepository ;
	
	public TouristGroupController() {
		TGRepository = new TouristGroupRepository();
		customerRepository = new CustomerRepository();
		tourPositionRepository = new TourPositionRepository();
		tourRepository = new TourRepository();
		hotelRepository = new HotelRepository();
	}
	
	public List<TouristGroup> getAll(){
		List<TouristGroup> TGs = new ArrayList<TouristGroup>();
		
		TGs = TGRepository.findAll();
		TGs.forEach(TG->{
			TG.setCustomers(customerRepository.findAllByTouristGroupId(TG.getId()));
			TG.setTourPositions(tourPositionRepository.findAllByTouristGroupId(TG.getId()));
			TG.setTour(tourRepository.findById(TG.getTourId()).orElse(null));
		});
		return TGs;
	}
	
	public TouristGroup getById(Long id){
		TouristGroup TG;
		TG = TGRepository.findById(id).orElse(new TouristGroup());
		if(TG.getId() == null) {
			return TG;
		}
		TG.setHotels(hotelRepository.findAllByTouristGroupId(id));
		TG.setCustomers(customerRepository.findAllByTouristGroupId(id));
		TG.setTourPositions(tourPositionRepository.findAllByTouristGroupId(id));
		TG.setTour(tourRepository.findById(TG.getTourId()).orElse(null));
		return TG;
	}
	
	public List<TourPosition> groupAllTourPositionByTourPositionId(TouristGroup TG, Long positionId) {
		List<TourPosition> tourPositions = new ArrayList<TourPosition>();
		TG.getTourPositions().forEach(tourPosition->{
			if(positionId == 0 || tourPosition.getPositionId() == positionId) {
				tourPositions.add(tourPosition);
			}
		});
		return tourPositions;
	}
	
	public List<TouristGroup> saveAll(Iterable<TouristGroup> touristGroups){
		return TGRepository.saveAll(touristGroups);
	}
	
	public void saveAllWithCustomerAndTourPositionAndTour(Iterable<TouristGroup> touristGroups){
		List<TouristGroup> TGs = new ArrayList<TouristGroup>();
		logger.info("before save");
		logger.info(touristGroups.toString());
		List<TouristGroup> savedTGs = new ArrayList<TouristGroup>();
		List<TourPosition> savedTourPositions = new ArrayList<TourPosition>();
		List<Customer> savedCustomer = new ArrayList<Customer>();
		Tour savedTour = null;
		savedTGs = TGRepository.saveAll(touristGroups);
		for(TouristGroup TG: touristGroups) {
			savedCustomer = customerRepository.saveAll(TG.getCustomers());
//			savedTour = tourRepository.save(TG.getTour());
			savedTourPositions = tourPositionRepository.saveAll(TG.getTourPositions()); 
		}
		
		for(TouristGroup savedTG: savedTGs) {
			savedTG.setCustomers(savedCustomer);
			savedTG.setTour(savedTour);
			savedTG.setTourPositions(savedTourPositions);
		}
		logger.info("after save");
		logger.info(savedTGs.toString());
		TGRepository.saveRelationShip(savedTGs);
	}
	
	public void saveWithCustomerAndTourPositionAndTour(TouristGroup touristGroup) {
		List<TouristGroup> TGs = new ArrayList<TouristGroup>();
		TGs.add(touristGroup);
		saveAllWithCustomerAndTourPositionAndTour(TGs);
	}
	
	public TouristGroup changeStatusToDeleted(TouristGroup touristGroup) {
		touristGroup = TGRepository.findById(touristGroup.getId()).get();
		logger.info(touristGroup.toString());
		return TGRepository.save(touristGroup);
	}
}
