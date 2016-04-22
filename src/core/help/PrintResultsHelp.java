package core.help;

import javax.swing.ImageIcon;

/**
 * A help panel that describes how to print search results.
 * 
 * @author  Marcinina Alvaran
 * @version 1.0
 * @see     HelpPanel
 * @see     HelpFrame
 */
public class PrintResultsHelp extends HelpPanel
{
    /**
     * Creates a "Print Results" help panel.
     */
    public PrintResultsHelp()
    {
        setTitle("Printing Search Results");
        setTutorialText();
        setVisible(true);
    }

    /**
     * Adds instructions to panel about how to print search results.
     */
    protected void setTutorialText()
    {
        String iconPath1, step1;
        ImageIcon stepIcon1;
        
        // Add step 1 : Click toolbar button
        step1 = "Open the print menu by pressing “Export” on the upper toolbar.";
        iconPath1 = getIconPath("PrintResults-S1.png");
        stepIcon1 = new ImageIcon(iconPath1);
        addStep(step1);
        addStep(stepIcon1);
        addSpacing();
    }

}
