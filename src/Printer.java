/** @author Jake Leonard
 * 
 * 
 *
 */

public class Printer {
	
	private String name;
	private double tension;
	private double compression;
	private double impact;
	private double complexity;
	private double leadTime;
	private boolean eoc;
	private String rom;
	private String [] arrayOfROM;			// The actual array obtains values when instantiated in the Driver file.
	private double tolerance;
	private String finish;
	

	/* Add boolean? Could make associative array with hash.
	 * This is for sorting the final table and maybe adding checkboxes to the table like: "Meets Spec: [X]"
	 */
	private boolean[] matches;
	
	Printer(){
		
		name = "";
		tension = 0;
		compression = 0;
		impact = 0;
		complexity = 0;
		leadTime = 0;
		eoc = false;
		rom = "";
		arrayOfROM = new String[0];			// Will revise at a later point.
		tolerance = 0;
		finish = "";
		
		matches = new boolean[9]; // Should default to false.
		
		
	}

	/*Printer(String name, double tension, double compression, double impact,
			double complexity, double leadTime, boolean eoc, String rom, double tolerance, String finish){
		
		this.name = name;
		this.tension = tension;
		this.compression = compression;
		this.impact = impact;
		this.complexity = complexity;
		this.leadTime = leadTime;
		this.eoc = eoc;
		this.rom = rom;
		this.tolerance = tolerance;
		this.finish = finish;
		
		matches = new boolean[9];
	}*/

	Printer(String name, double tension, double compression, double impact,
			double complexity, double leadTime, boolean eoc, String [] rom, double tolerance, String finish){

		this.name = name;
		this.tension = tension;
		this.compression = compression;
		this.impact = impact;
		this.complexity = complexity;
		this.leadTime = leadTime;
		this.eoc = eoc;
		this.arrayOfROM = rom;
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

	public boolean isEoc() {
		return eoc;
	}

	public void setEoc(boolean eoc) {
		this.eoc = eoc;
	}

	public String getRom() {
		return rom;
	}

	public String[] getArrayRom() { return arrayOfROM; }

	public void setRom(String rom) {
		this.rom = rom;
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
