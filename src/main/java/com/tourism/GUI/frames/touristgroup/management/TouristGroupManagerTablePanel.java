package com.tourism.GUI.frames.touristgroup.management;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.mysql.cj.protocol.x.ReusableOutputStream;
import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.frames.touristgroup.Resources;
import com.tourism.GUI.frames.touristgroup.TouristGroupMainPanel;

public class TouristGroupManagerTablePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass().getName());
	SimpleDateFormat sDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	JTable tbl;
	DefaultTableModel model;
	JScrollPane scroller;

	public TouristGroupManagerTablePanel() {
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
					TG.getOtherPrice(), TG.getStatus()});
		});
	}


	public void initComp() {
		
		tbl.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Long selectedId = (Long) tbl.getValueAt(tbl.getSelectedRow() , 0);
				
				for(TouristGroup obj : TouristGroupMainPanel.touristGroups) {
					if(obj.getId() == selectedId) {
						logger.info(obj.toString());
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