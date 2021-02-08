package com.tourism.GUI.frames.touristgroup;

import java.awt.Color;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.util.DatePicker;
import com.tourism.GUI.util.IconUtil;

public class SearchPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	GroupLayout layout;
	JLabel lblId;
	JTextField txtId;
	JPanel pnlId;
	
	JLabel lblName;
	JTextField txtName;
	JPanel pnlName;
	
	JLabel lblTourName;
	JComboBox<String> cbxTourName;
	JPanel pnlTourName;
	
	JLabel lblDepatureDate;
	JTextField txtDepatureDate;
	JButton btnDepatureDate;
	JPanel pnlDepatureDate;
	
	JLabel lblEndDate;
	JTextField txtEndDate;
	JButton btnEndDate;
	JPanel pnlEndDate;
	
	JLabel lblStatus;
	JComboBox<String> cbxStatus;
	JPanel pnlStatus;
	
	JButton btnCreate;
	JButton btnSearch;
	
	public SearchPanel() {
		TouristGroupFrame.touristGroups.add(new TouristGroup());
		initData();
		initComp();
	}
	
	public void initData() {	
		layout = new GroupLayout(this);
		lblId = new JLabel("Mã đoàn");
		txtId = new JTextField();
		pnlId = new JPanel();
		
		lblName = new JLabel("Tên đoàn");
		txtName = new JTextField();
		pnlName = new JPanel();
		
		lblTourName = new JLabel("Tên tour");
		cbxTourName = new JComboBox<String>(new String[] {});
		pnlTourName = new JPanel();
		
		lblDepatureDate = new JLabel("Ngày khởi hành");
		txtDepatureDate = new JTextField();
		btnDepatureDate = new JButton(Resources.CALENDAR_ICON);
		pnlDepatureDate = new JPanel();
		
		lblEndDate = new JLabel("Ngày kết thúc");
		txtEndDate = new JTextField();
		btnEndDate = new JButton(Resources.CALENDAR_ICON);
		pnlEndDate = new JPanel();
		
		lblStatus = new JLabel("Trạng thái");
		cbxStatus = new JComboBox<String>(new String[] {"active", "deleted"});
		pnlStatus = new JPanel();

		btnCreate = new JButton("Thêm đoàn");
		btnSearch = new JButton("Tìm");
	}
	
	public void initComp() {
		JComponent[] compPanelSearchItems = {pnlId, pnlName, pnlTourName, pnlDepatureDate, pnlEndDate, pnlStatus};
		for (JComponent comp : compPanelSearchItems) {
			comp.setPreferredSize(Resources.INPUT_SEARCH_PANEL);
			comp.setBackground(Color.BLACK);
		}
		
		JComponent[] compInputs = {txtId, txtName,cbxTourName, txtDepatureDate, txtEndDate, cbxStatus};
		for (JComponent comp : compInputs) {
			comp.setPreferredSize(Resources.INPUT_SEARCH_TEXTFIELD);
		}
		
		pnlId.add(lblId);
		pnlId.add(txtId);
		
		pnlName.add(lblName);
		pnlName.add(txtName);
		
		pnlTourName.add(lblTourName);
		pnlTourName.add(cbxTourName);
		
		pnlDepatureDate.add(lblDepatureDate);
		pnlDepatureDate.add(txtDepatureDate);
		pnlDepatureDate.add(btnDepatureDate);
		
		pnlEndDate.add(lblEndDate);
		pnlEndDate.add(txtEndDate);
		pnlEndDate.add(btnEndDate);
		
		pnlStatus.add(lblStatus);
		pnlStatus.add(cbxStatus);
		
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(lblId)
						.addComponent(lblName)
						.addComponent(lblTourName))
				.addGroup(layout.createParallelGroup()
						.addComponent(txtId)
						.addComponent(txtName)
						.addComponent(cbxTourName))
				.addGroup(layout.createParallelGroup()
						.addComponent(lblDepatureDate)
						.addComponent(lblEndDate)
						.addComponent(lblStatus))
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addComponent(txtDepatureDate)
										.addComponent(txtEndDate))
								.addGroup(layout.createParallelGroup()
										.addComponent(btnDepatureDate)
										.addComponent(btnEndDate)))
						.addComponent(cbxStatus))
				.addGap(25)
				.addGroup(layout.createParallelGroup()
						.addComponent(btnCreate)
						.addComponent(btnSearch))
				);
		
		layout.setVerticalGroup(
				layout.createParallelGroup(Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(lblId)
								.addComponent(txtId)
								.addComponent(lblDepatureDate)
								.addComponent(txtDepatureDate)
								.addComponent(btnDepatureDate))
						.addGroup(layout.createParallelGroup()
								.addComponent(lblName)
								.addComponent(txtName)
								.addComponent(lblEndDate)
								.addComponent(txtEndDate)
								.addComponent(btnEndDate))
						.addGroup(layout.createParallelGroup()
								.addComponent(lblTourName)
								.addComponent(cbxTourName)
								.addComponent(lblStatus)
								.addComponent(cbxStatus))
						)
				.addGap(100)
				.addGroup(layout.createSequentialGroup()
						.addComponent(btnCreate)
						.addGap(FRAMEBITS)
						.addComponent(btnSearch)
						)
				);
	}
}
