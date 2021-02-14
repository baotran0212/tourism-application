package com.tourism.GUI.frames.touristgroup.modify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.tourism.BUS.TouristGroupController;
import com.tourism.GUI.frames.touristgroup.TouristGroupMainPanel;
import com.tourism.GUI.util.ConfirmDialog;

public class TouristGroupModifyBottomBar extends JPanel {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass().getName());
	JButton btnSave;
	JButton btnDelete;
	JButton btnCancel;
	GroupLayout layout;
	TouristGroupController touristGroupController = new TouristGroupController();
	
	public TouristGroupModifyBottomBar() {
		initData();
		initComp();
	}
	
	public void initData() {
		layout = new GroupLayout(this);
		btnSave = new JButton("Lưu");
		btnDelete = new JButton("Xóa");
		btnCancel = new JButton("Hủy");
	}
	
	public void initComp() {
		btnCancel.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				TouristGroupMainPanel.initManagerPanel();
			}
		});
		
		btnSave.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				TouristGroupBasicModifyPanel.commitToSelectedTouristGroup();
				touristGroupController.saveWithRelationships(TouristGroupMainPanel.selectedTouristGroup);
				TouristGroupMainPanel.initManagerPanel();
			}
		});
		
		btnDelete.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if(TouristGroupMainPanel.selectedTouristGroup != null && TouristGroupMainPanel.selectedTouristGroup.getId() != null) {
					if(new ConfirmDialog("Xác nhận xóa").confirm())
						touristGroupController.changeStatusToDeleted(TouristGroupMainPanel.selectedTouristGroup);
					TouristGroupMainPanel.initManagerPanel();
				}
			}
		});
		
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING,layout.createSequentialGroup()
						.addContainerGap(0, Short.MAX_VALUE)
						.addComponent(btnSave)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnCancel)
						.addGap(0, 0, Short.MAX_VALUE)
						.addComponent(btnDelete)));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(btnSave)
						.addComponent(btnCancel)
						.addComponent(btnDelete)
						));
		this.setLayout(layout);
	}
}
