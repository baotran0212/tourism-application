package com.tourism.GUI.frames.touristgroup.modify;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.tourism.BUS.TouristGroupCostController;
import com.tourism.DAL.CostTypeRepository;
import com.tourism.BUS.TouristGroupController;
import com.tourism.DTO.TouristGroupCost;
import com.tourism.DTO.CostType;
import com.tourism.DTO.TouristGroup;
import com.tourism.GUI.CustomTable;
import com.tourism.GUI.MainFrame;
import com.tourism.GUI.Resources;
import com.tourism.GUI.frames.touristgroup.TouristGroupMainPanel;

public class AddTouristGroupCostToTouristGroupDialog {
	JDialog dialog;
	JPanel pnl;
	GroupLayout layout;
	
	JLabel lblTotalCost;
	JTextField txtTotalCost;
	
	JLabel lblDescription;
	JComboBox<String> cbxCostType;
	JButton btnAddCostType;
	JPanel pnlAddCostType;
	JTextArea txaDescription;
	
	JButton btnAdd;
	JButton btnCancel;
	
	TouristGroupCostController touristGroupCostController;
	TouristGroupController touristGroupController;
	TouristGroupCost selectedTouristGroupCost;
	
	public AddTouristGroupCostToTouristGroupDialog() {
		this.selectedTouristGroupCost = new TouristGroupCost();
		initData();
		initComp();
	}
	
	public AddTouristGroupCostToTouristGroupDialog(TouristGroupCost cost) {
		this.selectedTouristGroupCost = cost;
		initData();
		initComp();
	}
	
	private void initData() {
		dialog = new JDialog();
		pnl = new JPanel();
		layout = new GroupLayout(pnl);
		
		lblTotalCost = new JLabel("Tổng chi phí");
		txtTotalCost = new JTextField();

		lblDescription = new JLabel("Chi tiết:");
		cbxCostType = new JComboBox<String>();
		btnAddCostType = new JButton(Resources.ADD_ICON_S);
		pnlAddCostType = new JPanel(new FlowLayout(FlowLayout.LEADING));
		txaDescription = new JTextArea();
		
		btnAdd = new JButton("Lưu");
		btnCancel = new JButton("Hủy");
		
		touristGroupCostController = new TouristGroupCostController();
		touristGroupController = new TouristGroupController();
	}
	
	private void initComp() {
		if(selectedTouristGroupCost.getId()!=null) {
			txtTotalCost.setText(selectedTouristGroupCost.getTotalPrice().toString());
			txaDescription.setText(selectedTouristGroupCost.getDescription());
		}
		
		pnlAddCostType.add(cbxCostType);
		pnlAddCostType.add(btnAddCostType);
		List<CostType> costTypes = new CostTypeRepository().findAll();
		costTypes.forEach(costType->{
			cbxCostType.addItem(costType.getId() +". "+ costType.getName());
		});
		
		btnAddCostType.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String selectedItem = cbxCostType.getSelectedItem().toString();
				txaDescription.setText(txaDescription.getText() + "\n" 
			+ selectedItem.substring(selectedItem.indexOf(".")+1) + " : "
			+ new CostTypeRepository().findById(Long.parseLong(selectedItem.substring(0,selectedItem.indexOf(".")))).get().getDescription()
			);
			}
		});
		
		btnAdd.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				selectedTouristGroupCost.setTouristGroupId(TouristGroupMainPanel.selectedTouristGroup.getId());
				selectedTouristGroupCost.setTotalPrice(Double.parseDouble(txtTotalCost.getText()));
				selectedTouristGroupCost.setDescription(txaDescription.getText());
				dialog.dispose();
			}
		});
		
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				selectedTouristGroupCost = null;
				dialog.dispose();
			}
		});
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(lblTotalCost)
						.addComponent(txtTotalCost))
				.addGroup(layout.createSequentialGroup()
						.addComponent(lblDescription)
						.addComponent(pnlAddCostType))
				.addComponent(txaDescription)
				.addGroup(layout.createSequentialGroup()
						.addComponent(btnAdd)
						.addComponent(btnCancel))
				);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.CENTER)
						.addComponent(lblTotalCost)
						.addComponent(txtTotalCost,Resources.INPUT_HEIGHT_S, Resources.INPUT_HEIGHT_S, Resources.INPUT_HEIGHT_S))
				.addGroup(layout.createParallelGroup(Alignment.CENTER)
						.addComponent(lblDescription)
						.addComponent(pnlAddCostType, Resources.INPUT_HEIGHT_M, Resources.INPUT_HEIGHT_M, Resources.INPUT_HEIGHT_M))
				.addComponent(txaDescription)
				.addGroup(layout.createParallelGroup()
						.addComponent(btnAdd)
						.addComponent(btnCancel))
				);
		
		pnl.setBackground(Resources.SECONDARY);
		pnl.setPreferredSize(Resources.SQUARE_XXL);
		pnl.setLayout(layout);
		dialog.add(pnl);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	
	public Optional<TouristGroupCost> addCostToTouristGroup(){
		selectedTouristGroupCost = touristGroupCostController.create(selectedTouristGroupCost);
		return selectedTouristGroupCost!=null ? Optional.of(selectedTouristGroupCost) : Optional.empty();
	}
	
	public TouristGroupCost modifyCostTouristGroup(){
		selectedTouristGroupCost = touristGroupCostController.modify(selectedTouristGroupCost);
		System.out.print("saved" + selectedTouristGroupCost);
		return selectedTouristGroupCost;
	}
	
	public static void main(String[] args) {
		new AddTouristGroupCostToTouristGroupDialog();
	}
}
