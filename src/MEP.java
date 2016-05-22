import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MEP extends JPanel {
	private JTable table;
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Create the panel.
	 *  
	 */
	public MEP()  {
		setOpaque(false);
		setBounds(new Rectangle(0, 0,1190,546));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBounds(10, 62, 1180, 247);
		add(scrollPane);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			st=con.createStatement();
			rs = st.executeQuery("select * from Packages");
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
		
		JLabel lblManageExistingPackages = new JLabel("MANAGE EXISTING PACKAGES");
		lblManageExistingPackages.setForeground(new Color(210, 105, 30));
		lblManageExistingPackages.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 22));
		lblManageExistingPackages.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageExistingPackages.setBounds(485, 11, 391, 34);
		add(lblManageExistingPackages);
		
		JLabel lblDeletePackage = new JLabel("DELETE PACKAGE");
		lblDeletePackage.setForeground(Color.BLACK);
		lblDeletePackage.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeletePackage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDeletePackage.setBounds(33, 320, 172, 34);
		add(lblDeletePackage);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(68, 410, 102, 43);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblPid = new JLabel("PID");
		lblPid.setHorizontalAlignment(SwingConstants.CENTER);
		lblPid.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPid.setBounds(53, 365, 118, 34);
		add(lblPid);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();
						table.setEnabled(false);
						st.executeUpdate("delete from Packages where pid = '"+textField.getText()+"'");
						rs = st.executeQuery("select * from Packages");
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
		
		JLabel lblUpdatePackage = new JLabel("UPDATE PACKAGE");
		lblUpdatePackage.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdatePackage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUpdatePackage.setBounds(342, 320, 166, 34);
		add(lblUpdatePackage);
		
		JLabel label = new JLabel("PID");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		label.setBounds(275, 365, 118, 34);
		add(label);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(291, 410, 102, 43);
		add(textField_1);
		
		JLabel lblPackageName = new JLabel("PACKAGE NAME");
		lblPackageName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPackageName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPackageName.setBounds(471, 365, 118, 34);
		add(lblPackageName);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_2.setColumns(10);
		textField_2.setBounds(434, 410, 248, 43);
		add(textField_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(859, 410, 300, 109);
		add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JLabel lblPackageInfo = new JLabel("PACKAGE INFO.");
		lblPackageInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPackageInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPackageInfo.setBounds(892, 365, 118, 34);
		add(lblPackageInfo);
		
		JLabel lblPrice = new JLabel("PRICE");
		lblPrice.setForeground(Color.BLACK);
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPrice.setBounds(706, 365, 118, 34);
		add(lblPrice);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_3.setColumns(10);
		textField_3.setBounds(716, 410, 102, 43);
		add(textField_3);

		JButton btnUpdate = new JButton("UPDATE / ADD NEW ");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();
					st.executeUpdate("insert into Packages values ('"+textField_1.getText()+"','"+textField_2.getText()+"','"+textArea.getText()+"',"+textField_3.getText()+")");
					JOptionPane.showMessageDialog(null, "new package has been inserted");
					
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					try {
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog (null, "PACKAGE ALREADY EXISTS" +"\n"+"DO YOU WANT TO UPDATE IT?","warning",dialogButton);
						if(dialogResult == JOptionPane.YES_OPTION){
							st.executeUpdate("update packages set package_name = '"+textField_2.getText()+"',package_info = '"+textArea.getText()+"',package_price = "+textField_3.getText()+" where pid = '"+textField_1.getText()+"'");
							JOptionPane.showMessageDialog(null, "package has been updated");
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
					rs = st.executeQuery("select * from packages");
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
		
		JLabel lblNewLabel = new JLabel("SEARCH PID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(942, 11, 118, 40);
		add(lblNewLabel);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_4.setBounds(1058, 19, 111, 32);
		add(textField_4);
		textField_4.setColumns(10);
		textField_4.getDocument().addDocumentListener(new DocumentListener() {
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
				  if(! textField_4.getText().equals("")){
					  try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
							st=con.createStatement();
							rs = st.executeQuery("select * from packages where PID like '%"+ textField_4.getText()+"%'");
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
							rs = st.executeQuery("select * from packages");
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
