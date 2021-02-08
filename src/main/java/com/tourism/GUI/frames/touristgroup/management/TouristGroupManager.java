package com.tourism.GUI.frames.touristgroup.management;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.tourism.DAL.TourPositionRepository;
import com.tourism.DAL.TouristGroupRepository;
import com.tourism.GUI.frames.touristgroup.Resources;

public class TouristGroupManager extends JPanel {
	private static final long serialVersionUID = 1L;
	static DetailTouristGroupPanel detailPanel;
	SearchPanel searchPanel;
	TouristGroupManagerTablePanel touristGroupManagerTable;
	
	public TouristGroupManager() {
		initData();
		initComp();
	}
	
	public void initData() {
		detailPanel = new DetailTouristGroupPanel(Long.valueOf(0));
		searchPanel = new SearchPanel();
		touristGroupManagerTable = new TouristGroupManagerTablePanel();
	}
	
	public void initComp() {
		detailPanel.setPreferredSize(Resources.DETAIL_PANEL);
		searchPanel.setPreferredSize(Resources.SEARCH_PANEL);
		touristGroupManagerTable.setPreferredSize(Resources.MANAGER_TABLE_PANEL);
		
		this.add(detailPanel);
		this.add(searchPanel);
		this.add(touristGroupManagerTable);
		this.setPreferredSize(new Dimension(1110,700));
		this.setBackground(Color.CYAN);
	}
}
