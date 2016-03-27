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
private RangedTextField<Double> m_Compression_RTF, m_Tension_RTF, m_Tolerance_RTF, m_Impact_RTF;
private ArrayList<String> m_RangeOfMaterials;
private JToolBar m_ToolBar;
private JScrollPane m_ScrollPane;
private Driver m_Driver;
private MenuUI m_MenuUI;
private PrinterList printerList;

public final int FRAME_WIDTH;
public final int FRAME_HEIGHT;

private int screenWidth;
private int screenHeight;

public static void main(String args [])
{
	new MenuUI();
}

/**
 * Creates window for Printer Search Program
 */
public MenuUI()
{
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();// Getting size of screen
	screenWidth = gd.getDisplayMode().getWidth();
	screenHeight = gd.getDisplayMode().getHeight();
	
	FRAME_WIDTH = (int) ((int) screenWidth *0.75);
	FRAME_HEIGHT = (int) ((int) screenHeight *0.75);

	m_Driver = new Driver();
	m_RangeOfMaterials = new ArrayList<String>();
    createComponents();
    designComponents(screenWidth, screenHeight);
    addActionListeners();
    addComponents();
    
    m_Menu_F.pack();
    m_Menu_F.setVisible(true);
    
    m_MenuUI = this;
    
	PrinterList printerList = Driver.generatePrinterList();
}

/**
 * Instantiates GUI components.
 */
private void createComponents() {
	m_Menu_F = new JFrame("Menu");
	m_Menu_P = new JPanel();
	m_ToolBar = new JToolBar("ToolBar");
	
	m_BroadSearch_TF = new JTextField();
	
	m_LeadTime_TF = new JTextField();
	m_PartComplexity_TF = new JTextField();
	
	// TODO Implemented a generic Number class for RangedTextField
	/*
	m_Tolerance_RTF = new RangedTextField(200, 0, RangedTextField.DOUBLE);
	m_Tension_RTF = new RangedTextField(200, 0, RangedTextField.DOUBLE);
	m_Impact_RTF = new RangedTextField(200, 0, RangedTextField.INTEGER);
	*/
	m_Compression_RTF = new RangedTextField<Double>(9.999, 0.000, 0.001);
	m_Tolerance_RTF = new RangedTextField<Double>(9.999, 0.000, 0.001);
	m_Tension_RTF = new RangedTextField<Double>(9.999, 0.000, 0.001);
	m_Impact_RTF = new RangedTextField<Double>(200.0, 0.0, .001);
	
	m_Finish_CB = new JComboBox<String>(new String [] {"Search All", "Matte", "Gloss"});//TODO load these fRangeOfMaterials a file or something...
	m_Materials_CB = new JComboBox<String>(new String [] {"Search All", "Aluminum", "Stainless", "Clear All"});
	m_Customizable_CB = new JComboBox<String>(new String [] {"Search All", "True", "False"});
	
	m_ScrollPane = new JScrollPane();
	m_SearchResult_P = new JPanel();
	m_SearchParam_P = new JPanel();
	
	m_FilterResults_B = new JButton("Filter Results");
}

/**
 * Sets GUI component values.
 */
private void designComponents(int screenWidth, int screenHeight) {
    
	m_Menu_F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//m_Menu_F.setLayout(new BorderLayout(5,5));
	m_Menu_P.setLayout(new BorderLayout(5,5));
	m_Menu_P.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	m_Menu_P.setMaximumSize(new Dimension(FRAME_WIDTH , FRAME_HEIGHT));
	
	m_Menu_F.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	m_Menu_F.setMaximumSize(new Dimension(FRAME_WIDTH , FRAME_HEIGHT));
	m_Menu_F.setLocation((screenWidth/2) - (FRAME_WIDTH /2),(screenHeight/2) - (FRAME_HEIGHT/2));// centering
	m_Menu_F.setResizable(false);
	
	m_ScrollPane.setOpaque(false);
	m_ScrollPane.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	
	designSearchParam();
	
	designSearchResult();
	
	designToolBar();
}

/**
 * Sets values for search result GUI components.
 * 
 * @param FRAME_WIDTH   the width of the frame
 * @param FRAME_HEIGHT the width of the height
 */
