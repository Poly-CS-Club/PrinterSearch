package core.settings;

import core.MenuWindow;
import core.ToolBox;
import javax.swing.*;
import java.awt.*;

/**
 * Creates a pop-up window containing settings
 * program.
 * 
 * @author James Quigley
 * @version 1.0
 */
public class SettingsFrame extends JFrame
{
    private static final long serialVersionUID = -2836575744299552995L;

    private JTabbedPane settingsLibrary;
    private ImageIcon logo;

    /**
     * Creates a default settings frame
     */
    public SettingsFrame()
    {
        setTitle("Settings");
        designSettingsFrame();
        createSettingsLibrary();
        logo = new ImageIcon(getLogoPath("sif" + "t-logo-color.png"));
        setIconImage(logo.getImage());
        add(settingsLibrary);
        settingsLibrary.setVisible(true);
        pack();
    }

    /**
     * Initializes the settings window.
     */
    private void designSettingsFrame()
    {
        int frameWidth, frameHeight;

        // Determine window dimensions
        frameWidth = (int) (MenuWindow.FRAME_WIDTH*0.65);
        frameHeight = (int) (MenuWindow.FRAME_HEIGHT*0.6);

        // Set up window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        pack();
        setResizable(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    /**
     * Fills library with settings panels.
     */
    private void createSettingsLibrary()
    {
        settingsLibrary = new JTabbedPane();
        
        // Add tab for changing search weights
        settingsLibrary.addTab("Search Weights", new JScrollPane(new SearchWeightSettings()));

        //Add tab for changing search highlight color
        settingsLibrary.addTab("Highlight Color", new JScrollPane(new HighlightColorSettings()));
    }
    
    /**
     * Returns the appropriate logo icon path depending on the user's
     * operating system
     * 
     * @param fileName the String with the logo's file name
     * @return the String containing the logo's path
     */
    public String getLogoPath(String fileName)
    {
        boolean checkMac = ToolBox.isMacOS();
        if (checkMac)
            return "src\\" + fileName;
        else
            return "src/" + fileName;
    }
}
