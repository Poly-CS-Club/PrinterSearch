import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * @author Joshua Becker
 * This is the panel for the search filters seen in the menu
 */
public class SearchFiltersPanel extends JPanel {
	
	private JTextField m_BroadSearch_TF;
	private JComboBox<String>  m_Finish_CB, m_Materials_CB, m_Vendor_CB;
	private RangedTextField<Double> m_Compression_RTF, m_Tension_RTF, m_Tolerance_RTF, m_Impact_RTF;
	private JButton m_FilterResults_B, m_ClearResults_B;
	/**
	 * auto generated serial version UID for panel
	 */
	private static final long serialVersionUID = -3913570132700721319L;
	
	
	public SearchFiltersPanel()
	{
		this.setName("Filter Panel");
		createComponents();
		designComponents();
		addComponents();
	}
	public SearchFiltersPanel(String name)
	{
		this.setName(name);
		createComponents();
		designComponents();
		addComponents();
	}
	/**
	 * Initiates all of the Panels components
	 */
	private void createComponents()
	{
		// Instantiate text fields
		m_BroadSearch_TF = new JTextField();

		// Instantiate ranged text fields
		m_Compression_RTF = new RangedTextField<Double>(100.00, 0.000, RangedTextField.DOUBLE);
		m_Tolerance_RTF = new RangedTextField<Double>(100.00, 0.000, RangedTextField.DOUBLE);
		m_Tension_RTF = new RangedTextField<Double>(100.00, 0.000, RangedTextField.DOUBLE);
		m_Impact_RTF = new RangedTextField<Double>(100.00, 0.000, RangedTextField.DOUBLE);
		
		m_Compression_RTF.getMaxSpinner().setValue(0.000);
		m_Tolerance_RTF.getMaxSpinner().setValue(0.000);
		m_Tension_RTF.getMaxSpinner().setValue(0.000);
		m_Impact_RTF.getMaxSpinner().setValue(0.000);
		
		m_FilterResults_B = new JButton("Filter Results");
		m_ClearResults_B = new JButton("Clear Results");
		
		// Instantiate combo boxes
		populateFinish();
		populateMaterial();
		populateVender();

		
	}
	/**
	 * designs the look and of the panel and its components.
	 */
	private void designComponents()
	{
		Dimension defaultMaxSize = new Dimension(170, 30),
				  defaultMinSize = new Dimension(150, 30);
		Component[] searchComponents = {m_BroadSearch_TF, m_Vendor_CB, m_Compression_RTF, m_Tension_RTF, m_Tolerance_RTF,
									    m_Impact_RTF, m_Materials_CB, m_Finish_CB};
		
		
		// Set up search panel
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(175, MenuUI.FRAME_HEIGHT));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		// Set up search parameter component dimensions
		for(Component parameter : searchComponents) {
			parameter.setMaximumSize(defaultMaxSize);
			parameter.setMinimumSize(defaultMinSize);
		}
		m_FilterResults_B.setPreferredSize(new Dimension(160,30));
		m_ClearResults_B.setPreferredSize(new Dimension(160,30));
		
