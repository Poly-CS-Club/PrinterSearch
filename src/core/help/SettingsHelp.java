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
public class SettingsHelp extends HelpPanel
{
    public SettingsHelp()
    {
        setTitle("Setting Search Filter Weights");
        setTutorialText();
        setVisible(true);
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
        iconPath1 = getIconPath("Settings-S1.png");
        stepIcon1 = new ImageIcon(iconPath1);
        addStep(step1);
        addStep(stepIcon1);
        addSpacing();
        
        // TODO: Add step 2 (add weight numbers)
        
        // TODO: Add error references
    }
}
