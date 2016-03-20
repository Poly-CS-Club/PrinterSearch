import java.util.ArrayList;
import java.util.HashSet;

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
	 * @param tension           the double with the specified tension
	 * @param compression       the double with the specified compression
	 * @param impact            the double with the specified impact
	 * @param complexity        the double with the specified complexity
	 * @param leadTime          the double with the specified lead time
	 * @param easeOfChange      the boolean with the specified easeOfChange
	 * @param rangeOfMaterials  the HashSet with the range of materials list
	 * @param tolerance         the double with the specified tolerance
	 * @param finish            the String representing the finish
	 */
	public void setMatches(double tension, double compression, double impact, 
			               double complexity, double leadTime, boolean easeOfChange,
			               HashSet<String> rangeOfMaterials, double tolerance, String finish){
		
		
		//PrinterList newMatch = new PrinterList();
		
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
		
		for(Printer printer : printerList){
				
			if(tension <= printer.getTension())
				printer.setMatches(true, 0);

			if(compression <= printer.getCompression())	// TODO: Should this be <= or >=?
				printer.setMatches(true, 1);
			
			if(impact <= printer.getImpact())
				printer.setMatches(true, 2);
			
			if(complexity <= printer.getComplexity())
				printer.setMatches(true, 3);
			
			if(leadTime <= printer.getLeadTime())
				printer.setMatches(true, 4);
			
			//if(easeOfChange == printer.isEaseOfChange()) // Sort of odd. Assuming EaseOfChange is a parameter that can be desired true or not necessary (false)
			//	printer.setMatches(true, 5);

			/*
			// TODO: Will need significant String validation, splitting, case, etc. Unless drop-down list?
			for (int g = 0; g < rom.length; g++) {
				if(rom[g].equalsIgnoreCase(printer.getRom()))
					printer.setMatches(true, 6);
			}
			*/
			
			// TODO: Check HastSet implementation of range of Materials
			if(rangeOfMaterials.equals(printer.getRangeOfMaterials()))
				printer.setMatches(true, 6);
			
			if(tolerance <= printer.getTolerance())
				printer.setMatches(true, 7);
			
			// TODO: Will need significant String validation, splitting, case, etc. Unless drop-down list?
			if(finish.equalsIgnoreCase(printer.getFinish()))
				printer.setMatches(true, 8);
				
		} 
	}
}
