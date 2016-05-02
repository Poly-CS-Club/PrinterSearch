package core;
import java.util.HashSet;

/**
 * A 3D metal printer.
 *
 * @author  Jake Leonard, Joshua Becker, Marcinina Alvaran, Alireza Bahremand
 * @version 1.0
 */
public class Printer {

	private String name;
	private String vendor;
	private double tension;
	private double compression;
	private double impact;
	private HashSet<String> materials;
	private double tolerance;
	private double finish;


	/* Add boolean? Could make associative array with hash.
	 * This is for sorting the final table and maybe adding checkboxes to the table like: "Meets Spec: [X]"
	 */
	//private boolean[] matches;
	private int[] matches;

	/**
	 * Instantiates a printer with default values.
	 */
	public Printer(){

		name = "";
		vendor = "";
		tension = 0;
		compression = 0;
		impact = 0;
		materials = new HashSet<String>();
		tolerance = 0;
		finish = 0;
		matches = new int[7];
		for (int index=0; index<9; index++)
			matches[index] = 0;

	}

	/**
	 * Instantiates a printer with specified values and a boolean list representing
	 * matches values for all parameters except name.
	 *
	 * @param name            the String with the specified printer name
	 * @param vendor          the String with the specified vendor name
	 * @param tension         the double with the specified tension
	 * @param compression     the double with the specified compression
	 * @param impact          the double with the specified impact
	 * @param materials       the HashSet with the range of materials
	 * @param tolerance       the double with the specified tolerance
	 * @param finish          the String representing the finish
	 */
	public Printer(String name, String vendor, double tension, double compression, double impact, HashSet<String> materials, double tolerance, double finish){

		this.name = name;
		this.vendor = vendor;
		this.tension = tension;
		this.compression = compression;
		this.impact = impact;
		this.materials = materials;
		this.tolerance = tolerance;
		this.finish = finish;

		matches = new int[7];
	}

	/**
	 * Returns an array containing search filter match information.
	 * <p>
	 * Matches array Index Reference:
     * <ul>
     * <li> 0 = Tension</li>
     * <li> 1 = Compression</li>
     * <li> 2 = Impact</li>
     * <li> 3 = vendor</li>
     * <li> 4 = Range of Mats.</li>
     * <li> 5 = Tolerance</li>
     * <li> 6 = Finish</li>
     * </ul)
     * 
	 * @return an integer array containing filter match information
	 */
	public int[] getMatches() {
		return matches;
	}

	/**
	 * Return the number of filter parameters for this printer.
	 * 
	 * @return the number of filter matches for this printer
	 */
	public int getTotalMatches(){
		int numMatches = 0;
		for(int i=0;i<matches.length;i++)		// If match result is greater than 0, as in it has a weighted match, add that up to the total!
			if(matches[i] > 0)
				numMatches += matches[i];


		return numMatches;
	}

	/**
	 * Set matches takes the int weight value given to set that specified index value
	 * weighted result value.
	 * 
	 * @param valueWeight	the number with the weighted value to be set for indexed value.
	 * @param index	        the indexed value receiving the weight.
     */
	public void setMatches(int valueWeight, int index) {
		matches[index] = valueWeight;
	}

	/**
	 * Adds specified material to printer's range of materials list.
	 *
	 * @param  material the material to add to the range of materials
	 */
	public void addMaterial(String material) {
		materials.add(material);
	}

	/**
	 *  Returns a String with the range of materials.
	 *  <p>
	 *  If the range of materials has more than one element, then a
	 *  multi-line String is returned.
	 */
	public String materialsString() {
		String rawString,
		       materialsString = "";
		String[] materialsArray;

		// Convert HashSet to String
		rawString = "" + materials;
		materialsArray = rawString.split(",");
		if (materialsArray.length > 1) {
		    for (int index = 0; index < materialsArray.length-1; index++)
			    materialsString += (materialsArray[index] + "<br>");
		    materialsString += materialsArray[materialsArray.length-1];
		}
		else {
			materialsString = rawString;
		}

		// Remove brackets
		materialsString = String.copyValueOf(
				materialsString.toCharArray(),1,
				materialsString.length()-2);

		// Add multi-line compatibility with HTML
		materialsString = "<html>" + materialsString + "</html>";

		return materialsString;
	}
	
//--------------------------------setter/getters---------------------------------------//
	/**
	 * Returns the printer's name
	 * 
	 * @return the String with the printer's name
	 */
	public String getPrinterName() {
		return name;
	}

	/**
	 * Sets the printer's name to the specified name.
	 * 
	 * @param name  the String containing the printer's new name
	 */
	public void setPrinterName(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the printer's vendor.
	 * 
	 * @return  the String with the name of the printer's vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * Sets the name of the printer's vendor to the specified vendor name.
	 * 
	 * @param vendor  the String with the vendor's name
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * Returns the tension value for the printer in ksi units.
	 * 
	 * @return the tension value for the printer in ksi units
	 */
	public double getTension() {
		return tension;
	}

	/**
	 * Sets the tension value for the printer in ksi units.
	 * 
	 * @param tension  the new tension value for the printer in ksi units
	 */
	public void setTension(double tension) {
		this.tension = tension;
	}

	/**
	 * Returns the printer's compression value in ksi units.
	 * 
	 * @return the printer's compression value in ksi units
	 */
	public double getCompression() {
		return compression;
	}

	/**
	 * Sets the printer's compression value in ksi units.
	 * 
	 * @param compression  the new compression value for the printer in ksi units
	 */
	public void setCompression(double compression) {
		this.compression = compression;
	}

	/**
	 * Returns the printer's impact value in lb-ft units.
	 * 
	 * @return the printer's impact value in lb-ft units
	 */
	public double getImpact() {
		return impact;
	}

	/**
	 * Sets the printer's impact value in lb-ft units.
	 * 
	 * @param impact  the new impact value for the printer in lb-ft units
	 */
	public void setImpact(double impact) {
		this.impact = impact;
	}
	
	/**
	 * Returns the HashSet<String> containing the printer's range of materials.
	 * 
	 * @return  the HashSet<String> with the printer's range of materials
	 */
	public HashSet<String> getMaterialHash() {
		return materials;
	}

	/**
	 * Returns the printer's tolerance value in inches.
	 * 
	 * @return the printer's tolerance value in inches
	 */
	public double getTolerance() {
		return tolerance;
	}

	/**
	 * Sets the printer's tolerance values in inches.
	 * 
	 * @param tolerance  the new tolerance value for the printer in inches
	 */
	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}

	/**
	 * Returns the printer's finish value in micro-inches.
	 * 
	 * @return the printer's finish value in micro-inches
	 */
	public double getFinish() {
		return finish;
	}

	/**
	 * Sets the printer's finish value in micro-inches.
	 * 
	 * @param finish the new finish value for the printer in micro-inches
	 */
	public void setFinish(double finish) {
		this.finish = finish;
	}


}
