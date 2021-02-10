package com.tourism.GUI.frames.touristgroup.management;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.Date;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.frames.touristgroup.TouristGroupFrame;

public class DetailTouristGroupPanel extends JPanel {
	Logger logger = Logger.getLogger(getClass().getName());
	static TouristGroup TG;
	GroupLayout layout;
	
	JLabel lblId;
	JTextField txtId;

	JLabel lblName;
	JTextField txtName;

	JLabel lblDepatureDate;
	JTextField txtDepatureDate;

	JLabel lblEndDate;
	JTextField txtEndDate;

	JLabel lblFoodPrice;
	JTextField txtFoodPrice;

	JLabel lblTransportPrice;
	JTextField txtTransportPrice;

	JLabel lblHotelPrice;
	JTextField txtHotelPrice;
	
	JLabel lblOtherPrice;
	JTextField txtOtherPrice;
	
	JLabel lblTour;
	JTextField txtTour;
	
	JLabel lblStatus;
	JTextField txtStatus;
	
	JLabel lblCustomerCount;
	JTextField txtCustomerCount;
	
	JLabel lblEmployeeCount;
	JTextField txtEmployeeCount;

	JButton btnModify;
	JButton btnDelete;
	
	public DetailTouristGroupPanel() {
		
	}
	public DetailTouristGroupPanel(Long touristGroupId) {
		initData(touristGroupId);
		initComp();
	}
	
	public void initData(Long touristGroupId) {
		DetailTouristGroupPanel.TG = new TouristGroup();
		TouristGroupFrame.touristGroups.forEach(TG->{
			if(TG.getId() == touristGroupId) {
				DetailTouristGroupPanel.TG = TG;
			}
		});

		logger.info(TG.toString());
		layout = new GroupLayout(this);
		lblId = new JLabel("Mã đoàn" + TG.getId());
		lblId.setText("Max doan" + TG.getId());
		txtId = new JTextField(TG.getId()+"");

		lblName = new JLabel("Tên đoàn");
		txtName = new JTextField(TG.getName());
		//----
		lblName.setText(TG.getName());
		txtName.setText(TG.getName());
		//---
		lblDepatureDate = new JLabel("Ngày khởi hành");
		txtDepatureDate = new JTextField(TG.getDepatureDate()+"");
		txtDepatureDate.setText(TG.getDepatureDate()+"");
		
		lblEndDate = new JLabel("Ngày kết thúc");
		txtEndDate = new JTextField(TG.getEndDate()+"");

		lblFoodPrice = new JLabel("Phí ăn uống");
		txtFoodPrice = new JTextField();

		lblTransportPrice = new JLabel("Phí phương tiện");
		txtTransportPrice = new JTextField();

		lblHotelPrice = new JLabel("Phí khách sạn");
		txtHotelPrice = new JTextField();

		lblOtherPrice = new JLabel("Chi phí khác");
		txtOtherPrice = new JTextField();

		lblTour = new JLabel("Tour");
		txtTour = new JTextField();

		lblStatus= new JLabel("Trạng thái");
		txtStatus = new JTextField();

		lblCustomerCount= new JLabel("Số khách");
		txtCustomerCount = new JTextField();

		lblEmployeeCount= new JLabel("Số nhân viên");
		txtEmployeeCount = new JTextField();

		btnModify= new JButton("Sửa");
		btnDelete = new JButton("Xóa");
	}
	
	public void initComp() {
		btnModify.addActionListener(new BtnModifyAction(txtName));
		this.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(lblId)
						.addComponent(lblName)
						.addComponent(lblDepatureDate)
						.addComponent(lblEndDate))
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
						.addComponent(lblTour)
						.addComponent(lblStatus)
						.addComponent(lblCustomerCount)
						.addComponent(lblEmployeeCount)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(txtTour)
						.addComponent(txtStatus)
						.addComponent(txtCustomerCount)
						.addComponent(txtEmployeeCount)
						)
				.addGap(ImageObserver.FRAMEBITS)
				.addGroup(layout.createParallelGroup()
						.addComponent(btnDelete)
						.addComponent(btnModify))
				);
		
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.CENTER)
								.addComponent(lblId)
								.addComponent(txtId)
								.addComponent(lblFoodPrice)
								.addComponent(txtFoodPrice)
								.addComponent(lblTour)
								.addComponent(txtTour)
								)
						.addGroup(layout.createParallelGroup(Alignment.CENTER)
								.addComponent(lblName)
								.addComponent(txtName)
								.addComponent(lblTransportPrice)
								.addComponent(txtTransportPrice)
								.addComponent(lblStatus)
								.addComponent(txtStatus))
						.addGroup(layout.createParallelGroup(Alignment.CENTER)
								.addComponent(lblDepatureDate)
								.addComponent(txtDepatureDate)
								.addComponent(lblHotelPrice)
								.addComponent(txtHotelPrice)
								.addComponent(lblCustomerCount)
								.addComponent(txtCustomerCount))
						.addGroup(layout.createParallelGroup(Alignment.CENTER)
								.addComponent(lblEndDate)
								.addComponent(txtEndDate)
								.addComponent(lblOtherPrice)
								.addComponent(txtOtherPrice)
								.addComponent(lblEmployeeCount)
								.addComponent(txtEmployeeCount))
						)
				.addGroup(layout.createSequentialGroup()
								.addComponent(btnModify)
								.addGap(ImageObserver.FRAMEBITS)
								.addComponent(btnDelete))
				);
	}
}

class BtnModifyAction implements ActionListener{
	JTextField txt;
	public BtnModifyAction(JTextField txt) {
		this.txt = txt;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 txt.setText(DetailTouristGroupPanel.TG.getName());
	}
}

class BtnDeleteAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}