package com.tourism.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.tourism.DTO.User;
import com.tourism.GUI.frames.analysis.AnalysisMainPanel;
import com.tourism.GUI.frames.tour.TourFrame;
import com.tourism.GUI.frames.touristgroup.TouristGroupMainPanel;
import com.tourism.GUI.util.IconUtil;

/**
 * MainFrame
 */
public class MainFrame extends JFrame {
	static final long serialVersionUID = 4L;
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
	private User user = new User(null, "Long", "09456345", "password");
	// Left side menu
	private JPanel pnlLeftSideMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, -2, -2));
	private JPanel pnlTopBar = new JPanel(null);
	JLabel functionSelected = new JLabel("--------\\/--------");
	List<String> titleMenuItems;
	JPanel pnlMenuItems[];
	JLabel lblMenuItems[];

	// Array Jpanel nội dung chính
	JPanel[] pnlMainContents;
	public static JPanel pnlTourManager;
	public static JPanel pnlTouristGroupManager;
	public static JPanel pnlAnalysis;
	JLayeredPane layeredContent = new JLayeredPane();

	public MainFrame() {
		initComp();
	}

	public void initComp() {
		this.titleMenuItems = new ArrayList<String>();
		setLayout(new BorderLayout());

		// Left side menu
		pnlLeftSideMenu.setPreferredSize(new Dimension(200, 600));
		Dimension dimensionMenuItem = new Dimension(204, 50);

		this.titleMenuItems.add("Thống kê");
		this.titleMenuItems.add("Quản lý tour");
		this.titleMenuItems.add("Quản lý đoàn");

		pnlMenuItems = new JPanel[titleMenuItems.size()];
		lblMenuItems = new JLabel[titleMenuItems.size()];

		for (int i = 0; i < titleMenuItems.size(); i++) {
			pnlMenuItems[i] = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 12));
			lblMenuItems[i] = new JLabel(titleMenuItems.get(i));

			pnlMenuItems[i].add(lblMenuItems[i]);
			pnlMenuItems[i].setPreferredSize(dimensionMenuItem);
			int selectedPanelIndex = i;
			pnlMenuItems[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent evt) {
					switchPanel(selectedPanelIndex);
				}
			});
			pnlLeftSideMenu.add(pnlMenuItems[i]);
		}
		// Top mennu
		ImageIcon iconApp = IconUtil.loadIcon("src/images/app_logo.png", 160, 50);
		JLabel lblLogo = new JLabel(iconApp);
		lblLogo.setBounds(20, 0, 160, 50);

		// Selected
		functionSelected = new JLabel("--------\\\\/--------");
		functionSelected.setBounds(650, 0, 300, 50);
		// User
		JLabel lblUser = new JLabel("Hello, " + user.getName());
		lblUser.setBounds(1050, 0, 200, 50);

		pnlTopBar.add(lblLogo);
		pnlTopBar.add(functionSelected);
		pnlTopBar.add(lblUser);
		pnlTopBar.setPreferredSize(new Dimension(1100, 50));

		this.add(pnlLeftSideMenu, BorderLayout.WEST);
		this.add(pnlTopBar, BorderLayout.NORTH);
		// Main content
		layeredContent.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		layeredContent.setPreferredSize(new Dimension(1110, 700));

		// Init main content panel
		pnlTourManager = new TourFrame();
		pnlTouristGroupManager = new TouristGroupMainPanel();
		pnlAnalysis = new AnalysisMainPanel();

		this.pnlMainContents = new JPanel[] { pnlAnalysis, pnlTourManager, pnlTouristGroupManager };

		for (int i = 0; i < pnlMainContents.length; i++) {
			this.layeredContent.add(pnlMainContents[i], pnlMainContents.length - i, 0);
		}

		this.add(layeredContent, BorderLayout.CENTER);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void switchPanel(int selectPanelIndex) {
		for (int i = 0; i < pnlMenuItems.length; i++) {
			if (i == selectPanelIndex) {
				// lblMenuItems[selectPanelIndex].setForeground(Cl.colorBlue);
				// pnlMenuItems[selectPanelIndex].setBorder(Cl.blueLine);
				functionSelected.setText(titleMenuItems.get(i));
				continue;
			}
			// pnlMenuItem[i].setBorder(Cl.whiteLine);
			// lblMenuItem[i].setForeground(Color.white);
		}

		layeredContent.removeAll();
		layeredContent.add(pnlMainContents[selectPanelIndex]);
		layeredContent.repaint();
		layeredContent.revalidate();
	}

	public static void main(String[] args) {
		MainFrame main = new MainFrame();
	}
}