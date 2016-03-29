import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

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
private JPanel m_SearchResult_P, m_SearchParam_P, m_Menu_P;
private JButton m_FilterResults_B;
private JTextField m_BroadSearch_TF, m_LeadTime_TF, m_PartComplexity_TF;
private JComboBox<String>  m_Finish_CB, m_Materials_CB, m_Customizable_CB;
private RangedTextField<Double> m_Compression_RTF, m_Tension_RTF,
                                m_Tolerance_RTF, m_Impact_RTF;
private HashSet<String> m_RangeOfMaterials;
private JToolBar m_ToolBar;
private JScrollPane m_ScrollPane;
private Driver m_Driver;
private MenuUI m_MenuUI;
private PrinterList printerList;

public final int FRAME_WIDTH;
public final int FRAME_HEIGHT;
private int screenWidth;
private int screenHeight;

public final String[] searchParameters = 
        {"Search", "Compression", "Tension", "Tolerance", "Impact",
         "Lead Time", "Part Complexity", "Customizable", "Material",
         "Finish"};

public static void main(String args [])
{
	new MenuUI();
}

/**
 * Creates window for Printer Search Program
 */
public MenuUI()
{
	GraphicsDevice graphicsDevice;
	PrinterList printerList;
	
	// Obtain window dimensions
	graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().
			getDefaultScreenDevice();
	screenWidth = graphicsDevice.getDisplayMode().getWidth();
	screenHeight = graphicsDevice.getDisplayMode().getHeight();
	FRAME_WIDTH = (int) ((int) screenWidth *0.75);
	FRAME_HEIGHT = (int) ((int) screenHeight *0.75);

	// Set up menu UI window
    createComponents();
    designComponents(screenWidth, screenHeight);
    addActionListeners();
    addComponents();
    m_Menu_F.pack();
    m_Menu_F.setVisible(true);
    m_MenuUI = this;
    
	// Instantiate non-GUI objects
    m_Driver = new Driver();
	m_RangeOfMaterials = new HashSet<String>();
	printerList = Driver.generatePrinterList();
}

/**
 * Instantiates GUI components.
 */
private void createComponents() {
	// Instantiate GUI framework
	m_Menu_F = new JFrame("Menu");
	m_Menu_P = new JPanel();
	m_ToolBar = new JToolBar("ToolBar");
	
	// Instantiate text fields
	m_BroadSearch_TF = new JTextField();
	m_LeadTime_TF = new JTextField();
	m_PartComplexity_TF = new JTextField();
	
	// Instantiate ranged text fields
	m_Compression_RTF = new RangedTextField<Double>(9.999, 0.000, 0.001);
	m_Tolerance_RTF = new RangedTextField<Double>(9.999, 0.000, 0.001);
	m_Tension_RTF = new RangedTextField<Double>(9.999, 0.000, 0.001);
	m_Impact_RTF = new RangedTextField<Double>(200.0, 0.0, .001);
	
	// Instantiate combo boxes
	// TODO load these RangeOfMaterials to a file or something...
	m_Finish_CB = new JComboBox<String>(new String[] {
			"Search All", "Matte", "Gloss"});
	m_Materials_CB = new JComboBox<String>(new String[] {
			"Search All", "Aluminum", "Stainless", "Clear All"});
	m_Customizable_CB = new JComboBox<String>(new String[] {
			"Search All", "True", "False"});
	
	// Instantiate GUI layout components and button
	m_ScrollPane = new JScrollPane();
	m_SearchResult_P = new JPanel();
	m_SearchParam_P = new JPanel();
	m_FilterResults_B = new JButton("Filter Results");
}

/**
 * Sets GUI component values.
 */
private void designComponents(int screenWidth, int screenHeight) {
    
	// Set panel layout and size
	m_Menu_P.setLayout(new BorderLayout(5,5));
	m_Menu_P.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	m_Menu_P.setMaximumSize(new Dimension(FRAME_WIDTH , FRAME_HEIGHT));
	
	/*
	 * Finalize window close operation and set window size
	 * Added 50 to height because mac interface will not show filter button.
	 * Set to resizable to accommodate for filter button at bottom.
	 */
	m_Menu_F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//m_Menu_F.setLayout(new BorderLayout(5,5));
	m_Menu_F.setSize(FRAME_WIDTH, (FRAME_HEIGHT+50));
	m_Menu_F.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
	m_Menu_F.setMaximumSize(new Dimension(FRAME_WIDTH , (FRAME_HEIGHT+50)));
	m_Menu_F.setLocationRelativeTo(null);						
	m_Menu_F.setResizable(true);

	// Set up scroll pane
	m_ScrollPane.setOpaque(false);
	m_ScrollPane.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	
	// Set up remaining GUI components
	designSearchParam();
	designSearchResult();
	designToolBar();
}

