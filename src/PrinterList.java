/** @author Jake Leonard
 * 
 */

import java.util.ArrayList;

public class PrinterList {
	
	private ArrayList<Printer> printerList;
	
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
	
	public void setMatches(double tension, double compression, double impact, 
			double complexity, double leadTime, boolean eoc, String rom, double tolerance, String finish){
		
		
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

			if(compression <= printer.getCompression())	// Should this be <= or >=?
				printer.setMatches(true, 1);
			
			if(impact <= printer.getImpact())
				printer.setMatches(true, 2);
			
			if(complexity <= printer.getComplexity())
				printer.setMatches(true, 3);
			
			if(leadTime <= printer.getLeadTime())
				printer.setMatches(true, 4);
			
			if(eoc == printer.isEoc()) // Sort of odd. Assuming EOC is a parameter that can be desired true or not necessary (false)
				printer.setMatches(true, 5);
			
			if(rom.equals(printer.getRom())) // Will need significant String validation, splitting, case, etc. Unless drop-down list?
				printer.setMatches(true, 6); 
			
			if(tolerance <= printer.getTolerance())
				printer.setMatches(true, 7);
			
			if(finish.equals(printer.getFinish())) // Will need significant String validation, splitting, case, etc. Unless drop-down list?
				printer.setMatches(true, 8);
				
		} 
	}
}
