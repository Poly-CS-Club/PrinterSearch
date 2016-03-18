import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Reza on 3/18/16.
 */
public class ExampleXML {

    public static void main(String[] args) throws IOException {
        try {
            File file = new File("src/printers.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            // After the last few lines we have directed our java file command to parse the xml file to be readable and usable.

            System.out.println("Root element:" + document.getDocumentElement().getNodeName());  // Here we print out the root element, which is PRINTERS.

            NodeList listOfPrinters = document.getElementsByTagName("printer");                 // Now we retrieve a nodelist for the printer tag, therefore a size of 2.
            System.out.println("Testing our size of the printer list, it is :" + listOfPrinters.getLength() + "\n");


            // A for loop that iteratres through the listOfPrinters list, and in that for loop we retrieve the tag of ROM to see the specifics of how many instances there are
            // within that tag.
            for (int jojo = 0; jojo < listOfPrinters.getLength(); jojo++) {             // this for loop iterates through the number of printers.
                Element printerElementInstance = (Element) listOfPrinters.item(jojo);

                NodeList listROM = printerElementInstance.getElementsByTagName("ROM");  // Now we create a new list specifically for just ROM.
                //ArrayList<String> romListArray = new ArrayList<String>();

                String lineToBeAdded = listROM.item(0).getTextContent();            // Retrieve single string inputted value, because input is considered one element within xml tag.

                String [] romArray = lineToBeAdded.split("\\s+");                   // Split our storing between whitespace.

                System.out.println("The length of this god damn fucking array is: " + romArray.length);

                for (int jiji = 0; jiji < romArray.length; jiji++) {
                    System.out.println("The ROM attribute in this bitch is: " + romArray[jiji]);
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }   // End of main method.


}
