package core.help;

import core.ToolBox;

/**
 * An "About" panel containing release information and credits.
 * 
 * @author  Marcinina Alvaran
 * @version 1.0
 */
public class AboutHelp extends HelpPanel
{
    private static final long serialVersionUID = -1333809010116444692L;

    /**
     * Creates an "About" help panel.
     */
    public AboutHelp()
    {
        setTitle("About");
        setTutorialText();
    }
    
    /**
     * Adds release information and credits to help panel.
     */
    protected void setTutorialText()
    {
        // Add logo
        addStep(ToolBox.getLogoIcon());
        
        // Add general Information
        addBody("Version: 1.0");
        addBody("Polytechnic Computer Science Club");
        addBody("Arizona State University");
        addSpacing();
        addSpacing();
        
        // Add credits
        addStep("Credits");
        addBody("Klariza Alvran (Logo)");
        addBody("Marcinina Alvaran");
        addBody("Alireza Bahremand");
        addBody("Joshua Becker");
        addBody("Trevor Forrey");
        addBody("Jacob Leonard");
        addBody("James Quigley");
    }

}
