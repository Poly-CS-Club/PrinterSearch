import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/** 
 * A list of 3D metal printers being considered for the
 * product (TODO: replace with a brief description of the product).
 * 
 * @author Jake Leonard, (others on team), Marcinina Alvaran
 * @version (TODO: to be added by original programmer)
 * @see Printer
 */
public class PrinterList {
	
	private ArrayList<Printer> printerList;
	
	/**
	 * Instantiate an empty printer list.
	 */
	public PrinterList(){
		
		printerList = new ArrayList<Printer>();
	}

	public ArrayList<Printer> getPrinterList() {
		return printerList;
	}
	
	public int getNumberOfPrinters()
	{
		return printerList.size();
	}

	public void setPrinterList(ArrayList<Printer> printerList) {
		this.printerList = printerList;
	}

	public void addPrinter(Printer printer){		
		printerList.add(printer);
		
	}
	
	public Printer getPrinter(int index){
		return printerList.get(index);
	}
	
	/* Arguments are passed to this method from driver (either GUI or cmd-line based); here a search takes place among the printer objects
	 * which then returns a new list consisting of matches ordered by total number of matches. Elsewhere will provide boolean checks 
	 * to indicate that the particular parameters meets desired specifications. 
	 */
	
	/**
	 * Searches the printer list for matches based on specified printer attributes and returns
	 * a list of matches ordered by total number of matches.
	 * 
	 * @param tension         the double with the specified tension
	 * @param compression     the double with the specified compression
	 * @param impact          the double with the specified impact
	 * @param complexity      the double with the specified complexity
	 * @param leadTime        the double with the specified lead time
	 * @param customizable    the boolean with the specified easeOfChange
	 * @param materials       the HashSet with the range of materials list
	 * @param tolerance       the double with the specified tolerance
	 * @param finish          the String representing the finish
	 */

	

	 // - ADD COMPRESSION!
	public void setMatches(double minTension, double maxTension, double minImpact, double maxImpact, double complexity, double leadTime, String customizable, String materials,
			double minTolerance, double maxTolerance, String finish){

		/* Matches array Index Reference:
		 * 
		 *  0 = Tension
		 *  1 = Compression
		 *  2 = Impact
		 *  3 = Part Complexity
		 *  4 = Lead Time
		 *  5 = Ease of Customizing
		 *  6 = Range of Mats.
		 *  7 = Tolerance
		 *  8 = Finish
		 */
		/* REFERENCE LIST: (Add Compression)
		
		m_BroadSearch_TF = new JTextField();
		m_LeadTime_TF = new JTextField();
		m_PartComplexity_TF = new JTextField();
		
		m_Tolerance_RTF = new RangedTextField<Double>(9.999, 0.000, 0.001);
		m_Tension_RTF = new RangedTextField<Double>(9.999, 0.000, 0.001);
		m_Impact_RTF = new RangedTextField<Integer>(200, 0, 1);
		
		m_Finish_CB = new JComboBox<String>(new String [] {"Search All", "Matte", "Gloss"});//TODO load these fRangeOfMaterials a file or something...
		m_RangeOfMaterials_CB = new JComboBox<String>(new String [] {"Search All", "Aluminum", "Stainless", "Clear All"});
		m_EaseOfCustomization_CB = new JComboBox<String>(new String [] {"Search All", "True", "False"});

		 */ 
		
		for(Printer printer : printerList){
				
			if(minTension <= printer.getTension() && printer.getTension() <= maxTension)
				printer.setMatches(true, 0);
			
			/* Not yet implemented since compression isn't searchable
			if(minCompression <= printer.getCompression() && printer.getCompression <= maxCompression)	// TODO: Should this be <= or >=?
				printer.setMatches(true, 1);
			*/ 
			
			if(minImpact <= printer.getImpact() & printer.getImpact() <= maxImpact)
				printer.setMatches(true, 2);
			
			if(complexity <= printer.getComplexity())
				printer.setMatches(true, 3);
			
			// Revisit this:
			if(leadTime <= printer.getLeadTime()) // Made double for now. Not certain this is integer or if this needs to be a range yet. 
				printer.setMatches(true, 4);
			
			if(customizable.equalsIgnoreCase(String.valueOf(printer.isCustomizable()))) // Sort of odd. Assuming EaseOfChange is a parameter that can be desired true or not necessary (false)
				printer.setMatches(true, 5);

			/*
			// TODO: Will need significant String validation, splitting, case, etc. Unless drop-down list?
			for (int g = 0; g < rom.length; g++) {
				if(rom[g].equalsIgnoreCase(printer.getRom()))
					printer.setMatches(true, 6);
			}
			*/
			
			// TODO: Check HastSet implementation of range of Materials
			if(materials.equals(printer.getMaterials()))
				printer.setMatches(true, 6);
			
			if(minTolerance <= printer.getTolerance() && printer.getTolerance() <= maxTolerance)
				printer.setMatches(true, 7);
			
			// TODO: Will need significant String validation, splitting, case, etc. Unless drop-down list?
			if(finish.equalsIgnoreCase(printer.getFinish()))
				printer.setMatches(true, 8);
				
		} 
	}
}
