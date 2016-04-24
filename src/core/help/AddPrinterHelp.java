package core.help;

import javax.swing.ImageIcon;

/**
 * A help panel that describes how to add a printer to the database.
 * 
 * @author  Marcinina Alvaran
 * @version 1.0
 * @see     HelpPanel
 * @see     HelpFrame
 */
public class AddPrinterHelp extends HelpPanel
{
    private static final long serialVersionUID = 4606672083412540442L;

    /**
     * Creates an "Add Printer" help panel.
     */
    public AddPrinterHelp()
    {
        setTitle("Adding a Printer to the Database");
        setTutorialText();
    }
    
    /**
     * Adds instructions to panel about how to add a printer to the database.
     */
    protected void setTutorialText()
    {
        String errorReference, errorIconPath,
               iconPath1, iconPath2, iconPath3, iconPath4,
               step1, step2, step3, step4, vendorInfoNote;
        
        // Add step 1 : Click toolbar button
        step1 = "Step 1: Press the \"Add Printer\" button on the upper toolbar.";
        iconPath1 = getIconPath("AddPrinter-S1.png");
        addStep(step1);
        addStep(new ImageIcon(iconPath1));
        addSpacing();
        
        // Add step 2 : Enter information
        step2 = "Step 2: Enter information into the fields.";
        iconPath2 = getIconPath("AddPrinter-S2.png");
        addStep(step2);
        addStep(new ImageIcon(iconPath2));
        addSpacing();
        
        // Add error reference
        errorReference = "Solutions to Error Messages";
        errorIconPath = getIconPath("AddPrinter-Errors.png");
        addStep(errorReference);
        addStep(new ImageIcon(errorIconPath));
        addSpacing();
        
        // Add step 3: (Optional) Add vendor information
        step3 = "Step 3: (Optional) If adding a new vendor, add vendor information.";
        vendorInfoNote =
                "** NOTE: Program must be restarted for new vendor " +
                "to appear in the \"Vendor\" search filter list. **";
        iconPath3 = getIconPath("AddPrinter-S3.png");
        addStep(step3);
        addStep(vendorInfoNote);
        addStep(new ImageIcon(iconPath3));
        addSpacing();
        
        // Add step 4 : Confirm printer addition
        step4 = "Step 4: Ensure printer was successfully added by viewing " +
                "the bottom of the list.";
        iconPath4 = getIconPath("AddPrinter-S4.png");
        addStep(step4);
        addStep(new ImageIcon(iconPath4));
        addSpacing();
    }
}