/**
 * Displays unfiltered printer list.
 */
private void designSearchResult() {
	Printer currentPrinter;
	PrinterUI tableHeader;
	
	// Create search results' table header
	tableHeader = new PrinterUI(1,FRAME_WIDTH , FRAME_HEIGHT,
			"Name","Tension","Compression","Impact", "Complexity",
			"Lead Time","Ease","Materials","Tolerance","Finish");
	m_SearchResult_P.setLayout(
			new BoxLayout(m_SearchResult_P, BoxLayout.Y_AXIS));
	//m_SearchResult_P.setPreferredSize(new Dimension(FRAME_WIDTH - 190, FRAME_HEIGHT));
	m_SearchResult_P.setBorder(BorderFactory.createLineBorder(Color.gray));

	// Add tool tips for long header categories before adding to GUI
    tableHeader.getPartComplexity().setToolTipText("Part Complexity");
    tableHeader.getCustomizable().setToolTipText("Ease of Customization");
    tableHeader.getMaterials().setToolTipText("Range of Materials");
	m_SearchResult_P.add(tableHeader);
	
	// Populate search result list with entire printer list
	printerList = Driver.generatePrinterList();
	for(int i = 2; i <= printerList.getNumberOfPrinters()+1; i++)
	{
		// Convert values to Strings
		currentPrinter = printerList.getPrinter(i-2);
		m_SearchResult_P.add(new PrinterUI(i,FRAME_WIDTH , FRAME_HEIGHT,
				currentPrinter.getPrinterName() + "",
				currentPrinter.getTension()+ "",
				currentPrinter.getCompression()+ "",
				currentPrinter.getImpact()+ "",
				currentPrinter.getComplexity()+ "",
				currentPrinter.getLeadTime()+ "",
				currentPrinter.customizableString(),
				currentPrinter.materialsString(),
				currentPrinter.getTolerance()+ "",
				currentPrinter.getFinish()+ ""));
	}
	
	// Add results to scroll pane
	m_ScrollPane.setViewportView(m_SearchResult_P);
	m_ScrollPane.setBounds(new Rectangle(FRAME_WIDTH , FRAME_HEIGHT*2));
}

/**
 * Displays filtered printer list.
 */
public void displaySearchResults(ArrayList<Printer> outputList){
	Printer currentPrinter;
	PrinterUI tableHeader;
	
	// Create search results' table header
	tableHeader = new PrinterUI(1,FRAME_WIDTH , FRAME_HEIGHT,
			"Name","Tension","Compression","Impact", "Complexity",
			"Lead Time","Ease","Materials","Tolerance","Finish");
	m_SearchResult_P.setLayout(
			new BoxLayout(m_SearchResult_P, BoxLayout.Y_AXIS));
	//m_SearchResult_P.setPreferredSize(new Dimension(FRAME_WIDTH - 190, FRAME_HEIGHT));
	m_SearchResult_P.setBorder(BorderFactory.createLineBorder(Color.gray));

	// Add tool tips for long header categories before adding to GUI
    tableHeader.getPartComplexity().setToolTipText("Part Complexity");
    tableHeader.getCustomizable().setToolTipText("Ease of Customization");
    tableHeader.getMaterials().setToolTipText("Range of Materials");
	m_SearchResult_P.add(tableHeader);
	
	// Populate search results with any printer matches
	for(int i = outputList.size()-1; i >=0;i--)
	{
		currentPrinter = outputList.get(i);
		m_SearchResult_P.add(new PrinterUI(i,FRAME_WIDTH , FRAME_HEIGHT,
				currentPrinter.getPrinterName() + "",
				currentPrinter.getTension()+ "",
				currentPrinter.getCompression()+ "",
				currentPrinter.getImpact()+ "",
				currentPrinter.getComplexity()+ "",
				currentPrinter.getLeadTime()+ "",
				currentPrinter.customizableString(),
				currentPrinter.materialsString(),
				currentPrinter.getTolerance()+ "",
				currentPrinter.getFinish()+ ""));
	}
	
	// Add results to scroll pane
	m_ScrollPane.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	m_ScrollPane.setViewportView(m_SearchResult_P);
	m_ScrollPane.setBounds(new Rectangle(FRAME_WIDTH , FRAME_HEIGHT*2));
	
}

