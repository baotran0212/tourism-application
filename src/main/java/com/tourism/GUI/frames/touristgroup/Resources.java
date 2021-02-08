package com.tourism.GUI.frames.touristgroup;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.tourism.GUI.util.IconUtil;

public class Resources {
	
	Dimension searchPanelSize;
	public static ImageIcon CALENDAR_ICON = IconUtil.loadIcon("src/images/noun_Calendar.png", 20, 20);
	
	public static int SEARCH_PANEL_WIDTH = 1000;
	public static int SEARCH_PANEL_HEIGHT = 150;
	public static Dimension SEARCH_PANEL = new Dimension(SEARCH_PANEL_WIDTH, SEARCH_PANEL_HEIGHT);
	public static Dimension INPUT_SEARCH_TEXTFIELD = new Dimension(100,22);
	public static Dimension INPUT_SEARCH_PANEL = new Dimension(200,50);
	
	public static Dimension TG_MANAGER_TABLE_SCROLLER = new Dimension(1000, 300);
}
