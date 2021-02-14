package com.tourism.GUI.frames.analysis;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AnalysisMainPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public AnalysisMainPanel() {
		initComp();
	}
	
	public void initComp() {
		this.setPreferredSize(new Dimension(1110, 700));
		this.setBackground(Color.BLUE);
	}
	
	public static void main(String[] args) {
		
	}
}
