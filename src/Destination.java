import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Destination extends JPanel {
	Connection con;
	Statement st = null;
	ResultSet rs;
	/**
	 * Create the panel.
	 */
	static ArrayList<String> listofcities = new ArrayList<String>(); 
	public Destination() {
		setBounds(new Rectangle(0, 0, 1220, 650));
		setLayout(null);
		setOpaque(false);
		JButton btnViewPackages = new JButton("View packages");
		btnViewPackages.setForeground(Color.RED);
		btnViewPackages.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
		btnViewPackages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Packages();
			}
		});
		btnViewPackages.setBounds(23, 534, 203, 48);
		add(btnViewPackages);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			st=con.createStatement();

			rs = st.executeQuery("select cityname,citystate from city");
			while(rs.next())
			{
				//cities[noofcities++] = rs.getString("cityname");
				listofcities.add(rs.getString("cityname")+","+rs.getString("citystate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
		    try { if (rs != null) rs.close(); } catch (Exception e1) {};
		    try { if (st != null) st.close(); } catch (Exception e1) {};
		    try { if (con != null) con.close(); } catch (Exception e1) {};
		}
		
	
		
		JLabel lblSource = new JLabel("Source:");
		lblSource.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
		lblSource.setForeground(Color.RED);
		lblSource.setHorizontalAlignment(SwingConstants.CENTER);
		lblSource.setBounds(129, 316, 134, 40);
		add(lblSource);
		
		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setForeground(Color.RED);
		lblDestination.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestination.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
		lblDestination.setBounds(129, 400, 152, 40);
		add(lblDestination);
		
		Collections.sort(listofcities.subList(1, listofcities.size()));
		JComboBox comboBox = new JComboBox(listofcities.toArray());
		comboBox.setForeground(new Color(178, 34, 34));
		comboBox.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		comboBox.setBounds(291, 313, 220, 48);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(listofcities.toArray());
		comboBox_1.setForeground(new Color(178, 34, 34));
		comboBox_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		comboBox_1.setBounds(291, 397, 220, 48);
		
		add(comboBox_1);
		
		JButton btnSearch = new JButton("BOOK");
		btnSearch.setForeground(Color.RED);
		btnSearch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String x = String.valueOf(comboBox.getSelectedItem());
				String y = String.valueOf(comboBox_1.getSelectedItem());
				if(x.equals(y))
				{
					JOptionPane.showMessageDialog(null, "source and destination cannot be same!");
				}
				else{	boolean valid = true;
						StringTokenizer st1 = new StringTokenizer(x,",");
						StringTokenizer st2 = new StringTokenizer(y,",");
							try {
								Class.forName("oracle.jdbc.driver.OracleDriver");
								con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
								st=con.createStatement();
								rs = st.executeQuery("select * from fare where source_name = '"+st1.nextToken()+"' and source_state = '"+st1.nextToken()+"' and destination_name = '"+st2.nextToken()+"' and destination_state = '"+st2.nextToken()+"'");
								if(!rs.next())
								{
									valid = false;
								}
								else{
									if(Double.parseDouble(rs.getString("fare_per_person")) == 0.0 || Integer.parseInt(rs.getString("availability"))==0)
									{
										valid = false;
									}
								}
								if(valid){
					         	     Home.btkt.setVisible(true);
					         	     Home.destn.setVisible(false);
					         	     Home.login.setVisible(false);
					         	     bticket.filldetail(String.valueOf(comboBox.getSelectedItem()),String.valueOf(comboBox_1.getSelectedItem()));
									}else{
										JOptionPane.showMessageDialog(null,"THERE IS NO TRANSPORTATION FACILITY.PLEASE TRY ANOTHER!");
									}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}finally {
							    try { if (rs != null) rs.close(); } catch (Exception e1) {};
							    try { if (st != null) st.close(); } catch (Exception e1) {};
							    try { if (con != null) con.close(); } catch (Exception e1) {};
							}
				}
			}
		});
		btnSearch.setBounds(320, 492, 117, 40);
		add(btnSearch);
		
		JButton btnTicketHistory = new JButton("VIEW BOOKED TICKET");
		btnTicketHistory.setForeground(Color.RED);
		btnTicketHistory.setFont(new Font("Tempus Sans ITC", Font.BOLD, 19));
		btnTicketHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   new thistory();
			}
		});
		btnTicketHistory.setBounds(694, 253, 315, 61);
		add(btnTicketHistory);
		
		JButton btnUpdateInfo = new JButton("UPDATE  ACCOUNT DETAILS");
		btnUpdateInfo.setForeground(Color.RED);
		btnUpdateInfo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 19));
		btnUpdateInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();
					rs = st.executeQuery("select * from customer where username_cust = '"+Home.loggedinuser+"'");
					while(rs.next())
					{
						Updateinfo.textField.setText(rs.getString("username_cust"));
						Updateinfo.textField_1.setText(rs.getString("name_cust"));
						Updateinfo.passwordField.setText(rs.getString("password_cust"));
						Updateinfo.textField_2.setText(rs.getString("contact_cust"));
						Updateinfo.textField_3.setText(rs.getString("address_cust"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
				    try { if (rs != null) rs.close(); } catch (Exception e1) {};
				    try { if (st != null) st.close(); } catch (Exception e1) {};
				    try { if (con != null) con.close(); } catch (Exception e1) {};
				}
				
				Home.showUI();
				
			}
		});
		btnUpdateInfo.setBounds(694, 433, 315, 61);
		add(btnUpdateInfo);
		
		JLabel lblCitystate = new JLabel("CITY    ,  STATE");
		lblCitystate.setForeground(Color.RED);
		lblCitystate.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
		lblCitystate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCitystate.setBounds(302, 265, 203, 37);
		add(lblCitystate);
		
		JButton btnPackagesHistory = new JButton("VIEW BOOKED PACKAGES");
		btnPackagesHistory.setForeground(Color.RED);
		btnPackagesHistory.setFont(new Font("Tempus Sans ITC", Font.BOLD, 19));
		btnPackagesHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			     new phistory();
			}
		});
		btnPackagesHistory.setBounds(694, 343, 315, 61);
		add(btnPackagesHistory);
		
		JButton btnNewButton = new JButton("LOGOUT");
		btnNewButton.setForeground(new Color(139, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.loggedinuser = null;
				Home.destn.setVisible(false);
				Home.login.setVisible(true);
				Login.textField_2.setText("");
				Login.passwordField_1.setText("");
			}
		});
		btnNewButton.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		btnNewButton.setBounds(1076, 21, 134, 48);
		add(btnNewButton);
		
		JLabel lblWelcome = new JLabel("WELCOME TO TOUR AND TRAVELS.");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(new Color(255, 0, 0));
		lblWelcome.setFont(new Font("Sylfaen", Font.BOLD, 22));
		lblWelcome.setBounds(278, 11, 620, 58);
		add(lblWelcome);
		
		JLabel lblChooseYourSourcedestination = new JLabel("CHOOSE YOUR SOURCE AND DESTINATION.");
		lblChooseYourSourcedestination.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseYourSourcedestination.setForeground(new Color(255, 0, 0));
		lblChooseYourSourcedestination.setFont(new Font("Sylfaen", Font.BOLD, 21));
		lblChooseYourSourcedestination.setBounds(288, 63, 620, 58);
		add(lblChooseYourSourcedestination);
		
		JLabel lblWantSomeOffer = new JLabel("WANT SOME OFFER?? CLICK ON VIEW PACKAGES");
		lblWantSomeOffer.setHorizontalAlignment(SwingConstants.CENTER);
		lblWantSomeOffer.setForeground(new Color(255, 0, 0));
		lblWantSomeOffer.setFont(new Font("Sylfaen", Font.BOLD, 21));
		lblWantSomeOffer.setBounds(291, 121, 620, 58);
		add(lblWantSomeOffer);
	}
}
