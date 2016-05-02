package core.settings;

import core.AddPrinterLabel;
import core.MenuWindow;
import core.PrinterList;
import core.SearchResultPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A settings panel to change search weights
 * 
 * @author  James Quigley
 * @version 1.0
 * @see     SettingsPanel
 * @see     SettingsFrame
 */
public class HighlightColorSettings extends SettingsPanel
{
    private static final long serialVersionUID = 982727533936225308L;
    private JColorChooser colorChooser;
    /**
     * Creates a "Settings" help panel.
     */
    public HighlightColorSettings()
    {
        setTitle("Search Highlight Color");
        colorChooser = new JColorChooser();
        colorChooser.getSelectionModel().addChangeListener(new ColorChangeListener());
        add(colorChooser);
        setVisible(true);
    }

    /**
     * A color change listener that adjusts search filter match
     * highlighting within the program.
     * 
     * @author James Quigley
     *
     */
    private class ColorChangeListener implements ChangeListener{
        public void stateChanged(ChangeEvent e) {
            Color newColor = colorChooser.getColor();
            SearchResultPanel.highlight = newColor;
        }
    }
}
