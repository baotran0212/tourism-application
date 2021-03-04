package com.tourism.GUI.frames.analysis.TourOperationSituation;

import java.awt.Dimension;
import java.awt.ScrollPane;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.tourism.BUS.TourController;
import com.tourism.BUS.TouristGroupController;
import com.tourism.DTO.Tour;
import com.tourism.GUI.Resources;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;//ThỐng kê tour có ba

public class TourOperationSituation extends JPanel {
	JButton btnDetail;
	
	public TourOperationSituation() {
		btnDetail = new JButton("Chi tiết");
		btnDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				TourOperationSituationMain.initDetail(TourOperationSituationMain.selectedTour);
			}
		});
		this.add(btnDetail);
		
		this.setPreferredSize(new Dimension(Resources.MAIN_CONTENT_WIDTH, Resources.MAIN_CONTENT_HEIGHT-Resources.INPUT_HEIGHT_L));
	}
	

}
