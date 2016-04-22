package core;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
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
	private boolean onLink;
	private JPanel m_Main_P;
	public static int FRAME_HEIGHT = (int) (MenuWindow.FRAME_HEIGHT*.25);
	public static int FRAME_WIDTH = (int) (MenuWindow.FRAME_WIDTH*.5);
	
	public VendorInfoFrame(JLabel target)
	{
		onLink = true;
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
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setMinimumSize(new Dimension(FRAME_WIDTH-100, FRAME_HEIGHT-100));
		setMaximumSize(new Dimension(FRAME_WIDTH+100, FRAME_HEIGHT+100));
		setResizable(false);
		m_VendorInfo_L.getwebSite().addMouseListener(new MouseListener());
		
		m_Main_P.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	private void addComponents() 
	{
		m_Main_P.add(m_VendorInfo_L);
		add(m_Main_P);
	}

	public boolean isOnLink() {
		return onLink;
	}

	public void setOnLink(boolean onLink) {
		this.onLink = onLink;
	}
	private class MouseListener extends MouseAdapter
	{
		@Override
		public void mouseEntered(java.awt.event.MouseEvent evt) 
		{
			onLink = true;
			//System.out.println("true");
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent evt) 
		{	
			onLink = false;
			//System.out.println("false");
		}
		@Override
		public void mouseClicked(java.awt.event.MouseEvent evt) 
		{
			try {
				open(new URI(m_VendorInfo_L.getwebSite().getText()));
			} catch (URISyntaxException e) {
				JOptionPane.showMessageDialog(null, "Broken link");
			}
		}
		private void open(URI uri) {
		    if (Desktop.isDesktopSupported()) {
		      try {
		        Desktop.getDesktop().browse(uri);
		      } catch (IOException e) { JOptionPane.showMessageDialog(null, "Broken link");}
		    } else { JOptionPane.showMessageDialog(null, "Desktop Browser not found"); }
		  }
	}
}
