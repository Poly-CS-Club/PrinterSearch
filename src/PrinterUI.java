import java.awt.*;
import javax.swing.*;
import java.io.*;

public class PrinterUI extends JLabel implements Serializable
{
	private JLabel name, tenstion, comprestion, impact, partComplexity, leadTime, EOC, ROM, tolerance, finish;
    private int m_index;
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
        this.tolerance = new JLabel(tolerance);
        this.finish = new JLabel(finish);
        
        setPreferredSize(new Dimension(frameWidth-200, 25));
        setMinimumSize(new Dimension(frameWidth-220, 25));
        setMaximumSize(new Dimension(frameWidth-190, 25));
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
    private void designComponents()
    {
    	this.name.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.tenstion.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.comprestion.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.impact.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.partComplexity.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.leadTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.EOC.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.tolerance.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.finish.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    private void addComponents()
    {
        add(this.name);
        add(this.tenstion);
        add(this.comprestion);
        add(this.impact);
        add(this.partComplexity);
        add(this.leadTime);
        add(this.EOC);
        add(this.tolerance);
        add(this.finish);
        
    }
}