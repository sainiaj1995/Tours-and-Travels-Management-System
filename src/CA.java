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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;

public class CA extends JPanel {
	private JTable table;
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JTextField textField_4;
	private JTextField textField_5;
	/**
	 * Create the panel.
	 */
	public CA() {
		setOpaque(false);
		setBounds(new Rectangle(0, 0,1190,546));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 1180, 247);
		add(scrollPane);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			st=con.createStatement();
			rs = st.executeQuery("select * from customer");
			table = new JTable(buildTableModel(rs));	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (st != null) st.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
		table.setRowHeight(38);
		table.setEnabled(false);
		table.setFont(new Font("Serif", Font.BOLD, 16));
		table.setBackground(Color.YELLOW);

		scrollPane.setViewportView(table);
		
		JLabel lblManageExistingPackages = new JLabel("MANAGE CUSTOMERS DETAILS");
		lblManageExistingPackages.setForeground(new Color(210, 105, 30));
		lblManageExistingPackages.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 22));
		lblManageExistingPackages.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageExistingPackages.setBounds(437, 0, 383, 34);
		add(lblManageExistingPackages);
		
		JLabel lblDeletePackage = new JLabel("DELETE USER ACCOUNT");
		lblDeletePackage.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeletePackage.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblDeletePackage.setBounds(21, 320, 204, 34);
		add(lblDeletePackage);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(68, 410, 102, 43);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblPid = new JLabel("username");
		lblPid.setHorizontalAlignment(SwingConstants.CENTER);
		lblPid.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblPid.setBounds(53, 365, 141, 34);
		add(lblPid);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();
						table.setEnabled(false);
						st.executeUpdate("delete from customer where username_cust = '"+textField.getText()+"'");
						rs = st.executeQuery("select * from customer");
						table.setModel(buildTableModel(rs));
						table.setEnabled(true);
						
					} catch (SQLException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
					    try { if (rs != null) rs.close(); } catch (Exception e) {};
					    try { if (st != null) st.close(); } catch (Exception e) {};
					    try { if (con != null) con.close(); } catch (Exception e) {};
					}
			}
		});
		btnDelete.setForeground(new Color(128, 0, 0));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(68, 464, 92, 43);
		add(btnDelete);
		
		JLabel lblUpdatePackage = new JLabel("UPDATE CUSTOMER ACCOUNT DETAILS");
		lblUpdatePackage.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdatePackage.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblUpdatePackage.setBounds(437, 320, 362, 34);
		add(lblUpdatePackage);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblUsername.setBounds(275, 365, 118, 34);
		add(lblUsername);
		
		JLabel lblPackageName = new JLabel("NAME");
		lblPackageName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPackageName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblPackageName.setBounds(447, 365, 118, 34);
		add(lblPackageName);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_2.setColumns(10);
		textField_2.setBounds(433, 410, 156, 43);
		add(textField_2);
		
		JLabel lblPrice = new JLabel("CONTACT");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblPrice.setBounds(616, 365, 118, 34);
		add(lblPrice);

		JButton btnUpdate = new JButton("UPDATE / ADD NEW ");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = new String(passwordField.getPassword());
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();
					
					st.executeUpdate("insert into customer values ('"+textField_1.getText()+"','"+pass+"','"+textField_2.getText()+"','"+textField_4.getText()+"',"+"null,'"+textField_3.getText()+"')");
					
					JOptionPane.showMessageDialog(null, "new package has been inserted");
					
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					try {
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog (null, "USERNAME ALREADY EXISTS" +"\n"+"DO YOU WANT TO UPDATE IT?","warning",dialogButton);
						if(dialogResult == JOptionPane.YES_OPTION){
							System.out.println("update customer set password_cust = '"+pass+"',name_cust = '"+textField.getText()+"',address_cust = '"+textField_4.getText()+" where username_cust = '"+textField_1.getText()+"'");
							st.executeUpdate("update customer set password_cust = '"+pass+"',name_cust = '"+textField_2.getText()+"',address_cust = '"+textField_4.getText()+"' where username_cust = '"+textField_1.getText()+"'");
							
							JOptionPane.showMessageDialog(null, "customer account has been updated");
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
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
					rs = st.executeQuery("select * from customer");
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
			}
		});
		btnUpdate.setForeground(new Color(128, 0, 0));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.setBounds(342, 464, 274, 43);
		add(btnUpdate);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(268, 410, 156, 43);
		add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_3.setColumns(10);
		textField_3.setBounds(599, 410, 156, 43);
		add(textField_3);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblPassword.setBounds(770, 365, 118, 34);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(765, 411, 141, 42);
		add(passwordField);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblAddress.setBounds(968, 365, 118, 34);
		add(lblAddress);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_4.setColumns(10);
		textField_4.setBounds(916, 410, 264, 43);
		add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setForeground(Color.BLACK);
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_5.setColumns(10);
		textField_5.setBounds(1056, 8, 134, 43);
		add(textField_5);
		
		textField_5.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }

			  public void warn() {
				  if(! textField_5.getText().equals("")){
					  try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
							st=con.createStatement();
							rs = st.executeQuery("select * from customer where username_cust like '%"+ textField_5.getText()+"%'");
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
				  }
				  else{
					  try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
							st=con.createStatement();
							rs = st.executeQuery("select * from customer");
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
				  }
				  
			  }
			  
			});
		
		
		
		JLabel lblSearchUsername = new JLabel("SEARCH USERNAME");
		lblSearchUsername.setForeground(Color.RED);
		lblSearchUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchUsername.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblSearchUsername.setBounds(901, 11, 156, 34);
		add(lblSearchUsername);
		
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
