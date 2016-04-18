import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Settings pop-up window for setting filter weights
 *
 * @author Alireza Bahremand, Joshua Becker
 *
 */
public class SettingsFrame extends JFrame
{
	// Frame building
	JFrame settingsFrame;
	// Panel Building
	JPanel overAllSettingsPanel;			// Overall panel for adding all other panels with a layout.
	JPanel settingsPanelTopRow;			// Top row panel
	JPanel settingsPanelBottomRow;		// Bottom row panel
	JPanel updateButtonPanel;			// Panel for adding button so we can follow overall panel grid layout
	JButton updateWeightButton;			// Actual button to be added to updateButtonPanel.

	// Creation of our labels/textfields/combo-boxes
	AddPrinterLabel m_Name, m_Tension, m_Impact,
			m_Vendor, m_Tolerance, m_Compression,
			m_Finish, m_Materials;
	SettingsFrame()
	{
		createComponents();
		desingComponents();
		addActionListeners();
		addComponents();
	}


	private void createComponents() {
		settingsFrame = new JFrame();
		// Panel Building
		overAllSettingsPanel = new JPanel();			// Overall panel for adding all other panels with a layout.
		settingsPanelTopRow = new JPanel();			// Top row panel
		settingsPanelBottomRow = new JPanel();		// Bottom row panel
		updateButtonPanel = new JPanel();			// Panel for adding button so we can follow overall panel grid layout
		updateWeightButton = new JButton();
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

	private void desingComponents() {
		// Button building
		int frameWidth = (int) (MenuWindow.s_SCREEN_WIDTH*.3);
		int frameHeight = (int) (MenuWindow.s_SCREEN_HEIGHT*.25);


		updateWeightButton.setText("Update Weighting");
		updateWeightButton.setPreferredSize(new Dimension(145, 25));
		updateWeightButton.setMaximumSize(new Dimension(150, 30));
		updateWeightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		updateButtonPanel.add(updateWeightButton);

		setTitle("Welcome To Settings");
		setLocation(
				(MenuWindow.s_SCREEN_WIDTH/2) - (frameWidth/2),
				(MenuWindow.s_SCREEN_HEIGHT/2) - (frameHeight/2));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(frameWidth,frameHeight));

	}
	private void addActionListeners() {
		updateWeightButton.addActionListener(new WeightButtonListener());
		// After button is clicked  close frame because we have updated values from previous call.
	}

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
		overAllSettingsPanel.setLayout(new GridLayout(3,1,2,2));
		// Frame building.
		add(overAllSettingsPanel);

	}

	public void CloseFrame(){
		super.dispose();
	}

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

			// Setting weight value given for tension and coordinating the index to know which value we're altering for set matches.
			tension = m_Tension.getInput();
			if (!(tension.equals(""))) {
				//if (tension != "") {
				PrinterList.tensionWeighting = Integer.parseInt(tension);
				//PrinterList.weightToChange = 0;
			}

			// CHANGE COMPRESSION WEIGHTING INDEX 1
			compression = m_Compression.getInput();
			if (!(compression.equals(""))) {
				//if (compression != "") {
				PrinterList.compressionWeighting = Integer.parseInt(compression);
				//PrinterList.weightToChange = 1;
			}
			// CHANGE IMPACT WEIGHTING INDEX 2
			impact = m_Impact.getInput();
			if (!(impact.equals(""))) {
				//if (impact != "") {
				PrinterList.impactWeighting = Integer.parseInt(impact);
				//PrinterList.weightToChange = 2;
			}

			// CHANGE MATERIALS WEIGHTING INDEX 4
			materials = m_Materials.getInput();
			if (!(materials.equals(""))) {
				//if (materials != "") {
				PrinterList.materialsWeighting = Integer.parseInt(materials);
				//PrinterList.weightToChange = 4;
			}

			// CHANGE TOLERANCE WEIGHTING INDEX 5
			tolerance = m_Tolerance.getInput();
			if (!(tolerance.equals(""))) {
				//if (tolerance != "") {
				PrinterList.toleranceWeighting = Integer.parseInt(tolerance);
				//PrinterList.weightToChange = 5;
			}

			// CHANGE FINISH WEIGHTING INDEX 6
			finish = m_Finish.getInput();
			if (!(finish.equals(""))) {
				//if (finish != "") {
				PrinterList.finishWeighting = Integer.parseInt(finish);
				//PrinterList.weightToChange = 6;
			}

			setVisible(false);
			dispose();
		}
	}

}