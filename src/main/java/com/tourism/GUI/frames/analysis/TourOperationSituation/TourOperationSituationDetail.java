package com.tourism.GUI.frames.analysis.TourOperationSituation;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.tourism.DTO.Tour;
import com.tourism.GUI.Resources;

public class TourOperationSituationDetail extends JPanel{
	JButton btnBack;
	public TourOperationSituationDetail(Tour selectedTour) {
		btnBack = new JButton("Quay lai");
		btnBack.addMouseListener(new MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				TourOperationSituationMain.initContent();
			};
		});
		this.add(new JLabel("detaillllll"));
		this.add(btnBack);
		this.setPreferredSize(new Dimension(Resources.MAIN_CONTENT_WIDTH, Resources.MAIN_CONTENT_HEIGHT-Resources.INPUT_HEIGHT_L));
	}
}
