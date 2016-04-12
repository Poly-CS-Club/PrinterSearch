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

	/**
	 * Searches the printer list for matches based on specified printer
	 * attributes and returns a list of matches ordered by total number
	 * of matches.
	 * <p>
	 * Arguments are passed to this method from driver (either GUI or
	 * command-line based); here a search takes place among the printer
	 * objects which then returns a new list consisting of matches ordered
	 * by total number of matches. Elsewhere will provide boolean checks to
	 * indicate that the particular parameters meets desired specifications.
	 * 
	 * @param minTension      the specified minimum tension
	 * @param maxTension      the specified maximum tension
	 * @param minCompression  the specified minimum compression
	 * @param maxCompression  the specified maximum compression
	 * @param minImpact       the specified minimum impact
	 * @param maxImpact       the specified maximum impact
	 * @param materials       the specified materials
	 * @param minTolerance    the specified minimum tolerance
	 * @param maxTolerance    the specified maximum tolerance
	 * @param finish          the specified finish
	 */
	public void setMatches(
			double minTension, double maxTension,
			double minCompression, double maxCompression,
			double minImpact, double maxImpact,
			String materials,
			double minTolerance, double maxTolerance,
			String finish, String vendor){

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
		
		for(Printer printer : printerList)
		{
			if(minTension == 0 && maxTension == 0)
			{
				printer.setMatches(2, 0);
			}else if(minTension <= printer.getTension() && maxTension >= printer.getTension())
			{
				printer.setMatches(2, 0);
				//System.out.println("Weight of 2 added!");
			}
			if(minCompression == 0 && maxCompression == 0)
			{
				printer.setMatches(2, 1);
			}else if(minCompression <= printer.getCompression()
			&& maxCompression >= printer.getCompression()) {
				printer.setMatches(2, 1);
				//System.out.println("Weight of 2 added!");
			}
			if(minImpact == 0 && maxImpact == 0)
			{
				printer.setMatches(2, 2);
			}else if(minImpact <= printer.getImpact()
			&& printer.getImpact() <= maxImpact) {
				printer.setMatches(2, 2);
				//System.out.println("Weight of 2 added!");
			}
			if(vendor.equalsIgnoreCase("Select All"))
			{
				printer.setMatches(1, 3);
			}else if(vendor.equalsIgnoreCase(printer.getVendor())) {
				printer.setMatches(1, 3);
				//System.out.println("Weight of 1 added!");
			}
			String printerMaterials = printer.materialsString().replaceAll("\\<.*?>","");
			if(materials.equalsIgnoreCase("Select All"))
			{
				printer.setMatches(1, 4);
			}else if(materials.equalsIgnoreCase(printerMaterials)) {
				printer.setMatches(1, 4);
				//System.out.println("Weight of 1 added!");
			}
			if(minTolerance == 0 && maxTolerance == 0)
			{
				printer.setMatches(1, 5);
			}else if(minTolerance <= printer.getTolerance()
			&& maxTolerance >= printer.getTolerance()) {
				printer.setMatches(1, 5);
				//System.out.println("Weight of 1 added!");
			}
			// TODO: Will need significant String validation,
			// splitting, case, etc. Unless drop-down list?
			if(finish.equalsIgnoreCase("Select All"))
			{
				printer.setMatches(1, 6);
			}else if(finish.equalsIgnoreCase(printer.getFinish())) {
				printer.setMatches(1, 6);
				//System.out.println("Weight of 1 added!");
			}
		}
	}
	public void clearMatches()
	{
		for(Printer printer : printerList){
			int[] temp = printer.getMatches();
			for(int i=0;i<temp.length;i++){
				temp[i] = 0;
			}
		}
			
	}
}
