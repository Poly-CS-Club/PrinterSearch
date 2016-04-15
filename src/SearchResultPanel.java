import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * A panel that displays the printer search results.
 * 
 * @author Joshua Becker, Jacob Leonard, Marcinina Alvaran, Alireza Bahremand 
 *
 */
public class SearchResultPanel extends JPanel{
	PrinterLabel m_ResultHeader;
	private PrinterList m_PrinterList;
	/**
	 * auto generated serial version UID for panel
	 */
	private static final long serialVersionUID = -8720386740444854808L;
	
	public SearchResultPanel()
	{
		createComponents();
		designComponents();
		addComponents();
	}
	public SearchResultPanel(String name)
	{
		this.setName(name);
		createComponents();
		designComponents();
		addComponents();
	}
	private void createComponents()
	{
		// Create search results' table header
		m_ResultHeader = new PrinterLabel(1,MenuWindow.FRAME_WIDTH , MenuWindow.FRAME_HEIGHT,
				"NAME","VENDOR","TENSION (ksi)","COMPRESSION (ksi)","IMPACT (lb-ft)","MATERIALS","TOLERANCE (in)","FINISH (\u00B5in)");
		m_ResultHeader.setBackground(Color.lightGray);
		// Add tool tips for long header categories before adding to GUI
		m_ResultHeader.getMaterials().setToolTipText("Range of Materials");
		m_PrinterList = ToolBox.generatePrinterList();
	}
	/**
	 * designs the look and populates the Panel. components
	 */
	private void designComponents() 
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.gray));
	}
	/**
	 * add components to the Panel
	 */
	private void addComponents()
	{
		Printer currentPrinter;
		add(m_ResultHeader);
		for(int i = 2; i <= m_PrinterList.getNumberOfPrinters()+1; i++)
		{
			// Convert values to Strings
			currentPrinter = m_PrinterList.getPrinter(i-2);
			add(new PrinterLabel(i,MenuWindow.FRAME_WIDTH , MenuWindow.FRAME_HEIGHT,
					currentPrinter.getPrinterName()+ "",
					currentPrinter.getVendor(),
					currentPrinter.getTension()+ "",
					currentPrinter.getCompression()+ "",
					currentPrinter.getImpact()+ "",
					currentPrinter.materialsString(),
					currentPrinter.getTolerance()+ "",
					currentPrinter.getFinish()+ ""));
		}
	}
	/**
	 * updates the search results in the panel
	 * @param filter
	 */
	public void updateSearchResults(SearchFiltersPanel filter)
	{
		removeAll();
		m_PrinterList.setPrinterList(ToolBox.generatePrinterList().getPrinterList());
		m_PrinterList.clearMatches(m_PrinterList.getPrinterList());
		
	    m_PrinterList.setMatches(
	    		(Double) filter.getTension().getMin(), (Double) filter.getTension().getMax(),
	    		(Double) filter.getCompression().getMin(), (Double)filter.getCompression().getMax(),
	    		(Double) filter.getImpact().getMin(), (Double) filter.getImpact().getMax(),
	    		(String) filter.getMaterials().getSelectedItem(),
	    		(Double) filter.getTolerance().getMin(), (Double) filter.getTolerance().getMax(),
	    		(double) filter.getFinish().getMin(),(double) filter.getFinish().getMax(),
	    		(String) filter.getVendor().getSelectedItem());
	    
	    ArrayList<Printer> outputList = ToolBox.outputSearchedList(m_PrinterList);
	    updateSearchResults(outputList);
	}
	/**
	 * resets to default values
	 */
	public void clearResults()
	{
		removeAll();
		m_PrinterList.setPrinterList(ToolBox.generatePrinterList().getPrinterList());
		m_PrinterList.clearMatches(m_PrinterList.getPrinterList());
		designComponents();
		addComponents();
	}
	/**
	 * Updates filtered printer list.
	 */
	public void updateSearchResults(ArrayList<Printer> outputList){
		//removeAll();
		Printer currentPrinter;
		PrinterLabel tableHeader;
		
		// Create search results' table header
		tableHeader = new PrinterLabel(1,MenuWindow.FRAME_WIDTH , MenuWindow.FRAME_HEIGHT,
				"NAME","VENDOR","TENSION (ksi)","COMPRESSION (ksi)","IMPACT (lb-ft)","MATERIALS","TOLERANCE (in)","FINISH (\u00B5in)");

		// Add tool tips for long header categories before adding to GUI
	    tableHeader.getMaterials().setToolTipText("Range of Materials");
	    
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.gray));
		add(tableHeader);
		tableHeader.setBackground(Color.lightGray);

		// Populate search results with any printer matches
		for(int i = outputList.size()-1; i >=0;i--)
		{
			currentPrinter = outputList.get(i);
			PrinterLabel temp = new PrinterLabel(i+1,MenuWindow.FRAME_WIDTH , MenuWindow.FRAME_HEIGHT,
					currentPrinter.getPrinterName() + "",
					currentPrinter.getVendor()+ "",
					currentPrinter.getTension()+ "",
					currentPrinter.getCompression()+ "",
					currentPrinter.getImpact()+ "",
					currentPrinter.materialsString()+ "",
					currentPrinter.getTolerance()+ "",
					currentPrinter.getFinish()+ "");
			temp = highlightMatch(temp, currentPrinter);
			add(temp);
		}
		this.revalidate();
	}
	/**
	 * Highlights matches on screen through font differences.
	 * 
	 * @param printer     the printer whose parameters are being checked
	 * @param matchIndex  the index of the parameter in the match array
	 * @return            the String of the parameter
	 */
	private PrinterLabel highlightMatch(PrinterLabel label, Printer printer) {
		Color highlight = new Color(0,200,0);
		/* Matches array Index Reference:
		 * 
		 *  0 = Tension
		 *  1 = Compression
		 *  2 = Impact
		 *  3 = vendor
		 *  4 = Range of Mats.
		 *  5 = Tolerance
		 *  6 = Finish
		 */
		if(printer.getMatches()[0] > 0)
		{
			label.getTension().setForeground(highlight);
		}
		if(printer.getMatches()[1] > 0)
		{
			label.getCompression().setForeground(highlight);
		}
		if(printer.getMatches()[2] > 0)
		{
			label.getImpact().setForeground(highlight);
		}
		if(printer.getMatches()[3] > 0)
		{
			label.getVendor().setForeground(highlight);
		}
		if(printer.getMatches()[4] > 0)
		{
			label.getMaterials().setForeground(highlight);
		}
		if(printer.getMatches()[5] > 0)
		{
			label.getTolerance().setForeground(highlight);
		}
		if(printer.getMatches()[6] > 0)
		{
			label.getFinish().setForeground(highlight);
		}
		return label;
	}
	/**
	 * reloads the printers from the xml
	 */
	public void reload()
	{
		m_PrinterList = ToolBox.generatePrinterList();
		removeAll();
		designComponents();
		addComponents();
		this.revalidate();
	}
	/**
	 * @return the m_PrinterList
	 */
	public PrinterList getPrinterList() {
		return m_PrinterList;
	}
	/**
	 * @param m_PrinterList the m_PrinterList to set
	 */
	public void setPrinterList(PrinterList m_PrinterList) {
		this.m_PrinterList = m_PrinterList;
	}
}
