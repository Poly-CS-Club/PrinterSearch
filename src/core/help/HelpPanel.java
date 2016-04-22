package core.help;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.util.Arrays;

/**
 * Base panel for help window tutorial panels.
 * 
 * @author  Marcinina Alvaran
 * @version 1.0
 * @see     HelpFrame
 */
public abstract class HelpPanel extends JPanel
{
    private static final long serialVersionUID = -8567384508791776256L;
    
    protected JLabel title;
    protected JTextPane tutorialText;
    
    protected final Font DEFAULT_STEP_FONT = new Font(null, 1, 18);
    
    /**
     * Creates a default help panel with one tutorial step.
     */
    public HelpPanel()
    {
        // Initialize title
        title = new JLabel("Help Panel", SwingConstants.CENTER);
        title.setFont(new Font(null, 1, 22));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setVisible(true);
        
        // Initialize panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(SwingConstants.CENTER);
        
        // Add spacing to accentuate header
        addSpacing();
        add(title);
        addSpacing();
        addSpacing();
        setVisible(true);
    }
    
    /**
     * Adds step-by-step tutorial to help panel.
     */
    protected abstract void setTutorialText();
    
    /**
     * Adds step spacing (i.e. an empty line).
     */
    protected void addSpacing()
    {
        JPanel spacing = new JPanel();
        
        // Create spacing component
        spacing.setLayout(new BoxLayout(spacing, BoxLayout.Y_AXIS));
        spacing.setAlignmentX(CENTER_ALIGNMENT);
        spacing.add(new JLabel("\n"));
        spacing.setVisible(true);
        
        add(spacing);
    }
    
    /**
     * Adds an image tutorial step to the tutorial panel.
     * 
     * @param icon the ImageIcon with the tutorial image
     */
    protected void addStep(ImageIcon icon)
    {
        JPanel tutorialStep = new JPanel();
        
        // Create tutorial step component
        tutorialStep.setLayout(new BoxLayout(tutorialStep, BoxLayout.Y_AXIS));
        tutorialStep.setAlignmentX(CENTER_ALIGNMENT);
        tutorialStep.add(new JLabel(icon));
        tutorialStep.add(new JLabel("\n"));
        tutorialStep.setVisible(true);
        
        add(tutorialStep);
    }
    
    /**
     * Adds a text tutorial step to the tutorial panel.
     * 
     * @param text the String with the tutorial step text
     */
    protected void addStep(String text)
    {
        JLabel stepLabel = new JLabel(text, SwingConstants.CENTER);
        JPanel tutorialStep = new JPanel();
        
        // Create tutorial step component
        tutorialStep.setLayout(new BoxLayout(tutorialStep, BoxLayout.Y_AXIS));
        tutorialStep.setAlignmentX(CENTER_ALIGNMENT);
        stepLabel.setFont(DEFAULT_STEP_FONT);
        tutorialStep.add(stepLabel, SwingConstants.CENTER);
        tutorialStep.add(new JLabel("\n"));
        tutorialStep.setVisible(true);
        
        add(tutorialStep);
    }
    
    /**
     * Returns the appropriate icon path depending on the user's
     * operating system
     * 
     * @param fileName the String with the icon's file name
     * @return the String containing the icon's path
     */
    public String getIconPath(String fileName)
    {
        String operatingSystem = System.getProperty("os.name"),
               keyword = "Mac";
        boolean checkMac;
        
        // Check if user's operating system contains "Mac"
        checkMac = Arrays.asList(operatingSystem.split(" ")).contains(keyword);
        if (checkMac)
            return "src\\core\\help\\images\\" + fileName;
        else
            return "src/core/help/images/" + fileName;
    }
    
    /**
     * Sets the panel's title to specified string.
     * 
     * @param title the String with the new title
     */
    public void setTitle(String title){
        this.title.setText(title);
    }
    
    /**
     * Returns the panel's title.
     * 
     * @return the String with the panel's title
     */
    public String getTitle() {
        return title.getText();
    }
}
