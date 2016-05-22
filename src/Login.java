import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
public class Login extends JPanel {
	private JTextField textField;
	static JTextField textField_2;
	private JPasswordField passwordField;
	static  JPasswordField passwordField_1;
	JButton btnSignUp = new JButton("Sign Up!");
	static JButton btnLogin_1;
	Connection con;
	Statement st = null;
	ResultSet rs;
	JCheckBox chckbxClickToLogin;
	/**
	 * Create the panel.
	 */
	public Login() {
		
		setLayout(null);
		setBounds(new Rectangle(0, 0, 1220, 650));
		setOpaque(false);
		textField_2 = new HintTextField("username");
		textField_2.setBounds(450, 92, 200, 40); //450, 145, 200, 40
		add(textField_2);
		textField_2.setColumns(10);
		textField_2.setToolTipText("username");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(new Color(220, 20, 60));
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		 btnLogin_1 = new JButton("Login");
		btnLogin_1.setForeground(new Color(25, 25, 112));
		btnLogin_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnLogin_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usr = textField_2.getText();
				String pass = new String(passwordField_1.getPassword());
				if(chckbxClickToLogin.isSelected()){
					function_admin(usr,pass);
				}
				else{
					function_cust(usr,pass);
				}
			}
		});
		btnLogin_1.setBounds(474, 250, 146, 39);
		add(btnLogin_1);
		btnSignUp.setForeground(new Color(220, 20, 60));
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		
		btnSignUp.setFocusCycleRoot(true);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SignUp();
			}
		});
		btnSignUp.setBounds(474, 305, 146, 39);
		add(btnSignUp);
		
		JLabel lblTourAndTravels = new JLabel("Tour and Travels");
		lblTourAndTravels.setForeground(Color.RED);
		lblTourAndTravels.setHorizontalAlignment(SwingConstants.CENTER);
		lblTourAndTravels.setFont(new Font("Vijaya", Font.BOLD | Font.ITALIC, 38));
		lblTourAndTravels.setBounds(422, 11, 267, 39);
		add(lblTourAndTravels);
		
		passwordField_1 = new HintTextField2("password");
		passwordField_1.setBounds(450, 145, 200, 40);
		add(passwordField_1);
		passwordField_1.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField_1.setForeground(new Color(220, 20, 60));
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		 chckbxClickToLogin = new JCheckBox("CLICK TO LOGIN AS ADMIN");
		chckbxClickToLogin.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxClickToLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxClickToLogin.setBounds(186, 252, 267, 39);
		add(chckbxClickToLogin);
		
		JLabel lblNewUserSignup = new JLabel("NEW USER.TO SIGNUP CLICK HERE.");
		lblNewUserSignup.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewUserSignup.setForeground(new Color(220, 20, 60));
		lblNewUserSignup.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewUserSignup.setBounds(119, 304, 345, 39);
		add(lblNewUserSignup);
		passwordField_1.setToolTipText("password");
		
	}
	public void function_admin(String usr,String pass)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			st=con.createStatement();

			rs = st.executeQuery("select * from admin");
			while(rs.next())
			{
				String s = rs.getString("USERNAME_ADMIN");
				String p = rs.getString("PASSWORD_ADMIN");
				if(s.equals(usr) && p.equals(pass))
				{
					//JOptionPane.showMessageDialog(null, "welcome");
					Home.loggedinadmin = s;
					new AdminHome();
					
					// write code to show admin work space....
					return;
				}
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
		
		
		JOptionPane.showMessageDialog(null, "invalid credentials");
	}
	public void function_cust(String usr,String pass)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			st=con.createStatement();

			rs = st.executeQuery("select * from customer");
			while(rs.next())
			{
				String s = rs.getString("USERNAME_CUST");
				String p = rs.getString("PASSWORD_CUST");
				if(s.equals(usr) && p.equals(pass))
				{
					//JOptionPane.showMessageDialog(null, "welcome");
					Home.showDestination();
					Home.loggedinuser = s;
					
					// write code to show customer work space....
					return;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    try { if (rs != null) rs.close(); } catch (Exception e1) {};
		    try { if (st != null) st.close(); } catch (Exception e1) {};
		    try { if (con != null) con.close(); } catch (Exception e1) {};
		}
		
		JOptionPane.showMessageDialog(null, "invalid credentials");
	}
	class HintTextField extends JTextField implements FocusListener {

		  private final String hint;
		  private boolean showingHint;

		  public HintTextField(final String hint) {
		    super(hint);
		    this.hint = hint;
		    this.showingHint = true;
		    super.addFocusListener(this);
		  }

		  @Override
		  public void focusGained(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText("");
		      showingHint = false;
		    }
		  }
		  @Override
		  public void focusLost(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText(hint);
		      showingHint = true;
		    }
		  }

		  @Override
		  public String getText() {
		    return showingHint ? "" : super.getText();
		  }
		}
	class HintTextField2 extends JPasswordField implements FocusListener {

		  private final String hint;
		  private boolean showingHint;

		  public HintTextField2(final String hint) {
		    super(hint);
		    this.hint = hint;
		    this.showingHint = true;
		    super.addFocusListener(this);
		  }

		  @Override
		  public void focusGained(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText("");
		      showingHint = false;
		    }
		  }
		  @Override
		  public void focusLost(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText(hint);
		      showingHint = true;
		    }
		  }

		  @Override
		  public String getText() {
		    return showingHint ? "" : new String(super.getPassword());
		  }
		}
}