private void designSearchResult() {
	String customizable;
	Printer currentPrinter;
	PrinterUI tableHeader = new PrinterUI(1,FRAME_WIDTH , FRAME_HEIGHT,
			"Name","Tension","Compression","Impact", "Complexity",
			"Lead Time","Ease","Materials","Tolerance","Finish");
	
	m_SearchResult_P.setLayout(new BoxLayout(m_SearchResult_P, BoxLayout.Y_AXIS));
	//m_SearchResult_P.setPreferredSize(new Dimension(FRAME_WIDTH  - 190, FRAME_HEIGHT));
	m_SearchResult_P.setBorder(BorderFactory.createLineBorder(Color.gray));

    tableHeader.getPartComplexity().setToolTipText("Part Complexity");
    tableHeader.getCustomizable().setToolTipText("Ease of Customization");
    tableHeader.getMaterials().setToolTipText("Range of Materials");
	m_SearchResult_P.add(tableHeader);
	
	printerList = Driver.generatePrinterList();
	
	for(int i = 2; i <= printerList.getNumberOfPrinters()+1; i++)
	{
		currentPrinter = printerList.getPrinter(i-2);
		customizable = "True";
		if(!currentPrinter.isCustomizable())
			customizable = "False";
		
		m_SearchResult_P.add(new PrinterUI(i,FRAME_WIDTH , FRAME_HEIGHT,
				currentPrinter.getName() + "",
				currentPrinter.getTension()+ "",
				currentPrinter.getCompression()+ "",
				currentPrinter.getImpact()+ "",
				currentPrinter.getComplexity()+ "",
				currentPrinter.getLeadTime()+ "",
				customizable,
				currentPrinter.getMaterials(),
				currentPrinter.getTolerance()+ "",
				currentPrinter.getFinish()+ ""));
	}
	m_ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	m_ScrollPane.setViewportView(m_SearchResult_P);
	m_ScrollPane.setBounds(new Rectangle(FRAME_WIDTH , FRAME_HEIGHT*2));
	//m_ScrollPane.getViewport().setOpaque(false);
}

/** Display NEW search results.

*/

public void displaySearchResults(ArrayList<Printer> outputList){
	
	PrinterUI tableHeader = new PrinterUI(1,FRAME_WIDTH , FRAME_HEIGHT,
			"Name","Tension","Compression","Impact", "Complexity",
			"Lead Time","Ease","Materials","Tolerance","Finish");
	
	m_SearchResult_P.setLayout(new BoxLayout(m_SearchResult_P, BoxLayout.Y_AXIS));
	//m_SearchResult_P.setPreferredSize(new Dimension(FRAME_WIDTH  - 190, FRAME_HEIGHT));
	m_SearchResult_P.setBorder(BorderFactory.createLineBorder(Color.gray));

    tableHeader.getPartComplexity().setToolTipText("Part Complexity");
    tableHeader.getCustomizable().setToolTipText("Ease of Customization");
    tableHeader.getMaterials().setToolTipText("Range of Materials");
	m_SearchResult_P.add(tableHeader);
	
	for(int i = outputList.size()-1; i >=0;i--)
	{
		Printer currentPrinter = outputList.get(i);
		String isEaseOfChange = "True";
		if(!currentPrinter.isCustomizable())
			isEaseOfChange = "False";
		
		m_SearchResult_P.add(new PrinterUI(i,FRAME_WIDTH , FRAME_HEIGHT,
				currentPrinter.getName() + "",
				currentPrinter.getTension()+ "",
				currentPrinter.getCompression()+ "",
				currentPrinter.getImpact()+ "",
				currentPrinter.getComplexity()+ "",
				currentPrinter.getLeadTime()+ "",
				isEaseOfChange,
				currentPrinter.getMaterials(),
				currentPrinter.getTolerance()+ "",
				currentPrinter.getFinish()+ ""));
	}
	m_ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	m_ScrollPane.setViewportView(m_SearchResult_P);
	m_ScrollPane.setBounds(new Rectangle(FRAME_WIDTH , FRAME_HEIGHT*2));
	
}

/**
 * Sets components to their default values.
 */
