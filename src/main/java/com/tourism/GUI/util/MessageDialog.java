package com.tourism.GUI.util;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class MessageDialog {
	JDialog dialog;
	JLabel lblMessage;
	JButton btnYes;
	
	public MessageDialog(String message) {
		dialog = new JDialog();
		dialog.setModal(true);
		
		lblMessage = new JLabel(message);
		btnYes = new JButton("OK");
		
		lblMessage.setBounds(10, 0, 200, 50);
		btnYes.setBounds(50, 60, 100, 24);
		btnYes.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				btnYes.getActionCommand();
				dialog.dispose();
			}
		});
		
		dialog.setPreferredSize(new Dimension(250,150));
		dialog.setLayout(null);
		dialog.add(lblMessage);
		dialog.add(btnYes);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	public static void main(String[] args) {
		if(new ConfirmDialog("HOHO").confirm()) {
			System.out.println("yes");
		} else {
			System.out.print("NO");
		};
	}
}
