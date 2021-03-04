package com.tourism.GUI.frames.analysis;

import java.awt.ScrollPane;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.tourism.BUS.TourController;
import com.tourism.BUS.TouristGroupController;
import com.tourism.GUI.Resources;

public class TourOperationSituation extends JPanel {
	TourController tourController;
	TouristGroupController touristGroupController;
	
	JLabel lblTourSelected;
	JComboBox<String> cbxTourSelected;
	JLabel lblFromDate;
	JTextField txtFromDate;
	JButton btnFromDate;
	JLabel lblToDate;
	JTextField txtToDate;
	JButton btnToDate;
	JButton btnApplyAnalysis;
	JLabel lblSales;
	JLabel lblSalesValue;
	JLabel lblRevenue;
	JLabel lblRevenueValue;
	JLabel lblCountTouristGroup;
	JLabel lblCountTouristGroupValue;
	JPanel pnlAnalysis;
	GroupLayout layout;
	
	DefaultTableModel model;
	JTable tblTouristGroup;
	JScrollPane scroller;
	public TourOperationSituation() {
		initData();
		initComp();
	}
	
	private void initData() {
		tourController = new TourController();
		touristGroupController = new TouristGroupController();

	lblTourSelected = new JLabel("Tour");
	cbxTourSelected = new JComboBox<String>();
	lblFromDate = new JLabel("Từ");
	txtFromDate = new JTextField();
	btnFromDate = new JButton(Resources.CALENDAR_ICON);
	lblToDate = new JLabel("Đến");
	txtToDate = new JTextField();
	btnToDate = new JButton(Resources.CALENDAR_ICON);
	btnApplyAnalysis = new JButton("Thống kê");
	lblSales = new JLabel("Doanh số: ");
	lblSalesValue = new JLabel("0");
	lblRevenue = new JLabel("Doanh thu: ");
	lblRevenueValue = new JLabel("0");
	lblCountTouristGroup = new JLabel("Số lượng đoàn: ");
	lblCountTouristGroupValue = new JLabel("0");
	pnlAnalysis = new JPanel();
	layout = new GroupLayout(pnlAnalysis);

	model = new DefaultTableModel(new Object[] {"Mã", "Tên đoàn", "Ngày đi", "Ngày đi", "Ngày về", "Doanh thu"}, 0);
	tblTouristGroup = new JTable(model);
	scroller = new JScrollPane(tblTouristGroup);
	}
	private void initComp() {
		
		this.add(pnlAnalysis);
		this.add(scroller);
	}

}
