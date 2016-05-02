package core.help;

import javax.swing.ImageIcon;

/**
 * A help panel that describes how to access vendor information.
 * 
 * @author  Marcinina Alvaran
 * @version 1.0
 */
public class VendorInfoHelp extends HelpPanel
{
    /**
     * Creates a "Vendor Info" help panel.
     */
    public VendorInfoHelp()
    {
        setTitle("Vendor Info");
        setTutorialText();
    }
    
    /**
     * Adds instructions to the panel about how to view vendor information.
     */
    protected void setTutorialText()
    {
        String iconPath1, iconPath2, step1, step2A, step2B, note;
        
        // Program restart information
        note = "NOTE: New vendors will not be displayed in the filter list until " +
               "the program is restarted.";
        addStep(note);
        addSpacing();
        
        // Add step 1: Click vendor name
        step1 = "Click on the vendor name in the list of printers.";
        iconPath1 = getIconPath("Vendor-S1.png");
        addStep(step1);
        addStep(new ImageIcon(iconPath1));
        addSpacing();
        
        // Add step 2: Review information
        step2A = "Review vendor information in the information box.";
        step2B = "NOTE: Moving the mouse will cause the window to disappear.";
        iconPath2 = getIconPath("Vendor-S2.png");
        addStep(step2A);
        addStep(step2B);
        addStep(new ImageIcon(iconPath2));
        addSpacing();
    }

}
