import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class bookpackage extends JPanel {
	static Connection con;
	static Statement st = null;
	static ResultSet rs;
	static double fare_of_pack = 0.0;
	private static JTextField textField;
	private static JTextField textField_1;
	String months[] = {"01","02","03","04","05","06","07","08","09","10","11","12"}; 
	String days[] = new String[31];
	String year[] = new String[2];
	String curr_day,curr_month,curr_year;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private static JTextField textField_20;
	private JLabel lblNoOfTravellers;
	private JComboBox comboBox_9;
	private JButton btnCancel;
	private static JTextField textField_21;


	/**
	 * Create the panel.
	 */
	public bookpackage() {
		setBounds(new Rectangle(0, 0, 1220, 650));
		setLayout(null);
		
		JLabel lblPid = new JLabel("PACKAGE ID");
		lblPid.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblPid.setBounds(10, 45, 132, 25);
		add(lblPid);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.ITALIC, 16));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBounds(141, 34, 189, 48);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("PACKAGE NAME");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel.setBounds(408, 33, 137, 41);
		add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(577, 34, 385, 48);
		add(textField_1);
		textField_1.setColumns(10);
		
		
		for(int i=1;i<=31;i++)
		{
			days[i-1] = Integer.toString(i);
		}
		StringTokenizer st1;
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//System.out.println(sdf.format(date));
		String at = sdf.format(date);
		
		st1 = new StringTokenizer(at,"/");
		curr_day = st1.nextToken();
		curr_month = st1.nextToken();
		curr_year = st1.nextToken();
		/*String mm[] = Arrays.copyOfRange(months, 3, 5);
		for(String x:days){
			System.out.println(x);
		}*/
		year[0] = new String(curr_year);
		year[1] = new String(Integer.toString((Integer.parseInt(curr_year)+1)));
		String days1[];
		if((Integer.parseInt(curr_month) + (Integer.parseInt(curr_month)/8))%2 == 1)
			days1 = Arrays.copyOfRange(days, Integer.parseInt(curr_day)-1,days.length);
		else{
			days1 = Arrays.copyOfRange(days, Integer.parseInt(curr_day)-1,days.length-1);
		}
		String year1[];
		JComboBox comboBox_2;
		if(Integer.parseInt(curr_month) +4 <= 12){
			year1 = (Arrays.copyOfRange(year,0, 1));
		}
		else{
			year1 = year;
		}
		comboBox_2 = new JComboBox(year1);
		JComboBox comboBox = new JComboBox(days1);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			///
				
			//comboBox.setModel(new javax.swing.DefaultComboBoxModel(months));

			}
		});
		comboBox.setBounds(475, 142, 62, 35);
		add(comboBox);
		String months1[];
		if(Integer.parseInt(curr_month) +4 > 12)
		{
			months1 = Arrays.copyOfRange(months,Integer.parseInt(curr_month)-1,12);
		}
		else{
			months1 = Arrays.copyOfRange(months,Integer.parseInt(curr_month)-1,Integer.parseInt(curr_month)+4);
	}
		
		JComboBox comboBox_1 = new JComboBox(months1);
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String days4[];
				if(curr_month.equals(comboBox_1.getSelectedItem())){
					days4 = days1;
					//comboBox.setModel(new javax.swing.DefaultComboBoxModel(days1));

				}
				else{
					String days3[];
					if((Integer.parseInt(String.valueOf(comboBox_1.getSelectedItem())) + (Integer.parseInt(String.valueOf(comboBox_1.getSelectedItem()))/8))%2 == 1){
						days3 = Arrays.copyOfRange(days,0,days.length);
					}
					else{
						days3 = Arrays.copyOfRange(days,0,days.length-1);
					}
					days4 = days3;
					//comboBox.setModel(new javax.swing.DefaultComboBoxModel());
					//comboBox.setModel(new javax.swing.DefaultComboBoxModel(days3));
				}
				if(comboBox_1.getSelectedItem().equals("02"))
				{
					int i;
					for( i=0;i<days4.length;i++)
					{
						if(days4[i].equals("29"))
						{
							break;
						}
					}	
					if(Integer.parseInt(String.valueOf(comboBox_2.getSelectedItem()))%4 == 0)
					{
						days4 = Arrays.copyOfRange(days4, 0,i+1);
					}
					else{
						days4 = Arrays.copyOfRange(days4, 0,i);
					}
				}
				comboBox.setModel(new javax.swing.DefaultComboBoxModel(days4));
				
				
			}
		});
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//
			}
		});
		
		comboBox_1.setBounds(547, 142, 79, 35);
	   add(comboBox_1);
		
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(! curr_year.equals(comboBox_2.getSelectedItem()))
				{
					String months2[] = months;
					if(Integer.parseInt(curr_month) > 8){
						int temp = Integer.parseInt(curr_month) - 8;
						months2 = Arrays.copyOfRange(months,0, temp);
					}
					comboBox_1.setModel(new javax.swing.DefaultComboBoxModel(months2));
				}
				else{
					comboBox_1.setModel(new javax.swing.DefaultComboBoxModel(months1));
				}
			}
		});
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
			}
		});
		comboBox_2.setBounds(636, 142, 97, 35);
		add(comboBox_2);
		
		JLabel lblDayMonthYear = new JLabel("DAY                             MONTH                       YEAR");
		lblDayMonthYear.setBounds(475, 98, 274, 33);
		add(lblDayMonthYear);
		
		JLabel lblNewLabel_1 = new JLabel("DATE OF JOURNEY");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_1.setBounds(287, 137, 178, 39);
		add(lblNewLabel_1);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(51, 221, 91, 30);
		add(lblName);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBounds(10, 261, 231, 48);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAge = new JLabel("AGE");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAge.setBounds(305, 225, 45, 22);
		add(lblAge);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setBounds(287, 259, 79, 48);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_4.setBounds(10, 320, 231, 48);
		add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_5.setBounds(10, 379, 231, 48);
		add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_6.setBounds(10, 441, 231, 48);
		add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_7.setBounds(10, 500, 231, 48);
		add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_8.setBounds(10, 559, 231, 48);
		add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_9.setBounds(287, 320, 79, 48);
		add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_10.setColumns(10);
		textField_10.setBounds(287, 379, 79, 48);
		add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_11.setColumns(10);
		textField_11.setBounds(287, 438, 79, 48);
		add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_12.setColumns(10);
		textField_12.setBounds(287, 500, 79, 48);
		add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_13.setColumns(10);
		textField_13.setBounds(287, 559, 79, 48);
		add(textField_13);
		
		JLabel lblSex = new JLabel("SEX");
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSex.setBounds(424, 224, 46, 25);
		add(lblSex);
		String SEX[] = {"MALE","FEMALE","OTHER"};
		JComboBox comboBox_3 = new JComboBox(SEX);
		comboBox_3.setBounds(419, 259, 62, 48);
		add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox(SEX);
		comboBox_4.setBounds(419, 320, 62, 48);
		add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox(SEX);
		comboBox_5.setBounds(419, 379, 62, 48);
		add(comboBox_5);
		
		JComboBox comboBox_6 = new JComboBox(SEX);
		comboBox_6.setBounds(419, 438, 62, 48);
		add(comboBox_6);
		
		JComboBox comboBox_7 = new JComboBox(SEX);
		comboBox_7.setBounds(419, 500, 62, 48);
		add(comboBox_7);
		
		JComboBox comboBox_8 = new JComboBox(SEX);
		comboBox_8.setBounds(419, 559, 62, 48);
		add(comboBox_8);
		
		JLabel lblNewLabel_2 = new JLabel("UID(ADDHAR ID)");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(528, 221, 231, 30);
		add(lblNewLabel_2);
		
		textField_14 = new JTextField();
		textField_14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_14.setColumns(10);
		textField_14.setBounds(528, 261, 231, 48);
		add(textField_14);
		
		textField_15 = new JTextField();
		textField_15.setBackground(Color.WHITE);
		textField_15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_15.setColumns(10);
		textField_15.setBounds(528, 320, 231, 48);
		add(textField_15);
		
		textField_16 = new JTextField();
		textField_16.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_16.setColumns(10);
		textField_16.setBounds(528, 379, 231, 48);
		add(textField_16);
		
		textField_17 = new JTextField();
		textField_17.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_17.setColumns(10);
		textField_17.setBounds(528, 441, 231, 48);
		add(textField_17);
		
		textField_18 = new JTextField();
		textField_18.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_18.setColumns(10);
		textField_18.setBounds(528, 500, 231, 48);
		add(textField_18);
		
		textField_19 = new JTextField();
		textField_19.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_19.setColumns(10);
		textField_19.setBounds(528, 559, 231, 48);
		add(textField_19);
		
		JButton btnNewButton = new JButton("PROCEED TO PAYMENT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();
					rs = st.executeQuery("select * from no_of_packages_booked");
					rs.next();
					int notid = rs.getInt("no");
					notid++;
					st.executeUpdate("update no_of_packages_booked set no = "+Integer.toString(notid));
					String ptid = "PT" + Integer.toString(notid);
					String pid = textField.getText();
					String usrname  = Home.loggedinuser;
					String dtoj = String.valueOf(comboBox.getSelectedItem()) + "-" + String.valueOf(comboBox_1.getSelectedItem()) + "-" +String.valueOf(comboBox_2.getSelectedItem());
					String notr = String.valueOf(comboBox_9.getSelectedItem());
					String pname=null;
					rs = st.executeQuery("select * from packages where pid = '"+pid+"'");
					while(rs.next())
					{
						pname = rs.getString("PACKAGE_NAME");
					}
					String sql  = "insert into ticket_packages values('"+ptid+"','"+pid+"','"+usrname+"',to_date('"+dtoj+"','DD-MM-YYYY')," + notr+",'"+pname+"')";
					//System.out.println(sql);
					st.executeUpdate(sql);
					int i = Integer.parseInt(notr);
				    String names[] = {textField_2.getText(),textField_4.getText(),textField_5.getText(),textField_6.getText(),textField_7.getText(),textField_8.getText()};
				    String userId[] = {textField_14.getText(),textField_15.getText(),textField_16.getText(),textField_17.getText(),textField_18.getText(),textField_19.getText()};
				    String age[] = {textField_3.getText(),textField_9.getText(),textField_10.getText(),textField_11.getText(),textField_12.getText(),textField_13.getText()};
				    String sex[] = {String.valueOf(comboBox_3.getSelectedItem()),String.valueOf(comboBox_4.getSelectedItem()),String.valueOf(comboBox_5.getSelectedItem()),String.valueOf(comboBox_6.getSelectedItem()),String.valueOf(comboBox_7.getSelectedItem()),String.valueOf(comboBox_8.getSelectedItem())};
				    for(int j=0;j<i;j++)
				    {
				    	st.executeUpdate("insert into PTRAVELLERS values('"+ptid+"','"+userId[j]+"','"+pid+"','"+names[j]+"',"+age[j]+",'"+sex[j]+"')");
				    	//String sql4 = "insert into PTRAVELLERS values('"+ptid+"','"+userId[j]+"','"+pid+"','"+names[j]+"',"+age[j]+",'"+sex[j]+"')";
				    //System.out.println(sql4);
				    }
				  new PackageConfirmed(pid,textField_1.getText(),Double.toString(i*fare_of_pack),notr,dtoj,names,age,userId,sex);
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Please enter valid data this problem could occur because 'age should be valid integer' or 'user id must be valid and unique''");
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
				    try { if (rs != null) rs.close(); } catch (Exception e1) {};
				    try { if (st != null) st.close(); } catch (Exception e1) {};
				    try { if (con != null) con.close(); } catch (Exception e1) {};
				}
				
				textField_2.setText(null);
				textField_3.setText(null);
				textField_4.setText(null);
				textField_5.setText(null);
				textField_6.setText(null);
				textField_7.setText(null);
				textField_8.setText(null);textField_13.setText(null);textField_14.setText(null);textField_19.setText(null);
				textField_9.setText(null);textField_12.setText(null);textField_15.setText(null);textField_18.setText(null);
				textField_10.setText(null);textField_11.setText(null);textField_16.setText(null);textField_17.setText(null);
				
			}
		});
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 19));
		btnNewButton.setBounds(859, 416, 274, 73);
		add(btnNewButton);
		
		textField_20 = new JTextField();
		textField_20.setEditable(false);
		textField_20.setBounds(870, 309, 239, 48);
		add(textField_20);
		textField_20.setColumns(10);
		
		JCheckBox chckbxChange = new JCheckBox("CHANGE  MOBILE No.");
		chckbxChange.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			    if(chckbxChange.isSelected())
			    {
			    	textField_20.setEditable(true);
			    }
			}
		});
		chckbxChange.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxChange.setBounds(870, 272, 189, 23);
		add(chckbxChange);
		
		lblNoOfTravellers = new JLabel("No of Travellers");
		lblNoOfTravellers.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoOfTravellers.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNoOfTravellers.setBounds(10, 145, 144, 25);
		add(lblNoOfTravellers);
		String xs[] = {"1","2","3","4","5","6"};
		comboBox_9 = new JComboBox(xs);
		comboBox_9.setBounds(179, 142, 62, 35);
		add(comboBox_9);
		
		btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Packages.bookpack.setVisible(false);
				Packages.packinfo.setVisible(false);
				Packages.packmenu.setVisible(true);
				textField_2.setText(null);
				textField_3.setText(null);
				textField_4.setText(null);
				textField_5.setText(null);
				textField_6.setText(null);
				textField_7.setText(null);
				textField_8.setText(null);textField_13.setText(null);textField_14.setText(null);textField_19.setText(null);
				textField_9.setText(null);textField_12.setText(null);textField_15.setText(null);textField_18.setText(null);
				textField_10.setText(null);textField_11.setText(null);textField_16.setText(null);textField_17.setText(null);
				
			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 19));
		btnCancel.setBounds(859, 520, 159, 48);
		add(btnCancel);
		
		JLabel lblFare = new JLabel("FARE PER PERSON");
		lblFare.setHorizontalAlignment(SwingConstants.CENTER);
		lblFare.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblFare.setBounds(821, 140, 170, 32);
		add(lblFare);
		
		textField_21 = new JTextField();
		textField_21.setHorizontalAlignment(SwingConstants.CENTER);
		textField_21.setFont(new Font("Tahoma", Font.ITALIC, 16));
		textField_21.setEditable(false);
		textField_21.setColumns(10);
		textField_21.setBounds(1001, 129, 189, 48);
		add(textField_21);
	}
	
	public static void filldetail(String pid)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			st=con.createStatement();
			String sql = "select * from packages where PID ='"+packagemenu.pidselected+"'";
			rs = st.executeQuery(sql);
			while(rs.next()){
			textField.setText(rs.getString("PID"));
			textField_1.setText(rs.getString("PACKAGE_NAME"));
			textField_21.setText(rs.getString("PACKAGE_PRICE"));
			fare_of_pack = Double.parseDouble(rs.getString("PACKAGE_PRICE"));
			String sql1 = "select * from customer where username_cust ='"+Home.loggedinuser+"'";
			rs = st.executeQuery(sql1);
			while(rs.next())
			{
				textField_20.setText(rs.getString("CONTACT_CUST"));
			}
			//System.out.println(fare_of_pack);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    try { if (rs != null) rs.close(); } catch (Exception e1) {};
		    try { if (st != null) st.close(); } catch (Exception e1) {};
		    try { if (con != null) con.close(); } catch (Exception e1) {};
		}
	}
}
