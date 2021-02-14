package com.tourism.GUI.frames.touristgroup.modify;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.tourism.BUS.TouristGroupController;
import com.tourism.DTO.Customer;
import com.tourism.DTO.Hotel;
import com.tourism.DTO.Tour;
import com.tourism.DTO.TourPosition;
import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.Resources;
import com.tourism.GUI.frames.touristgroup.TestFrame;
import com.tourism.GUI.frames.touristgroup.TouristGroupMainPanel;

public class TouristGroupModify extends JPanel {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass().getName());
	TouristGroupBasicModifyPanel basicPanel;
	TouristGroupCustomerTable customerPanel;
	TouristGroupEmployeeTable employeePanel;
	TouristGroupHotelTable hotelPanel;
	TouristGroupModifyBottomBar bottomBar;
	GroupLayout layout;
	public static TouristGroup TG;
	public static TouristGroupController TGController = new TouristGroupController();
	public TouristGroupModify() {
		initData();
		initComp();
	}
	
	public TouristGroupModify(Long idTG) {
		TouristGroupModify.TG = TGController.getByIdNotDeleted(idTG);
		initData();
		initComp();
	}
	
	public void initData() {
		basicPanel = new TouristGroupBasicModifyPanel();
		customerPanel = new TouristGroupCustomerTable();
		employeePanel = new TouristGroupEmployeeTable();
		hotelPanel = new TouristGroupHotelTable();
		bottomBar = new TouristGroupModifyBottomBar();
		layout = new GroupLayout(this);
	}
	
	public void initComp() {
		setBackground(Color.GREEN);
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(basicPanel)
				.addGroup(layout.createSequentialGroup()
						.addComponent(customerPanel)
						.addComponent(employeePanel)
						.addComponent(hotelPanel)
						)
				.addComponent(bottomBar));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addComponent(basicPanel, 0, TGModifyResource.MODIFY_BASIC_PANEL_HEIGHT, TGModifyResource.MODIFY_BASIC_PANEL_HEIGHT)
						.addGroup(layout.createParallelGroup(Alignment.CENTER)
								.addComponent(customerPanel)
								.addComponent(employeePanel)
								.addComponent(hotelPanel)
								)
						.addComponent(bottomBar)
						)
				);
		this.setLayout(layout);
		setPreferredSize(new Dimension(1110,700));
	}
	
	public static void main(String[] args) {
		TouristGroupMainPanel.selectedTouristGroup = new TouristGroup();
		TouristGroup TG = TouristGroupMainPanel.selectedTouristGroup;
		TG.setCustomers(new ArrayList<Customer>());
		TG.setHotels(new ArrayList<Hotel>());
		TG.setTourPositions(new ArrayList<TourPosition>());
		TG.setTour(new Tour());
		new TestFrame(new TouristGroupModify());
	}
}
