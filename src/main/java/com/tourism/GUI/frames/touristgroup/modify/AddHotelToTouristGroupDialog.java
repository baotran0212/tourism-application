package com.tourism.GUI.frames.touristgroup.modify;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.tourism.BUS.HotelController;
import com.tourism.BUS.TouristGroupController;
import com.tourism.DTO.Hotel;
import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.CustomTable;

public class AddHotelToTouristGroupDialog {
	JDialog dialog;
	JPanel pnl;
	GroupLayout layout;
	
	DefaultTableModel model;
	JTable tbl;
	JScrollPane scroller;
	
	JButton btnAdd;
	JButton btnCancel;
	
	HotelController hotelController;
	TouristGroupController touristGroupController;
	TouristGroup TG;
	Hotel selectedHotel;
	
	public AddHotelToTouristGroupDialog(TouristGroup TG) {
		this.TG = TG;
		initData();
		initComp();
	}
	
	private void initData() {
		dialog = new JDialog();
		pnl = new JPanel();
		layout = new GroupLayout(pnl);
		
		model = new DefaultTableModel(new Object[] {"Mã", "Tên", "Phí", "Địa chỉ"}, 0);
		tbl = new CustomTable(model);
		scroller = new JScrollPane(tbl);
		
		btnAdd = new JButton("Thêm");
		btnCancel = new JButton("Hủy");
		
		hotelController = new HotelController();
		touristGroupController = new TouristGroupController();
		
		hotelController.getAll().forEach(hotel -> {
			model.addRow(new Object[] {
					hotel.getId(),
					hotel.getName(),
					hotel.getPrice(),
					hotel.getStreet() + hotel.getAddress3() + hotel.getAddress2() + hotel.getAddress1()
			});
		});
	}
	
	private void initComp() {
		btnAdd.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				Long hotelId = Long.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
				selectedHotel = hotelController.getById(hotelId);
				dialog.dispose();
			}
		});
		
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				selectedHotel = null;
				dialog.dispose();
			}
		});
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(scroller)
				.addGroup(layout.createSequentialGroup()
						.addComponent(btnAdd)
						.addComponent(btnCancel)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(scroller)
				.addGroup(layout.createParallelGroup()
						.addComponent(btnAdd)
						.addComponent(btnCancel)));
		
		pnl.setLayout(layout);
		dialog.add(pnl);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	
	public Optional<Hotel> addHotelToTouristGroup(){
		return selectedHotel!=null ? Optional.of(selectedHotel) : Optional.empty();
	}
}
