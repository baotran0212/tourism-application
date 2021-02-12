package com.tourism.GUI.frames.touristgroup.modify;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tourism.BUS.TourController;
import com.tourism.DAL.TourRepository;
import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.frames.touristgroup.TouristGroupMainPanel;

public class TouristGroupBasicModifyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass().getName());
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	GroupLayout layout;
	JLabel lblId;
	JTextField txtId;
	
	JLabel lblName;
	JTextField txtName;
	
	JLabel lblDepatureDate;
	JTextField txtDepatureDate;
	
	JLabel lblEnDate;
	JTextField txtEndDate;
	
	JLabel lblFoodPrice;
	JTextField txtFoodPrice;
	
	JLabel lblTransportPrice;
	JTextField txtTransportPrice;
	
	JLabel lblHotelPrice;
	JTextField txtHotelPrice;
	
	JLabel lblOtherPrice;
	JTextField txtOtherPrice;
	
	JLabel lblStatus;
	JComboBox<String> cbxStatus;
	
	JLabel lblTourName;
	JComboBox<String> cbxTourName;
	
	TouristGroup TG;
	TourController tourController;
	
	JPanel pnlHotelTable;
	public TouristGroupBasicModifyPanel() {
		if(TouristGroupMainPanel.selectedTouristGroup != null){
			TG = TouristGroupMainPanel.selectedTouristGroup;
		} else {
			TG = null;
		};
		 initData();
		 initComp();
	}
//	public TouristGroupBasicModifyPanel(TouristGroup TG) {
//		this.TG = TG;
//		initData();
//		initComp();
//	}
	public void initData() {
		tourController = new TourController();
		layout = new GroupLayout(this);
		
		lblId = new JLabel("Mã");
		txtId = new JTextField(TG.getId()!=null ? TG.getId().toString() : "");

		lblName = new JLabel("Tên đoàn");
		txtName= new JTextField(TG.getName());

		lblDepatureDate = new JLabel("Ngày khởi hành");
		txtDepatureDate= new JTextField(TG.getDepatureDate() != null ? sdf.format(TG.getDepatureDate()) : "");

		lblEnDate = new JLabel("Ngày kết thúc");
		txtEndDate= new JTextField(TG.getEndDate() !=null ? sdf.format(TG.getEndDate()) : "");

		lblFoodPrice = new JLabel("Phí thức ăn");
		txtFoodPrice= new JTextField(TG.getFoodPrice() !=null ? TG.getFoodPrice().toString() : "");

		lblTransportPrice = new JLabel("Phí phương tiện");
		txtTransportPrice= new JTextField( TG.getTransportPrice() != null ? TG.getTransportPrice().toString() : "");

		lblHotelPrice = new JLabel("Phí khách sạn");
		txtHotelPrice= new JTextField(TG.getHotelPrice() != null ? TG.getHotelPrice().toString() :"");

		lblOtherPrice = new JLabel("Phí khác");
		txtOtherPrice= new JTextField(TG.getOtherPrice() != null ? TG.getOtherPrice().toString() : "");

		lblStatus = new JLabel("Trạng thái");
		cbxStatus = new JComboBox<String>(new String[] {"Chưa đi", "Đang đi", "Đã hoàn thành"});

		lblTourName = new JLabel("Tour");
		cbxTourName = new JComboBox<String>();
		tourController.getAll().forEach(tour->{
			cbxTourName.addItem(tour.getName());
		});
		
		pnlHotelTable = new JPanel();
	}
	
	public void initComp() {
		cbxStatus.setSelectedItem(TG.getStatus());
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(lblId)
						.addComponent(lblName)
						.addComponent(lblDepatureDate)
						.addComponent(lblEnDate))
				.addGroup(layout.createParallelGroup()
						.addComponent(txtId)
						.addComponent(txtName)
						.addComponent(txtDepatureDate)
						.addComponent(txtEndDate))
				.addGroup(layout.createParallelGroup()
						.addComponent(lblFoodPrice)
						.addComponent(lblTransportPrice)
						.addComponent(lblHotelPrice)
						.addComponent(lblOtherPrice))
				.addGroup(layout.createParallelGroup()
						.addComponent(txtFoodPrice)
						.addComponent(txtTransportPrice)
						.addComponent(txtHotelPrice)
						.addComponent(txtOtherPrice))
				.addGroup(layout.createParallelGroup()
						.addComponent(lblStatus)
						.addComponent(lblTourName))
				.addGroup(layout.createParallelGroup()
						.addComponent(cbxStatus)
						.addComponent(cbxTourName))
				.addComponent(pnlHotelTable)
				);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(lblId)
						.addComponent(txtId)
						.addComponent(lblFoodPrice)
						.addComponent(txtFoodPrice)
						.addComponent(lblStatus)
						.addComponent(cbxStatus))
				.addGroup(layout.createParallelGroup()
						.addComponent(lblName)
						.addComponent(txtName)
						.addComponent(lblTransportPrice)
						.addComponent(txtTransportPrice)
						.addComponent(lblTourName)
						.addComponent(cbxTourName))
				.addGroup(layout.createParallelGroup()
						.addComponent(lblDepatureDate)
						.addComponent(txtDepatureDate)
						.addComponent(lblHotelPrice)
						.addComponent(txtHotelPrice))
				.addGroup(layout.createParallelGroup()
						.addComponent(lblEnDate)
						.addComponent(txtEndDate)
						.addComponent(lblOtherPrice)
						.addComponent(txtOtherPrice))
				.addComponent(pnlHotelTable)
				);
		this.setLayout(layout);
	}
}
