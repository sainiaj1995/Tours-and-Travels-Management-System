import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Packages extends JFrame {
	static packagemenu packmenu; 
	static packageinfo packinfo;
	static bookpackage bookpack;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Packages() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(new Rectangle(0, 0, 1220, 650));
		getContentPane().setLayout(new BorderLayout(0, 0));
		//String data[] = {"fddddddddgdfffffffffffffffffffffffffffffffffffffffffffffffff", "B", "C", "D","A", "B", "C", "D","A", "B", "C", "D","A", "B", "C", "D"};
		//getContentPane().add(packmenu);	
		//
		//packinfo.setVisible(false);
		packinfo = new packageinfo();
		bookpack = new bookpackage();
		packmenu = new packagemenu();
		packmenu.setVisible(true);
		getContentPane().add(bookpack);
		bookpack.setVisible(false);
		getContentPane().add(packinfo);
		packinfo.setVisible(false);
		getContentPane().add(packmenu);
	}
	public static void showpackage(String pid)
	{
		packinfo.setVisible(true);
		packmenu.setVisible(false);
		packageinfo.fillpackage(pid);
	}
	
	
}
