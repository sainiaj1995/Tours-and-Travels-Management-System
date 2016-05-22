import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class ticketconfirmed extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private static JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTextField textField_4;


	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ticketconfirmed(String src,String destn,String name[],String age[],String userid[],String sex[],String doj,int notr ) {
		/*Packages.bookpack.setVisible(false);
		Packages.packinfo.setVisible(false);
		Packages.packmenu.setVisible(true);*/
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(new Rectangle(0, 0, 1220, 650));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblCongratulations = new JLabel("CONGRATULATIONS!!!");
		lblCongratulations.setForeground(Color.GREEN);
		lblCongratulations.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongratulations.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));
		lblCongratulations.setBounds(374, 11, 448, 43);
		contentPane.add(lblCongratulations);
		
		
		
		
		JLabel lblNewLabel = new JLabel("SOURCE");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(51, 107, 110, 27);
		contentPane.add(lblNewLabel);
		
		
		textField = new JTextField(src);
		textField.setFont(new Font("Tahoma", Font.ITALIC, 17));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBounds(223, 103, 146, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblPackageName = new JLabel("DESTINATION");
		lblPackageName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPackageName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPackageName.setBounds(51, 158, 127, 36);
		contentPane.add(lblPackageName);
		
		textField_1 = new JTextField(destn);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.ITALIC, 17));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(223, 159, 295, 36);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Tahoma", Font.ITALIC, 17));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(985, 103, 146, 36);
		contentPane.add(textField_2);
		textField_3 = new JTextField(Integer.toString(notr));
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Tahoma", Font.ITALIC, 17));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(985, 159, 146, 36);
		contentPane.add(textField_3);
		
		
		
		JLabel lblTotalFare = new JLabel("TOTAL FARE");
		lblTotalFare.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalFare.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotalFare.setBounds(802, 107, 121, 27);
		contentPane.add(lblTotalFare);
		
		JLabel lblNoOfTravellers = new JLabel("NO OF TRAVELLERS");
		lblNoOfTravellers.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoOfTravellers.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNoOfTravellers.setBounds(805, 163, 167, 27);
		contentPane.add(lblNoOfTravellers);
		
		JLabel lblTravellersDetail = new JLabel("TRAVELLERS' DETAIL");
		lblTravellersDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblTravellersDetail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTravellersDetail.setBounds(534, 318, 183, 27);
		contentPane.add(lblTravellersDetail);
		Object column[] = {"NAME","AGE","SEX","USER_ID"};
		Object data[][] = new String[(notr)][4];
		for(int i=0;i<notr;i++)
		{
			data[i][0] = name[i];
			data[i][1] = age[i];
			data[i][2] = sex[i];
			data[i][3] = userid[i];
		}
		table = new JTable(data,column);
	table.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		table.setRowHeight(35);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setFont(new Font("Tahoma", Font.ITALIC, 15));
				//table.setModel(new MyModel());
				table.setEnabled(false);
				table.setGridColor(Color.RED);
				table.setFont(new Font("Serif", Font.ITALIC, 15));
				//table.setModel(tableModel);
	    getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBounds(201,356,818,225);
		JLabel lblDateOfJourney = new JLabel("DATE OF JOURNEY");
		lblDateOfJourney.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateOfJourney.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDateOfJourney.setBounds(43, 215, 167, 27);
		contentPane.add(lblDateOfJourney);
		
		textField_4 = new JTextField(doj);
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Tahoma", Font.ITALIC, 17));
		textField_4.setEditable(false);
		textField_4.setBounds(223, 211, 286, 36);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblYourPackageHas = new JLabel("YOUR TICKET HAS BEEN BOOKED");
		lblYourPackageHas.setForeground(new Color(128, 0, 0));
		lblYourPackageHas.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblYourPackageHas.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourPackageHas.setBounds(461, 65, 295, 27);
		contentPane.add(lblYourPackageHas);

	}
	public  static void setfare(double fare)
	{
		System.out.println("fare" + fare);
		textField_2.setText(Double.toString(fare));
	}

}
