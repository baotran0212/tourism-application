package com.tourism.GUI.frames.tour;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * TourismFrame
 */
public class TourFrame extends JPanel {

	private static final long serialVersionUID = 1L;

	public TourFrame() {
		initComp();
	}

	public void initComp() {
		this.setPreferredSize(new Dimension(1110, 700));
		this.setBackground(Color.GRAY);
	}
	
	public static void main(String[] args) {
		new TestFrame();
	}
}

class TestFrame extends JFrame {
	
	public TestFrame() {
		initComp();
	}
	
	public void initComp() {
		this.add(new TourFrame());
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}