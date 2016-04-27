package core;
import java.util.*;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

/**
 * A test of an implementation of the PrinterList class by
 * allowing the user to add printers to the printer list and searching
 * the subsequent list.
 *
 * @author  Jake Leonard, Trevor Forrey, Joshua Becker, Alireza Bahremand, Marcinina Alvaran
 * @version (TODO: to be included by original programmer)
 * @see PrinterList
 * @see Printer
 */
public class ToolBox {
	public static Document m_Document;
	
	public ToolBox()
	{
		DocumentBuilderFactory documentBuilderFactory;
		DocumentBuilder documentBuilder;
		File file = null;

		// Build list of Printer objects from XML file;
		try{
			// This is a important line, for when using a mac directory must be switched, the directory has \\ because of eclipse.
			boolean found = isMacOS();
			file = new File("src\\printerInformation.xml");
				if(found){
					File fileChange = new File("src/printerInformation.xml");	// Mac image path.
					file = fileChange;
				}
			

			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			m_Document = documentBuilder.parse(file);
		} catch(FileNotFoundException e){
			ToolBox.getPathOfXML();
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
	}

	public static boolean isMacOS() {
		String stringSearch = System.getProperty("os.name");
		String keyword = "Mac";
		Boolean foundMac = Arrays.asList(stringSearch.split(" ")).contains(keyword);
		return foundMac;
	}
	/**
	 * Generates printer list from XML file and returns list to calling method.
	 *
	 * @return the printer list generated from XML file
	 */
	public static PrinterList generatePrinterList(){
		NodeList nList;
		Node nNode;
		PrinterList printerList = new PrinterList();

		// Build list of Printer objects from XML file;

			// Display listed printers in console
			nList = m_Document.getElementsByTagName("printer");

			for(int i=0;i<nList.getLength();i++){
				nNode = nList.item(i);
				System.out.println(nNode.getNodeName());
				displayPrinterNodes(nNode, printerList);
			}
		return printerList;
	}

    /**
     * Iterates through the child-nodes of "printer" tag strings,
     * converts to appropriate data type, and then printer to console
     * for evaluation.
     */
	private static void displayPrinterNodes(Node node, PrinterList printers)
	{
		String name, vendor;
		double tension, compression, impact, tolerance, finish;
		String materialsString = "";
		HashSet<String> materialsSet = new HashSet<String>();
		Element eElement;

		// Retrieve parameter info from listed printers
		if(node.getNodeType() == Node.ELEMENT_NODE)
		{
			eElement = (Element)node;
			name = getString("NAME", eElement);
			//System.out.println(name);

			vendor = getString("VENDOR", eElement);
			//System.out.println(vendor);

			tension = Double.parseDouble(getString("TENSION", eElement));
			//System.out.println(tension);

			compression = Double.parseDouble(
					getString("COMPRESSION", eElement));
			//System.out.println(compression);

			impact = Double.parseDouble(getString("IMPACT", eElement));
			//System.out.println(impact);

			materialsString = getString("ROM", eElement);
			//System.out.println(materialsString);
			materialsSet = stringToHashSet(materialsString);

			tolerance = Double.parseDouble(getString("TOLERANCE", eElement));
			//System.out.println(tolerance);

			finish = Double.parseDouble(getString("FINISH", eElement));
			//System.out.println(finish);

			printers.addPrinter(new Printer(
					name, vendor, tension, compression, impact, materialsSet, tolerance, finish));
			/*System.out.println(
					"Added: " +
			        printers.getPrinter(0).getPrinterName());*/
		}
	}
	/**
	 * 
	 * @param printerName
	 * @param vendor
	 * @param printerTension
	 * @param printerCompression
	 * @param printerPartComplexity
	 * @param printerROM
	 * @param printerImpact
	 * @param printerLeadTime
	 * @param printerEaseOfChange
	 * @param printerTolerance
	 * @param printerFinish
	 */
	public static void addPrinter(String printerName, String vendor, String printerTension, String printerCompression, String printerROM,
								  String printerImpact, String printerTolerance, String printerFinish) 
	{
	        try {
	            /*
	             * Creates link to xml file
	             */

							StreamResult result = new StreamResult("src\\printerInformation.xml");
							Boolean found = isMacOS();
							if(found){
								StreamResult resultChange = new StreamResult("src/printerInformation.xml");	// Mac image path.
								result = resultChange;
							}

	            Element root = m_Document.getDocumentElement();
	            System.out.println(root.getLocalName());

	            /*
	             * Creates printer root element
	             */
	            Element newPrinter = m_Document.createElement("printer");


	            /*
	             * Inserts user given parameters into the new printer xml element in the following order:
	             *
	             * Creates element to represent attribute of printer,
	             * appends child element that holds value of the attribute,
	             * appends element to the new printer element
	             */
	            Element name = m_Document.createElement("NAME");
	            name.appendChild(m_Document.createTextNode(printerName));
	            newPrinter.appendChild(name);

				Element vendors = m_Document.createElement("VENDOR");
				vendors.appendChild(m_Document.createTextNode(vendor));
				newPrinter.appendChild(vendors);

	            Element tension = m_Document.createElement("TENSION");
	            tension.appendChild(m_Document.createTextNode(printerTension));
	            newPrinter.appendChild(tension);

	            Element compression = m_Document.createElement("COMPRESSION");
	            compression.appendChild(m_Document.createTextNode(printerCompression));
	            newPrinter.appendChild(compression);

	            Element impact = m_Document.createElement("IMPACT");
	            impact.appendChild(m_Document.createTextNode(printerImpact));
	            newPrinter.appendChild(impact);

	            Element partComplexity = m_Document.createElement("PART_COMPLEXITY");
	            partComplexity.appendChild(m_Document.createTextNode("123"));
	            newPrinter.appendChild(partComplexity);

	            Element leadTime = m_Document.createElement("LEAD_TIME");
	            leadTime.appendChild(m_Document.createTextNode("1123"));
	            newPrinter.appendChild(leadTime);

	            Element easeOfChange = m_Document.createElement("EOC");
	            easeOfChange.appendChild(m_Document.createTextNode("True"));
	            newPrinter.appendChild(easeOfChange);

	            Element ROM = m_Document.createElement("ROM");
	            ROM.appendChild(m_Document.createTextNode(printerROM));
	            newPrinter.appendChild(ROM);

	            Element tolerance = m_Document.createElement("TOLERANCE");
	            tolerance.appendChild(m_Document.createTextNode(printerTolerance));
	            newPrinter.appendChild(tolerance);

	            Element finish = m_Document.createElement("FINISH");
	            finish.appendChild(m_Document.createTextNode(printerFinish));
	            newPrinter.appendChild(finish);

	            //System.out.println("root name: " + root.getNodeName());
	            NodeList nodeList = root.getElementsByTagName("vendor");
	            boolean hasBeenAdded = false;
	            for(int i = 0; i < nodeList.getLength();i++)
	            {
	            	Element temp = (Element) nodeList.item(i);
	            	if(temp.getAttribute("name").equalsIgnoreCase(vendor)  && !hasBeenAdded)
	            	{
	            		((Element) nodeList.item(i)).appendChild(newPrinter);
	            		hasBeenAdded = true;
	            	}
	            }
	            
	            //if printer has not been added create new vendor slot ant add printer to it.
	            if(!hasBeenAdded)
	            {
	            	//TODO get vendor info from user.
	            	String webSite = JOptionPane.showInputDialog(vendor +": webSite:");
	            	String info = JOptionPane.showInputDialog(vendor +": Information:");
	            	nodeList = root.getElementsByTagName("VENDORS");
	            	Element newVendor = m_Document.createElement("vendor");
	            	Element vendorWebSite = m_Document.createElement("webSite");
	            	Element VendorInfo = m_Document.createElement("VendorInfo");
	            	
	            	newVendor.setAttribute("name", vendor);
	            	vendorWebSite.setAttribute("info", webSite);
	            	VendorInfo.setAttribute("info", info);
	            	
	            	newVendor.appendChild(vendorWebSite);
	            	newVendor.appendChild(VendorInfo);
	            	newVendor.appendChild(newPrinter);
	            	
	            	Element ven = (Element) nodeList.item(0);
	            	ven.appendChild(newVendor);
	            	
	            }


	            DOMSource source = new DOMSource(m_Document);

	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
							transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	  					transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	            transformer.transform(source, result);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
    /**
	 * Displays a list of printer matches sorted from highest number of matching
	 * attributes to lowest on the console.
	 *
	 * @param printers the ArrayList of printers
	 */
	public static ArrayList<Printer> outputSearchedList(PrinterList printers){
		// To-Do: Handle if no matches exist and is empty.

		ArrayList<Printer> list = printers.getPrinterList();
		ArrayList<Printer> outputList = new ArrayList<Printer>();

		for(Printer printer : list){
			@SuppressWarnings("unused")
			int matches = 0;
			int currentMatches = printer.getTotalMatches();

			if(currentMatches > 0 && !(outputList.contains(printer))){
				matches = printer.getTotalMatches();
				outputList.add(printer);
			}
		}

		// Basic Sorting of list
		int position;
		boolean keepLooking = true;
		Printer tempPrinter;

		while(keepLooking){
			keepLooking = false;
			for(position=0;position < outputList.size()-1;position++){
				if(outputList.get(position).getTotalMatches() > outputList.get(position+1).getTotalMatches()){
					//System.out.println(outputList.get(position).getTotalMatches() +  " is greater than " + outputList.get(position+1).getTotalMatches());
					tempPrinter = outputList.get(position+1);
					//System.out.println("Swapping position " + position + ": " + outputList.get(position));
					outputList.set(position+1, outputList.get(position));
					outputList.set(position, tempPrinter);
					keepLooking = true;
				}
			}
		}


	// Output to Console - Sorted by highest matches first.
	/*for(int i=list.size()-1;i>=0;i--){
		System.out.println(
				"\n\n---------------------------------" +
		        "     Printer Name: " + outputList.get(i).getPrinterName() +
		        "Number Of Matches: " + outputList.get(i).getTotalMatches() +
		        "\n----------------------------------");
	}*/
	return outputList;
}

	/**
	 * Insert description here.
	 *
	 * @param tagName the String with a printer tag
	 * @param element the Element
	 * @return a String with...
	 */
	public static String getString(String tagName, Element element) {
		NodeList list = element.getElementsByTagName(tagName);
		if (list != null && list.getLength() > 0) {
			NodeList subList = list.item(0).getChildNodes();

			if (subList != null && subList.getLength() > 0) {
				return subList.item(0).getNodeValue();
			}
		}

		return null;
	}

	/**
	 * Converts a String list delimited by ", " (without quotes) into a HashSet.
	 * @param list
	 * @return
	 */
	public static HashSet<String> stringToHashSet(String list) {
		String[] listArray;
		HashSet<String> hashSet = new HashSet<String>();

		listArray = list.split(", ");
		for (String element : listArray)
			hashSet.add(element);

		return hashSet;
		}
	/**
	 * gets a vendor object array filled with all vendors from the xml
	 */
	private static Object [] getVendor()
	{
		Node nNode;
		ArrayList<String> vendorList = new ArrayList<String>();

			// Display listed printers in console
			Element root = m_Document.getDocumentElement();
			NodeList nodeList = root.getElementsByTagName("vendor");

			for(int i=0;i<nodeList.getLength();i++){
				nNode = nodeList.item(i);
				Element element = (Element) nNode;
				if(!vendorList.contains(element.getAttribute("name")))
					vendorList.add(element.getAttribute("name"));
			}
		return vendorList.toArray();
	}
	public static String [] getVendorList()
	{
		Object [] vendors= ToolBox.getVendor();
		String [] vendorList = new String[vendors.length];
		
		int i = 0;
		for(Object name:vendors)
		{
			vendorList[i] = (String) name;
			i++;
		}
		return vendorList;
	}
	/**
	 * getting the material list from xml file
	 */
	private static Object [] getMaterial()
	{
		Node nNode;
		ArrayList<String> materialList = new ArrayList<String>();
		
			Element root = m_Document.getDocumentElement();
			NodeList nodeList = root.getElementsByTagName("ROM");

			for(int i=0;i<nodeList.getLength();i++){
				nNode = nodeList.item(i);
				Element element = (Element) nNode;
				if(!materialList.contains(element.getTextContent()) && !element.getTextContent().equals("aluminum, stainless"))
				{
					materialList.add(element.getTextContent());
				}
					
			}
		return materialList.toArray();
	}
	/**
	 * creates a String array from the Object array generated from the xml file
	 * @return Material List
	 */
	public static String [] getMaterialList()
	{
		Object [] materials= ToolBox.getMaterial();
		String [] materialsStr = new String[materials.length];
		int i = 0;
		for(Object name:materials)
		{
				materialsStr[i] = (String) name;
			i++;
		}
		return materialsStr;
	}
	
	public static String [] getVendorInfo(String vendorName)
	{
		ArrayList<String> vendorInfo = new ArrayList<String>();

			// Display listed printers in console
			Element root = m_Document.getDocumentElement();
			//get all vendors
			NodeList nodeList = root.getElementsByTagName("vendor");
			
			for(int i = 0; i < nodeList.getLength(); i++)
			{
				Element temp = (Element) nodeList.item(i);
				//if the vendor is found
				if(temp.getAttribute("name").equalsIgnoreCase(vendorName))
				{
					//get information
					String webSite = ((Element)(temp.getElementsByTagName("webSite")).item(0)).getAttribute("info");
					String vendorInformation = ((Element)(temp.getElementsByTagName("VendorInfo")).item(0)).getAttribute("info");
					vendorInfo.add(webSite);
					vendorInfo.add(vendorInformation);
					//get all printers from that vendor
					NodeList printers = temp.getElementsByTagName("printer");
					
					//get all names of printers
					for(int j = 0; j < printers.getLength(); j++)
					{
						String name;
						name = ((Element) printers.item(j)).getElementsByTagName("NAME").item(0).getTextContent();
						vendorInfo.add(name);
					}
				}
			}
		String [] vendorList = new String[vendorInfo.size()];
			
		int i = 0;
		for(String contents:vendorInfo)
		{
			vendorList[i] = contents;
			i++;
		}
		return vendorList;
	}
	/**
	 * getting the path from the user
	 */
	public static void getPathOfXML()
	{
		DocumentBuilderFactory documentBuilderFactory;
		DocumentBuilder documentBuilder;
		File file = null;
		
		int result = JOptionPane.showConfirmDialog(null, "Error printerInformation.xml was not found.", "", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION);
		
		if(result == JOptionPane.CLOSED_OPTION)
		{
			System.exit(0);
		}

		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Navigate to printerInformation.xml");
		fc.showOpenDialog(null);
		// Build list of Printer objects from XML file;
		try{
			// This is a important line, for when using a mac directory must be switched, the directory has \\ because of eclipse.
			file = fc.getSelectedFile();
				Boolean found = isMacOS();
				if(found){
					File fileChange = new File("src/ffprinterInformation.xml");	// Mac image path.
					file = fileChange;
				}
			

			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			m_Document = documentBuilder.parse(file);
			
		}catch(FileNotFoundException e){
			ToolBox.getPathOfXML();
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
	}
	

    
    /**
     * Returns the appropriate logo icon path depending on the user's
     * operating system
     * 
     * @param fileName the String with the logo's file name
     * @return the String containing the logo's path
     */
    public static ImageIcon getLogoIcon()
    {
        String iconName = "sift-logo-color.png";
        boolean macOSFound = isMacOS();
        if (macOSFound)
            return new ImageIcon("src\\" + iconName);
        else
            return new ImageIcon("src/" + iconName);
    }
}
