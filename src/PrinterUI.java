import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 * A JLabel containing printer information.
 * 
 * @author Joshua Becker
 *
 */
public class PrinterUI extends JLabel implements Serializable
{
	private JLabel name, tension, compression, impact, partComplexity,
	               leadTime, customizable, materials, tolerance, finish;
    private int m_index;
    
    /**
     * Create a label with specified printer information.
     * 
     * @param index           the index number
     * @param frameWidth      the width of the frame
     * @param frameHeight     the height of the frame
     * @param name            the String with the printer's name
     * @param tension         the String with the printer's tension value
     * @param compression     the String with the printer's compression value
     * @param impact          the String with the printer's impact value
     * @param partComplexity  the String with the printer's complexity
     * @param leadTime        the String with the printer's lead time
     * @param customizable    the String with the printer's ease of customization
     * @param materials       the String with the printer's range of materials
     * @param tolerance       the String with the printer's tolerance value
     * @param finish          the String with the printer's finish
     */
    PrinterUI(int index, int frameWidth, int frameHeight, String name, String tension,
    		String compression, String impact, String partComplexity, String leadTime, 
    		String customizable, String materials, String tolerance, String finish)
    {
        m_index = index;
        this.name = new JLabel(name, JTextField.CENTER);
        this.tension = new JLabel(tension, JTextField.CENTER);
        this.compression = new JLabel(compression, JTextField.CENTER);
        this.impact = new JLabel(impact, JTextField.CENTER);
        this.partComplexity = new JLabel(partComplexity, JTextField.CENTER);
        this.leadTime = new JLabel(leadTime, JTextField.CENTER);
        this.customizable = new JLabel(customizable, JTextField.CENTER);
        this.materials = new JLabel(materials, JTextField.CENTER);
        this.tolerance = new JLabel(tolerance, JTextField.CENTER);
        this.finish = new JLabel(finish, JTextField.CENTER);
        
        setPreferredSize(new Dimension(frameWidth-200, 40));
        setMinimumSize(new Dimension(frameWidth-220, 40));
        setMaximumSize(new Dimension(frameWidth-190, 60));
        setLayout(new GridLayout(1,10,2,2));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setOpaque(true);
        // TODO: Commented out to try alternating background colours
        /*
        if(m_index % 2 == 0)
        {
        	setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        }else
        {
        	setBorder(BorderFactory.createLineBorder(Color.black));
        }
        */
        
        if (m_index % 2 == 0)
        	setOpaque(false);
        else
        	setBackground(new Color(215, 215, 215));

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
    PrinterUI(int index, int frameWidth, int frameHeight)
    {
        m_index = index;
        name = new JLabel("default", JTextField.CENTER);
        tension = new JLabel("default", JTextField.CENTER);
        compression = new JLabel("default", JTextField.CENTER);
        impact = new JLabel("default", JTextField.CENTER);
        partComplexity = new JLabel("default", JTextField.CENTER);
        leadTime = new JLabel("default", JTextField.CENTER);
        customizable = new JLabel("default", JTextField.CENTER);
        materials = new JLabel("Default", JTextField.CENTER);
        tolerance = new JLabel("default", JTextField.CENTER);
        finish = new JLabel("default", JTextField.CENTER);
        
        setPreferredSize(new Dimension(frameWidth-200, 30));
        setMinimumSize(new Dimension(frameWidth-220, 30));
        setMaximumSize(new Dimension(frameWidth-190, 30));
        setLayout(new GridLayout(1,9,2,2));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        if(m_index % 2 == 0)
        {
        	setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        }else
        {
        	setBorder(BorderFactory.createLineBorder(Color.black));
        }

        designComponents();
        addComponents();
    }
    
    /**
     * Centers printer information labels on X-axis.
     */
    private void designComponents()
    {
    	this.name.setAlignmentX(Component.CENTER_ALIGNMENT);
        tension.setAlignmentX(Component.CENTER_ALIGNMENT);
        compression.setAlignmentX(Component.CENTER_ALIGNMENT); // <---- Refactor comprestion --> compression 
        impact.setAlignmentX(Component.CENTER_ALIGNMENT);
        partComplexity.setAlignmentX(Component.CENTER_ALIGNMENT);
        leadTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        customizable.setAlignmentX(Component.CENTER_ALIGNMENT);
        materials.setAlignmentX(Component.CENTER_ALIGNMENT);
        tolerance.setAlignmentX(Component.CENTER_ALIGNMENT);
        finish.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        name.setToolTipText(name.getText());
    }
    
    /**
     * Adds printer information labels to printerUI label.
     */
    private void addComponents()
    {
        add(name);
        add(tension);
        add(compression);
        add(impact);
        add(partComplexity);
        add(leadTime);
        add(customizable);
        add(materials);
        add(tolerance);
        add(finish);
        
    }
    
    public JLabel getPrinterName() {
    	return name;
    }
    
    public JLabel getTension() {
    	return tension;
    }
    
    public JLabel getCompression() {
    	return compression;
    }
    
    public JLabel getImpact() {
    	return impact;
    }
    
    public JLabel getPartComplexity() {
    	return partComplexity;
    }
    
    public JLabel getLeadTime() {
    	return leadTime;
    }
    
    public JLabel getCustomizable() {
    	return customizable;
    }
    
    public JLabel getMaterials() {
    	return materials;
    }
    
    public JLabel getTolerance() {
    	return tolerance;
    }
    
    public JLabel getFinish() {
    	return finish;
    }
}