package com.tourism.GUI;

import javax.swing.JFrame;

public class LoginFrame extends JFrame{
	public LoginFrame() {
		init();
	};
	public void init() {
		setSize(400,500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}
}
