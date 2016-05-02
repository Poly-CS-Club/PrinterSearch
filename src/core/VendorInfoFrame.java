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
	
	/**
	 * Creates a JLabel with vendor information.
	 * 
	 * @param target the JLabel containing the vendor's name
	 */
	public VendorInfoFrame(JLabel target)
	{
		onLink = true;
		m_Vendor_L = target;
		createComponents();
		designComponents();
		addComponents();
	}

	/**
	 * Instantiates label components.
	 */
	private void createComponents() {
		m_Main_P = new JPanel();
		m_VendorInfo_L = new VendorLabel(m_Vendor_L.getText());
	}

	/**
	 * Sets default values, dimensions, and borders for the fram.
	 */
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
		//m_VendorInfo_L.getwebSite().addMouseListener(new MouseListener());
		
		m_Main_P.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	/**
	 * Adds the background panel and vendor name label to the frame.
	 */
	private void addComponents() 
	{
		m_Main_P.add(m_VendorInfo_L);
		add(m_Main_P);
	}

	/**
	 * Returns whether vendor information is being viewed
	 * 
	 * @return true if vendor information is being viewed and false otherwise
	 */
	public boolean isOnLink() {
		return onLink;
	}

	/**
	 * Sets whether vendor information is being viewed.
	 * 
	 * @param onLink the boolean representing whether vendor information
	 *               is being viewed
	 */
	public void setOnLink(boolean onLink) {
		this.onLink = onLink;
	}
	
	/**
	 * A mouse listener that checks vendor label interaction.
	 * 
	 * @author Joshua Becker
	 *
	 */
	private class MouseListener extends MouseAdapter
	{
	    /**
	     * Sets onLink to true when mouse is actively hovering.
	     */
		@Override
		public void mouseEntered(java.awt.event.MouseEvent evt) 
		{
			onLink = true;
			//System.out.println("true");
		}

		/**
		 * Sets onLink to false when mouse exits.
		 */
		@Override
		public void mouseExited(java.awt.event.MouseEvent evt) 
		{	
			onLink = false;
			//System.out.println("false");
		}
		
		/**
		 * Opens vendor website on click.
		 */
		@Override
		public void mouseClicked(java.awt.event.MouseEvent evt) 
		{
			try {
				open(new URI(m_VendorInfo_L.getwebSite().getText()));
			} catch (URISyntaxException e) {
				JOptionPane.showMessageDialog(null, "Broken link");
			}
		}
		
		/**
		 * Opens specified website using a desktop browser.
		 * 
		 * @param uri the URI containing the vendor's website url
		 */
		private void open(URI uri) {
		    if (Desktop.isDesktopSupported()) {
		      try {
		        Desktop.getDesktop().browse(uri);
		      } catch (IOException e) { JOptionPane.showMessageDialog(null, "Broken link");}
		    } else { JOptionPane.showMessageDialog(null, "Desktop Browser not found"); }
		  }
	}
}
