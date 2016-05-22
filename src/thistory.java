import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class thistory extends JFrame {
	Connection con;
	Statement st = null;
	ResultSet rs;
	JScrollPane scrollPane;
	JTable table;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public thistory() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(new Rectangle(0, 0, 1220, 650));
		getContentPane().setLayout(null);
		String sql = "select b.tid ,b.source_city,b.destin_city,bt.userId,bt.name,b.doj from bticket b inner join  btravellers bt on b.tid = bt.tid  where b.username_cust ='"+Home.loggedinuser+"'"+" order by b.doj desc,b.tid";
		System.out.println(sql);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			st=con.createStatement();
			rs = st.executeQuery(sql);
			table = new JTable(buildTableModel(rs));
			 scrollPane = new JScrollPane(table);
				scrollPane.setBounds(10, 78, 1184, 319);
				getContentPane().add(scrollPane);
				table.setRowHeight(50);
				table.setEnabled(false);
				table.setFont(new Font("Serif", Font.BOLD, 16));
				table.setBackground(Color.YELLOW);
				
				
				JButton button = new JButton("<< BACK ");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				button.setBounds(10, 11, 89, 23);
				getContentPane().add(button);
				
				JLabel lblNewLabel = new JLabel("TICKET HISTORY");
				lblNewLabel.setForeground(new Color(184, 134, 11));
				lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 22));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setBounds(469, 11, 248, 35);
				getContentPane().add(lblNewLabel);
				
				JCheckBox chckbxCancelIndividuals = new JCheckBox("CANCEL INDIVIDUALS TICKET");
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
				chckbxCancelIndividuals.setFont(new Font("Tahoma", Font.PLAIN, 14));
				chckbxCancelIndividuals.setBounds(10, 429, 230, 25);
				getContentPane().add(chckbxCancelIndividuals);
				
				textField = new JTextField();
				textField.setBounds(10, 499, 89, 35);
				getContentPane().add(textField);
				textField.setColumns(10);
				
				JLabel lblTid = new JLabel("TID");
				lblTid.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblTid.setHorizontalAlignment(SwingConstants.CENTER);
				lblTid.setBounds(21, 472, 46, 27);
				getContentPane().add(lblTid);
				
				textField_1 = new JTextField();
				textField_1.setEnabled(false);
				textField_1.setBounds(123, 499, 170, 35);
				getContentPane().add(textField_1);
				textField_1.setColumns(10);
				
				JLabel lblNewLabel_1 = new JLabel("USERID");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setBounds(155, 468, 103, 35);
				getContentPane().add(lblNewLabel_1);
				
				JLabel lblName = new JLabel("NAME");
				lblName.setHorizontalAlignment(SwingConstants.CENTER);
				lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblName.setBounds(365, 468, 103, 35);
				getContentPane().add(lblName);
				
				textField_2 = new JTextField();
				textField_2.setEnabled(false);
				textField_2.setColumns(10);
				textField_2.setBounds(317, 499, 196, 35);
				getContentPane().add(textField_2);
				
				JButton btnCancel = new JButton("CANCEL TICKET");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						boolean valid = false;
						Date todayDate = new Date();
						//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
						st=con.createStatement();
						rs = st.executeQuery("select * from bticket where TID ='"+textField.getText()+"'");
						while(rs.next()){
						if(rs.getDate("doj").after(todayDate))
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
						sql = "select * from bticket where TID ='"+textField.getText()+"'";
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
								sql = "delete from btravellers where TID = '"+textField.getText()+"'";
								st.executeUpdate(sql);
								sql = "delete from bticket where TID = '"+textField.getText()+"'";
								st.executeUpdate(sql);
							}
							else{
								sql = "delete from btravellers where TID = '"+textField.getText()+"' and userid = '" + textField_1.getText() + "'" ;
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
						sql = "delete from btravellers where TID = '"+textField.getText()+"'" ;
						try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
							st=con.createStatement();
							
							st.executeUpdate(sql);
							sql = "delete from bticket where TID = '"+textField.getText()+"'";
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
					JOptionPane.showMessageDialog(null,"your ticket has been cancelled money will be refunded in your bank account soon thank you");
					dispose();
					new thistory();
					
					
					}
					else{
						JOptionPane.showMessageDialog(null, "Ticket has been used you can't cancel " + "\n" + "Or Does not exists!! PLEASE ENTER VALID TID");
					}
					}
				});
				btnCancel.setBounds(621, 499, 138, 35);
				getContentPane().add(btnCancel);
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