public void resetResults(){
	
    designComponents(screenWidth, screenHeight);
	
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
 * @param FRAME_WIDTH   the width of the frame
 * @param FRAME_HEIGHT the height of the frame
 */
private void designSearchParam()
{
	Dimension defaultMaxSize = new Dimension(170, 30),
			  defaultMinSize = new Dimension(150, 30);
	
	m_SearchParam_P.setLayout(new BoxLayout(m_SearchParam_P, BoxLayout.Y_AXIS));
	m_SearchParam_P.setPreferredSize(new Dimension(175, FRAME_HEIGHT));
	m_SearchParam_P.setBorder(BorderFactory.createLineBorder(Color.black));
	
	m_BroadSearch_TF.setMaximumSize(defaultMaxSize);
	m_BroadSearch_TF.setMinimumSize(defaultMinSize);
	m_BroadSearch_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_Compression_RTF.setMaximumSize(defaultMaxSize);
	m_Compression_RTF.setMinimumSize(defaultMinSize);
	m_Compression_RTF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_Tension_RTF.setMaximumSize(defaultMaxSize);
	m_Tension_RTF.setMinimumSize(defaultMinSize);
	m_Tension_RTF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_Impact_RTF.setMaximumSize(defaultMaxSize);
	m_Impact_RTF.setMinimumSize(defaultMinSize);
	m_Impact_RTF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_LeadTime_TF.setMaximumSize(defaultMaxSize);
	m_LeadTime_TF.setMinimumSize(defaultMinSize);
	m_LeadTime_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_PartComplexity_TF.setMaximumSize(defaultMaxSize);
	m_PartComplexity_TF.setMinimumSize(defaultMinSize);
	m_PartComplexity_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_Customizable_CB.setMaximumSize(defaultMaxSize);
	m_Customizable_CB.setMinimumSize(defaultMinSize);
	m_Customizable_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_Materials_CB.setMaximumSize(defaultMaxSize);
	m_Materials_CB.setMinimumSize(defaultMinSize);
	m_Materials_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_Materials_CB.setActionCommand("RangeOfMaterials");

	m_Tolerance_RTF.setMaximumSize(defaultMaxSize);
	m_Tolerance_RTF.setMinimumSize(defaultMinSize);
	m_Tolerance_RTF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
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
	m_Materials_CB.addActionListener(new ComboListener());
}

/**
 * Add components to menu frame.
 */
private void addComponents() {
	addSearchParamComponents();
	
	m_Menu_P.add(m_ToolBar, BorderLayout.PAGE_START);
	m_Menu_P.add(m_SearchParam_P, BorderLayout.LINE_START);
	m_Menu_P.add(m_ScrollPane, BorderLayout.LINE_END);
	m_Menu_F.add(m_Menu_P);
	m_Menu_F.setPreferredSize(new Dimension(FRAME_WIDTH , FRAME_HEIGHT));
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
	
	label = new JLabel("Compression:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Compression_RTF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Tension:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Tension_RTF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Tolerance:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Tolerance_RTF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Impact");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Impact_RTF);
	
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
	
	label = new JLabel("Ease Of Customization:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Customizable_CB);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Range Of Materials:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Materials_CB);
	
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
		String command = action.getActionCommand();
		switch(command)
		{
			case "RangeOfMaterials":
				String selectedItem = (String) m_Materials_CB.getSelectedItem();// getting selected Item
				//TODO Dont allow repeats in widnow...
				if(!selectedItem.equals("Search All") && !selectedItem.equals("Clear All"))
				{
					JLabel temp = new JLabel(selectedItem);//creating temp Label
					temp.setAlignmentX(Component.CENTER_ALIGNMENT);
					
					m_RangeOfMaterials.add(selectedItem);//adding item to list.
				
					int index = m_SearchParam_P.getComponentZOrder(m_Materials_CB);// getting index of Range Of Materials
				
					m_SearchParam_P.add(temp,index);// adding new label
				}else
				{
					m_SearchParam_P.removeAll();//removing all Components
					addSearchParamComponents(); //adding default Components
				}
				m_Menu_P.revalidate();// updating Panel
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
		String command = action.getActionCommand();
		switch(command)
		{
			case "Filter Results": //TODO implement this.
				
				// STEP ONE: CLEAR CURRENT RESULTS
				
				clearPanel(m_SearchResult_P); // Empty JPanel of current components (currently-listed printers in table)
			    clearPanel(m_ToolBar); // JToolBar to overloaded clearPanel. m_SearchResult_P doesn't position correctly without resetting/readding this. 

			    // STEP TWO: GET FIELDS & SET MATCHES FOR EACH PRINTER
			    
			    printerList.setMatches(
			    		(Double) m_Tension_RTF.getMin(), (Double) m_Tension_RTF.getMax(),
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
	
	public void clearPanel(JPanel panel){
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}

	public void clearPanel(JToolBar toolbar){
		toolbar.removeAll();
		toolbar.revalidate();
		toolbar.repaint();
	}
	
}
}
