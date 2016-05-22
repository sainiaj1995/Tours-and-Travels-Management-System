import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.Frame;
import javax.swing.JLabel;

public class AdminHome extends JFrame {
	MEP mep = new MEP();
	DEFAULTADMIN da = new DEFAULTADMIN();
	MEC mec = new MEC();
	AUC auc = new AUC();
	MTB mtb = new MTB();
	AA aa = new AA();
	CA ca = new CA();
	MPB mpb=new MPB();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AdminHome() {
		
		
		setTitle("ADMINISTRATOR");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(new Rectangle(0, 0, 1220, 650));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//contentPane.add(mep);
		mec.setBounds(10,54,1190,546);
		mep.setBounds(10,54,1190,546);
		auc.setBounds(10,54,1190,546);
		mtb.setBounds(10,54,1190,546);
		aa.setBounds(10,54,1190,546);
		ca.setBounds(10,54,1190,546);
		mpb.setBounds(10,54,1190,546);
		da.setBounds(10,54,1190,546);
		contentPane.add(da);
		
		//mep.setVisible(false);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1194, 43);
		contentPane.add(menuBar);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(100, 25));
		menuBar.add(rigidArea_2);
		JMenu mnPackages = new JMenu("MANAGE PACKAGES");
		mnPackages.setForeground(new Color(128, 0, 0));
		mnPackages.setHorizontalAlignment(SwingConstants.CENTER);
		mnPackages.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		menuBar.add(mnPackages);
		
		JMenuItem mntmViewBookedPackages = new JMenuItem("DELETE/UPDATE/INSERT PACKAGES");
		mntmViewBookedPackages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			     // mep.setVisible(true);
			      //da.setVisible(false);
				contentPane.removeAll();
				contentPane.add(mep);
				contentPane.add(da);
				
				contentPane.add(menuBar);
				contentPane.revalidate();
				contentPane.repaint();
				//contentPane.repaint();
			//	contentPane.revalidate();
			}
		});
		mnPackages.add(mntmViewBookedPackages);
		Component rigidArea = Box.createRigidArea(new Dimension(100,25));
		menuBar.add(rigidArea);
		
		JMenu mnTickets = new JMenu("MANAGE CITIES");
		mnTickets.setHorizontalAlignment(SwingConstants.CENTER);
		mnTickets.setForeground(new Color(128, 0, 0));
		mnTickets.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		menuBar.add(mnTickets);
		
		JMenuItem mntmViewBookedTickets = new JMenuItem("MANAGE FARE");
		mntmViewBookedTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				
				contentPane.add(mec);
				contentPane.add(da);
				//mec.repaint();
				contentPane.add(menuBar);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		mnTickets.add(mntmViewBookedTickets);
		
		JMenuItem mntmAddNewCities = new JMenuItem("ADD/UPDATE  CITIES");
		mntmAddNewCities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(auc);
				contentPane.add(da);
				contentPane.add(menuBar);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		mnTickets.add(mntmAddNewCities);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(100, 25));
		menuBar.add(rigidArea_1);
		
		JMenu mnUpdateD = new JMenu("MANAGE BOOKINGS");
		mnUpdateD.setHorizontalAlignment(SwingConstants.CENTER);
		mnUpdateD.setForeground(new Color(128, 0, 0));
		mnUpdateD.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		menuBar.add(mnUpdateD);
		
		JMenuItem mntmUpdateDetails = new JMenuItem("MANAGE TICKET BOOKINGS");
		mntmUpdateDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(mtb);
				contentPane.add(da);
				contentPane.add(menuBar);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		mnUpdateD.add(mntmUpdateDetails);
		
		JMenuItem mntmManagePackageBookings = new JMenuItem("MANAGE PACKAGE BOOKINGS");
		mntmManagePackageBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(mpb);
				contentPane.add(da);
				contentPane.add(menuBar);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		mnUpdateD.add(mntmManagePackageBookings);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(100, 25));
		menuBar.add(rigidArea_3);
		
		
		JMenu mnManageDetails = new JMenu("MANAGE DETAILS");
		mnManageDetails.setHorizontalAlignment(SwingConstants.CENTER);
		mnManageDetails.setForeground(new Color(128, 0, 0));
		mnManageDetails.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		menuBar.add(mnManageDetails);
		
		JMenuItem mntmAdminAccount = new JMenuItem("ADMIN ACCOUNT");
		mntmAdminAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(aa);
				contentPane.add(da);
				contentPane.add(menuBar);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		mnManageDetails.add(mntmAdminAccount);
		
		JMenuItem mntmCustomerAccounts = new JMenuItem("CUSTOMER ACCOUNTS");
		mntmCustomerAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			contentPane.removeAll();
				contentPane.add(ca);
				contentPane.add(da);
				contentPane.add(menuBar);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		mnManageDetails.add(mntmCustomerAccounts);
		
		JMenu menu = new JMenu("");
		menuBar.add(menu);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(100, 25));
		menuBar.add(rigidArea_4);
		
		JMenu mnAdmin = new JMenu(Home.loggedinadmin);
		System.out.println(Home.loggedinadmin);
		mnAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		mnAdmin.setForeground(new Color(128, 0, 0));
		mnAdmin.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		menuBar.add(mnAdmin);
		
		JMenuItem mntmLogout = new JMenuItem("LOGOUT");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login.textField_2.setText("");
				Login.passwordField_1.setText("");
				/*Login.textField_2.setRequestFocusEnabled(true);
				Login.passwordField_1.setRequestFocusEnabled(true);
				Login.textField_2.requestFocus();
				Login.btnLogin_1.requestFocusInWindow();*/
				Home.loggedinadmin = null;
				//Login.btnSignUp.requestFocusInWindow();
				dispose();
				
			}
		});
		mnAdmin.add(mntmLogout);
	}
}
