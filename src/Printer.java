import java.util.HashSet;

/**
 * A 3D metal printer.
 *
 * @author  Jake Leonard, (others on team), Marcinina Alvaran
 * @version (to be added by original programmer)
 */
public class Printer {

	private String name;
	private String vendor;
	private double tension;
	private double compression;
	private double impact;
	private HashSet<String> materials;
	private double tolerance;
	private String finish;


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
		finish = "";
		matches = new int[7];
		for (int index=0; index<9; index++)
			matches[index] = 0;

	}

	/**
	 * Instantiates a printer with specified values and a boolean list representing
	 * matches values for all parameters except name.
	 *
	 * @param name            the String with the specified printer name
	 * @param tension         the double with the specified tension
	 * @param compression     the double with the specified compression
	 * @param impact          the double with the specified impact
	 * @param complexity      the double with the specified complexity
	 * @param leadTime        the double with the specified lead time
	 * @param customizable    the boolean representing the ease of change
	 * @param materials       the HashSet with the range of materials
	 * @param tolerance       the double with the specified tolerance
	 * @param finish          the String representing the finish
	 */
	public Printer(String name, String vendor, double tension, double compression, double impact, HashSet<String> materials, double tolerance, String finish){

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

	public int[] getMatches() {
		return matches;
	}

	public int getTotalMatches(){
		int numMatches = 0;
		for(int i=0;i<matches.length;i++)		// If match result is greater than 0, as in it has a weighted match, add that up to the total!
			if(matches[i] > 0)
				numMatches++;


		return numMatches;
	}

	/**
	 * Set matches takes the int weight value given to set that specified index value weighted result value.
	 * @param valueWeight	Weighted value to be set for indexed value.
	 * @param index	Indexed value receiving the weight.
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
		materialsArray = rawString.split(", ");
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
	
	public String getPrinterName() {
		return name;
	}

	public void setPrinterName(String name) {
		this.name = name;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public double getTension() {
		return tension;
	}

	public void setTension(double tension) {
		this.tension = tension;
	}

	public double getCompression() {
		return compression;
	}

	public void setCompression(double compression) {
		this.compression = compression;
	}

	public double getImpact() {
		return impact;
	}

	public void setImpact(double impact) {
		this.impact = impact;
	}
	public HashSet<String> getMaterials() {
		return materials;
	}

	public double getTolerance() {
		return tolerance;
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}


}
