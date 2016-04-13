import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.Border;
/**
 * A window that shows the vender Info
 * 
 * @author Joshua Becker
 */
public class VendorInfoFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel m_Vendor_L;
	private VendorLabel m_VendorInfo_L;
	private JPanel m_Main_P;
	public static int FRAME_HEIGHT = (int) (MenuWindow.FRAME_HEIGHT*5);
	public static int FRAME_WIDTH = (int) (MenuWindow.FRAME_WIDTH*5);
	
	public VendorInfoFrame(JLabel target)
	{
		m_Vendor_L = target;
		createComponents();
		designComponents();
		addComponents();
	}

	private void createComponents() {
		m_Main_P = new JPanel();
		m_VendorInfo_L = new VendorLabel(m_Vendor_L.getText());
	}

	private void designComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		m_Main_P.setBackground(Color.GRAY);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setMinimumSize(new Dimension(FRAME_WIDTH-100, FRAME_HEIGHT-100));
		setMaximumSize(new Dimension(MenuWindow.FRAME_WIDTH, FRAME_HEIGHT+100));
		setResizable(false);
		
		m_Main_P.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	private void addComponents() 
	{
		m_Main_P.add(m_VendorInfo_L);
		add(m_Main_P);
	}
	
}
