import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 * A JLabel containing vendor information.
 * 
 * @author Joshua Becker
 *
 */
public class VendorLabel extends JLabel implements Serializable
{
	private JLabel header, name, webSite, otherPrinters;
    
/**
 * 
 * @param vendorName
 * @param webSite
 * @param otherPrinters
 */
    VendorLabel(String vendorName, String webSite, String otherPrinters)
    {
    	createComponents(vendorName, webSite,otherPrinters);
        designComponents();
        addComponents();
    }
    
    private void createComponents(String vendorName, String webSite, String otherPrinters)
    {
    	this.header = new JLabel(" Vendor Info ");
        this.name = new JLabel(" "+vendorName+" ");
        this.webSite = new JLabel(" "+webSite+" ");
        this.otherPrinters = new JLabel(" "+otherPrinters+" ");
    }
/**
 * 
 */
    private void designComponents()
    {
    	int height = VendorInfoFrame.FRAME_HEIGHT - 10;
    	int width = VendorInfoFrame.FRAME_WIDTH - 10;
    	
    	setPreferredSize(new Dimension(width,height));
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        
        this.header.setAlignmentX(Component.LEFT_ALIGNMENT);
    	this.name.setAlignmentX(Component.LEFT_ALIGNMENT);
        webSite.setAlignmentX(Component.LEFT_ALIGNMENT);
        otherPrinters.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        this.header.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    	this.name.setBorder(BorderFactory.createLineBorder(Color.black));
        this.webSite.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.otherPrinters.setBorder(BorderFactory.createLineBorder(Color.black));
        
        this.header.setPreferredSize(new Dimension(width,(int)(height*.18)));
    	this.name.setPreferredSize(new Dimension(width,(int)(height*.18)));
        this.webSite.setPreferredSize(new Dimension(width,(int)(height*.18)));
        this.otherPrinters.setPreferredSize(new Dimension(width,(int)(height*.18)));
        
        this.header.setMinimumSize(new Dimension(width,(int)(height*.15)));
    	this.name.setMinimumSize(new Dimension(width,(int)(height*.15)));
        this.webSite.setMinimumSize(new Dimension(width,(int)(height*.15)));
        this.otherPrinters.setMinimumSize(new Dimension(width,(int)(height*.15)));
        
        this.header.setForeground(Color.WHITE);
    	this.name.setForeground(Color.WHITE);
        this.webSite.setForeground(Color.WHITE);
        this.otherPrinters.setForeground(Color.WHITE);
    }
    
    /**
     * add components to JLabel
     */
    private void addComponents()
    {
    	add(header);
    	add(new JLabel("\n"));
        add(name);
        add(new JLabel("\n"));
        add(webSite);
        add(new JLabel("\n"));
        add(otherPrinters);
    }
    
    public JLabel getPrinterName() {
    	return name;
    }
    
    public JLabel getwebSite() {
    	return webSite;
    }
    
    public JLabel getotherPrinters() {
    	return otherPrinters;
    }
    
}