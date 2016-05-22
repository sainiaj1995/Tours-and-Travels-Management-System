import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MEC extends JPanel {
	static JTable table;
	Connection con;
	Statement st = null;
	ResultSet rs;
	private JTextField textField;
	JCheckBox chckbxAvailable;
	static ArrayList<String> listofcities = new ArrayList<String>(); 
	static JComboBox comboBox,comboBox_1;
	private JLabel lblSource;
	private JLabel lblFare;
	private JLabel lblUpdateAddfare;
	/**
	 * Create the panel.
	 */
	public MEC() {
		listofcities.clear();
		setOpaque(false);
		setBounds(new Rectangle(0, 0,1190,546));
		setLayout(null);
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
		comboBox = new JComboBox(listofcities.toArray());
		comboBox.setBounds(185, 365, 158, 29);
		add(comboBox);
		
		comboBox_1 = new JComboBox(listofcities.toArray());
		comboBox_1.setBounds(185, 421, 158, 29);
		add(comboBox_1);
		
		JLabel lblDestn = new JLabel("DESTINATION");
		lblDestn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblDestn.setBounds(39, 421, 123, 22);
		add(lblDestn);
		
		textField = new JTextField();
		textField.setBounds(513, 365, 123, 38);
		add(textField);
		textField.setColumns(10);
		
		 chckbxAvailable = new JCheckBox("AVAILABILITY");
		 chckbxAvailable.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		 chckbxAvailable.setSelected(true);
		chckbxAvailable.setBounds(499, 438, 180, 29);
		add(chckbxAvailable);
		
		JButton btnSave = new JButton("UPDATE/ADD");
		btnSave.setForeground(new Color(47, 79, 79));
		btnSave.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatefare();
			}
		});
		btnSave.setBounds(280, 497, 163, 38);
		add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 64, 1190, 247);
		add(scrollPane);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			st=con.createStatement();
			rs = st.executeQuery("select * from Fare");
			table = new JTable(buildTableModel(rs));	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
		    try { if (rs != null) rs.close(); } catch (Exception e1) {};
		    try { if (st != null) st.close(); } catch (Exception e1) {};
		    try { if (con != null) con.close(); } catch (Exception e1) {};
		}
		
		table.setRowHeight(38);
		table.setEnabled(false);
		table.setFont(new Font("Serif", Font.BOLD, 16));
		table.setBackground(Color.YELLOW);

		scrollPane.setViewportView(table);
		
		lblSource = new JLabel("SOURCE");
		lblSource.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblSource.setBounds(39, 372, 123, 22);
		add(lblSource);
		
		lblFare = new JLabel("FARE");
		lblFare.setHorizontalAlignment(SwingConstants.CENTER);
		lblFare.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblFare.setBounds(406, 372, 97, 22);
		add(lblFare);
		
		lblUpdateAddfare = new JLabel("UPDATE / ADD FARE");
		lblUpdateAddfare.setForeground(new Color(210, 105, 30));
		lblUpdateAddfare.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 22));
		lblUpdateAddfare.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateAddfare.setBounds(406, 11, 303, 42);
		add(lblUpdateAddfare);
	}
	public void updatefare(){
		if(String.valueOf(comboBox.getSelectedItem()).equals(String.valueOf(comboBox_1.getSelectedItem())))
		{
			JOptionPane.showMessageDialog(null, "SOURCE AND DESTINATION SHOULDN'T BE SAME!");
			
		}
		else{
			String sql = new String();
			String src = String.valueOf(comboBox.getSelectedItem());
			String destn = String.valueOf(comboBox_1.getSelectedItem());
			StringTokenizer st1 = new StringTokenizer(src,",");
			StringTokenizer st2 = new StringTokenizer(destn,",");
			String src_name = st1.nextToken();
			String src_state = st1.nextToken();
			String destn_name = st2.nextToken();
			String destn_state = st2.nextToken();
			String fare = textField.getText();
			String aval = (chckbxAvailable.isSelected())?"1":"0";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				st=con.createStatement();
			sql = "insert into fare values('"+src_name+"',"+"'"+src_state+"',"+"'"+destn_name+"',"+"'"+destn_state+"',"+fare+","+aval+")";
			//System.out.println(sql);
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "FARE HAS BEEN ADDED!");
		} catch (SQLException e) {
			try {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "FARE ALREADY EXISTS" +"\n"+"DO YOU WANT TO UPDATE IT?","warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					String adss = "update fare set FARE_PER_PERSON = "+fare+",AVAILABILITY = "+aval+" where SOURCE_NAME = '"+src_name+"' and source_state = '"+src_state+"' and destination_name = '"+destn_name+"' and destination_state = '"+destn_state+"'";
					System.out.println(adss);
					st.executeUpdate(adss);
				
					JOptionPane.showMessageDialog(null, "FARE has been updated");
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
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
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				st=con.createStatement();
				rs = st.executeQuery("select * from fare");
				table.setEnabled(true);
				table.setModel(buildTableModel(rs));
				table.setEnabled(false);
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
		JLabel lblManageExistingPackages = new JLabel("MANAGE EXISTING CITIES");
		lblManageExistingPackages.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblManageExistingPackages.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageExistingPackages.setBounds(484, 0, 308, 34);
		add(lblManageExistingPackages);
	}
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);
	    
	    
	}

}
