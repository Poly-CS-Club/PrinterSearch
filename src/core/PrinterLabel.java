package core;
import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 * A JLabel containing printer information.
 * 
 * @author Joshua Becker
 *
 */
@SuppressWarnings("serial")
public class PrinterLabel extends JLabel implements Serializable
{
	private JLabel name, vendor, tension, compression, impact,
	               materials, tolerance, finish;
	private boolean needsLink;
    private int m_index;
    
    /**
     * Create a label with specified printer information.
     * 
     * @param index           the index number
     * @param frameWidth      the width of the frame
     * @param frameHeight     the height of the frame
     * @param name            the String with the printer's name
     * @param vendor          the String with the vendor's name
     * @param tension         the String with the printer's tension value
     * @param compression     the String with the printer's compression value
     * @param impact          the String with the printer's impact value
     * @param materials       the String with the printer's range of materials
     * @param tolerance       the String with the printer's tolerance value
     * @param finish          the String with the printer's finish
     * @param needsLink       the boolean for if the component is associated with vendor info
     */
    PrinterLabel(int index, int frameWidth, int frameHeight, String name,String vendor, String tension,
    		String compression, String impact, String materials, String tolerance, String finish, boolean needsLink)
    {
        // Init printer label values
        m_index = index;
        this.name = new JLabel(name, JTextField.CENTER);
        this.vendor = new JLabel(vendor, JTextField.CENTER);
        this.tension = new JLabel(tension, JTextField.CENTER);
        this.compression = new JLabel(compression, JTextField.CENTER);
        this.impact = new JLabel(impact, JTextField.CENTER);
        this.materials = new JLabel(materials, JTextField.CENTER);
        this.tolerance = new JLabel(tolerance, JTextField.CENTER);
        this.finish = new JLabel(finish, JTextField.CENTER);
        this.needsLink = needsLink;
        
        // Design printer label
        setPreferredSize(new Dimension(frameWidth-200, 40));
        setMinimumSize(new Dimension(frameWidth-300, 40));
        setMaximumSize(new Dimension(frameWidth-190, 60));
        setLayout(new GridLayout(1,10,2,2));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setOpaque(true);
        
        // Set alternating list entry backgrounds
        if (m_index % 2 == 0)
        	setOpaque(false);
        else
        	setBackground(new Color(215, 215, 215));

        // Include components
        designComponents();
        addComponents();
    }
    
    /**
     * Create a default printer label.
     * 
     * @param index       the index number
     * @param frameWidth  the width of the frame
     * @param frameHeight the height of the frame
     */
    PrinterLabel(int index, int frameWidth, int frameHeight)
    {
        // Init printer label
        m_index = index;
        name = new JLabel("default", JTextField.CENTER);
        vendor = new JLabel("default", JTextField.CENTER);
        tension = new JLabel("default", JTextField.CENTER);
        compression = new JLabel("default", JTextField.CENTER);
        impact = new JLabel("default", JTextField.CENTER);
        materials = new JLabel("Default", JTextField.CENTER);
        tolerance = new JLabel("default", JTextField.CENTER);
        finish = new JLabel("default", JTextField.CENTER);
        
        // Design printer label
        setPreferredSize(new Dimension(frameWidth-200, 45));
        setMinimumSize(new Dimension(frameWidth-220, 40));
        setMaximumSize(new Dimension(frameWidth-190, 50));
        setLayout(new GridLayout(1,9,2,2));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setOpaque(true);
        
        // Set alternating list entry backgrounds
        if (m_index % 2 == 0)
            setOpaque(false);
        else
            setBackground(new Color(215, 215, 215));

        // Include components
        designComponents();
        addComponents();
    }
    
    /**
     * Centers printer information labels on X-axis.
     */
    private void designComponents()
    {
        // Center components
    	this.name.setAlignmentX(Component.CENTER_ALIGNMENT);
    	vendor.setAlignmentX(Component.CENTER_ALIGNMENT);
        tension.setAlignmentX(Component.CENTER_ALIGNMENT);
        compression.setAlignmentX(Component.CENTER_ALIGNMENT); 
        impact.setAlignmentX(Component.CENTER_ALIGNMENT);
        materials.setAlignmentX(Component.CENTER_ALIGNMENT);
        tolerance.setAlignmentX(Component.CENTER_ALIGNMENT);
        finish.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Links component to vendor information
        if(needsLink)
        {
        	vendor.setPreferredSize(new Dimension(90,30));
        	vendor.addMouseListener(new MouseActionListener(vendor));
        	vendor.setToolTipText("Click For More Info");
        }
        name.setToolTipText(name.getText());
    }
    
    /**
     * Adds printer information labels to printerUI label.
     */
    private void addComponents()
    {
        add(name);
        add(vendor);
        add(tension);
        add(compression);
        add(impact);
        add(materials);
        add(tolerance);
        add(finish);
        
    }
    
    /**
     * Returns the label with the printer's name.
     * 
     * @return the JLabel containing the printer's name
     */
    public JLabel getPrinterName() {
    	return name;
    }
    
    /**
     * Returns the label with the printer's tension value.
     * 
     * @return the JLabel containing the printer's tension value
     */
    public JLabel getTension() {
    	return tension;
    }
    
    /**
     * Returns the label with the printer's compression value.
     * 
     * @return the JLabel containing the printer's compression value
     */
    public JLabel getCompression() {
    	return compression;
    }
    
    /**
     * Returns the label with the printer's impact value.
     * 
     * @return the JLabel containing the printer's impact value
     */
    public JLabel getImpact() {
    	return impact;
    }
    
    /**
     * Returns the label with the vendor's name.
     * 
     * @return the JLabel containing the vendor's name
     */
    public JLabel getVendor(){
    	return vendor;
    }
    
    /**
     * Returns the label with the printer's range of materials.
     * 
     * @return the JLabel containing the printer's range of materials
     */
    public JLabel getMaterials() {
    	return materials;
    }
    
    /**
     * Returns the label with the printer's tolerance value.
     * 
     * @return the JLabel with the printer's tolerance value
     */
    public JLabel getTolerance() {
    	return tolerance;
    }
    
    /**
     * Returns the label with the printer's finish value.
     * 
     * @return the JLabel containing the printer's finish value
     */
    public JLabel getFinish() {
    	return finish;
    }
}