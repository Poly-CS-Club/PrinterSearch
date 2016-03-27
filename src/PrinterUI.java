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
	private JLabel name, tension, comprestion, impact, partComplexity,
	               leadTime, easeOfCustomization, rangeOfMaterials, tolerance, finish;
    private int m_index;
    
    /**
     * Create a label with specified printer information.
     * 
     * @param index                the index number
     * @param frameWidth           the width of the frame
     * @param frameHeight          the height of the frame
     * @param name                 the String with the printer's name
     * @param tenstion             the String with the printer's tension value
     * @param comprestion          the String with the printer's compression value
     * @param impact               the String with the printer's impact value
     * @param partComplexity       the String with the printer's complexity
     * @param leadTime             the String with the printer's lead time
     * @param easeOfCustomization  the String with the printer's ease of customization
     * @param rangeOfMaterials     the String with the printer's range of materials
     * @param tolerance            the String with the printer's tolerance value
     * @param finish               the String with the printer's finish
     */
    PrinterUI(int index, int frameWidth, int frameHeight, String name, String tenstion,
    		String comprestion, String impact, String partComplexity, String leadTime, 
    		String easeOfCustomization, String rangeOfMaterials, String tolerance, String finish)
    {
        m_index = index;
        this.name = new JLabel(name, JTextField.CENTER);
        this.tension = new JLabel(tenstion, JTextField.CENTER);
        this.comprestion = new JLabel(comprestion, JTextField.CENTER);
        this.impact = new JLabel(impact, JTextField.CENTER);
        this.partComplexity = new JLabel(partComplexity, JTextField.CENTER);
        this.leadTime = new JLabel(leadTime, JTextField.CENTER);
        this.easeOfCustomization = new JLabel(easeOfCustomization, JTextField.CENTER);
        this.rangeOfMaterials = new JLabel(rangeOfMaterials, JTextField.CENTER);
        this.tolerance = new JLabel(tolerance, JTextField.CENTER);
        this.finish = new JLabel(finish, JTextField.CENTER);
        
        setPreferredSize(new Dimension(frameWidth-200, 40));
        setMinimumSize(new Dimension(frameWidth-220, 40));
        setMaximumSize(new Dimension(frameWidth-190, 60));
        setLayout(new GridLayout(1,10,2,2));
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
        comprestion = new JLabel("default", JTextField.CENTER);
        impact = new JLabel("default", JTextField.CENTER);
        partComplexity = new JLabel("default", JTextField.CENTER);
        leadTime = new JLabel("default", JTextField.CENTER);
        easeOfCustomization = new JLabel("default", JTextField.CENTER);
        rangeOfMaterials = new JLabel("Default", JTextField.CENTER);
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
        comprestion.setAlignmentX(Component.CENTER_ALIGNMENT); // <---- Refactor comprestion --> compression 
        impact.setAlignmentX(Component.CENTER_ALIGNMENT);
        partComplexity.setAlignmentX(Component.CENTER_ALIGNMENT);
        leadTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        easeOfCustomization.setAlignmentX(Component.CENTER_ALIGNMENT);
        rangeOfMaterials.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        add(comprestion);
        add(impact);
        add(partComplexity);
        add(leadTime);
        add(easeOfCustomization);
        add(rangeOfMaterials);
        add(tolerance);
        add(finish);
        
    }
    
    public JLabel getPartComplexity() {
    	return partComplexity;
    }
    
    public JLabel getEaseOfCustomization() {
    	return easeOfCustomization;
    }
    
    public JLabel getRangeOfMaterials() {
    	return rangeOfMaterials;
    }
}