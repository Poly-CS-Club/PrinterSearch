import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * window for adding printers to xml
 * @author Joshua Becker
 *
 */
public class AddPrinterFrame {

	private JFrame m_Main_F;
	private JFrame m_OldFrame;
	private AddPrinterLabel m_Name, m_Tension, m_Impact,
	                        m_Vendor, m_Tolerance, m_Compression,
	                        m_Finish, m_Materials;
	private JPanel m_TopRow_P, m_LastRow_P, m_Button_P;
	private JButton m_AddPrinter_B;
	private ToolBox m_Driver;
	private MenuWindow m_MenuUI;
	
	/**
	 * Creates a printer UI with specified frame and driver.
	 * 
	 * @param mainFrame the specified JFrame
	 * @param driver    the specified driver
	 */
	public AddPrinterFrame(JFrame mainFrame, ToolBox driver, MenuWindow menuUI)
	{
		m_Driver = driver;
		m_MenuUI = menuUI;
		m_OldFrame = mainFrame;
		
		// Create and add window and components
	    createComponents();
	    designComponents();
	    addActionListeners();
	    addComponents();
	    m_Main_F.pack();
	    m_Main_F.setVisible(true);
	}
	/**
	 * Instantiates GUI components.
	 */
	private void createComponents() {
		// Instantiate window and button
		m_Main_F = new JFrame("Add New Printers");
		
		m_AddPrinter_B = new JButton("Add New Printer");
		
		// Instantiate test fields
		m_Name = new AddPrinterLabel("Name", new JTextField());
		m_Compression = new AddPrinterLabel("Compression", new JTextField());
		m_Tension = new AddPrinterLabel("Tension", new JTextField());
		m_Impact = new AddPrinterLabel("Impact", new JTextField());
		m_Vendor = new AddPrinterLabel("Vendor", new JTextField());
		m_Tolerance = new AddPrinterLabel("Tolerance", new JTextField());
		m_Finish = new AddPrinterLabel("Finish", new JComboBox<String>(ToolBox.getFinishList()));
		m_Materials = new AddPrinterLabel("Materials", new JComboBox<String>(ToolBox.getMaterialList()));
		
		// Instantiate panels
		m_TopRow_P = new JPanel();
		m_LastRow_P = new JPanel();
		m_Button_P = new JPanel();
	}

