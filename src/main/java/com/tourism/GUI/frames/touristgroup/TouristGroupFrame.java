package com.tourism.GUI.frames.touristgroup;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.*;

import com.tourism.DAL.TourPositionRepository;
import com.tourism.DAL.TouristGroupRepository;
import com.tourism.DTO.TourPosition;
import com.tourism.DTO.TouristGroup;

public class TouristGroupFrame extends JPanel {
	private static final long serialVersionUID = 1L;
	public static List<TouristGroup> touristGroups;
	List<TourPosition> tourPosition; 
	SearchPanel searchPanel;
	TouristGroupManagerTable touristGroupManagerTable;
	
	public TouristGroupFrame() {
		initData();
		initComp();
	}
	
	public void initData() {
		touristGroups = new TouristGroupRepository().findAll();
		this.tourPosition = new TourPositionRepository().findAll();
		searchPanel = new SearchPanel();
		touristGroupManagerTable = new TouristGroupManagerTable();
	}
	
	public void initComp() {
		searchPanel.setPreferredSize(Resources.SEARCH_PANEL);
		touristGroupManagerTable.setPreferredSize(new Dimension(1000, 400));
		
		this.add(searchPanel);
		this.add(touristGroupManagerTable);
		this.setPreferredSize(new Dimension(1110,700));
		this.setBackground(Color.CYAN);
	}
	public static void main(String[] args) {
		new TestFrame();
	}
}

class TestFrame extends JFrame {
	TouristGroupFrame tg = new TouristGroupFrame();
	private static final long serialVersionUID = 1L;
	public TestFrame() {
		init();
	}
	public void init() {
		add(tg);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
