package core.help;

import javax.swing.ImageIcon;

/**
 * A help panel that describes how to set weights to search filters in the
 * settings window.
 * 
 * @author  Marcinina Alvaran
 * @version 1.0
 * @see     HelpPanel
 * @see     HelpFrame
 */
public class SearchWeightsHelp extends HelpPanel
{
    public SearchWeightsHelp()
    {
        setTitle("Setting Search Filter Weights");
        setTutorialText();
    }
    
    /**
     * Adds instructions to panel about how to set search filter weights.
     */
    protected void setTutorialText()
    {
        String errorReference, errorIconPath,
               iconPath1, iconPath2,
               step1, step2;
 
        ImageIcon errorIcon, stepIcon1, stepIcon2;
 
        // Add step 1 : Click toolbar button
        step1 = "Step 1: Press the \"Settings\" button on the upper toolbar.";
        iconPath1 = getIconPath("Weights-S1.png");
        stepIcon1 = new ImageIcon(iconPath1);
        addStep(step1);
        addStep(stepIcon1);
        addSpacing();
        
        // Add step 2 : Add search weight numbers
        step2 = "Step 2: Enter integers into search weight fields.";
        iconPath2 = getIconPath("Weights-S2.png");
        stepIcon2 = new ImageIcon(iconPath2);
        addStep(step2);
        addStep(stepIcon2);
        addSpacing();
        
        // TODO: Add error references
    }
}