/**
 * Sets components to their default values.
 */
public void resetResults()
{
    designComponents(screenWidth, screenHeight);
}

/**
 * Sets values for the tool bar GUI components.
 */
private void designToolBar()
{
	// Set up settings button
	JButton button = new JButton("Settings");
	button.setActionCommand("Settings");
	button.addActionListener(new ButtonListener());
	m_ToolBar.add(button);
	
	// Set up help button
	button = new JButton("Help");
	button.setActionCommand("Help");
	button.addActionListener(new ButtonListener());
	m_ToolBar.add(button);
	
	// Set up button to add printer
	button = new JButton("Add Printer");
	button.setActionCommand("Add Printer");
	button.addActionListener(new ButtonListener());
	m_ToolBar.add(button);
}

/**
 * Sets size and alignment for search parameter GUI components.
 */
private void designSearchParam()
{
	Dimension defaultMaxSize = new Dimension(170, 30),
			  defaultMinSize = new Dimension(150, 30);
	Component[] searchComponents =
        {m_BroadSearch_TF, m_Compression_RTF, m_Tension_RTF, m_Tolerance_RTF,
         m_Impact_RTF, m_LeadTime_TF, m_PartComplexity_TF, m_Customizable_CB,
         m_Materials_CB, m_Finish_CB};
	
	
	// Set up search panel
	m_SearchParam_P.setLayout(
			new BoxLayout(m_SearchParam_P, BoxLayout.Y_AXIS));
	m_SearchParam_P.setPreferredSize(new Dimension(175, FRAME_HEIGHT));
	m_SearchParam_P.setBorder(BorderFactory.createLineBorder(Color.black));
	
	// Set up search button
	m_FilterResults_B.setPreferredSize(new Dimension(100,25));
	m_FilterResults_B.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	// Set up search parameter component dimensions
	for(Component parameter : searchComponents) {
		parameter.setMaximumSize(defaultMaxSize);
		parameter.setMinimumSize(defaultMinSize);
	}
	
	// Align search parameter components
	m_BroadSearch_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_Compression_RTF.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_Tension_RTF.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_Impact_RTF.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_LeadTime_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_PartComplexity_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_Customizable_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_Materials_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_Materials_CB.setActionCommand("RangeOfMaterials");
	m_Tolerance_RTF.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_Finish_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
}

/**
 * Adds action listeners to GUI components.
 */
private void addActionListeners()
{
	m_FilterResults_B.addActionListener(new ButtonListener());
	m_Materials_CB.addActionListener(new ComboListener());
}

/**
 * Add components to menu framework.
 */
private void addComponents()
{
	addSearchParamComponents();
	m_Menu_P.add(m_ToolBar, BorderLayout.PAGE_START);
	m_Menu_P.add(m_SearchParam_P, BorderLayout.LINE_START);
	m_Menu_P.add(m_ScrollPane, BorderLayout.LINE_END);
	m_Menu_F.add(m_Menu_P);
	m_Menu_F.setPreferredSize(new Dimension(FRAME_WIDTH , FRAME_HEIGHT));
}

/**
 * Add components to the search panel of the GUI.
 */
private void addSearchParamComponents()
{
	Component[] searchComponents =
        {m_BroadSearch_TF, m_Compression_RTF, m_Tension_RTF, m_Tolerance_RTF,
         m_Impact_RTF, m_LeadTime_TF, m_PartComplexity_TF, m_Customizable_CB,
         m_Materials_CB, m_Finish_CB};
	
	// Add search parameter titles and spacing to GUI
	for(int index=0; index<10; index++) {
		addSearchLabel("\n");
		addSearchLabel(searchParameters[index]);
		m_SearchParam_P.add(searchComponents[index]);
	}

	// Add button with spacing to GUI
	addSearchLabel("\n");
	m_SearchParam_P.add(m_FilterResults_B);
}

/**
 * Creates and adds a label to the search parameter panel
 * 
 * @param searchParameter the String contained in the label
 */
private void addSearchLabel(String text)
{
	JLabel label;
	label = new JLabel(text);
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
}

public JFrame getM_Menu_F(){
	
	return  m_Menu_F;
	
}

public JPanel getSearchResultsPanel()
{
	return m_SearchResult_P;
}

/**
 * An action listener for a Combo Box.
 * 
 * @author Joshua Becker
 *
 */
private class ComboListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent action) {
		String command, selectedItem;
		int index;
		
