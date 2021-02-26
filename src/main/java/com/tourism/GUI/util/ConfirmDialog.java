package com.tourism.GUI.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ConfirmDialog {
	
	JDialog dialog;
	JLabel lblMessage;
	JButton btnYes;
	JButton btnNo;
	Boolean answer;
	
	public ConfirmDialog(String message) {
		dialog = new JDialog();
		dialog.setModal(true);
		
		lblMessage = new JLabel(message);
		btnYes = new JButton("Có");
		btnNo = new JButton("Không");
		answer = false; 
		
		lblMessage.setBounds(10, 0, 200, 50);
		btnYes.setBounds(10, 60, 100, 24);
		btnNo.setBounds(120, 60, 100, 24);
		
		btnYes.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				btnYes.getActionCommand();
				answer = true;
				dialog.dispose();
			}
		});
		
		btnNo.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				btnYes.getActionCommand();
				answer = false;
				dialog.dispose();
			}
		});
		
		dialog.setPreferredSize(new Dimension(250,150));
		dialog.setLayout(null);
		dialog.add(lblMessage);
		dialog.add(btnYes);
		dialog.add(btnNo);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	public Boolean confirm() {
		return answer;
	}
	
	public static void main(String[] args) {
		if(new ConfirmDialog("HOHO").confirm()) {
			System.out.println("yes");
		} else {
			System.out.print("NO");
		};
	}
}
