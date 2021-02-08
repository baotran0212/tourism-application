package com.tourism.GUI.frames.touristgroup;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TouristGroupManagerTable extends JPanel {
	private static final long serialVersionUID = 1L;
	JTable tbl;
	DefaultTableModel model; 
	JScrollPane scroller;
	
	public TouristGroupManagerTable() {
		initData();
		initComp();
	}
	
	public void initData() {
		
		
		model = new DefaultTableModel(
				new Object[][] {}, 
				new Object[] {"Mã", "Tên đoàn", "Ngày khởi hành", "Ngày kết thúc", "Tổng thu", "Trạng thái", "Hành động"} );
		tbl = new JTable(model);
		scroller = new JScrollPane(tbl);
		
		TouristGroupFrame.touristGroups.forEach(TG->{
			model.addRow(new Object[] {TG.getId(), TG.getName(), TG.getDepatureDate(), TG.getEndDate(), TG.getOtherPrice(), TG.getStatus()});
		});
	}
	public void initComp() {
		
		scroller.setSize(Resources.TG_MANAGER_TABLE_SCROLLER);
		add(scroller);
	}
}