		command = action.getActionCommand();
		switch(command)
		{
		    case "RangeOfMaterials":
				selectedItem = (String) m_Materials_CB.getSelectedItem();
				
				// Add new material to materials combo box if not already included
				if(!selectedItem.equals("Search All") && !selectedItem.equals("Clear All"))
				{
					JLabel temp = new JLabel(selectedItem);
					temp.setAlignmentX(Component.CENTER_ALIGNMENT);
					m_RangeOfMaterials.add(selectedItem);
					index = m_SearchParam_P.getComponentZOrder(m_Materials_CB);
					m_SearchParam_P.add(temp,index);
				}
				
				// Refresh search parameters
				else
				{
					m_SearchParam_P.removeAll();
					addSearchParamComponents();
				}
				m_Menu_P.revalidate();
				break;
				
			default: JOptionPane.showMessageDialog(m_Menu_F,"Command: " + command,"Unknown Command", JOptionPane.PLAIN_MESSAGE);
				break;
		}
		
	}
	
}
/**
 * An action listener for a button.
 * 
 * @author Joshua Becker (and other team members:) Jacob Leonard
 *
 */
private class ButtonListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent action) {

		/*m_FilterResults_B.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Initiate a search results window page.
				SearchResultsWindow filteredResults = new SearchResultsWindow("", (Double) m_Tension_RTF.getMin(), (Double) m_Compression_RTF.getMin(),
						(Double) m_Impact_RTF.getMin(), Double.valueOf(m_PartComplexity_TF.getText()), Double.valueOf(m_LeadTime_TF.getText()),
						(boolean)m_Customizable_CB.getSelectedItem(), (String) m_Materials_CB.getSelectedItem(), (Double) m_Tolerance_RTF.getMin(),
						(String) m_Finish_CB.getSelectedItem());
			}
		});*/

		String command = action.getActionCommand();
		switch(command)
		{
			case "Filter Results": //TODO implement this.

				// STEP ONE: CLEAR CURRENT RESULTS
				
				clearInterface(m_SearchResult_P); // Empty JPanel of current components (currently-listed printers in table)
			    clearInterface(m_ToolBar); // JToolBar to overloaded clearPanel. m_SearchResult_P doesn't position correctly without resetting/readding this. 

			    // STEP TWO: GET FIELDS & SET MATCHES FOR EACH PRINTER
			    
			    printerList.setMatches(
			    		(Double) m_Tension_RTF.getMin(), (Double) m_Tension_RTF.getMax(),
			    		(Double) m_Compression_RTF.getMin(), (Double) m_Compression_RTF.getMax(),
			    		(Double) m_Impact_RTF.getMin(), (Double) m_Impact_RTF.getMax(),
			    		Double.valueOf(m_PartComplexity_TF.getText()),
			    		Double.valueOf(m_LeadTime_TF.getText()),
			    		(String)m_Customizable_CB.getSelectedItem(),
			    		(String) m_Materials_CB.getSelectedItem(),
			    		(Double) m_Tolerance_RTF.getMin(), (Double) m_Tolerance_RTF.getMax(),
			    		(String) m_Finish_CB.getSelectedItem());
			    
			    // STEP THREE: SORT & SHOW RESULTS
			   			    ArrayList<Printer> outputList = Driver.outputSearchedList(printerList); // Console demonstration. Will reuse m_Driver.outputSearchedList perhaps to regenerate search params, not sure.
			    // Change outputSearchedList to return PrinterList with new values. 
			    
			    displaySearchResults(outputList);
			    designToolBar(); // Reset tool-bar once done, otherwise search results layout seems to break.
				break;

			case "Help": //TODO Help Window or Pop-up
				break;
			case "Settings"://TODO Settings Window or pop-up
				break;
			case "Add Printer": new AddPrinterUI(m_Menu_F, m_Driver, m_MenuUI);
				break;
			default: JOptionPane.showMessageDialog(m_Menu_F,"Command: " + command,"Unknown Command", JOptionPane.PLAIN_MESSAGE);
				break;
		}
		
	}
	
	/**
	 * Removes components from specified panel.
	 * 
	 * @param panel the JPanel whose components will be removed
	 */
	public void clearInterface(JPanel panel){
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}

	/**
	 * Removes components from specified tool bar.
	 * 
	 * @param toolbar the JToolBat whose components will be removed
	 */
	public void clearInterface(JToolBar toolbar){
		toolbar.removeAll();
		toolbar.revalidate();
		toolbar.repaint();
	}
	
}
}
