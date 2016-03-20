import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

/**
 * GUI for the Printer Search Program.
 * 
 * @author  Joshua Becker
 * @version 1.1
 * @see     PrinterUI
 * @see     AddPrinterUI
 */
public class MenuUI
{
	
private JFrame m_Menu_F;
private JPanel m_SearchResult_P, m_SearchParam_P;
private JLabel m_SearchParam_L;
private JButton m_FilterResults_B;
private JTextField m_BroadSearch_TF, m_Tension_TF, m_Impact_TF, m_LeadTime_TF, m_PartComplexity_TF, m_EOC_TF, m_Tolerance_TF;
private JComboBox<String>  m_Finish_CB, m_ROM_CB;
private JToolBar m_ToolBar;
private JScrollPane m_ScrollPane;
private Driver m_Driver;

public static void main(String args [])
{
	new MenuUI();
}

/**
 * Creates window for Printer Search Program
 */
public MenuUI()
{
	m_Driver = new Driver();
    createComponents();
    designComponents();
    addActionListeners();
    addComponents();
    
    m_Menu_F.pack();
    m_Menu_F.setVisible(true);
}

/**
 * Instantiates GUI components.
 */
private void createComponents() {
	m_Menu_F = new JFrame("Menu");
	m_ToolBar = new JToolBar("ToolBar");
	
	m_BroadSearch_TF = new JTextField();
	m_Tension_TF = new JTextField();
	m_Impact_TF = new JTextField();
	m_LeadTime_TF = new JTextField();
	m_PartComplexity_TF = new JTextField();
	m_EOC_TF = new JTextField();
	
	m_Tolerance_TF = new JTextField();
	
	m_Finish_CB = new JComboBox<String>(new String [] {"Search All", "Matte", "Gloss"});//TODO load these from a file or something...
	m_ROM_CB = new JComboBox<String>(new String [] {"Search All", "Aluminum", "Stainless"});
	
	m_ScrollPane = new JScrollPane();
	m_SearchResult_P = new JPanel();
	m_SearchParam_P = new JPanel();
	
	m_FilterResults_B = new JButton("Filter Results");
}

/**
 * Sets GUI component values.
 */
private void designComponents() {
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();// Getting size of screen
	int screenWidth = gd.getDisplayMode().getWidth();
	int screenHeight = gd.getDisplayMode().getHeight();
	
	int frameWidth = (int) ((int) screenWidth *0.75);
	int frameHeight = (int) ((int) screenHeight *0.75);
    
	m_Menu_F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	m_Menu_F.setLayout(new BorderLayout(5,5));
	m_Menu_F.setSize(frameWidth, frameHeight);
	m_Menu_F.setLocation((screenWidth/2) - (frameWidth/2),(screenHeight/2) - (frameHeight/2));// centering
	m_Menu_F.setResizable(false);
	
	m_ScrollPane.setOpaque(false);
	m_ScrollPane.setVerticalScrollBarPolicy(
			m_ScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
	designSearchParam(frameWidth, frameHeight);
	
	designSearchResult(frameWidth, frameHeight);
	
	designToolBar();
}

/**
 * Sets values for search result GUI components.
 * 
 * @param frameWidth  the width of the frame
 * @param frameHeight the width of the height
 */
private void designSearchResult(int frameWidth, int frameHeight) {
	m_SearchResult_P.setLayout(new BoxLayout(m_SearchResult_P, BoxLayout.Y_AXIS));
	m_SearchResult_P.setPreferredSize(new Dimension(frameWidth - 190, frameHeight));
	m_SearchResult_P.setBorder(BorderFactory.createLineBorder(Color.gray));
	
	m_SearchResult_P.add(new PrinterUI(1,frameWidth, frameHeight,
			"Name","Tension","Compression","Impact", "Part Complex.",
			"Lead Time","EOC","ROM","Tolerance","Finish"));
	PrinterList printerList = m_Driver.generatePrinterList();
	
	for(int i = 2; i <= printerList.getNumberOfPrinters()+1; i++)
	{
		Printer currentPrinter = printerList.getPrinter(i-2);
		String isEaseOfChange = "True";
		if(!currentPrinter.isEaseOfChange())
			isEaseOfChange = "False";
		
		m_SearchResult_P.add(new PrinterUI(i,frameWidth, frameHeight,
				currentPrinter.getName() + "",
				currentPrinter.getTension()+ "",
				currentPrinter.getCompression()+ "",
				currentPrinter.getImpact()+ "",
				currentPrinter.getComplexity()+ "",
				currentPrinter.getLeadTime()+ "",
				isEaseOfChange,
				currentPrinter.getRangeOfMaterials(),
				currentPrinter.getTolerance()+ "",
				currentPrinter.getFinish()+ ""));
	}
	
	m_ScrollPane.setViewportView(m_SearchResult_P);
	m_ScrollPane.getViewport().setOpaque(false);
}

/**
 * Sets values for the tool bar GUI components.
 */
private void designToolBar()
{
	JButton button = new JButton("Settings");
	button.setActionCommand("Settings");
	button.addActionListener(new ButtonListener());
	m_ToolBar.add(button);
	
	button = new JButton("Help");
	button.setActionCommand("Help");
	button.addActionListener(new ButtonListener());
	m_ToolBar.add(button);
	
	button = new JButton("Add Printer");
	button.setActionCommand("Add Printer");
	button.addActionListener(new ButtonListener());
	m_ToolBar.add(button);
}

/**
 * Sets values for search parameter GUI components.
 * 
 * @param frameWidth  the width of the frame
 * @param frameHeight the height of the frame
 */
private void designSearchParam(int frameWidth, int frameHeight)
{
	Dimension defaultMaxSize = new Dimension(170, 25),
			  defaultMinSize = new Dimension(150, 25);
	
	m_SearchParam_P.setLayout(new BoxLayout(m_SearchParam_P, BoxLayout.Y_AXIS));
	m_SearchParam_P.setPreferredSize(new Dimension(175, frameHeight));
	m_SearchParam_P.setBorder(BorderFactory.createLineBorder(Color.black));
	
	m_BroadSearch_TF.setMaximumSize(defaultMaxSize);
	m_BroadSearch_TF.setMinimumSize(defaultMinSize);
	m_BroadSearch_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_Tension_TF.setMaximumSize(defaultMaxSize);
	m_Tension_TF.setMinimumSize(defaultMinSize);
	m_Tension_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_Impact_TF.setMaximumSize(defaultMaxSize);
	m_Impact_TF.setMinimumSize(defaultMinSize);
	m_Impact_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_LeadTime_TF.setMaximumSize(defaultMaxSize);
	m_LeadTime_TF.setMinimumSize(defaultMinSize);
	m_LeadTime_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_PartComplexity_TF.setMaximumSize(defaultMaxSize);
	m_PartComplexity_TF.setMinimumSize(defaultMinSize);
	m_PartComplexity_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_EOC_TF.setMaximumSize(defaultMaxSize);
	m_EOC_TF.setMinimumSize(defaultMinSize);
	m_EOC_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_ROM_CB.setMaximumSize(defaultMaxSize);
	m_ROM_CB.setMinimumSize(defaultMinSize);
	m_ROM_CB.setAlignmentX(Component.CENTER_ALIGNMENT);

	m_Tolerance_TF.setMaximumSize(defaultMaxSize);
	m_Tolerance_TF.setMinimumSize(defaultMinSize);
	m_Tolerance_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_Finish_CB.setMaximumSize(defaultMaxSize);
	m_Finish_CB.setMinimumSize(defaultMinSize);
	m_Finish_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_FilterResults_B.setPreferredSize(new Dimension(100,25));
	m_FilterResults_B.setAlignmentX(Component.CENTER_ALIGNMENT);
}

/**
 * Adds action listeners to GUI components.
 */
private void addActionListeners()
{
	m_FilterResults_B.addActionListener(new ButtonListener());
}

/**
 * Add components to menu frame.
 */
private void addComponents() {
	addSearchParamComponents();
	
	m_Menu_F.add(m_ToolBar, BorderLayout.PAGE_START);
	m_Menu_F.add(m_SearchParam_P, BorderLayout.LINE_START);
	m_Menu_F.add(m_ScrollPane, BorderLayout.LINE_END);
	// TODO: Commented out for scroll bar
	//m_Menu_F.add(m_SearchResult_P, BorderLayout.LINE_END);
}

/**
 * Add components to the search panel of the GUI.
 */
private void addSearchParamComponents() {
	JLabel label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Search:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_BroadSearch_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Tension:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Tension_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Impact");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Impact_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Lead Time:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_LeadTime_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Part Complexity:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_PartComplexity_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("EOC:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_EOC_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("ROM:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_ROM_CB);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Tolerance:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Tolerance_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Finish:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Finish_CB);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_FilterResults_B);
	
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
			case "Filter Results": //TODO implement this.
				break;
			case "Help": //TODO Help Window or Pop-up
				break;
			case "Settings"://TODO Settings Window or pop-up
				break;
			case "Add Printer": new AddPrinterUI(m_Menu_F, m_Driver);
				break;
			default: JOptionPane.showMessageDialog(m_Menu_F,"Command: " + command,"Unknown Command", JOptionPane.PLAIN_MESSAGE);
				break;
		}
		
	}
	
}
}
