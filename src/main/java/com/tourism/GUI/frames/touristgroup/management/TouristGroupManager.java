package com.tourism.GUI.frames.touristgroup.management;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import com.tourism.DAL.TourPositionRepository;
import com.tourism.DAL.TouristGroupRepository;
import com.tourism.GUI.frames.touristgroup.Resources;

public class TouristGroupManager extends JPanel {
	private static final long serialVersionUID = 1L;
	static TouristGroupDetailPanel detailPanel;
	TouristGroupSearchPanel searchPanel;
	TouristGroupManagerTablePanel touristGroupManagerTable;
	GroupLayout layout;
	
	public TouristGroupManager() {
		initData();
		initComp();
	}
	
	public void initData() {
		detailPanel = new TouristGroupDetailPanel();
		searchPanel = new TouristGroupSearchPanel();
		touristGroupManagerTable = new TouristGroupManagerTablePanel();
		layout = new GroupLayout(this);
	}
	
	public void initComp() {
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(detailPanel)
				.addComponent(searchPanel)
				.addComponent(touristGroupManagerTable));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(detailPanel, 100, Resources.DETAIL_HEIGHT, Resources.DETAIL_HEIGHT)
				.addComponent(searchPanel)
				.addComponent(touristGroupManagerTable));
		
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(1110,700));
		this.setBackground(Color.CYAN);
	}
}
