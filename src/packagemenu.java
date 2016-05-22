import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class packagemenu extends JPanel {

	/**
	 * Create the panel.
	 */
	static String pidselected="null";
	Connection con;
	Statement st = null;
	ResultSet rs;
	ArrayList<String> packagedata = new ArrayList<String>();
	ArrayList<String> packageid = new ArrayList<String>();
	public packagemenu() {
		
		setLayout(new BorderLayout(10, 10));
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			st=con.createStatement();
			rs = st.executeQuery("select * from packages");
			while(rs.next())
			{
				packagedata.add(rs.getString("PACKAGE_NAME"));
				packageid.add(rs.getString("PID"));
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
		JLabel lblDoubleClickOn = new JLabel("DOUBLE CLICK ON PACKAGES TO VIEW DETAILS");
		lblDoubleClickOn.setForeground(new Color(139, 0, 0));
		lblDoubleClickOn.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblDoubleClickOn.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblDoubleClickOn, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		//String data[] = {"fddddddddgdfffffffffffffffffffffffffffffffffffffffffffffffff", "B", "C", "D","A", "B", "C", "D","A", "B", "C", "D","A", "B", "C", "D"};
		
		JList list = new JList(packagedata.toArray());
		list.setBackground(new Color(211, 211, 211));
		list.setBorder(new LineBorder(new Color(128, 0, 0), 4));
		list.setToolTipText("DOUBLE CLICK TO VIEW DETAILS");
		list.setForeground(new Color(255, 0, 0));
		//list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setFont(new Font("Tempus Sans ITC", Font.BOLD, 26));
		list.setFixedCellHeight(50);
		list.setCellRenderer(getRenderer());
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setViewportView(list);
		 
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        list.setFixedCellHeight(50);
		        if (evt.getClickCount() == 2) {
		        		
		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            pidselected = packageid.get(index);
		            Packages.showpackage(pidselected);
		            Packages.bookpack.filldetail(pidselected);
		           // System.out.println(index);
		            
		            
		        } else if (evt.getClickCount() == 3) {

		            // Triple-click detected
		            int index = list.locationToIndex(evt.getPoint());
		        }
		    }
		});
		
	}
	private ListCellRenderer<? super String> getRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0,Color.darkGray));
                return listCellRendererComponent;
            }
        };
    }
}