	/**
	 * Sets GUI component values.
	 */
	private void designComponents() {
		addIcon();
		
		int frameWidth, frameHeight, screenWidth, screenHeight;
		
		// Determine window dimensions
		screenWidth = MenuWindow.s_SCREEN_WIDTH;
		screenHeight = MenuWindow.s_SCREEN_HEIGHT;
		frameWidth = MenuWindow.FRAME_WIDTH/2;
		frameHeight = MenuWindow.FRAME_HEIGHT/4;
		
		// Set up window
		m_Main_F.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		m_Main_F.setPreferredSize(new Dimension(frameWidth, frameHeight));
		m_Main_F.setMinimumSize(new Dimension(frameWidth-m_MenuUI.getSearchFilterPanel().getWidth()-5, 130));
		m_Main_F.setLocation(
				(screenWidth/2) - (frameWidth/2),
				(screenHeight/2) - (frameHeight/2));
		m_Main_F.setResizable(true);
		m_Main_F.setLayout(new GridLayout(3,1,1,1));
		
		// Set up submit button
		m_AddPrinter_B.setPreferredSize(new Dimension(150,25));
		m_AddPrinter_B.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	/**
	 * Adds action listeners to GUI components.
	 */
	private void addActionListeners()
	{
		m_AddPrinter_B.addActionListener(new ButtonListener());
	}

	/**
	 * Add components to main frame.
	 */
	private void addComponents() 
	{
		
		// Add parameter top row components to panel
		m_TopRow_P.add(m_Name);
		m_TopRow_P.add(m_Vendor);
		m_TopRow_P.add(m_Tension);
		m_TopRow_P.add(m_Compression);
		
		// Add parameter last row components to panel
		m_LastRow_P.add(m_Impact);
		m_LastRow_P.add(m_Materials);
		m_LastRow_P.add(m_Tolerance);
		m_LastRow_P.add(m_Finish);
		
		// Add button
		m_Button_P.add(m_AddPrinter_B);
		
		// Add GUI sub-components to window
		m_Main_F.add(m_TopRow_P);
		m_Main_F.add(m_LastRow_P);
		m_Main_F.add(m_Button_P);
	}
	/**
	 * adds an Icon to the Frame
	 */
	private void addIcon()
	{
		String stringSearch = System.getProperty("os.name");
		String keyword = "Mac";
		ImageIcon img = new ImageIcon("src\\printer-orange.png");	// Windows image path.
		Boolean found = Arrays.asList(stringSearch.split(" ")).contains(keyword);
		if(found){
			ImageIcon imgChange = new ImageIcon("printer-orange.png");	// Mac image path.
			img = imgChange;
		}
		Image image = (img.getImage());
		m_Main_F.setIconImage(image);
	}
	/**
	 * An action listener for a button.
	 * 
	 * @author Joshua Becker
	 *
	 */
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent action) {
			String command = action.getActionCommand();
			boolean errorFlag = false, emptyField = false;
			switch(command)
			{
			    //TODO create window or pop-up for invalid inputs...
				case "Add New Printer":
					String tension = null;
					String compression = null;
					String impact = null;
					String tolerance = null;
					String Vendor = null;
					String name = null;
					
					tension = m_Tension.getInput();
					compression = m_Compression.getInput();
					impact = m_Impact.getInput();
					tolerance = m_Tolerance.getInput();
					Vendor = m_Vendor.getInput();
					name = m_Name.getInput();
					
					if(tension.equals("") || compression.equals("") || impact.equals("") || tolerance.equals("") ||
					   Vendor.equals("") || name.equals(""))
					{
						emptyField = true;
					}
					
				    try
				    {
				    	//Checking for valid inputs
				    	//TODO give a more descriptive error message, with exactly witch one is causing the error.
				    	Double.parseDouble(tension);
				    	Double.parseDouble(compression);
				    	Double.parseDouble(impact);
				    	Double.parseDouble(tolerance);
				    }catch(NumberFormatException e)
				    {
				    	errorFlag = true;
				    }
					// Add printer to master printer database
				    if(!errorFlag && !emptyField)
				    {
						// Inform user that the printer was successfully added
						JOptionPane.showMessageDialog(
								m_Main_F, "Printer Added to DataBase",
								"Message", JOptionPane.PLAIN_MESSAGE);
						
						// Add new printer to search results panel
						m_MenuUI.getSearchResultsPanel().add(new PrinterLabel(
						        m_MenuUI.getSearchResultsPanel().getComponentCount()+1,
								MenuWindow.FRAME_WIDTH, MenuWindow.FRAME_HEIGHT,
								name,
								Vendor,
								tension,
								compression,
								impact,
								(String) m_Materials.getSelectedItem(),
								tolerance,
								(String) m_Finish.getSelectedItem()));
						
						//Refresh search results panel and the main frame.
						m_MenuUI.getSearchResultsPanel().revalidate();
						m_MenuUI.setSize(m_MenuUI.getWidth()-1, m_MenuUI.getHeight()-1);
						m_MenuUI.setSize(m_MenuUI.getWidth()+1, m_MenuUI.getHeight()+1);
						// adding printer to data base
						ToolBox.addPrinter(name, Vendor, tension, compression, (String) m_Materials.getSelectedItem(), 
										   impact, tolerance, (String) m_Finish.getSelectedItem());
				    }else if(errorFlag && !emptyField)
				    {
				    	JOptionPane.showMessageDialog(m_Main_F, "Error one or more of the inputs are incompatible.", "Warning", JOptionPane.PLAIN_MESSAGE);
				    }else
				    {
				    	JOptionPane.showMessageDialog(m_Main_F, "Warning all data fields must have parameters.", "Warning", JOptionPane.PLAIN_MESSAGE);			    	
				    }
					break;
				default: JOptionPane.showMessageDialog(
						m_Main_F, "Command: " + command,
						"Unknown Command", JOptionPane.PLAIN_MESSAGE);
					break;
			}
			
		}
		
	}
}
