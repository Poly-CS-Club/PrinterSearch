package core;
import java.util.ArrayList;

/**
 * A list of 3D metal printers being considered for the
 * product (TODO: replace with a brief description of the product).
 *
 * @author  Jake Leonard, Joshua Becker, Alireza Bahremand, Marcinina Alvaran
 * @version 1.0
 * @see     Printer
 */
public class PrinterList {

	private ArrayList<Printer> printerList;
	
	// Set from the beginning to default values. 

	public static int tensionWeighting = 2;
	public static int compressionWeighting = 2;
	public static int impactWeighting = 2;
	public static int vendorWeighting = 1;
	public static int materialsWeighting = 1;
	public static int toleranceWeighting = 1;
	public static int finishWeighting = 1;
	public static int weightToChange = 1;
	//public int DEFAULT_WEIGHTING = 1;

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
	 * @param minCompression  the specified minimum compression
	 * @param minImpact       the specified minimum impact
	 * @param materials       the String with the specified materials
	 * @param maxTolerance    the specified maximum tolerance
	 * @param maxFinish       the specified maximum finish
	 * @param vendor          the String with the specified vendor
	 */
	public void setMatches(
			double minTension,
			double minCompression,
			double minImpact,
			String materials,
			double maxTolerance,
			double maxFinish,
			String vendor){

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
			// Tension Section
			// If we have altered our value, let's put proper weighting in.

				if (minTension <= printer.getTension()) {
					printer.setMatches(tensionWeighting, 0);
				}

			// Compression Section
				if(minCompression <= printer.getCompression()) {
					printer.setMatches(compressionWeighting, 1);
				}


			// Impact Section

				if (minImpact <= printer.getImpact()) {
					printer.setMatches(impactWeighting, 2);
				}


			// Vendor Section
			System.out.println("Vendor: " + vendor + " VENDOR: " + printer.getVendor());
			if(vendor.equalsIgnoreCase("Select All"))
			{
				printer.setMatches(vendorWeighting, 3);
			}else if(vendor.equalsIgnoreCase(printer.getVendor())) {
				printer.setMatches(vendorWeighting, 3);
			}

			// Printer Materials Section
			String printerMaterials = printer.materialsString().replaceAll("\\<.*?>","");
			System.out.println(printerMaterials);

			if (materials.equalsIgnoreCase("Select All")) {
				printer.setMatches(materialsWeighting, 4);
			} else if (printerMaterials.contains(" ")) { // If there is whitespace in the String, then there is a second entry
				String[] StringArray = printerMaterials.split(" ");
				for (int i = 0; i < StringArray.length; i++)
					if (materials.equalsIgnoreCase(StringArray[i]))
						printer.setMatches(materialsWeighting, 4);
			} else if (materials.equalsIgnoreCase(printerMaterials)) {
				printer.setMatches(materialsWeighting, 4);
			}
			

			// Tolerance Section
			if (maxTolerance >= printer.getTolerance()) {
				printer.setMatches(toleranceWeighting, 5);
			}


			// Finish Section
			if(maxFinish >= printer.getFinish())  {
				printer.setMatches(finishWeighting, 6);
			}
		}
	}

	public void clearMatches(ArrayList<Printer> list)
	{
		for(Printer printer : list){
			int[] temp = printer.getMatches();
			for(int i=0;i<temp.length;i++){
				//System.out.println(i + "::" + temp[i]);
				temp[i] = 0;
			}
		}

	}
}


