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
        setVisible(true);
    }
    
    /**
     * Adds instructions to panel about how to add a printer to the database.
     */
    protected void setTutorialText()
    {
        String errorReference, errorIconPath,
               iconPath1, iconPath2, iconPath3,
               step1, step2, step3;
        
        ImageIcon errorIcon, stepIcon1, stepIcon2, stepIcon3;
        
        // Add step 1 : Click toolbar button
        step1 = "Step 1: Press the \"Add Printer\" button on the upper toolbar.";
        iconPath1 = getIconPath("AddPrinter-S1.png");
        stepIcon1 = new ImageIcon(iconPath1);
        addStep(step1);
        addStep(stepIcon1);
        addSpacing();
        
        // Add step 2 : Enter information
        step2 = "Step 2: Enter information into the fields.";
        iconPath2 = getIconPath("AddPrinter-S2.png");
        stepIcon2 = new ImageIcon(iconPath2);
        addStep(step2);
        addStep(stepIcon2);
        addSpacing();
        
        // Add error reference
        errorReference = "Solutions to Error Messages";
        errorIconPath = getIconPath("AddPrinter-Errors.png");
        errorIcon = new ImageIcon(errorIconPath);
        addStep(errorReference);
        addStep(errorIcon);
        addSpacing();
        
        // Add step 3 : Confirm printer addition
        step3 = "Step 3: Ensure printer was successfully added by viewing the list.";
        iconPath3 = getIconPath("AddPrinter-S3.png");
        stepIcon3 = new ImageIcon(iconPath3);
        addStep(step3);
        addStep(stepIcon3);
        addSpacing();
    }
}
