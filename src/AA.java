import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AA extends JPanel {
	Connection con;
	Statement st = null;
	ResultSet rs;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JTextField textField_2;
	/**
	 * Create the panel.
	 */
	public AA() {
		setBounds(new Rectangle(0, 0,1190,546));
		setLayout(null);
		setOpaque(false);
		
		JLabel lblChangeYourDetails = new JLabel("CHANGE YOUR DETAILS HERE");
		lblChangeYourDetails.setForeground(new Color(178, 34, 34));
		lblChangeYourDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeYourDetails.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblChangeYourDetails.setBounds(400, 11, 383, 45);
		add(lblChangeYourDetails);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(217, 107, 190, 45);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(80, 114, 107, 29);
		add(lblUsername);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(100, 200, 107, 29);
		add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(217, 193, 190, 45);
		add(textField_1);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(80, 281, 107, 29);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(217, 279, 190, 39);
		add(passwordField);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_2.setBounds(217, 354, 190, 39);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblContact = new JLabel("CONTACT");
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblContact.setBounds(80, 356, 107, 29);
		add(lblContact);
		
		JButton btnNewButton = new JButton("CHANGE USERNAME");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();
					st.executeUpdate("update admin set username_admin = '"+textField.getText()+"' where username_admin = '"+Home.loggedinadmin+"'" );
					Home.loggedinadmin = textField.getText();
					JOptionPane.showMessageDialog(null, "username has been updated");
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
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(456, 107, 199, 45);
		add(btnNewButton);
		
		JButton btnChangeName = new JButton("CHANGE NAME");
		btnChangeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();
					st.executeUpdate("update admin set name_admin = '"+textField_1.getText()+"' where username_admin = '"+Home.loggedinadmin+"'" );
					JOptionPane.showMessageDialog(null, "NAME has been updated");
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
		btnChangeName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnChangeName.setBounds(456, 192, 199, 45);
		add(btnChangeName);
		
		JButton btnChangePassword = new JButton("CHANGE PASSWORD");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();
					String pass = new String(passwordField.getPassword());
					st.executeUpdate("update admin set password_admin = '"+pass+"' where username_admin = '"+Home.loggedinadmin+"'" );
					JOptionPane.showMessageDialog(null, "password has been updated");
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
		btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnChangePassword.setBounds(456, 267, 199, 45);
		add(btnChangePassword);
		
		JButton btnChangeContact = new JButton("CHANGE CONTACT");
		btnChangeContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();
					st.executeUpdate("update admin set contact_admin = '"+textField_2.getText()+"' where username_admin = '"+Home.loggedinadmin+"'" );
					JOptionPane.showMessageDialog(null, "contact has been updated");
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
		btnChangeContact.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnChangeContact.setBounds(456, 348, 199, 45);
		add(btnChangeContact);
		
	}
}
