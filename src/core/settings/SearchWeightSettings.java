package core.settings;

import core.AddPrinterLabel;
import core.MenuWindow;
import core.PrinterList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A settings panel to change search weights
 * 
 * @author  James Quigley, Alireza Bahremand
 * @version 1.0
 * @see     SettingsPanel
 * @see     SettingsFrame
 */
public class SearchWeightSettings extends SettingsPanel
{
    private static final long serialVersionUID = 982727533936225308L;
    // Frame building
    JFrame settingsFrame;
    // Panel Building
    JPanel overAllSettingsPanel;		// Overall panel for adding all other panels with a layout.
    JPanel settingsPanelTopRow;			// Top row panel
    JPanel settingsPanelBottomRow;		// Bottom row panel
    JPanel updateButtonPanel;			// Panel for adding button so we can follow overall panel grid layout
    JPanel resetButtonPanel;            // Panel for adding reset button onto.
    JButton updateWeightButton;			// Actual button to be added to updateButtonPanel.
    JButton resetWeightButton;
    AddPrinterLabel m_Name, m_Tension, m_Impact,
            m_Vendor, m_Tolerance, m_Compression,
            m_Finish, m_Materials;
    /**
     * Creates a "Settings" help panel.
     */
    public SearchWeightSettings()
    {
        setTitle("Search Weights");
        setVisible(true);
        createComponents();
        designComponents();
        addActionListeners();
        addComponents();
    }

    /**
     * Instantiates search weight settings components.
     */
    private void createComponents() {
        settingsFrame = new JFrame();
        // Panel Building
        overAllSettingsPanel = new JPanel();			// Overall panel for adding all other panels with a layout.
        settingsPanelTopRow = new JPanel();			// Top row panel
        settingsPanelBottomRow = new JPanel();		// Bottom row panel
        updateButtonPanel = new JPanel();			// Panel for adding button so we can follow overall panel grid layout
        resetButtonPanel = new JPanel();
        updateWeightButton = new JButton();
        resetWeightButton = new JButton();
        // Instantiating the labels/textfields/combo-boxes
        //m_Name = new AddPrinterLabel("Name", new JTextField());
        m_Compression = new AddPrinterLabel("Compression", new JTextField());
        m_Tension = new AddPrinterLabel("Tension", new JTextField());
        m_Impact = new AddPrinterLabel("Impact", new JTextField());
        //m_Vendor = new AddPrinterLabel("Vendor", new JTextField());
        m_Tolerance = new AddPrinterLabel("Tolerance", new JTextField());
        m_Finish = new AddPrinterLabel("Finish", new JTextField());
        m_Materials = new AddPrinterLabel("Materials", new JTextField());
    }

    /**
     * Sets component values.
     */
    private void designComponents() {
        // Button building
        int frameWidth = (int) (MenuWindow.s_SCREEN_WIDTH*.3);
        int frameHeight = (int) (MenuWindow.s_SCREEN_HEIGHT*.25);

        updateWeightHints();

        updateWeightButton.setText("Update Weighting");
        updateWeightButton.setPreferredSize(new Dimension(145, 25));
        updateWeightButton.setMaximumSize(new Dimension(150, 30));
        updateWeightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateButtonPanel.add(updateWeightButton);

        resetWeightButton.setText("Reset Weighting");
        resetWeightButton.setPreferredSize(new Dimension(150, 25));     // Longer width because of longer text.
        resetWeightButton.setMaximumSize(new Dimension(160, 30));
        resetWeightButton.setAlignmentX(Component.CENTER_ALIGNMENT);     // MAY CHANGE
        resetButtonPanel.add(resetWeightButton);

        setLocation(
                (MenuWindow.s_SCREEN_WIDTH/2) - (frameWidth/2),
                (MenuWindow.s_SCREEN_HEIGHT/2) - (frameHeight/2));
        setPreferredSize(new Dimension(frameWidth,frameHeight));

    }

    /**
     * Displays current search weights in the text field.
     */
    private void updateWeightHints(){
        m_Compression.setM_ComponentText(PrinterList.compressionWeighting + "");
        m_Tension.setM_ComponentText(PrinterList.tensionWeighting + "");
        m_Impact.setM_ComponentText(PrinterList.impactWeighting + "");
        m_Tolerance.setM_ComponentText(PrinterList.toleranceWeighting +"");
        m_Finish.setM_ComponentText(PrinterList.finishWeighting + "");
        m_Materials.setM_ComponentText(PrinterList.materialsWeighting + "");
    }

    /**
     * Adds action listeners to buttons.
     */
    private void addActionListeners() {
        updateWeightButton.addActionListener(new WeightButtonListener());
        resetWeightButton.addActionListener(new ResetWeightButtonListener());
        // After button is clicked  close frame because we have updated values from previous call.
    }

