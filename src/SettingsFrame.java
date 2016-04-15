import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
		m_Name = new AddPrinterLabel("Name", new JTextField());
		m_Compression = new AddPrinterLabel("Compression", new JTextField());
		m_Tension = new AddPrinterLabel("Tension", new JTextField());
		m_Impact = new AddPrinterLabel("Impact", new JTextField());
		m_Vendor = new AddPrinterLabel("Vendor", new JTextField());
		m_Tolerance = new AddPrinterLabel("Tolerance", new JTextField());
		m_Finish = new AddPrinterLabel("Finish", new JTextField());
		m_Materials = new AddPrinterLabel("Materials", new JComboBox<String>(ToolBox.getMaterialList()));
		
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
	private void addActionListeners()
	{
		
	}
	private void addComponents() {
		// Panel Building for top row panel
		settingsPanelTopRow.add(m_Name);
		settingsPanelTopRow.add(m_Vendor);
		settingsPanelTopRow.add(m_Tension);
		settingsPanelTopRow.add(m_Compression);

		// Add parameter last row components to panel
		settingsPanelBottomRow.add(m_Impact);
		settingsPanelBottomRow.add(m_Materials);
		settingsPanelBottomRow.add(m_Tolerance);
		settingsPanelBottomRow.add(m_Finish);
		// Overall Panel Building
		overAllSettingsPanel.add(settingsPanelTopRow);
		overAllSettingsPanel.add(settingsPanelBottomRow);
		overAllSettingsPanel.add(updateButtonPanel);
		overAllSettingsPanel.setLayout(new GridLayout(3,1,1,1));
		// Frame building.
		add(overAllSettingsPanel);
		
	}
}