import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class MPB extends JPanel {
	Connection con;
	Statement st = null;
	ResultSet rs;	JScrollPane scrollPane;
	JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public MPB() {
		setBounds(new Rectangle(0, 0,1190,546));
		setLayout(null);
		setOpaque(false);
		String sql = "select tp.ptid,tp.username_cust ,tp.package_name,pt.userId,pt.name,tp.pdoj from ticket_packages tp inner join  ptravellers pt on tp.ptid = pt.ptid " +" order by tp.pdoj desc,tp.ptid";
		//System.out.println(sql);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
		st=con.createStatement();
			rs = st.executeQuery(sql);
			table = new JTable(buildTableModel(rs));
			 scrollPane = new JScrollPane(table);
			 scrollPane.setOpaque(false);
				scrollPane.setBounds(0, 57, 1194, 319);
				add(scrollPane);
				table.setRowHeight(50);
				table.setEnabled(false);
				table.setFont(new Font("Serif", Font.BOLD, 16));
				table.setBackground(Color.YELLOW);
				
				JLabel lblNewLabel = new JLabel("BOOKED PACKAGES HISTORY");
				lblNewLabel.setForeground(new Color(184, 134, 11));
				lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 22));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setBounds(444, 11, 356, 35);
				add(lblNewLabel);
				
				JCheckBox chckbxCancelIndividuals = new JCheckBox("CANCEL INDIVIDUALS PACKAGES");
				chckbxCancelIndividuals.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent arg0) {
						if(chckbxCancelIndividuals.isSelected())
						{
							textField_1.setEnabled(true);
							textField_2.setEnabled(true);
						}
						else{

							textField_1.setEnabled(false);
							textField_2.setEnabled(false);
						}

					}
				});
				chckbxCancelIndividuals.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
				chckbxCancelIndividuals.setBounds(10, 429, 297, 25);
				add(chckbxCancelIndividuals);
				
				textField = new JTextField();
				textField.setBounds(10, 499, 89, 35);
				add(textField);
				textField.setColumns(10);
				
				JLabel lblTid = new JLabel("PTID");
				lblTid.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
				lblTid.setHorizontalAlignment(SwingConstants.CENTER);
				lblTid.setBounds(21, 472, 46, 27);
				add(lblTid);
				
				textField_1 = new JTextField();
				textField_1.setEnabled(false);
				textField_1.setBounds(123, 499, 170, 35);
				add(textField_1);
				textField_1.setColumns(10);
				
				JLabel lblNewLabel_1 = new JLabel("USERID");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setBounds(155, 468, 103, 35);
				add(lblNewLabel_1);
				
				JLabel lblName = new JLabel("NAME");
				lblName.setHorizontalAlignment(SwingConstants.CENTER);
				lblName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
				lblName.setBounds(365, 468, 103, 35);
				add(lblName);
				
				textField_2 = new JTextField();
				textField_2.setEnabled(false);
				textField_2.setColumns(10);
				textField_2.setBounds(317, 499, 196, 35);
				add(textField_2);
				
				JButton btnCancel = new JButton("CANCEL PACKAGE");
				btnCancel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						boolean valid = false;
						Date todayDate = new Date();
						//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
						st=con.createStatement();
						rs = st.executeQuery("select * from ticket_packages where PTID ='"+textField.getText()+"'");
						while(rs.next()){
						if(rs.getDate("pdoj").after(todayDate))
						{
							valid  = true;
						}
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
					if(valid){
					String sql;	
					if(chckbxCancelIndividuals.isSelected())
					{
						sql = "select * from ticket_packages where PTID ='"+textField.getText()+"'";
						try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
							st=con.createStatement();
							rs  = st.executeQuery(sql);
							int no=0;
							while(rs.next())
							{
								no = rs.getInt("no_of_travellers");
							}
							if(no == 1)
							{
								sql = "delete from ptravellers where PTID = '"+textField.getText()+"'";
								st.executeUpdate(sql);
								sql = "delete from ticket_packages where PTID = '"+textField.getText()+"'";
								st.executeUpdate(sql);
							}
							else{
								sql = "delete from ptravellers where PTID = '"+textField.getText()+"' and userid = '" + textField_1.getText() + "'" ;
								st.executeUpdate(sql);
							}
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
					
					}
					else{
						sql = "delete from ptravellers where PTID = '"+textField.getText()+"'" ;
						try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
							st=con.createStatement();
							st.executeUpdate(sql);
							sql = "delete from ticket_packages where PTID = '"+textField.getText()+"'";
							st.executeUpdate(sql);
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
					
					}
					JOptionPane.showMessageDialog(null,"your package has been cancelled money will be refunded in your bank account soon thank you");
					
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
						st=con.createStatement();
						rs = st.executeQuery("select tp.ptid,tp.username_cust ,tp.package_name,pt.userId,pt.name,tp.pdoj from ticket_packages tp inner join  ptravellers pt on tp.ptid = pt.ptid " +" order by tp.pdoj desc,tp.ptid");
						table.setModel(buildTableModel(rs));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
					    try { if (rs != null) rs.close(); } catch (Exception e1) {};
					    try { if (st != null) st.close(); } catch (Exception e1) {};
					    try { if (con != null) con.close(); } catch (Exception e1) {};
					}
					
					
					}
					else{
						JOptionPane.showMessageDialog(null, "PACKAGE has been used you can't cancel");
					}
					}
				});
				btnCancel.setBounds(621, 492, 179, 42);
				add(btnCancel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}finally {
		    try { if (rs != null) rs.close(); } catch (Exception e1) {};
		    try { if (st != null) st.close(); } catch (Exception e1) {};
		    try { if (con != null) con.close(); } catch (Exception e1) {};
		}
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
