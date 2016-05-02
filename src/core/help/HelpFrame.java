package core.help;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import core.MenuWindow;
import core.ToolBox;

/**
 * Creates a pop-up window containing help information for the printer search
 * program.
 * 
 * @author  Marcinina Alvaran
 * @version 1.0
 * @see     HelpPanel
 */
public class HelpFrame extends JFrame
{
    private static final long serialVersionUID = -2836575744299552995L;
    
    private JTabbedPane helpLibrary;
    @SuppressWarnings("unused")
	private ImageIcon logo;

    /**
     * Creates a default help frame
     */
    public HelpFrame()
    {
        setTitle("Help");
        designHelpFrame();
        createHelpLibrary();
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
        frameWidth = (int) (MenuWindow.FRAME_WIDTH*0.85);
        frameHeight = (int) (MenuWindow.FRAME_HEIGHT*0.8);

        // Set up window attributes
        setIconImage(ToolBox.getLogoIcon().getImage());
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
        
        // Add tab for "Vendor Info" tutorial
        helpLibrary.add("Vendor Info", new JScrollPane(new VendorInfoHelp()));
        
        // Add tab for "About" section
        // NOTE: Should always be listed last to stay at end of tabs.
        helpLibrary.add("About", new JScrollPane(new AboutHelp()));
    }
}