    /**
     * Adds components to the search weights settings panel.
     */
    private void addComponents() {
        // Panel Building for top row panel
        //settingsPanelTopRow.add(m_Name);
        //settingsPanelTopRow.add(m_Vendor);
        settingsPanelTopRow.add(m_Tension);
        settingsPanelTopRow.add(m_Compression);
        settingsPanelTopRow.add(m_Impact);

        // Add parameter last row components to panel
        settingsPanelBottomRow.add(m_Materials);
        settingsPanelBottomRow.add(m_Tolerance);
        settingsPanelBottomRow.add(m_Finish);
        // Overall Panel Building
        overAllSettingsPanel.add(settingsPanelTopRow);
        overAllSettingsPanel.add(settingsPanelBottomRow);
        overAllSettingsPanel.add(updateButtonPanel);
        overAllSettingsPanel.add(resetButtonPanel);
        overAllSettingsPanel.setLayout(new GridLayout(4,1,2,2));
        // Frame building.
        add(overAllSettingsPanel);

    }

    /**
     * A button listener to validate search weight inputs and update search weights.
     *  
     * @author Alireza Bahremand
     *
     */
    private class WeightButtonListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         * @param action
         */
        @Override
        public void actionPerformed(ActionEvent action) {
            String tension = null;
            String compression = null;
            String impact = null;
            String tolerance = null;
            String materials = null;
            String finish = null;
            boolean inputError = false;

            // Setting weight value given for tension and coordinating the index to know which value we're altering for set matches.
            tension = m_Tension.getInput();
            /**
             * There are try catch blocks for each of the input-weight changes because we try to convert input to an integer
             * and store that integer value into the static attribute value. If we cannot perform a successful parsing of string to int
             * that means the input is not a valid integer, therefore we catch the exception as a number format exception
             * and print onto the printerlabel textfield stating we have invalid input so user can see
             * where they went wrong with updating weight values.
             * Boolean error flag is used so we know not to update weight values with previous values if there is an error,
             * if there isn't a error we make use of the serializable implementation of the printer labels.
             */
            if (!(tension.equals(""))) {
                try {
                    PrinterList.tensionWeighting = Integer.parseInt(tension);
                } catch (NumberFormatException n1) {
                    inputError = true;
                    m_Tension.setM_ComponentText("Invalid Input!");
                }
            }

            // CHANGE COMPRESSION WEIGHTING INDEX 1
            compression = m_Compression.getInput();
            if (!(compression.equals(""))) {
                try {
                    PrinterList.compressionWeighting = Integer.parseInt(compression);
                } catch (NumberFormatException n2) {
                    inputError = true;
                    m_Compression.setM_ComponentText("Invalid Input!");
                }
            }
            // CHANGE IMPACT WEIGHTING INDEX 2
            impact = m_Impact.getInput();
            if (!(impact.equals(""))) {
                try {
                    PrinterList.impactWeighting = Integer.parseInt(impact);
                } catch (NumberFormatException n3) {
                    inputError = true;
                    m_Impact.setM_ComponentText("Invalid Input!");
                }
            }

            // CHANGE MATERIALS WEIGHTING INDEX 4
            materials = m_Materials.getInput();
            if (!(materials.equals(""))) {
                try {
                    PrinterList.materialsWeighting = Integer.parseInt(materials);
                } catch (NumberFormatException n4) {
                    inputError = true;
                    m_Materials.setM_ComponentText("Invalid Input!");
                }
            }

            // CHANGE TOLERANCE WEIGHTING INDEX 5
            tolerance = m_Tolerance.getInput();
            if (!(tolerance.equals(""))) {
                try {
                    PrinterList.toleranceWeighting = Integer.parseInt(tolerance);
                } catch (NumberFormatException n5) {
                    inputError = true;
                    m_Tolerance.setM_ComponentText("Invalid Input!");
                }
            }

            // CHANGE FINISH WEIGHTING INDEX 6
            finish = m_Finish.getInput();
            if (!(finish.equals(""))) {
                try {
                    PrinterList.finishWeighting = Integer.parseInt(finish);
                } catch (NumberFormatException n6) {
                    inputError = true;
                    m_Finish.setM_ComponentText("Invalid Input!");
                }
            }

            if (!inputError) {
                updateWeightHints();
            }
        }
    }

    /**
     * Returns search weights to default values.
     * <p>
     * <b>Default values</b>
     * <ul>
     * <li>Tension = 2</li>
     * <li>Compression = 2</li>
     * <li>Impact = 2</li>
     * <li>Tolerance = 1</li>
     * <li>Materials = 1</li>
     * <li>Finish = 1</li>
     * </ul>
     * 
     * @author James Quigley
     *
     */
    private class ResetWeightButtonListener implements ActionListener {

        /**
         * Invoked when an action occurs, resets all weighting back to default values as specified by client.
         * @param action
         */
        @Override
        public void actionPerformed(ActionEvent action) {
            int tension = 2;
            int compression = 2;
            int impact = 2;
            int tolerance = 1;
            int materials = 1;
            int finish = 1;

            // Setting weight value given for tension and coordinating the index to know which value we're altering for set matches.
            PrinterList.tensionWeighting = tension;

            PrinterList.compressionWeighting = compression;

            PrinterList.impactWeighting = impact;

            PrinterList.materialsWeighting = materials;

            PrinterList.toleranceWeighting = tolerance;

            PrinterList.finishWeighting = finish;

            updateWeightHints();
        }
    }
}