import java.util.HashSet;

/**
 * A 3D metal printer.
 * 
 * @author  Jake Leonard, (others on team), Marcinina Alvaran
 * @version (to be added by original programmer)
 */
public class Printer {
	
	private String name;
	private double tension;
	private double compression;
	private double impact;
	private double complexity;
	private double leadTime;
	private boolean easeOfChange;
	/*
	private String rom;
	private String [] arrayOfROM;			// The actual array obtains values when instantiated in the Driver file.
	*/
	private HashSet<String> rangeOfMaterials;
	private double tolerance;
	private String finish;
	

	/* Add boolean? Could make associative array with hash.
	 * This is for sorting the final table and maybe adding checkboxes to the table like: "Meets Spec: [X]"
	 */
	private boolean[] matches;
	
	/**
	 * Instantiates a printer with default values.
	 */
	public Printer(){
		
		name = "";
		tension = 0;
		compression = 0;
		impact = 0;
		complexity = 0;
		leadTime = 0;
		easeOfChange = false;
		/*
		rom = "";
		arrayOfROM = new String[0];			// Will revise at a later point.
		*/
		rangeOfMaterials = new HashSet<String>();
		tolerance = 0;
		finish = "";
		
		// Marci's attempt at false defaulting
		matches = new boolean[9];
		for (int index=0; index<9; index++)
			matches[index] = false;
		
	}

	/*Printer(String name, double tension, double compression, double impact,
			double complexity, double leadTime, boolean easeOfChange,
			String rom, double tolerance, String finish){
		
		this.name = name;
		this.tension = tension;
		this.compression = compression;
		this.impact = impact;
		this.complexity = complexity;
		this.leadTime = leadTime;
		this.easeOfChange = easeOfChange;
		this.rom = rom;
		this.tolerance = tolerance;
		this.finish = finish;
		
		matches = new boolean[9];
	}*/

	/**
	 * Instantiates a printer with specified values and a boolean list representing
	 * matches values for all parameters except name.
	 * 
	 * @param name              the String with the specified printer name
	 * @param tension           the double with the specified tension
	 * @param compression       the double with the specified compression
	 * @param impact            the double with the specified impact
	 * @param complexity        the double with the specified complexity
	 * @param leadTime          the double with the specified lead time
	 * @param easeOfChange      the boolean representing the ease of change
	 * @param rangeOfMaterials  the HashSet withthe range of materials
	 * @param tolerance         the double with the specified tolerance
	 * @param finish            the String representing the finish
	 */
	public Printer(String name, double tension, double compression, double impact, double complexity, double leadTime, boolean easeOfChange, HashSet<String> rangeOfMaterials, double tolerance, String finish){

		this.name = name;
		this.tension = tension;
		this.compression = compression;
		this.impact = impact;
		this.complexity = complexity;
		this.leadTime = leadTime;
		this.easeOfChange = easeOfChange;
		this.rangeOfMaterials = rangeOfMaterials;
		this.tolerance = tolerance;
		this.finish = finish;

		matches = new boolean[9];
	}

	public boolean[] getMatches() {
		return matches;
	}
	
	public int getTotalMatches(){
		int numMatches = 0;
		for(int i=0;i<matches.length;i++)
			if(matches[i] == true)
				numMatches++;
		
		return numMatches;
	}

	public void setMatches(boolean value, int index) {
		matches[index] = value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public double getComplexity() {
		return complexity;
	}

	public void setComplexity(double complexity) {
		this.complexity = complexity;
	}

	public double getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(double leadTime) {
		this.leadTime = leadTime;
	}

	public boolean isEaseOfChange() {
		return easeOfChange;
	}

	public void setEaseOfChange(boolean easeOfChange) {
		this.easeOfChange = easeOfChange;
	}

	// TODO: Review possibility of using a set for range of materials
	/*
	public String getRom() {
		return rom;
	}

	public String[] getArrayRom() { return arrayOfROM; }

	public void setRom(String rom) {
		this.rom = rom;
	}
	*/
	
	/**
	 * Adds specified material to printer's range of materials list.
	 * 
	 * @author Marcinina Alvaran
	 * @param  material the material to add to the range of materials
	 */
	public void addMaterial(String material) {
		rangeOfMaterials.add(material);
	}
	
	/*
	 *  TODO: Check HashSet implementation of range of materials
	 *  HashSet string looks like [material_1, material_2] with
	 *  brackets and spaces as is.
	 */
	public String getRangeOfMaterials() {
		return "" + rangeOfMaterials;
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
