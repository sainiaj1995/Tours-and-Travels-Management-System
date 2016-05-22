import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Updateinfo extends JPanel {
	static JTextField textField;
	static JTextField textField_1;
	static JTextField textField_2;
	static JTextField textField_3;
	static JPasswordField passwordField;
	static JPasswordField passwordField_1;
	Connection con;
	Statement st = null;
	ResultSet rs;

	
	public Updateinfo() {
		setBounds(new Rectangle(0, 0, 1220, 650));
		setLayout(null);
		setOpaque(false);
		JLabel lblUpdateYourAccount = new JLabel("UPDATE YOUR ACCOUNT DETAILS");
		lblUpdateYourAccount.setForeground(Color.RED);
		lblUpdateYourAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateYourAccount.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 21));
		lblUpdateYourAccount.setBounds(398, 11, 378, 44);
		add(lblUpdateYourAccount);
	
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(new Color(178, 34, 34));
		lblUsername.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 17));
		lblUsername.setBounds(239, 109, 171, 44);
		add(lblUsername);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(new Color(178, 34, 34));
		lblName.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 17));
		lblName.setBounds(239, 172, 171, 44);
		add(lblName);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(new Color(178, 34, 34));
		lblPassword.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 17));
		lblPassword.setBounds(249, 234, 171, 44);
		add(lblPassword);
		
		JLabel lblContact = new JLabel("CONTACT");
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setForeground(new Color(178, 34, 34));
		lblContact.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 17));
		lblContact.setBounds(239, 306, 171, 44);
		add(lblContact);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setForeground(new Color(178, 34, 34));
		lblAddress.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 17));
		lblAddress.setBounds(239, 367, 171, 44);
		add(lblAddress);
		
		textField = new JTextField();
		textField.setForeground(new Color(0, 128, 128));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(417, 111, 213, 42);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(new Color(0, 128, 128));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_1.setColumns(10);
		textField_1.setBounds(417, 174, 213, 42);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setForeground(new Color(0, 128, 128));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_2.setColumns(10);
		textField_2.setBounds(417, 308, 213, 42);
		add(textField_2);
		
		
		textField_3 = new JTextField();
		textField_3.setForeground(new Color(0, 128, 128));
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_3.setColumns(10);
		textField_3.setBounds(420, 368, 329, 42);
		add(textField_3);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(417, 237, 213, 43);
		add(passwordField);
		passwordField.setForeground(new Color(0, 128, 128));
		
		JLabel lblEnterOldPassword = new JLabel("ENTER OLD PASSWORD");
		lblEnterOldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterOldPassword.setForeground(new Color(178, 34, 34));
		lblEnterOldPassword.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 17));
		lblEnterOldPassword.setBounds(176, 494, 234, 44);
		add(lblEnterOldPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField_1.setForeground(new Color(0, 128, 128));
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		passwordField_1.setBounds(417, 495, 213, 43);
		add(passwordField_1);
		
		JButton btnNewButton = new JButton("SAVE AND EXIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
					st=con.createStatement();
					rs = st.executeQuery("select * from customer where username_cust = '"+Home.loggedinuser+"'");
					while(rs.next())
					{
						String pass = new String(passwordField_1.getPassword());
						if(rs.getString("password_cust").equals(pass))
						{
							String passn = new String(passwordField.getPassword());
							st.executeQuery("update customer set username_cust = '"+textField.getText()+"', name_cust = '"+textField_1.getText()+"', password_cust = '"+passn+"', DOB_CUST = null , contact_cust = '"+textField_2.getText()+"', address_cust = '"+textField_3.getText()+"' where username_cust = '"+Home.loggedinuser+"'");           
							passwordField_1.setText(null);
							JOptionPane.showMessageDialog(null, "Your account details have been updated!!!");
							Home.showUI();
						}
						else{
							JOptionPane.showMessageDialog(null, "Your old password is not correct!!!");
						}
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
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setForeground(new Color(165, 42, 42));
		btnNewButton.setBounds(667, 494, 171, 44);
		add(btnNewButton);
		
		JButton button = new JButton("<<--  BACK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.showUI();
			}
		});
		button.setForeground(new Color(165, 42, 42));
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(30, 11, 156, 44);
		add(button);
		
	}
}
