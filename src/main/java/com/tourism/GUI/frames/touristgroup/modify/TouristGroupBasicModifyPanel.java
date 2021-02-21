package com.tourism.GUI.frames.touristgroup.modify;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import com.tourism.BUS.TourController;
import com.tourism.DTO.Hotel;
import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.Resources;
import com.tourism.GUI.frames.touristgroup.TestFrame;
import com.tourism.GUI.frames.touristgroup.TouristGroupMainPanel;
import com.tourism.GUI.util.DatePicker;
import com.tourism.service.Validation;

public class TouristGroupBasicModifyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass().getName());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	GroupLayout layout;
	JLabel lblId;
	JTextField txtId;
	
	JLabel lblName;
	static JTextField txtName;
	
	JLabel lblDepatureDate;
	static JTextField txtDepatureDate;
	JButton btnDepatureDate;
	JPanel pnlDepatureDate;
	
	JLabel lblEnDate;
	static JTextField txtEndDate;
	JButton btnEndDate;
	JPanel pnlEndDate;
	
	JLabel lblFoodPrice;
	static JTextField txtFoodPrice;
	
	JLabel lblTransportPrice;
	static JTextField txtTransportPrice;
	
	JLabel lblHotelPrice;
	static JTextField txtHotelPrice;
	
	JLabel lblOtherPrice;
	static JTextField txtOtherPrice;
	
	JLabel lblStatus;
	static JComboBox<String> cbxStatus;
	
	static JLabel lblTourName;
	static JComboBox<String> cbxTourName;
	
	TouristGroup TG;
	TourController tourController;
	
	JPanel pnlHotelTable;
	public TouristGroupBasicModifyPanel() {	
		TG = TouristGroupMainPanel.selectedTouristGroup;
		initData();
		initComp();
	}

	public void initData() {
		tourController = new TourController();
		layout = new GroupLayout(this);
		
		lblId = new JLabel("Mã");
		txtId = new JTextField(TG.getId()!=null ? TG.getId().toString() : "");

		lblName = new JLabel("Tên đoàn");
		txtName= new JTextField(TG.getName());

		lblDepatureDate = new JLabel("Ngày khởi hành");
		txtDepatureDate= new JTextField(TG.getDepatureDate() != null ? sdf.format(TG.getDepatureDate()) : "");
		btnDepatureDate = new JButton(Resources.CALENDAR_ICON);
		pnlDepatureDate = new JPanel(new FlowLayout(0,0,0));
		
		lblEnDate = new JLabel("Ngày kết thúc");
		txtEndDate= new JTextField(TG.getEndDate() !=null ? sdf.format(TG.getEndDate()) : "");
		btnEndDate = new JButton(Resources.CALENDAR_ICON);
		pnlEndDate = new JPanel(new FlowLayout(0,0,0));
		
		lblFoodPrice = new JLabel("Phí thức ăn");
		txtFoodPrice= new JTextField(TG.getFoodPrice() !=null ? TG.getFoodPrice().toString() : "");

		lblTransportPrice = new JLabel("Phí phương tiện");
		txtTransportPrice= new JTextField( TG.getTransportPrice() != null ? TG.getTransportPrice().toString() : "");

		lblHotelPrice = new JLabel("Phí khách sạn");
		txtHotelPrice= new JTextField(TG.getHotelPrice() != null ? TG.getHotelPrice().toString() :"");

		lblOtherPrice = new JLabel("Phí khác");
		txtOtherPrice= new JTextField(TG.getOtherPrice() != null ? TG.getOtherPrice().toString() : "");

		lblStatus = new JLabel("Trạng thái");
		cbxStatus = new JComboBox<String>(Resources.TOURIST_GROUP_STATUSES);

		lblTourName = new JLabel("Tour");
		cbxTourName = new JComboBox<String>();
		tourController.getAll().forEach(tour->{
			cbxTourName.addItem(tour.getId()+ ". " + tour.getName());
			if(tour.getId() == TG.getTourId())
				cbxTourName.setSelectedItem( tour.getId() + ". "  + tour.getName() );
		});
		
		pnlHotelTable = new JPanel();
	}
	
	public void initComp() {
		txtId.setEditable(false);
		txtId.setBackground(Resources.PRIMARY);
		txtHotelPrice.setEditable(false);
		txtHotelPrice.setBackground(Resources.PRIMARY);
		
		txtDepatureDate.setPreferredSize(Resources.INPUT_TYPE_DATE);
		btnDepatureDate.setPreferredSize(Resources.SQUARE_XXS);
		btnDepatureDate.setBackground(Resources.PRIMARY_DARK);
		btnDepatureDate.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				txtDepatureDate.setText(new DatePicker().getPickedDate("yyyy-MM-dd"));
			}
		});
		pnlDepatureDate.setBackground(Resources.PRIMARY);
		pnlDepatureDate.add(txtDepatureDate);
		pnlDepatureDate.add(btnDepatureDate);
		
		
		txtEndDate.setPreferredSize(Resources.INPUT_TYPE_DATE);
		btnEndDate.setPreferredSize(Resources.SQUARE_XXS);
		btnEndDate.setBackground(Resources.PRIMARY_DARK);
		btnEndDate.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				txtEndDate.setText(new DatePicker().getPickedDate("yyyy-MM-dd"));
			}
		});
		pnlEndDate.setBackground(Resources.PRIMARY);
		pnlEndDate.add(txtEndDate);
		pnlEndDate.add(btnEndDate);
		
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
						.addComponent(txtId, 100, Resources.INPUT_WIDTH_M, Resources.INPUT_WIDTH_L)
						.addComponent(txtName, 100, Resources.INPUT_WIDTH_M, Resources.INPUT_WIDTH_L)
						.addComponent(pnlDepatureDate)
						.addComponent(pnlEndDate))
				.addGroup(layout.createParallelGroup()
						.addComponent(lblFoodPrice)
						.addComponent(lblTransportPrice)
						.addComponent(lblHotelPrice)
						.addComponent(lblOtherPrice))
				.addGroup(layout.createParallelGroup()
						.addComponent(txtFoodPrice, 100, Resources.INPUT_WIDTH_M, Resources.INPUT_WIDTH_L)
						.addComponent(txtTransportPrice, 100, Resources.INPUT_WIDTH_M, Resources.INPUT_WIDTH_L)
						.addComponent(txtHotelPrice, 100, Resources.INPUT_WIDTH_M, Resources.INPUT_WIDTH_L)
						.addComponent(txtOtherPrice, 100, Resources.INPUT_WIDTH_M, Resources.INPUT_WIDTH_L))
				.addGroup(layout.createParallelGroup()
						.addComponent(lblStatus)
						.addComponent(lblTourName))
				.addGroup(layout.createParallelGroup()
						.addComponent(cbxStatus)
						.addComponent(cbxTourName))
				);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.CENTER)
						.addComponent(lblId)
						.addComponent(txtId)
						.addComponent(lblFoodPrice)
						.addComponent(txtFoodPrice)
						.addComponent(lblStatus)
						.addComponent(cbxStatus))
				.addGroup(layout.createParallelGroup(Alignment.CENTER)
						.addComponent(lblName)
						.addComponent(txtName)
						.addComponent(lblTransportPrice)
						.addComponent(txtTransportPrice)
						.addComponent(lblTourName)
						.addComponent(cbxTourName))
				.addGroup(layout.createParallelGroup(Alignment.CENTER)
						.addComponent(lblDepatureDate)
						.addComponent(pnlDepatureDate)
						.addComponent(lblHotelPrice)
						.addComponent(txtHotelPrice, 10, Resources.INPUT_HEIGHT_M, Resources.INPUT_HEIGHT_M))
				.addGroup(layout.createParallelGroup(Alignment.CENTER)
						.addComponent(lblEnDate)
						.addComponent(pnlEndDate)
						.addComponent(lblOtherPrice)
						.addComponent(txtOtherPrice, 10, Resources.INPUT_HEIGHT_M, Resources.INPUT_HEIGHT_M))
				);
		this.setLayout(layout);
		this.setBackground(Resources.PRIMARY);
	}
	
	public static Boolean commitToSelectedTouristGroup() {
		TouristGroup TG=TouristGroupMainPanel.selectedTouristGroup;
		if(txtName.getText().equals(""))
			return false;
		if(!Validation.checkDate(txtDepatureDate.getText()))
			return false;
		if(!Validation.checkDate(txtEndDate.getText()))
			return false;
		if(Validation.checkDigit(txtFoodPrice.getText()))
			return false;
		if(Validation.checkDigit(txtFoodPrice.getText()))
			return false;
		if(Validation.checkDigit(txtOtherPrice.getText()))
			return false;
		try {
			if( Resources.simpleDateFormat.parse(txtDepatureDate.getText())
					.after(Resources.simpleDateFormat.parse(txtEndDate.getText())))
				return false;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if(cbxStatus.getSelectedItem().toString().equals(""))
			return false;
		
		TG.setName(txtName.getText());
		try {
			TG.setDepatureDate(Resources.simpleDateFormat.parse(txtDepatureDate.getText()));
			TG.setEndDate(Resources.simpleDateFormat.parse(txtEndDate.getText()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		TG.setFoodPrice(Double.valueOf(txtFoodPrice.getText()));
		TG.setTransportPrice(Double.valueOf(txtTransportPrice.getText()));
		TG.setHotelPrice(Double.valueOf(txtHotelPrice.getText()));
		TG.setOtherPrice(Double.valueOf(txtOtherPrice.getText()));
		TG.setStatus(cbxStatus.getSelectedItem().toString());
		String tourName = cbxTourName.getSelectedItem().toString();
		TG.setTourId(Long.valueOf(tourName.substring(0, tourName.lastIndexOf("."))));
		return true;
	}
	
	public static void updateHotelPriceTextField() {
		Double price = Double.valueOf(0);
		for(Hotel hotel : TouristGroupMainPanel.selectedTouristGroup.getHotels()) {
			price += hotel.getPrice();
		}
		TouristGroupMainPanel.selectedTouristGroup.setHotelPrice(price);
		txtHotelPrice.setText(price.toString());
	}	
	
	public static void main(String[] args) {
		new TestFrame(new TouristGroupModify());
	}
}
