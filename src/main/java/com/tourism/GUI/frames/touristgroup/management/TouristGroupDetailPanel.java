package com.tourism.GUI.frames.touristgroup.management;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tourism.BUS.TouristGroupController;
import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.Resources;
import com.tourism.GUI.frames.touristgroup.TouristGroupMainPanel;
import com.tourism.GUI.util.ConfirmDialog;

public class TouristGroupDetailPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass().getName());
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
	
	TouristGroupController touristGroupController;
	public TouristGroupDetailPanel() {
		initData();
		initComp();
	}
	
	public void initData() {
		touristGroupController = new TouristGroupController();
		layout = new GroupLayout(this);
		lblId = new JLabel("Mã đoàn");
		txtId = new JTextField();
		
		lblName = new JLabel("Tên đoàn");
		txtName = new JTextField();

		lblDepatureDate = new JLabel("Ngày khởi hành");
		txtDepatureDate = new JTextField();
		
		lblEndDate = new JLabel("Ngày kết thúc");
		txtEndDate = new JTextField();

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
		JTextField[] txtField = new JTextField[] {txtId, txtName, txtDepatureDate, txtEndDate, txtFoodPrice, txtTransportPrice, txtHotelPrice, txtOtherPrice, txtTour, txtStatus, txtCustomerCount, txtEmployeeCount};
		for(JTextField txt: txtField) {
			txt.setEditable(false);
			txt.setBackground(Resources.PRIMARY);
			txt.setBorder(BorderFactory.createLineBorder(Resources.PRIMARY_DARK, 1, true));
		}
		
		btnModify.setBackground(Resources.PRIMARY_DARK);
		btnModify.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if(TouristGroupMainPanel.selectedTouristGroup != null && TouristGroupMainPanel.selectedTouristGroup.getId() != null)
					TouristGroupMainPanel.initModifyPanel();
			}
		});
		btnDelete.setBackground(Resources.PRIMARY_DARK);
		btnDelete.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if(TouristGroupMainPanel.selectedTouristGroup != null && TouristGroupMainPanel.selectedTouristGroup.getId() != null) {
					if(new ConfirmDialog("Xác nhận xóa").confirm())
						touristGroupController.changeStatusToDeleted(TouristGroupMainPanel.selectedTouristGroup);
					TouristGroupMainPanel.initManagerPanel();
				}
			}
		});
		
		this.setLayout(layout);
		this.setBackground(Resources.PRIMARY);
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

	static public void reload() {
		TouristGroup TG = TouristGroupMainPanel.selectedTouristGroup;
		TouristGroupDetailPanel detailPanel = TouristGroupManager.detailPanel; 
		detailPanel.txtName.setText(TG.getName());
		detailPanel.txtId.setText(TG.getId().toString());
		detailPanel.txtDepatureDate.setText(Resources.simpleDateFormat.format(TG.getDepatureDate()));
		detailPanel.txtEndDate.setText(Resources.simpleDateFormat.format(TG.getEndDate()));
		detailPanel.txtFoodPrice.setText(TG.getFoodPrice().toString());
		detailPanel.txtTransportPrice.setText(TG.getTransportPrice().toString());
		detailPanel.txtHotelPrice.setText(TG.getHotelPrice().toString());
		detailPanel.txtOtherPrice.setText(TG.getOtherPrice().toString());
		detailPanel.txtTour.setText(TG.getTour().getName());
		detailPanel.txtStatus.setText(TG.getStatus());
		detailPanel.txtCustomerCount.setText(TG.getCustomers().size()+"");
		detailPanel.txtEmployeeCount.setText(TG.getTourPositions().size()+"");
	}
}