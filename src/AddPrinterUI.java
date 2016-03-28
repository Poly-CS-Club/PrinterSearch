import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class AddPrinterUI {

	private JFrame m_Main_F;
	private JFrame m_OldFrame;
	private JTextField m_Name_TF, m_Tension_TF, m_Impact_TF, m_LeadTime_TF,
	                   m_PartComplexity_TF, m_Tolerance_TF, m_Compression_TF;
	private JComboBox<String> m_Finish_CB, m_Materials_CB, m_Customizable_CB;
	private JPanel m_Labels_P, m_Input_P, m_Button_P;
	private JButton m_AddPrinter_B;
	private Driver m_Driver;
	private MenuUI m_MenuUI;
	
	public final Component[] parameterComponents =
        {m_Compression_TF, m_Tension_TF, m_Tolerance_TF, m_Impact_TF,
         m_LeadTime_TF, m_PartComplexity_TF, m_Customizable_CB,
         m_Materials_CB, m_Finish_CB};
	
	/**
	 * Creates a printer UI with specified frame and driver.
	 * 
	 * @param mainFrame the specified JFrame
	 * @param driver    the specified driver
	 */
	public AddPrinterUI(JFrame mainFrame, Driver driver, MenuUI menuUI)
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
		m_Name_TF = new JTextField();
		m_Compression_TF = new JTextField();
		m_Tension_TF = new JTextField();
		m_Impact_TF = new JTextField();
		m_LeadTime_TF = new JTextField();
		m_PartComplexity_TF = new JTextField();
		m_Tolerance_TF = new JTextField();
		
		// Instantiate panels
		m_Labels_P = new JPanel(new GridLayout(1,10,2,2));
		m_Input_P = new JPanel(new GridLayout(1,10,2,2));
		m_Button_P = new JPanel(new FlowLayout());
		
		// Instantiate combo boxes
		m_Finish_CB = new JComboBox<String>(
		        new String [] {"Matte", "Gloss", "Add New"}); 
		m_Materials_CB = new JComboBox<String>(
				new String [] {"Aluminum", "Stainless", "Add New"});
		m_Customizable_CB = new JComboBox<String>(
				new String [] {"true", "false"});
	}

	/**
	 * Sets GUI component values.
	 */
	private void designComponents() {
		GraphicsDevice graphicsDevice;
		Dimension defaultMaxSize, defaultMinSize;
		int frameWidth, frameHeight, screenWidth, screenHeight;
		
		// Determine window dimensions
		graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().
				getDefaultScreenDevice();
		screenWidth = graphicsDevice.getDisplayMode().getWidth();
		screenHeight = graphicsDevice.getDisplayMode().getHeight();
		frameWidth = screenWidth;
		frameHeight = (int) (screenHeight * 0.12);
		
		// Set up window
		m_Main_F.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		m_Main_F.setPreferredSize(new Dimension(frameWidth, frameHeight));
		m_Main_F.setMinimumSize(new Dimension(frameWidth, 130));
		m_Main_F.setLocation(
				(screenWidth/2) - (frameWidth/2),
				(screenHeight/2) - (frameHeight/2));
		m_Main_F.setResizable(false);
		m_Main_F.setLayout(new GridLayout(3,1,10,1));
		
		// Set up component dimensions
		defaultMaxSize = new Dimension(170, 25);
		defaultMinSize = new Dimension(150, 25);
		for(Component parameter : parameterComponents) {
			parameter.setMaximumSize(defaultMinSize);
			parameter.setMinimumSize(defaultMaxSize);
		}
		
		// Align parameter components
		m_Compression_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Name_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Tension_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Impact_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_LeadTime_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_PartComplexity_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Customizable_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Materials_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Tolerance_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Finish_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
		
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
		// Add parameter titles to panel
		m_Labels_P.add(new JLabel("Name"));
		m_Labels_P.add(new JLabel("Tension"));
		m_Labels_P.add(new JLabel("Compression"));
		m_Labels_P.add(new JLabel("Impact"));
		m_Labels_P.add(new JLabel("Part Complexity"));
		m_Labels_P.add(new JLabel("Lead Time"));
		m_Labels_P.add(new JLabel("EOC"));
		m_Labels_P.add(new JLabel("ROM"));
		m_Labels_P.add(new JLabel("Tolerance"));
		m_Labels_P.add(new JLabel("Finish"));
		
		// Add parameter text fields to panel
		m_Input_P.add(m_Name_TF);
		m_Input_P.add(m_Tension_TF);
		m_Input_P.add(m_Compression_TF);
		m_Input_P.add(m_Impact_TF);
		m_Input_P.add(m_PartComplexity_TF);
		m_Input_P.add(m_LeadTime_TF);
		m_Input_P.add(m_Customizable_CB);
		m_Input_P.add(m_Materials_CB);
		m_Input_P.add(m_Tolerance_TF);
		m_Input_P.add(m_Finish_CB);
		
		// Add button
		m_Button_P.add(m_AddPrinter_B);
		
		// Add GUI sub-components to window
		m_Main_F.add(m_Labels_P);
		m_Main_F.add(m_Input_P);
		m_Main_F.add(m_Button_P);
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
			switch(command)
			{
			    //TODO check all inputs to see if valid or not.
			    //TODO create window or pop-up for invalid inputs...
				case "Add New Printer":
					// Add printer to master printer database
					m_Driver.addPrinter(
							m_Name_TF.getText(), m_Tension_TF.getText(),
							m_Compression_TF.getText(),
							m_PartComplexity_TF.getText(),
							(String) m_Materials_CB.getSelectedItem(),
							m_Impact_TF.getText(), m_LeadTime_TF.getText(),
							(String) m_Customizable_CB.getSelectedItem(),
							m_Tolerance_TF.getText(),
							(String) m_Finish_CB.getSelectedItem());
					
					// Inform user that the printer was successfully added
					JOptionPane.showMessageDialog(
							m_Main_F, "Printer Added to DataBase",
							"Message", JOptionPane.PLAIN_MESSAGE);
					
					// Add new printer to search results panel
					m_MenuUI.getSearchResultsPanel().add(new PrinterUI(
					        m_MenuUI.getSearchResultsPanel().getComponentCount()+1,
							m_MenuUI.FRAME_WIDTH, m_MenuUI.FRAME_HEIGHT,
							m_Name_TF.getText(), m_Tension_TF.getText(), 
							m_Compression_TF.getText(),
							m_PartComplexity_TF.getText(), 
							(String) m_Materials_CB.getSelectedItem(),
							m_Impact_TF.getText(), m_LeadTime_TF.getText(),
							(String) m_Customizable_CB.getSelectedItem(), 
							m_Tolerance_TF.getText(),
							(String) m_Finish_CB.getSelectedItem()));
					
					//Refresh search results panel
					m_MenuUI.getSearchResultsPanel().revalidate();
					break;
				default: JOptionPane.showMessageDialog(
						m_Main_F, "Command: " + command,
						"Unknown Command", JOptionPane.PLAIN_MESSAGE);
					break;
			}
			
		}
		
	}
}
