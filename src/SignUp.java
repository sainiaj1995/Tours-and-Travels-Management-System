import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JButton;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JTextField textField_3;
	JTextArea textArea;
	Connection con;
	Statement st = null;
	ResultSet rs;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public SignUp() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(158, 34, 247, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblUsername.setBounds(42, 33, 106, 32);
		contentPane.add(lblUsername);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setBounds(158, 80, 247, 35);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblName.setBounds(52, 76, 96, 37);
		contentPane.add(lblName);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(158, 131, 247, 35);
		contentPane.add(passwordField);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_3.setBounds(158, 268, 247, 49);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		 textArea = new JTextArea(15,3);
		 textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setWrapStyleWord(true);
		  textArea.setLineWrap(true);
		  textArea.setRows(3);
		textArea.setBounds(158, 184, 247, 58);
		contentPane.add(textArea);
		
		JButton btnSaveAndClose = new JButton("Save and close");
		btnSaveAndClose.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnSaveAndClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				function_signup();
				dispose();
			}
		});
		btnSaveAndClose.setBounds(341, 354, 165, 34);
		contentPane.add(btnSaveAndClose);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblPassword.setBounds(24, 134, 124, 37);
		contentPane.add(lblPassword);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblAddress.setBounds(24, 189, 124, 37);
		contentPane.add(lblAddress);
		
		JLabel lblContact = new JLabel("CONTACT");
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblContact.setBounds(34, 280, 124, 37);
		contentPane.add(lblContact);
	}
	public void function_signup()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			st=con.createStatement();
			String usr = textField.getText();
			String pass = new String(passwordField.getPassword());
			String name = textField_1.getText();
			String addr = textArea.getText();
			String contact = textField_3.getText();
			String sql = "insert into customer values('"+usr+"',"+"'"+pass+"',"+"'"+name+"',"+"'"+addr+ "'," + "to_date('01/04/1997','DD/MM/YYYY')" +"," + "'" +contact+"')";     
			System.out.println(sql);
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "invalid credentials");
			new SignUp();
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    try { if (rs != null) rs.close(); } catch (Exception e1) {};
		    try { if (st != null) st.close(); } catch (Exception e1) {};
		    try { if (con != null) con.close(); } catch (Exception e1) {};
		}

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
}
