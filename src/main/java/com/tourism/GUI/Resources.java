package com.tourism.GUI;

import java.awt.Dimension;
import java.text.SimpleDateFormat;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.tourism.GUI.util.IconUtil;

public class Resources {
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Dimension searchPanelSize;

	public static int SQUARE_EDGE_XXS = 34;
	public static int SQUARE_EDGE_XS = 40;
	public static int SQUARE_EDGE_S = 46;
	
	public static Dimension SQUARE_XXS = new Dimension(SQUARE_EDGE_XXS, SQUARE_EDGE_XXS);
	
	public static int INPUT_HEIGHT_M = 34;
	public static int INPUT_WIDTH_M = 200;
	public static int INPUT_HEIGHT_L = 38;
	public static int INPUT_WIDTH_L = 240;
	public static Dimension INPUT_TYPE_DATE = new Dimension(INPUT_WIDTH_M, INPUT_HEIGHT_M);
	
	public static ImageIcon CALENDAR_ICON = IconUtil.loadIcon("src/images/noun_Calendar.png", 24, 24);
	public static ImageIcon ADD_ICON = IconUtil.loadIcon("src/images/add.png", 24, 24);
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
	
	public static String[] TOURIST_GROUP_STATUSES = new String[] {"Chưa đi", "Đang đi", "Đã hoàn thành"};
}
