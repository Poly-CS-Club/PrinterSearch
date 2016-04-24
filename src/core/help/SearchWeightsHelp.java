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
    private static final long serialVersionUID = 1325524297338942097L;

    /**
     * Creates a "Search Weights" help panel.
     */
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
               iconPath1, iconPath2A, iconPath2B,
               step1, step2A, step2B;
 
        // Add step 1 : Click toolbar button
        step1 = "Step 1: Press the \"Settings\" button on the upper toolbar.";
        iconPath1 = getIconPath("Weights-S1.png");
        addStep(step1);
        addStep(new ImageIcon(iconPath1));
        addSpacing();
        
        // Add option for general search weight changes
        step2A = "Option 2-A: General search weight changes";
        iconPath2A = getIconPath("Weights-S2A.png");
        addStep(step2A);
        addStep(new ImageIcon(iconPath2A));
        addSpacing();
        
        // Add option for parameter exclusion
        step2B = "Option 2-B: Excluding individual parameters";
        iconPath2B = getIconPath("Weights-S2B.png");
        addStep(step2B);
        addStep(new ImageIcon(iconPath2B));
        addSpacing();
        
        // Add error reference
        errorReference = "Solutions to Error Message";
        errorIconPath = getIconPath("Weights-Error.png");
        addStep(errorReference);
        addStep(new ImageIcon(errorIconPath));
        addSpacing();
    }
}
