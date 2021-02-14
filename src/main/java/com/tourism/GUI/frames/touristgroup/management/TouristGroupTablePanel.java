package com.tourism.GUI.frames.touristgroup.management;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.Resources;
import com.tourism.GUI.frames.touristgroup.TouristGroupMainPanel;

public class TouristGroupTablePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass().getName());
	SimpleDateFormat sDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	JTable tbl;
	DefaultTableModel model;
	JScrollPane scroller;

	public TouristGroupTablePanel() {
		initData();
		initComp();
	}

	public void initData() {

		model = new DefaultTableModel(new Object[] { "Mã", "Tên đoàn", "Ngày khởi hành",
				"Ngày kết thúc", "Tổng thu", "Trạng thái"}, 0);
		tbl = new JTable(model);
		scroller = new JScrollPane(tbl);
		
		TouristGroupMainPanel.touristGroups.forEach(TG -> {
			model.addRow(new Object[] { TG.getId(), TG.getName(), TG.getDepatureDate(), TG.getEndDate(),
					TG.getFoodPrice() + TG.getTransportPrice() + TG.getHotelPrice() + TG.getOtherPrice(), TG.getStatus()});
		});
	}


	public void initComp() {
		
		tbl.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Long selectedId = (Long) tbl.getValueAt(tbl.getSelectedRow() , 0);
				
				for(TouristGroup obj : TouristGroupMainPanel.touristGroups) {
					if(obj.getId() == selectedId) {
						TouristGroupMainPanel.selectedTouristGroup=obj;
					}
				}
				TouristGroupDetailPanel.reload();
			}
		});
//		tbl.addMouseListener(new MouseListener() {
//			@Override
//			public void mouseReleased(MouseEvent e) {
//			}
//			@Override
//			public void mousePressed(MouseEvent e) {
//				Long selectedId = (Long) tbl.getValueAt(tbl.getSelectedRow() , 0);
//				TouristGroupManager.detailPanel = new DetailTouristGroupPanel(selectedId);
//				TouristGroupManager.detailPanel.initData(selectedId);
//				TouristGroupManager.detailPanel.repaint();
//				logger.info(selectedId + "");
//			}
//			@Override
//			public void mouseExited(MouseEvent e) {
//			}
//			@Override
//			public void mouseEntered(MouseEvent e) {
//			}
//			@Override
//			public void mouseClicked(MouseEvent e) {
//			}
//		});
		
		tbl.setPreferredSize(Resources.MANAGER_TABLE);
		scroller.setPreferredSize(Resources.MANAGER_TABLE_SCROLLER);
		add(scroller);
	}
}