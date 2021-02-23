package com.tourism.GUI.frames.touristgroup.modify;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.tourism.BUS.TouristGroupController;
import com.tourism.DTO.TouristGroupCost;
import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.CustomTable;
import com.tourism.GUI.Resources;
import com.tourism.GUI.frames.touristgroup.TouristGroupMainPanel;
import com.tourism.GUI.util.ConfirmDialog;
import com.tourism.GUI.util.MessageDialog;

public class TouristGroupCostTable extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TouristGroup TG;
	
	GroupLayout layout;
	JLabel lblHotelList;
	
	JButton btnAdd;
	
	JPanel pnlSelectedHotel;
	JLabel lblSelectedHotelId;
	JLabel lblSeletecHotel;
	
	JButton btnRemove;
	
	JScrollPane scroller;
	JTable tbl;
	DefaultTableModel model;
	TouristGroupController touristGroupController;
	public TouristGroupCostTable() {
		TG = TouristGroupMainPanel.selectedTouristGroup;
		initData();
		initComp();
	}
	
	public void initData() {
		touristGroupController = new TouristGroupController();
		layout = new GroupLayout(this);
		model = new DefaultTableModel(new Object[] {"Mã", "Tên", "Giá", "Địa chỉ"} , 0);
		
		lblHotelList = new JLabel("Danh sách khách sạn");
		
		btnAdd = new JButton(Resources.ADD_ICON);
		
		pnlSelectedHotel = new JPanel();
		lblSeletecHotel = new JLabel("Khách sạn:");
		lblSelectedHotelId = new JLabel();
		
		btnRemove = new JButton("Xóa");
		
		tbl = new CustomTable(model);
		scroller = new JScrollPane(tbl);
	}
	
	public void initComp() {
		btnAdd.setBackground(Resources.PRIMARY);
		btnAdd.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				Optional<TouristGroupCost> opt = new AddTouristGroupCostToTouristGroupDialog(TG).addHotelToTouristGroup();
				opt.ifPresent(hotel -> {
					TG.getTouristGroupCosts().add(hotel);
				});
				loadTable();
			}
		});
		
		pnlSelectedHotel.add(lblSeletecHotel);
		pnlSelectedHotel.add(lblSelectedHotelId);
		
		btnRemove.setBackground(Resources.PRIMARY_DARK);
		btnRemove.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if(new ConfirmDialog("Xóa khách sạn khỏi danh sách?").confirm()) {
					Long hotelId = Long.valueOf(lblSelectedHotelId.getText());
					TouristGroupMainPanel.selectedTouristGroup.getTouristGroupCosts().removeIf(
							hotel->(hotel.getId() == hotelId ));
					loadTable();
				}
			}
		});
		
		loadTable();
		
		tbl.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				String hotelId = tbl.getValueAt(tbl.getSelectedRow(), 0).toString();
				lblSelectedHotelId.setText(hotelId);
			}
		});
		tbl.setBackground(Resources.PRIMARY);
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(lblHotelList))
				.addGroup(layout.createSequentialGroup()
						.addComponent(btnAdd, Resources.SQUARE_EDGE_XXS ,Resources.SQUARE_EDGE_XXS, Resources.SQUARE_EDGE_XXS)
						.addContainerGap()
						.addComponent(pnlSelectedHotel)
						.addComponent(btnRemove))
				.addComponent(scroller));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(lblHotelList)
				.addGroup(layout.createParallelGroup()
						.addComponent(btnAdd, Resources.SQUARE_EDGE_XXS, Resources.SQUARE_EDGE_XXS, Resources.SQUARE_EDGE_XXS)
						.addComponent(pnlSelectedHotel)
						.addComponent(btnRemove))
				.addComponent(scroller));
		this.setLayout(layout);
	}
	
	public void loadTable() {
		model.setRowCount(0);
		if(TouristGroupMainPanel.selectedTouristGroup.getTouristGroupCosts() != null )
			TouristGroupMainPanel.selectedTouristGroup.getTouristGroupCosts().forEach(hotel ->{
				model.addRow(new Object[] {
				});
			});;
	}
}
