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
	private JTextField m_Name_TF, m_Tenstion_TF, m_Impact_TF, m_LeadTime_TF, m_PartComplexity_TF, m_Tolerance_TF, m_Compression_TF;
	private JComboBox<String>  m_Finish_CB, m_ROM_CB, m_EOC_CB;
	private JPanel m_Labels_P, m_Input_P, m_Button_P;
	private JButton m_AddPrinter_B;
	private Driver m_Driver;
	
	/**
	 * Creates a printer UI with specified frame and driver.
	 * 
	 * @param mainFrame the specified JFrame
	 * @param driver    the specified driver
	 */
	public AddPrinterUI(JFrame mainFrame, Driver driver)
	{
		m_Driver = driver;
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
		m_Main_F = new JFrame("Add New Printers");
		
		m_Name_TF = new JTextField();
		m_Compression_TF = new JTextField();
		m_Tenstion_TF = new JTextField();
		m_Impact_TF = new JTextField();
		m_LeadTime_TF = new JTextField();
		m_PartComplexity_TF = new JTextField();
		
		m_Labels_P = new JPanel(new GridLayout(1,10,2,2));
		m_Input_P = new JPanel(new GridLayout(1,10,2,2));
		m_Button_P = new JPanel(new FlowLayout());
		
		m_Tolerance_TF = new JTextField();
		
		m_Finish_CB = new JComboBox<String>(new String [] {"Matte", "Gloss", "Add New"});
		m_ROM_CB = new JComboBox<String>(new String [] {"Aluminum", "Stainless", "Add New"});
		m_EOC_CB = new JComboBox<String>(new String [] {"true", "false"});
		
		m_AddPrinter_B = new JButton("Add New Printer");
	}

	/**
	 * Sets GUI component values.
	 */
	private void designComponents() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();// Getting size of screen
		int screenWidth = gd.getDisplayMode().getWidth(),
		    screenHeight = gd.getDisplayMode().getHeight();
		
		int frameWidth = (int) (screenWidth * 0.75),
		    frameHeight = (int) (screenHeight * 0.12);
		
		Dimension defaultMaxSize = new Dimension(170, 25),
				  defaultMinSize = new Dimension(150, 25);
		
		m_Main_F.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		m_Main_F.setPreferredSize(new Dimension(frameWidth, frameHeight));
		m_Main_F.setMinimumSize(new Dimension(frameWidth, 100));
		m_Main_F.setLocation((screenWidth/2) - (frameWidth/2),(screenHeight/2) - (frameHeight/2));// centering
		m_Main_F.setResizable(false);
		m_Main_F.setLayout(new GridLayout(3,1,10,1));
		
		m_Compression_TF.setMaximumSize(defaultMaxSize);
		m_Compression_TF.setMinimumSize(defaultMinSize);
		m_Compression_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		m_Name_TF.setMaximumSize(defaultMaxSize);
		m_Name_TF.setMinimumSize(defaultMinSize);
		m_Name_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		m_Tenstion_TF.setMaximumSize(defaultMaxSize);
		m_Tenstion_TF.setMinimumSize(defaultMinSize);
		m_Tenstion_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		m_Impact_TF.setMaximumSize(defaultMaxSize);
		m_Impact_TF.setMinimumSize(defaultMinSize);
		m_Impact_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		m_LeadTime_TF.setMaximumSize(defaultMaxSize);
		m_LeadTime_TF.setMinimumSize(defaultMinSize);
		m_LeadTime_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		m_PartComplexity_TF.setMaximumSize(defaultMaxSize);
		m_PartComplexity_TF.setMinimumSize(defaultMinSize);
		m_PartComplexity_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		m_EOC_CB.setMaximumSize(defaultMaxSize);
		m_EOC_CB.setMinimumSize(defaultMinSize);
		m_EOC_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		m_ROM_CB.setMaximumSize(defaultMaxSize);
		m_ROM_CB.setMinimumSize(defaultMinSize);
		m_ROM_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		m_Tolerance_TF.setMaximumSize(defaultMaxSize);
		m_Tolerance_TF.setMinimumSize(defaultMinSize);
		m_Tolerance_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		m_Finish_CB.setMaximumSize(defaultMaxSize);
		m_Finish_CB.setMinimumSize(defaultMinSize);
		m_Finish_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
		
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
		
		m_Input_P.add(m_Name_TF);
		m_Input_P.add(m_Tenstion_TF);
		m_Input_P.add(m_Compression_TF);
		m_Input_P.add(m_Impact_TF);
		m_Input_P.add(m_PartComplexity_TF);
		m_Input_P.add(m_LeadTime_TF);
		m_Input_P.add(m_EOC_CB);
		m_Input_P.add(m_ROM_CB);
		m_Input_P.add(m_Tolerance_TF);
		m_Input_P.add(m_Finish_CB);
		
		m_Button_P.add(m_AddPrinter_B);
		
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
				case "Add New Printer":
					//TODO check all inputs to see if valid or not.
					//TODO create window or pop-up for invalid inputs...
						m_Driver.addPrinter(m_Name_TF.getText(), m_Tenstion_TF.getText(), m_Compression_TF.getText(), m_PartComplexity_TF.getText(), (String) m_ROM_CB.getSelectedItem(), m_Impact_TF.getText(),
								m_LeadTime_TF.getText(),(String) m_EOC_CB.getSelectedItem(), m_Tolerance_TF.getText(), (String) m_Finish_CB.getSelectedItem());
						JOptionPane.showMessageDialog(m_Main_F,"Printer Added to DataBase","Message", JOptionPane.PLAIN_MESSAGE);
					break;
				default: JOptionPane.showMessageDialog(m_Main_F,"Command: " + command,"Unknown Command", JOptionPane.PLAIN_MESSAGE);
					break;
			}
			
		}
		
	}
}
