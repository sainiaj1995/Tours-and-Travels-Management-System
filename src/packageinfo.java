import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class packageinfo extends JPanel {
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextArea textArea;
	static Connection con;
	static Statement st = null;
	static ResultSet rs;
	/**
	 * Create the panel.
	 */
	public packageinfo() {
		setBounds(new Rectangle(0, 0, 1220, 650));
		setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("CLICK HERE TO VIEW ANOTHER PACKAGES");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Packages.packinfo.setVisible(false);
				Packages.packmenu.setVisible(true);
			}
		});
		add(btnNewButton, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.LIGHT_GRAY);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PACAKAGE ID:-");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(47, 17, 166, 45);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setEditable(false);
		textField.setBounds(223, 17, 297, 43);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PACKAGE NAME-:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(588, 21, 156, 37);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_1.setEditable(false);
		textField_1.setBounds(778, 20, 413, 45);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPackagePrice = new JLabel("PACKAGE PRICE:-");
		lblPackagePrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPackagePrice.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblPackagePrice.setBounds(349, 113, 188, 37);
		panel.add(lblPackagePrice);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_2.setEditable(false);
		textField_2.setBounds(564, 113, 405, 45);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDescription = new JLabel("DESCRIPTION-:");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblDescription.setBounds(26, 205, 134, 29);
		panel.add(lblDescription);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textArea.setBackground(SystemColor.controlHighlight);
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setEditable(false);
		textArea.setBounds(135, 245, 1063, 252);
		panel.add(textArea);
		
		JButton btnBookThisPackage = new JButton("BOOK THIS PACKAGE NOW!");
		btnBookThisPackage.setBounds(10, 508, 1220, 41);
		panel.add(btnBookThisPackage);
		btnBookThisPackage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   Packages.bookpack.setVisible(true);
			   Packages.packinfo.setVisible(false);
			   
			}
		});
		btnBookThisPackage.setForeground(Color.BLUE);
		btnBookThisPackage.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		
	

	}
	public static void fillpackage(String pid)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			st=con.createStatement();

			rs = st.executeQuery("select * from packages where PID ="+"'"+pid+"'");
			rs.next();
			textField.setText(rs.getString("PID"));
			textField_1.setText(rs.getString("PACKAGE_NAME"));
			textField_2.setText(rs.getString("PACKAGE_PRICE"));
			textArea.setText(rs.getString("package_info"));
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
}
