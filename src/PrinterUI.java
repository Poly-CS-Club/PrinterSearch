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
	private JLabel name, tenstion, comprestion, impact, partComplexity, leadTime, EOC, ROM, tolerance, finish;
    private int m_index;
    
    /**
     * Create a label with specified printer information.
     * 
     * @param index           the index number
     * @param frameWidth      the width of the frame
     * @param frameHeight     the height of the frame
     * @param name            the String with the printer's name
     * @param tenstion        the String with the printer's tension value
     * @param comprestion     the String with the printer's compression value
     * @param impact          the String with the printer's impact value
     * @param partComplexity  the String with the printer's complexity
     * @param leadTime        the String with the printer's lead time
     * @param EOC             the String with the printer's ease of customization
     * @param ROM             the String with the printer's range of materials
     * @param tolerance       the String with the printer's tolerance value
     * @param finish          the String with the printer's finish
     */
    PrinterUI(int index, int frameWidth, int frameHeight, String name, String tenstion, String comprestion, String impact, String partComplexity, 
    		String leadTime, String EOC, String ROM, String tolerance, String finish)
    {
        m_index = index;
        this.name = new JLabel(name);
        this.tenstion = new JLabel(tenstion);
        this.comprestion = new JLabel(comprestion);
        this.impact = new JLabel(impact);
        this.partComplexity = new JLabel(partComplexity);
        this.leadTime = new JLabel(leadTime);
        this.EOC = new JLabel(EOC);
        this.ROM = new JLabel(ROM);
        this.tolerance = new JLabel(tolerance);
        this.finish = new JLabel(finish);
        
        setPreferredSize(new Dimension(frameWidth-200, 25));
        setMinimumSize(new Dimension(frameWidth-220, 25));
        setMaximumSize(new Dimension(frameWidth-190, 25));
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
        this.name = new JLabel("default");
        this.tenstion = new JLabel("default");
        this.comprestion = new JLabel("default");
        this.impact = new JLabel("default");
        this.partComplexity = new JLabel("default");
        this.leadTime = new JLabel("default");
        this.EOC = new JLabel("default");
        this.ROM = new JLabel("Default");
        this.tolerance = new JLabel("default");
        this.finish = new JLabel("default");
        
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
        this.tenstion.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.comprestion.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.impact.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.partComplexity.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.leadTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.EOC.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.ROM.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.tolerance.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.finish.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    
    /**
     * Adds printer information labels to printerUI label.
     */
    private void addComponents()
    {
        add(this.name);
        add(this.tenstion);
        add(this.comprestion);
        add(this.impact);
        add(this.partComplexity);
        add(this.leadTime);
        add(this.EOC);
        add(this.ROM);
        add(this.tolerance);
        add(this.finish);
        
    }
}