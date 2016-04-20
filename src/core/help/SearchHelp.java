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
    /**
     * Creates a "Search" help panel.
     */
    public SearchHelp()
    {
        setTitle("Performing a Search");
        setTutorialText();
        setVisible(true);
    }
    
    /**
     * Adds instructions to panel about how to perform a search.
     */
    protected void setTutorialText()
    {
        String filterIconPath, reviewIconPath,
               filterStep, reviewStep;
        
        ImageIcon filterIcon, reviewIcon;
        
        // Add filter step
        filterStep = "Filter currently databased printers.";
        filterIconPath = getIconPath("Search-S1.png");
        filterIcon = new ImageIcon(filterIconPath);
        addStep(filterStep, filterIcon);
        
        // Add review results step
        reviewStep = "Review results\n" +
                "(Best matches on top of the list, Filter matches denoted in green)";
        reviewIconPath = getIconPath("Search-S2.png");
        reviewIcon = new ImageIcon(reviewIconPath);
        addStep(reviewStep, reviewIcon);
    }

}
