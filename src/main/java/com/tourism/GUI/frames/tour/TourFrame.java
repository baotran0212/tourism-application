package com.tourism.GUI.frames.tour;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.tourism.DAL.TourRepository;
import com.tourism.DAL.TypeRepository;
import com.tourism.DTO.Tour;
import com.tourism.DTO.Type;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TourFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txt_maTour;
	private JTextField txt_tenTour;
	private JTextField txt_giaTour;
	private JTextField txt_loaiTour;
	private JTextField txt_trangThaiTour;
	private JTextField txt_timTenTour;
	private JTextField txt_timDiaDiem;
	private JComboBox cbb_loaiTour = new JComboBox();
	private JTable table;
	
	/**
	 * Function
	 */
	List<Tour> listTour = new ArrayList<>();
	List<com.tourism.DTO.Type> listType = new ArrayList<>();
	DefaultTableModel tableModel;
	TourRepository tourRepository = new TourRepository();
	TypeRepository typeRepository = new TypeRepository();
	private void findAll() {
		listTour = tourRepository.findAll();
		tableModel.setRowCount(0);
		listTour.forEach((tour)->{
			tableModel.addRow(new Object[] {
				tour.getId(), tour.getName(), formatMoney(tour.getPrice()), tour.getDescription(), tour.getStatus()
			});
		});
	}
	
	private void getTypleOfTourToCombobox() {
		listType = typeRepository.findAll();
		for(com.tourism.DTO.Type item : listType) {
			cbb_loaiTour.addItem(item.getName());
		}
	}
	
	private static Object formatMoney(Object money){
        Locale locale = new Locale("vi","VN");
        DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(locale);
        DecimalFormatSymbols formatSymbol = new DecimalFormatSymbols();
        formatSymbol.setCurrencySymbol("");
        format.setDecimalFormatSymbols(formatSymbol);
        return format.format(money);
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
					TourFrame frame = new TourFrame();
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
	public TourFrame() {
		//Frame============================================================
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 781, 471);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(0, 0, 775, 135);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("M\u00E3 tour");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 44, 64, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("CHI TI\u1EBET TOUR");
		lblNewLabel.setBounds(310, 11, 135, 21);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("T\u00EAn tour");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 69, 64, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Gi\u00E1 tour");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 94, 64, 14);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Lo\u1EA1i h\u00ECnh du l\u1ECBch");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(265, 44, 112, 14);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Tr\u1EA1ng th\u00E1i");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(264, 70, 64, 14);
		panel_1.add(lblNewLabel_1_4);
		
		JButton btn_sua = new JButton("S\u1EEDa");
		btn_sua.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn_sua.setBounds(599, 65, 89, 23);
		panel_1.add(btn_sua);
		
		JButton btn_xoa = new JButton("X\u00F3a");
		btn_xoa.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn_xoa.setBounds(599, 90, 89, 23);
		panel_1.add(btn_xoa);
		
		txt_maTour = new JTextField();
		txt_maTour.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_maTour.setBounds(70, 42, 153, 20);
		panel_1.add(txt_maTour);
		txt_maTour.setColumns(10);
		
		txt_tenTour = new JTextField();
		txt_tenTour.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_tenTour.setColumns(10);
		txt_tenTour.setBounds(70, 66, 153, 20);
		panel_1.add(txt_tenTour);
		
		txt_giaTour = new JTextField();
		txt_giaTour.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_giaTour.setColumns(10);
		txt_giaTour.setBounds(70, 94, 153, 20);
		panel_1.add(txt_giaTour);
		
		txt_loaiTour = new JTextField();
		txt_loaiTour.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_loaiTour.setColumns(10);
		txt_loaiTour.setBounds(387, 42, 153, 20);
		panel_1.add(txt_loaiTour);
		
		txt_trangThaiTour = new JTextField();
		txt_trangThaiTour.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_trangThaiTour.setColumns(10);
		txt_trangThaiTour.setBounds(387, 67, 153, 20);
		panel_1.add(txt_trangThaiTour);
		
		JButton btn_them = new JButton("Th\u00EAm");
		btn_them.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn_them.setBounds(599, 41, 89, 23);
		panel_1.add(btn_them);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(0, 144, 775, 67);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_5 = new JLabel("T\u00EAn tour");
		lblNewLabel_1_5.setBounds(10, 11, 53, 17);
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panel_2.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_5_1 = new JLabel("\u0110\u1ECBa \u0111i\u1EC3m");
		lblNewLabel_1_5_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_5_1.setBounds(215, 11, 65, 17);
		panel_2.add(lblNewLabel_1_5_1);
		
		JLabel lblNewLabel_1_5_2 = new JLabel("Lo\u1EA1i h\u00ECnh");
		lblNewLabel_1_5_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_5_2.setBounds(423, 11, 65, 17);
		panel_2.add(lblNewLabel_1_5_2);
		
		JButton btn_tim = new JButton("T\u00ECm");
		btn_tim.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn_tim.setBounds(676, 8, 89, 23);
		panel_2.add(btn_tim);
		
		txt_timTenTour = new JTextField();
		txt_timTenTour.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_timTenTour.setBounds(70, 10, 116, 20);
		panel_2.add(txt_timTenTour);
		txt_timTenTour.setColumns(10);
		
		txt_timDiaDiem = new JTextField();
		txt_timDiaDiem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_timDiaDiem.setColumns(10);
		txt_timDiaDiem.setBounds(279, 9, 116, 20);
		panel_2.add(txt_timDiaDiem);
		
		
		cbb_loaiTour.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cbb_loaiTour.setBounds(486, 9, 180, 22);
		panel_2.add(cbb_loaiTour);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_3.setBounds(0, 222, 775, 249);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 775, 249);
		panel_3.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setSurrendersFocusOnKeystroke(true);
		table.setForeground(Color.DARK_GRAY);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		JTableHeader header = table.getTableHeader();
	      header.setBackground(Color.blue);
	      header.setForeground(Color.white);
	      header.setOpaque(false);
	      Font headerFont = new Font("Times New Roman", Font.BOLD, 14);
	      table.getTableHeader().setFont(headerFont);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 Tour", "T\u00EAn Tour", "Gi\u00E1 Tour", "H\u00ECnh \u1EA3nh", "Tr\u1EA1ng th\u00E1i"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		//Function=========================================================
			tableModel = (DefaultTableModel) table.getModel();
			findAll();
			getTypleOfTourToCombobox();
			
			btn_them.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddTour addTour = new AddTour();
					addTour.setVisible(true);
				}
			});
	}
}
