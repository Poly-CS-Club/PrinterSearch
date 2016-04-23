package core.help;

import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import core.MenuWindow;

/**
 * Creates a pop-up window containing help information for the printer search
 * program.
 * 
 * @author Marcinina Alvaran
 * @version 1.0
 */
public class HelpFrame extends JFrame
{
    private static final long serialVersionUID = -2836575744299552995L;
    
    private JTabbedPane helpLibrary;
    private ImageIcon logo;

    /**
     * Creates a default help frame
     */
    public HelpFrame()
    {
        setTitle("Help");
        designHelpFrame();
        createHelpLibrary();
        logo = new ImageIcon(getLogoPath("sift-logo-color.png"));
        setIconImage(logo.getImage());
        add(helpLibrary);
        helpLibrary.setVisible(true);
        pack();
    }

    /**
     * Initializes the help window.
     */
    private void designHelpFrame()
    {
        int frameWidth, frameHeight;

        // Determine window dimensions
        frameWidth = (int) (MenuWindow.FRAME_WIDTH*0.65);
        frameHeight = (int) (MenuWindow.FRAME_HEIGHT*0.8);

        // Set up window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        pack();
        setResizable(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    /**
     * Fills library with help information panels.
     */
    private void createHelpLibrary()
    {
        helpLibrary = new JTabbedPane();
        
        // Add tab for "Add Printer" tutorial
        helpLibrary.addTab("Add Printer", new JScrollPane(new AddPrinterHelp()));
        
        // Add tab for "Search" tutorial
        helpLibrary.add("Searching", new JScrollPane(new SearchHelp()));
        
        // Add tab for "Search Weights" tutorial
        helpLibrary.add("Search Weights", new JScrollPane(new SearchWeightsHelp()));
        
        // Add tab for "Printing" tutorial
        helpLibrary.add("Printing", new JScrollPane(new PrintResultsHelp()));
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
        String operatingSystem = System.getProperty("os.name"),
               keyword = "Mac";
        boolean checkMac;
        
        // Check if user's operating system contains "Mac"
        checkMac = Arrays.asList(operatingSystem.split(" ")).contains(keyword);
        if (checkMac)
            return "src\\" + fileName;
        else
            return "src/" + fileName;
    }
}
