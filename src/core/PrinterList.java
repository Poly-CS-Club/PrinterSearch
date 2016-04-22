package core;
import java.util.ArrayList;

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
	 * @param maxTension      the specified maximum tension
	 * @param minCompression  the specified minimum compression
	 * @param maxCompression  the specified maximum compression
	 * @param minImpact       the specified minimum impact
	 * @param maxImpact       the specified maximum impact
	 * @param materials       the specified materials
	 * @param minTolerance    the specified minimum tolerance
	 * @param maxTolerance    the specified maximum tolerance
	 * @param minFinish          the specified finish
	 * @param maxFinish
	 * @param vendor
	 */
	public void setMatches(
			double minTension, double maxTension,
			double minCompression, double maxCompression,
			double minImpact, double maxImpact,
			String materials,
			double minTolerance, double maxTolerance,
			double minFinish, double maxFinish,
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

				if (minTension <= printer.getTension() && maxTension >= printer.getTension()) {
					printer.setMatches(tensionWeighting, 0);
				}

			// Compression Section
				if(minCompression <= printer.getCompression()
						&& maxCompression >= printer.getCompression()) {
					printer.setMatches(compressionWeighting, 1);
				}


			// Impact Section

				if (minImpact <= printer.getImpact()
						&& printer.getImpact() <= maxImpact) {
					printer.setMatches(impactWeighting, 2);
				}


			/**
			 * Vendor Section
			 * DONT KNOW ABOUT CHANGING WEIGHT FOR VENDOR.
			 */
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
			if (minTolerance <= printer.getTolerance()
					&& maxTolerance >= printer.getTolerance()) {
				printer.setMatches(toleranceWeighting, 5);
			}


			// Finish Section
			// TODO: Will need significant String validation,
			// splitting, case, etc. Unless drop-down list?

			if(minFinish <= printer.getFinish()
					&& maxFinish >= printer.getFinish())  {
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