		// Align search parameter components
		m_BroadSearch_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Compression_RTF.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Tension_RTF.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Impact_RTF.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Materials_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Tolerance_RTF.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Finish_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_Vendor_CB.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_FilterResults_B.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_ClearResults_B.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		m_Materials_CB.setActionCommand("RangeOfMaterials");
	}
	/**
	 * Add components to the search panel of the GUI.
	 */
	private void addComponents()
	{
		Component[] searchComponents = {m_BroadSearch_TF, m_Vendor_CB, m_Compression_RTF, m_Tension_RTF, m_Tolerance_RTF,
			    m_Impact_RTF, m_Materials_CB, m_Finish_CB};
		
		String[] filterHeaders = {"Search", "Vendor", "Compression", "Tension", "Tolerance", "Impact", "Material", "Finish", ""};
		
		// Add search parameter titles and spacing to GUI
		for(int index=0; index<searchComponents.length; index++) {
			addSearchLabel("\n");
			addSearchLabel(filterHeaders[index]);
			add(searchComponents[index]);
		}
		
		addSearchLabel("\n");
		add(m_FilterResults_B);
		addSearchLabel("\n");
		add(m_ClearResults_B);
	}
	/**
	 * Creates and adds a label to the search parameter panel
	 * 
	 * @param text the String contained in the label
	 */
	private void addSearchLabel(String text)
	{
		JLabel label;
		label = new JLabel(text);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);
	}
	private void populateFinish()
	{
		Object [] materials= ToolBox.getFinishList();
		m_Finish_CB = new JComboBox<String>();
		m_Finish_CB.addItem("Select All");
		for(Object name:materials)
		{
			m_Finish_CB.addItem((String)name);
		}
	}
	private void populateMaterial()
	{
		Object [] materials= ToolBox.getMaterialList();
		m_Materials_CB = new JComboBox<String>();
		m_Materials_CB.addItem("Select All");
		for(Object name:materials)
		{
			m_Materials_CB.addItem((String)name);
		}
	}
	private void populateVender()
	{
		Object [] vendors= ToolBox.getVendorList();
		m_Vendor_CB = new JComboBox<String>();
		m_Vendor_CB.addItem("Select All");
		for(Object name:vendors)
		{
			m_Vendor_CB.addItem((String)name);
		}
	}
	//--------------------------------Getters/Setters---------------------------------------//
	/**
	 * @return the m_BroadSearch_TF
	 */
	public JTextField getBroadSearch() {
		return m_BroadSearch_TF;
	}
	/**
	 * @param m_BroadSearch_TF the m_BroadSearch_TF to set
	 */
	public void setBroadSearch(JTextField m_BroadSearch_TF) {
		this.m_BroadSearch_TF = m_BroadSearch_TF;
	}
	/**
	 * @return the m_Materials_CB
	 */
	public JComboBox<String> getMaterials() {
		return m_Materials_CB;
	}
	/**
	 * @param m_Materials_CB the m_Materials_CB to set
	 */
	public void setMaterials(JComboBox<String> m_Materials_CB) {
		this.m_Materials_CB = m_Materials_CB;
	}
	/**
	 * @return the m_Compression_RTF
	 */
	public RangedTextField<Double> getCompression() {
		return m_Compression_RTF;
	}
	/**
	 * @param m_Compression_RTF the m_Compression_RTF to set
	 */
	public void setCompression(RangedTextField<Double> m_Compression_RTF) {
		this.m_Compression_RTF = m_Compression_RTF;
	}
	/**
	 * @return the m_Tension_RTF
	 */
	public RangedTextField<Double> getTension() {
		return m_Tension_RTF;
	}
	/**
	 * @param m_Tension_RTF the m_Tension_RTF to set
	 */
	public void setTension(RangedTextField<Double> m_Tension_RTF) {
		this.m_Tension_RTF = m_Tension_RTF;
	}
	/**
	 * @return the m_Tolerance_RTF
	 */
	public RangedTextField<Double> getTolerance() {
		return m_Tolerance_RTF;
	}
	/**
	 * @param m_Tolerance_RTF the m_Tolerance_RTF to set
	 */
	public void setTolerance(RangedTextField<Double> m_Tolerance_RTF) {
		this.m_Tolerance_RTF = m_Tolerance_RTF;
	}
	/**
	 * @return the m_Impact_RTF
	 */
	public RangedTextField<Double> getImpact() {
		return m_Impact_RTF;
	}
	/**
	 * @param m_Impact_RTF the m_Impact_RTF to set
	 */
	public void setImpact(RangedTextField<Double> m_Impact_RTF) {
		this.m_Impact_RTF = m_Impact_RTF;
	}
	/**
	 * @return the m_Finish_CB
	 */
	public JComboBox<String> getFinish() {
		return m_Finish_CB;
	}
	/**
	 * @param m_Finish_CB the m_Finish_CB to set
	 */
	public void setFinish(JComboBox<String> m_Finish_CB) {
		this.m_Finish_CB = m_Finish_CB;
	}
	/**
	 * @return the m_FilterResults_B
	 */
	public JButton getFilterResults() {
		return m_FilterResults_B;
	}
	/**
	 * @param m_FilterResults_B the m_FilterResults_B to set
	 */
	public void setFilterResults(JButton m_FilterResults_B) {
		this.m_FilterResults_B = m_FilterResults_B;
	}
	/**
	 * @return the m_ClearResults_B
	 */
	public JButton getClearResults() {
		return m_ClearResults_B;
	}
	/**
	 * @param m_ClearResults_B the m_ClearResults_B to set
	 */
	public void setClearResults(JButton m_ClearResults_B) {
		this.m_ClearResults_B = m_ClearResults_B;
	}
	/**
	 * @return the m_Vendor_CB
	 */
	public JComboBox<String> getVendor() {
		return m_Vendor_CB;
	}
	/**
	 * @param m_Vendor_CB the m_Vendor_CB to set
	 */
	public void setVendor(JComboBox<String> m_Vendor_CB) {
		this.m_Vendor_CB = m_Vendor_CB;
	}

}
