package com.tourism.GUI.frames.touristgroup.modify;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.tourism.BUS.TouristGroupController;
import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.frames.touristgroup.Resources;
import com.tourism.GUI.frames.touristgroup.TestFrame;
import com.tourism.GUI.frames.touristgroup.TouristGroupMainPanel;

public class TouristGroupModify extends JPanel {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass().getName());
	TouristGroupBasicModifyPanel basicPanel;
	TouristGroupCustomerTable customerPanel;
	TouristGroupEmployeeTable employeePanel;
	TouristGroupModifyBottomBar bottomBar;
	GroupLayout layout;
	public static TouristGroup TG;
	public static TouristGroupController TGController = new TouristGroupController();
	public TouristGroupModify() {
		initData();
		initComp();
	}
	
	public TouristGroupModify(Long idTG) {
		TouristGroupModify.TG = TGController.getById(idTG);
		initData();
		initComp();
	}
	
	public void initData() {
		basicPanel = new TouristGroupBasicModifyPanel();
		customerPanel = new TouristGroupCustomerTable();
		employeePanel = new TouristGroupEmployeeTable();
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
						)
				.addComponent(bottomBar));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addComponent(basicPanel, 0, TGModifyResource.MODIFY_BASIC_PANEL_HEIGHT, TGModifyResource.MODIFY_BASIC_PANEL_HEIGHT)
						.addGroup(layout.createParallelGroup(Alignment.CENTER)
								.addComponent(customerPanel, Alignment.CENTER)
								.addComponent(employeePanel, Alignment.CENTER)
								)
						.addComponent(bottomBar)
						)
				);
		this.setLayout(layout);
		setPreferredSize(new Dimension(1110,700));
	}
	
	public static void main(String[] args) {
		new TestFrame(new TouristGroupModify());
	}
}
