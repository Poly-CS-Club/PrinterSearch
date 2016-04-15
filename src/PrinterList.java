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
	public static int tensionWeighting = 0;
	public static int compressionWeighting = 0;
	public static int impactWeighting = 0;
	public static int materialsWeighting = 0;
	public static int toleranceWeighting = 0;
	public static int finishWeighting = 0;
	public static int weightToChange = 0;
	public int DEFAULT_WEIGHTING = 0;

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
			// If we have altered our value, lets put proper weighting in.
			if (tensionWeighting != 0) {
				printer.setMatches(tensionWeighting, 0);
				System.out.println("Weighting for Tension is now: " + tensionWeighting);
			} else {
				//System.out.println(minTension + "<=" + printer.getTension() + "   " + maxTension + ">=" + printer.getTension());
				if (minTension <= printer.getTension() && maxTension >= printer.getTension()) {
					DEFAULT_WEIGHTING = 2;
					printer.setMatches(DEFAULT_WEIGHTING, 0);
				}
			}

			// Compression Section
			if (compressionWeighting != 0) {
				printer.setMatches(compressionWeighting, 1);
				System.out.println("Weighting for Compression is now: " + compressionWeighting);
			} else {
				if(minCompression <= printer.getCompression()
						&& maxCompression >= printer.getCompression()) {
					DEFAULT_WEIGHTING = 2;
					printer.setMatches(DEFAULT_WEIGHTING, 1);
				}
			}

			// Impact Section
			if (impactWeighting != 0) {
				printer.setMatches(impactWeighting, 2);
				System.out.println("Weighting for Impact is now: " + impactWeighting);
			} else {
				if (minImpact <= printer.getImpact()
						&& printer.getImpact() <= maxImpact) {
					DEFAULT_WEIGHTING = 2;
					printer.setMatches(DEFAULT_WEIGHTING, 2);
				}
			}

			/**
			 * Vendor Section
			 * DONT KNOW ABOUT CHANGING WEIGHT FOR VENDOR.
			 */
			System.out.println("Vendor: " + vendor + " VENDOR: " + printer.getVendor());
			if(vendor.equalsIgnoreCase("Select All"))
			{
				DEFAULT_WEIGHTING = 1;
				printer.setMatches(DEFAULT_WEIGHTING, 3);
			}else if(vendor.equalsIgnoreCase(printer.getVendor())) {
				DEFAULT_WEIGHTING = 1;
				printer.setMatches(DEFAULT_WEIGHTING, 3);
			}

			// Printer Materials Section
			String printerMaterials = printer.materialsString().replaceAll("\\<.*?>","");
			System.out.println(printerMaterials);

			if (materialsWeighting != 0) {
				printer.setMatches(materialsWeighting, 4);
				System.out.println("Weighting for Printer Materials is now: " + materialsWeighting);
			} else {
				if (materials.equalsIgnoreCase("Select All")) {
					DEFAULT_WEIGHTING = 1;
					printer.setMatches(DEFAULT_WEIGHTING, 4);
				} else if (printerMaterials.contains(" ")) { // If there is whitespace in the String, then there is a second entry
					String[] StringArray = printerMaterials.split(" ");
					for (int i = 0; i < StringArray.length; i++)
						if (materials.equalsIgnoreCase(StringArray[i]))
							DEFAULT_WEIGHTING = 1;
					printer.setMatches(DEFAULT_WEIGHTING, 4);
				} else if (materials.equalsIgnoreCase(printerMaterials)) {
					DEFAULT_WEIGHTING = 1;
					printer.setMatches(DEFAULT_WEIGHTING, 4);
				}
			}

			// Tolerance Section
			if (toleranceWeighting != 0) {
				printer.setMatches(toleranceWeighting, 5);
				System.out.println("Weighting for Tolerance is now: " + toleranceWeighting);
			} else {
				if (minTolerance <= printer.getTolerance()
						&& maxTolerance >= printer.getTolerance()) {
					DEFAULT_WEIGHTING = 1;
					printer.setMatches(DEFAULT_WEIGHTING, 5);
				}
			}

			// Finish Section
			// TODO: Will need significant String validation,
			// splitting, case, etc. Unless drop-down list?
			if (finishWeighting != 0) {
				printer.setMatches(finishWeighting, 6);
				System.out.println("Weighting for Finish is now: " + finishWeighting);
			} else {
				if(minFinish <= printer.getFinish()
						&& maxFinish >= printer.getFinish())  {
					DEFAULT_WEIGHTING = 1;
					printer.setMatches(DEFAULT_WEIGHTING, 6);
				}
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


