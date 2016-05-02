package core.settings;

import core.ToolBox;
import javax.swing.*;
import java.awt.*;

/**
 * Base panel for settings window panels
 * 
 * @author  James Quigley
 * @version 1.0
 * @see     SettingsFrame
 */
public abstract class SettingsPanel extends JPanel
{
    private static final long serialVersionUID = -8567384508791776256L;

    protected JLabel title;
    protected JTextPane tutorialText;

    protected final Font DEFAULT_STEP_FONT = new Font(null, 1, 18);

    /**
     * Creates a default settings panel.
     */
    public SettingsPanel()
    {
        // Initialize title
        title = new JLabel("Settings Panel", SwingConstants.CENTER);
        title.setFont(new Font(null, 1, 22));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setVisible(true);
        
        // Initialize panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(SwingConstants.CENTER);
        
        // Add spacing to accentuate header
        add(title);

        setVisible(true);
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
        // Check if user's operating system contains "Mac"
        boolean checkMac = ToolBox.isMacOS();
        if (checkMac)
            return "src\\core\\settings\\images\\" + fileName;
        else
            return "src/core/settings/images/" + fileName;
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
