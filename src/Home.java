import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Home extends JFrame {
	
	//panels delaration
	static Login login = new Login();
	static Destination destn = new Destination();
	static String loggedinuser = null;
	static String loggedinadmin = null;
	static bticket btkt = new bticket();
	static Updateinfo ui = new Updateinfo();
	static DEFAULTADMIN da= new DEFAULTADMIN();
	Connection con;
	Statement st = null;
	ResultSet rs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(0, 0, 1220, 650));
		getContentPane().setLayout(null);
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			st=con.createStatement();
			System.out.println("Connected");
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		login.setBounds(0, 0,1220, 650);
		getContentPane().add(login,BorderLayout.CENTER);
		login.setLayout(null);
		
		ui.setBounds(0, 0,1220, 650);
		getContentPane().add(ui,BorderLayout.CENTER);
		ui.setLayout(null);
		ui.setVisible(false);
		
		
		destn.setBounds(0, 0,1220, 650);
		getContentPane().add(destn,BorderLayout.CENTER);
		destn.setLayout(null);
		destn.setVisible(false);
		getContentPane().add(btkt,BorderLayout.CENTER);
		btkt.setLayout(null);
		btkt.setVisible(false);
	
		da.setBounds(0, 0,1220, 650);
		getContentPane().add(da);
		da.setLayout(null);
		da.setVisible(false);
		
		//da.setVisible(false);
		
		this.addWindowListener(new WindowAdapter() {
		    public void windowOpened(WindowEvent e) {
		    	login.btnSignUp.requestFocusInWindow();
		    }
		});
		
		setVisible(true);
	}
	
	public static void showDestination()
	{
		da.setVisible(false);
		login.setVisible(false);
		destn.setVisible(true);
		da.setVisible(true);
	}
	public static void showUI(){
		if(ui.isVisible())
		{
			da.setVisible(false);
			ui.setVisible(false);
			destn.setVisible(true);
			da.setVisible(true);
		}
		else{
			
			da.setVisible(false);
			ui.setVisible(true);
			destn.setVisible(false);
			da.setVisible(true);
		}
	}

}
