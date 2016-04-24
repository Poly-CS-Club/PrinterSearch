package core.help;

import javax.swing.ImageIcon;

/**
 * A help panel that describes how to perform searches on the printer database.
 * 
 * @author  Marcinina Alvaran
 * @version 1.0
 * @see     HelpPanel
 * @see     HelpFrame
 */
public class SearchHelp extends HelpPanel
{
    private static final long serialVersionUID = 982727533936225308L;

    /**
     * Creates a "Search" help panel.
     */
    public SearchHelp()
    {
        setTitle("Performing a Search");
        setTutorialText();
    }
    
    /**
     * Adds instructions to panel about how to perform a search.
     */
    protected void setTutorialText()
    {
        String filterIconPath, reviewIconPath,
               filterStep, reviewStep1, reviewStep2;
        
        // Add filter step
        filterStep = "Filter currently databased printers.";
        filterIconPath = getIconPath("Search-S1.png");
        addStep(filterStep);
        addStep(new ImageIcon(filterIconPath));
        addSpacing();
        
        // Add review results step
        reviewStep1 = "Review results";
        reviewStep2 = "(Best matches on top of the list, Filter matches denoted in green)";
        reviewIconPath = getIconPath("Search-S2.png");
        addStep(reviewStep1);
        addStep(reviewStep2);
        addStep(new ImageIcon(reviewIconPath));
        addSpacing();
    }

}
