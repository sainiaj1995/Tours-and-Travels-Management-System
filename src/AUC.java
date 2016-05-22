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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class AUC extends JPanel {
	private JTable table;
	Connection con;
	Statement st = null;
	ResultSet rs;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Create the panel.
	 */
	public AUC() {
		setBounds(new Rectangle(0, 0,1190,546));
		setLayout(null);
		setOpaque(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 45, 1180, 247);
		add(scrollPane);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			st=con.createStatement();
			rs = st.executeQuery("select * from city");
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
		
		JLabel lblManageExistingPackages = new JLabel("UPDATE OR ADD CITIES");
		lblManageExistingPackages.setForeground(new Color(210, 105, 30));
		lblManageExistingPackages.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 22));
		lblManageExistingPackages.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageExistingPackages.setBounds(458, 0, 308, 34);
		add(lblManageExistingPackages);
		
		JLabel lblDeletePackage = new JLabel("DELETE CITY");
		lblDeletePackage.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeletePackage.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblDeletePackage.setBounds(33, 320, 152, 34);
		add(lblDeletePackage);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(53, 410, 148, 43);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblPid = new JLabel("CITY");
		lblPid.setHorizontalAlignment(SwingConstants.CENTER);
		lblPid.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPid.setBounds(53, 365, 118, 34);
		add(lblPid);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
					boolean cityfound = false;
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
						st=con.createStatement();

						table.setEnabled(false);
						String del = "delete from fare where ((source_name ='"+textField.getText()+"' and Source_state = '" + textField_4.getText() + "') or (destination_name = '"+textField.getText()+"' and destination_STATE = '" +textField_4.getText() + "'))";
						st.executeUpdate(del);
						del = "delete from city where cityname ='"+textField.getText()+"' and citystate = '" + textField_4.getText()+"'";
						if(st.executeUpdate(del) == 0)
						{
							JOptionPane.showMessageDialog(null, "enter valid city name take care of case sensitivity!");
							
						}
						else{
							JOptionPane.showMessageDialog(null, "city has been removed");
						}
						rs = st.executeQuery("select * from city");
						table.setModel(buildTableModel(rs));
						table.setEnabled(true);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
						MEC.table.setEnabled(true);
						MEC.table.setModel(buildTableModel(rs));
						MEC.table.setEnabled(false);
						ArrayList<String> listofcities = new ArrayList<String>();
						rs = st.executeQuery("select * from city");
						while(rs.next())
						{
							//cities[noofcities++] = rs.getString("cityname");
							listofcities.add(rs.getString("cityname")+","+rs.getString("citystate"));
						}
						MEC.comboBox.setModel(new javax.swing.DefaultComboBoxModel(listofcities.toArray()));
						MEC.comboBox_1.setModel(new javax.swing.DefaultComboBoxModel(listofcities.toArray()));
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
		});
		btnDelete.setForeground(new Color(128, 0, 0));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(166, 464, 92, 43);
		add(btnDelete);
		
		JLabel lblUpdatePackage = new JLabel("UPDATE PACKAGE");
		lblUpdatePackage.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdatePackage.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblUpdatePackage.setBounds(667, 305, 177, 34);
		add(lblUpdatePackage);
		
		JLabel lblCity = new JLabel("CITY");
		lblCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCity.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCity.setBounds(476, 365, 118, 34);
		add(lblCity);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(468, 410, 158, 43);
		add(textField_1);
		
		JLabel lblPackageName = new JLabel("STATE");
		lblPackageName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPackageName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPackageName.setBounds(663, 365, 118, 34);
		add(lblPackageName);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_2.setColumns(10);
		textField_2.setBounds(636, 410, 208, 43);
		add(textField_2);
		
		JLabel lblPrice = new JLabel("COUNTRY");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPrice.setBounds(862, 365, 118, 34);
		add(lblPrice);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_3.setColumns(10);
		textField_3.setBounds(854, 410, 165, 43);
		add(textField_3);

		JButton btnUpdate = new JButton(" ADD NEW ");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();

					st.executeUpdate("insert into city values ('"+textField_1.getText()+"','"+textField_2.getText()+"','"+textField_3.getText()+"')");
					JOptionPane.showMessageDialog(null, "new city has been added");
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
				    try { if (rs != null) rs.close(); } catch (Exception e1) {};
				    try { if (st != null) st.close(); } catch (Exception e1) {};
				    try { if (con != null) con.close(); } catch (Exception e1) {};
				}
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();

					rs = st.executeQuery("select * from city");
					table.setEnabled(true);
					table.setModel(buildTableModel(rs));
					table.setEnabled(false);
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
				
				ArrayList<String> listofcities = new ArrayList<String>();
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();

					rs = st.executeQuery("select * from city");
					while(rs.next())
					{
						//cities[noofcities++] = rs.getString("cityname");
						listofcities.add(rs.getString("cityname")+","+rs.getString("citystate"));
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
				
				MEC.comboBox.setModel(new javax.swing.DefaultComboBoxModel(listofcities.toArray()));
				MEC.comboBox_1.setModel(new javax.swing.DefaultComboBoxModel(listofcities.toArray()));
			}
		});
		btnUpdate.setForeground(new Color(128, 0, 0));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.setBounds(606, 464, 274, 43);
		add(btnUpdate);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_4.setColumns(10);
		textField_4.setBounds(211, 410, 150, 43);
		add(textField_4);
		
		JLabel lblState = new JLabel("STATE");
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		lblState.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblState.setBounds(222, 365, 118, 34);
		add(lblState);
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
