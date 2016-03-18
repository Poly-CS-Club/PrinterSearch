/** @author Jake Leonard
 * 
 */

import java.util.*;
import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Driver {

	public static void main(String[] args) {
		
		PrinterList printerList = generatePrinterList();
		
		/* The following is simply a rudimentary test of search functionality
		 * 
		 */
		Scanner scanner = new Scanner(System.in);
		boolean newInput = true;
		do {
			
			System.out.println("Enter your specifications: ");
			
			System.out.println("Tension: ");
			double tension = scanner.nextDouble();
			
			System.out.println("Compression: ");
			double compression = scanner.nextDouble();
			
			System.out.println("Impact: ");
			double impact = scanner.nextDouble();
			
			System.out.println("Complexity: ");
			double complexity = scanner.nextDouble();
			
			System.out.println("Lead Time: ");
			double leadTime = scanner.nextDouble();
			
			System.out.println("Ease of Customizing (true/false): "); // Will need to better validate this input.
			boolean eoc = scanner.nextBoolean();
			
			System.out.println("Range of Materials: ");
			scanner.nextLine(); // Clear buffer
			String rom = scanner.nextLine();
			
			System.out.println("Tolerance: ");
			double tolerance = scanner.nextDouble();
			
			System.out.println("Desired Finish Type: ");
			scanner.nextLine();
			String finish = scanner.nextLine();
			
			printerList.setMatches(tension, compression, impact, complexity, leadTime, eoc, rom, tolerance, finish);
			
			outputSearchedList(printerList);
			
			
			// Continue entering new input?
			System.out.println("Perform New Search? (y/n)");
			if(scanner.nextLine().equals("n")){
				newInput = false;
				System.out.println("Program ending...");
			}
				
		} while(newInput == true);


	}
	
	/*
	 * Generates printer list from XML file
	 * Returns list to calling method
	 */
	public static PrinterList generatePrinterList(){
		
		PrinterList printerList = new PrinterList();
		
		// Build list of Printer objects from XML file;
		try{
			File file = new File("src/printers.xml");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			
			System.out.println("Root element:" + document.getDocumentElement().getNodeName());
			
			NodeList nList = document.getElementsByTagName("printer");
			
			/* The following iterates through the child-nodes of "printer" tags,
			 * retrieving the text within as String, converts, and then printers to console
			 * for evaluation.
			 */
			for(int i=0;i<nList.getLength();i++){
				
				String name;
				double tension;
				double compression;
				double impact;
				double complexity;
				double leadTime;
				boolean eoc;
				String rom;
				double tolerance;
				String finish;
				
				Node nNode = nList.item(i);
				
				System.out.println(nNode.getNodeName());
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element)nNode;
					
					name = eElement.getElementsByTagName("NAME").item(0).getTextContent();
					System.out.println(name);
					
					tension = Double.parseDouble(eElement.getElementsByTagName("TENSION").item(0).getTextContent());
					System.out.println(tension);
					
					compression = Double.parseDouble(eElement.getElementsByTagName("COMPRESSION").item(0).getTextContent());
					System.out.println(compression);
					
					impact = Double.parseDouble(eElement.getElementsByTagName("IMPACT").item(0).getTextContent());
					System.out.println(impact);
					
					complexity = Double.parseDouble(eElement.getElementsByTagName("PART_COMPLEXITY").item(0).getTextContent());
					System.out.println(complexity);
					
					leadTime = Double.parseDouble(eElement.getElementsByTagName("LEAD_TIME").item(0).getTextContent());
					System.out.println(leadTime);
					
					eoc = Boolean.valueOf(eElement.getElementsByTagName("EOC").item(0).getTextContent());
					System.out.println(eoc);
					
					rom = eElement.getElementsByTagName("ROM").item(0).getTextContent();
					System.out.println(rom);
					
					tolerance = Double.parseDouble(eElement.getElementsByTagName("TOLERANCE").item(0).getTextContent());
					System.out.println(tolerance);
					
					finish = eElement.getElementsByTagName("FINISH").item(0).getTextContent();
					System.out.println(finish);
					
					printerList.addPrinter(new Printer(name, tension, compression, impact, complexity,leadTime, eoc, rom, tolerance, finish));
					System.out.println("Added: " + printerList.getPrinter(0).getName());
				}
			}
				
			}catch(FileNotFoundException e){
				System.out.println("File Not Found" + e);
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return printerList;
	}
	
	public static void outputSearchedList(PrinterList printers){
		
		ArrayList<Printer> list = printers.getPrinterList();
		
		ArrayList<Printer> outputList = new ArrayList<Printer>(); // Handle if no matches exist and is empty. 
		
		for(int i=0;i<list.size();i++){
		
			int matches = 0;
			Printer tempPrinter;
			
			for(Printer printer : list){
				
				int currentMatches = printer.getTotalMatches();
				
				if(currentMatches > 0 && currentMatches >= matches){
					matches = printer.getTotalMatches();
					outputList.add(printer);
				}
			}
		}
		
		
		// Output to Console
		
		for(int i=0;i<outputList.size();i++){
			System.out.println("\n\n---------------------------------");
			System.out.println("Printer Name: " + outputList.get(i).getName());
			System.out.println("# Of Matches: " + outputList.get(i).getTotalMatches()); 
			System.out.println("\n--------------------------------------");
		}
	}
}
