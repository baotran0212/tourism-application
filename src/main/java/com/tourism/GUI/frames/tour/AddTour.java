package com.tourism.GUI.frames.tour;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.tourism.DAL.LocationRepository;
import com.tourism.DAL.TourRepository;
import com.tourism.DAL.TypeRepository;
import com.tourism.DTO.Location;
import com.tourism.DTO.Tour;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddTour extends JFrame {

	private JPanel contentPane;
	private JTextField txt_loaiHinh;
	private JTextField txt_tenTour;
	private JTextField txt_giaTour;
	private JTable table;
	
	/**
	 * Function
	 */
	List<Tour> listTour = new ArrayList<>();
	List<com.tourism.DTO.Type> listType = new ArrayList<>();
	List<com.tourism.DTO.Location> listLocation = new ArrayList<>();
	DefaultTableModel tableModel1;
	DefaultTableModel tableModel2;
	TourRepository tourRepository = new TourRepository();
	TypeRepository typeRepository = new TypeRepository();
	LocationRepository locationRepository = new LocationRepository();
	private JTable table_1;
	private JTextField txtTmKim;
	
	private void findAllLocation() {
		listLocation = locationRepository.findAll();
		tableModel1.setRowCount(0);
		listLocation.forEach((location)->{
			tableModel1.addRow(new Object[] {
					location.getId(), location.getName(), location.getStreet()
			});
		});
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TourFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TourFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TourFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TourFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTour frame = new AddTour();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddTour() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 719, 475);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(107, 9, 519, 199);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Th\u00EAm Tour");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(204, 11, 98, 27);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Lo\u1EA1i h\u00ECnh");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 52, 77, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("T\u00EAn");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 87, 77, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Gi\u00E1");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 119, 77, 14);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("M\u00F4 t\u1EA3");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(10, 156, 77, 14);
		panel_1.add(lblNewLabel_1_3);
		
		txt_loaiHinh = new JTextField();
		txt_loaiHinh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_loaiHinh.setBounds(88, 50, 116, 20);
		panel_1.add(txt_loaiHinh);
		txt_loaiHinh.setColumns(10);
		
		txt_tenTour = new JTextField();
		txt_tenTour.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_tenTour.setColumns(10);
		txt_tenTour.setBounds(88, 85, 116, 20);
		panel_1.add(txt_tenTour);
		
		txt_giaTour = new JTextField();
		txt_giaTour.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_giaTour.setColumns(10);
		txt_giaTour.setBounds(88, 117, 116, 20);
		panel_1.add(txt_giaTour);
		
		JTextPane txt_moTa = new JTextPane();
		txt_moTa.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_moTa.setBounds(88, 144, 116, 43);
		panel_1.add(txt_moTa);
		
		JTextPane txt_hinhAnh = new JTextPane();
		txt_hinhAnh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_hinhAnh.setBounds(295, 52, 196, 107);
		panel_1.add(txt_hinhAnh);
		
		JButton btn_chonHinh = new JButton("Ch\u1ECDn h\u00ECnh");
		btn_chonHinh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn_chonHinh.setBounds(344, 164, 107, 23);
		panel_1.add(btn_chonHinh);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(10, 219, 709, 245);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Danh s\u00E1ch c\u00E1c \u0111\u1ECBa \u0111i\u1EC3m");
		lblNewLabel_1_3_1.setBounds(104, 7, 168, 30);
		lblNewLabel_1_3_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panel_2.add(lblNewLabel_1_3_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 345, 137);
		panel_2.add(scrollPane);
		
		table = new JTable();
		
		table.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"M\u00E3 \u0111\u1ECBa \u0111i\u1EC3m", "T\u00EAn \u0111\u1ECBa \u0111i\u1EC3m", "\u0110\u01B0\u1EDDng"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(68);
		table.getColumnModel().getColumn(0).setMaxWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(53);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
		scrollPane.setViewportView(table);
		
		JButton btn_Them = new JButton("Th\u00EAm");
		btn_Them.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn_Them.setBounds(310, 211, 89, 23);
		panel_2.add(btn_Them);
		
		JButton btn_themDiaDiem = new JButton("+");
		
		btn_themDiaDiem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btn_themDiaDiem.setBounds(10, 185, 59, 23);
		panel_2.add(btn_themDiaDiem);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("C\u00E1c \u0111\u1ECBa \u0111i\u1EC3m \u0111\u00E3 ch\u1ECDn");
		lblNewLabel_1_3_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_3_1_1.setBounds(453, 7, 168, 30);
		panel_2.add(lblNewLabel_1_3_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(365, 48, 334, 137);
		panel_2.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"M\u00E3 \u0111\u1ECBa \u0111i\u1EC3m", "T\u00EAn \u0111\u1ECBa \u0111i\u1EC3m", "\u0110\u01B0\u1EDDng"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(66);
		table_1.getColumnModel().getColumn(0).setMaxWidth(100);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(2).setMaxWidth(100);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		scrollPane_1.setViewportView(table_1);
		
		txtTmKim = new JTextField();
		txtTmKim.setText("T\u00ECm ki\u1EBFm ...");
		txtTmKim.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTmKim.setBounds(10, 25, 86, 20);
		panel_2.add(txtTmKim);
		txtTmKim.setColumns(10);
		//Function=========================================================
		
		tableModel1 = (DefaultTableModel) table.getModel();
		tableModel2 = (DefaultTableModel) table_1.getModel();
		findAllLocation();
		
		btn_Them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tour tour = new Tour();
				
				tour.setName(txt_tenTour.getText());
				tour.setPrice(Double.parseDouble(txt_giaTour.getText()));
				tour.setTypeId(Long.parseLong(txt_loaiHinh.getText()));
				tour.setDescription(txt_moTa.getText());
				tourRepository.save(tour);
				JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
				AddTour addTour = new AddTour();
				addTour.setVisible(false);
			}
		});
		
		btn_themDiaDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if(index >= 0) {
					List<Location> listIndex = locationRepository.findAll();
					tableModel2.setRowCount(0);					
					listIndex.forEach((item)->{
						tableModel2.addRow( new Object[] {
								table.getModel().getValueAt(index, 0).toString(), table.getModel().getValueAt(index, 1).toString(),
								table.getModel().getValueAt(index, 2).toString()
						});					
					});
					
				}
			}
		});
	}
}
