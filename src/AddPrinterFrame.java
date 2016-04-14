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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
 * Pop-up window for adding printers to XML.  This frame is able to highlight
 * empty inputs.
 * 
 * @author Joshua Becker, Marcinina Alvaran
 */
public class AddPrinterFrame extends JFrame{


	private static final Color
	        EMPTY_TEXT_HIGHLIGHT = new Color(74, 239, 202),
	        DEFAULT_BG_COLOR = new Color(250, 250, 250);
	
	private JFrame m_Main_F;
	private AddPrinterLabel m_Name, m_Tension, m_Impact,
	                        m_Vendor, m_Tolerance, m_Compression,
	                        m_Finish, m_Materials;
	private JPanel m_TopRow_P, m_LastRow_P, m_Button_P;
	private JButton m_AddPrinter_B;
	private MenuWindow m_MenuUI;
	
	/**
	 * Creates a printer UI with specified frame and driver.
	 * 
	 * @param mainFrame the specified JFrame
	 * @param driver    the specified driver
	 */
	public AddPrinterFrame(MenuWindow menuUI)
	{
		m_MenuUI = menuUI;
		
		// Create and add window and components
	    createComponents();
	    designComponents();
	    addActionListeners();
	    addComponents();
	    pack();
	    setVisible(true);
	}
	/**
	 * Instantiates GUI components.
	 */
	private void createComponents() {
		
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(frameWidth, frameHeight));
		setMinimumSize(new Dimension(frameWidth-m_MenuUI.getSearchFilterPanel().getWidth()-5, 130));
		setLocation(
				(screenWidth/2) - (frameWidth/2),
				(screenHeight/2) - (frameHeight/2));
		setResizable(true);
		setLayout(new GridLayout(3,1,1,1));
		
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
		
		// AddPrinterLabel action listeners
		Component[] textFieldArray = {
				m_Name.getComponent(), m_Tension.getComponent(), m_Impact.getComponent(),
                m_Vendor.getComponent(), m_Tolerance.getComponent(), m_Compression.getComponent(),
                m_Finish.getComponent(), m_Materials.getComponent()};
		
		for (Component textField : textFieldArray)
			textField.addMouseListener(new PrinterMouseListener());
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
		add(m_TopRow_P);
		add(m_LastRow_P);
		add(m_Button_P);
		m_Main_F = this;
	}
	/**
	 * adds an Icon to the Frame
	 */
	private void addIcon()
	{
		String stringSearch = System.getProperty("os.name");
		String keyword = "Mac";
		ImageIcon img = new ImageIcon("src\\sift-logo-128x128.png");	// Windows image path.
		Boolean found = Arrays.asList(stringSearch.split(" ")).contains(keyword);
		if(found){
			ImageIcon imgChange = new ImageIcon("sift-logo-128x128.png");	// Mac image path.
			img = imgChange;
		}
		Image image = (img.getImage());
		setIconImage(image);
	}
	
	/**
	 * Changes component's background color to specified color
	 * 
	 * @param component  the component whose background color will be changed
	 * @param color      the new background color to highlight with
	 */
	private void addHighlight(AddPrinterLabel printerLabel, Color color) {
		printerLabel.getComponent().setBackground(color);
	}
	
	/**
	 * Changes any highlighted components' background color to the default
	 * background color.
	 */
	private void removeHighlight(Component component) {
		if (component.getBackground() == EMPTY_TEXT_HIGHLIGHT)
			component.setBackground(DEFAULT_BG_COLOR);
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
					String parameter = null;
					
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
						
						// Highlight empty fields
						if (tension.equals(""))
							addHighlight(m_Tension, EMPTY_TEXT_HIGHLIGHT);
						if (compression.equals(""))
							addHighlight(m_Compression, EMPTY_TEXT_HIGHLIGHT);
						if (impact.equals(""))
							addHighlight(m_Impact, EMPTY_TEXT_HIGHLIGHT);
						if (tolerance.equals(""))
							addHighlight(m_Tolerance, EMPTY_TEXT_HIGHLIGHT);
						if (Vendor.equals(""))
							addHighlight(m_Vendor, EMPTY_TEXT_HIGHLIGHT);
						if (name.equals(""))
							addHighlight(m_Name, EMPTY_TEXT_HIGHLIGHT);
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
						
						// adding printer to data base
						ToolBox.addPrinter(name, Vendor, tension, compression, (String) m_Materials.getSelectedItem(), 
										   impact, tolerance, (String) m_Finish.getSelectedItem());
						m_MenuUI.getSearchResultsPanel().reload();
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
	
	/**
	 * An action listener for a mouse to be used with an AddPrinterLabel
	 * component.
	 * 
	 * @author Marcinina Alvaran
	 * @see    AddPrinterLabel
	 */
	private class PrinterMouseListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e){
		}
		
		@Override
		public void mousePressed(MouseEvent e)
		{
			removeHighlight((Component)e.getSource());
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
}
