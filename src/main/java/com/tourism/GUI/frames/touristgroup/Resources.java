package com.tourism.GUI.frames.touristgroup;

import java.awt.Dimension;
import java.text.SimpleDateFormat;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.tourism.GUI.util.IconUtil;

public class Resources {
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	Dimension searchPanelSize;
	public static ImageIcon CALENDAR_ICON = IconUtil.loadIcon("src/images/noun_Calendar.png", 20, 20);
	public static ImageIcon ADD_ICON = IconUtil.loadIcon("src/images/add.png", 30, 30);
	public static Dimension CLENDAR_BUTTON = new Dimension(10,10);
	
	public static int DETAIL_WIDTH = 1000;
	public static int DETAIL_HEIGHT = 200;
	public static Dimension DETAIL_PANEL = new Dimension(DETAIL_WIDTH, DETAIL_HEIGHT);
	
	public static int SEARCH_PANEL_WIDTH = 1000;
	public static int SEARCH_PANEL_HEIGHT = 120;
	public static Dimension SEARCH_PANEL = new Dimension(SEARCH_PANEL_WIDTH, SEARCH_PANEL_HEIGHT);
	public static Dimension INPUT_SEARCH_TEXTFIELD = new Dimension(100,22);
	public static Dimension INPUT_SEARCH_PANEL = new Dimension(200,50);
	
	public static Dimension MANAGER_TABLE = new Dimension(900,800);
	public static Dimension MANAGER_TABLE_SCROLLER = new Dimension(1000, 300);
	public static Dimension MANAGER_TABLE_PANEL = new Dimension(1000,300);
	
}
