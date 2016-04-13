import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class SettingsFrame extends JFrame
{
	// Frame building
	JFrame settingsFrame = new JFrame();
	// Panel Building
	JPanel overAllSettingsPanel = new JPanel();			// Overall panel for adding all other panels with a layout.
	JPanel settingsPanelTopRow = new JPanel();			// Top row panel
	JPanel settingsPanelBottomRow = new JPanel();		// Bottom row panel
	JPanel updateButtonPanel = new JPanel();			// Panel for adding button so we can follow overall panel grid layout
	JButton updateWeightButton = new JButton();			// Actual button to be added to updateButtonPanel.

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

	private void desingComponents() {
		// Button building
		updateWeightButton.setText("Update Weighting");
		updateWeightButton.setPreferredSize(new Dimension(150, 25));
		updateWeightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		updateButtonPanel.add(updateWeightButton);
		
		setTitle("Welcome To Settings");
		this.setLocationRelativeTo(null);			// Center it.
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
	}

	private void createComponents() {
		// Instantiating the labels/textfields/combo-boxes
		m_Name = new AddPrinterLabel("Name", new JTextField());
		m_Compression = new AddPrinterLabel("Compression", new JTextField());
		m_Tension = new AddPrinterLabel("Tension", new JTextField());
		m_Impact = new AddPrinterLabel("Impact", new JTextField());
		m_Vendor = new AddPrinterLabel("Vendor", new JTextField());
		m_Tolerance = new AddPrinterLabel("Tolerance", new JTextField());
		m_Finish = new AddPrinterLabel("Finish", new JComboBox<String>(ToolBox.getFinishList()));
		m_Materials = new AddPrinterLabel("Materials", new JComboBox<String>(ToolBox.getMaterialList()));
		
	}
}