import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

public class DEFAULTADMIN extends JPanel {

	/**
	 * Create the panel.
	 */
	public DEFAULTADMIN() {
		setBounds(new Rectangle(0, 0,1220,650));
		setLayout(null);


ImageIcon image = new ImageIcon("C:\\Users\\mypc\\Desktop\\CiffTravelandTours_Banner1.png");
JLabel label = new JLabel("", new ImageIcon("C:\\Users\\Ajay\\workspace\\Tours and Travels\\resources\\travel_the_world-wallpaper-1366x768.jpg"), JLabel.CENTER);
//JPanel panel = new JPanel(new BorderLayout());
add( label);
label.setBounds(new Rectangle(0, 0,1220,650));

	}
}
