package com.tourism.GUI.frames.touristgroup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.*;

import com.tourism.DAL.TourPositionRepository;
import com.tourism.DAL.TouristGroupRepository;
import com.tourism.DTO.TourPosition;
import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.frames.touristgroup.management.TouristGroupManager;
import com.tourism.GUI.frames.touristgroup.modify.TouristGroupModify;

public class TouristGroupFrame extends JPanel {
	private static final long serialVersionUID = 1L;
	public static List<TouristGroup> touristGroups;
	public static JPanel mainContent;
	List<TourPosition> tourPosition; 
	
	public TouristGroupFrame() {
		super(new FlowLayout(0,0,0));
		initManagerPanel();
		this.add(mainContent);
	}
	
	public static void initManagerPanel() {
		TouristGroupFrame.touristGroups = new TouristGroupRepository().findAll();
		mainContent = new TouristGroupManager();
	}
	
	public static void initModifierPanel(Long idTG) {
		mainContent = new TouristGroupModify(idTG);
	}
	
	public static void initCreatorPanel() {
		mainContent = new TouristGroupModify();
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
