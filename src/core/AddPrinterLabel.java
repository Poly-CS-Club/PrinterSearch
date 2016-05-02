package core;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * 
 * Creates a simple label and component table/grid used in AddPrinterFrame.
 * 
 * @author Joshua Becker
 * @see    AddPrinterFrame
 */
public class AddPrinterLabel extends JLabel
{
	/**
	 * gen serialVersionUID
	 */
	private static final long serialVersionUID = 7665624464908541652L;
	
	private JLabel m_Title_L;
	private Component m_Component;
	
	/**
	 * Creates a JLabel with the specified title and text field.
	 * 
	 * @param title      the String with the text field's title
	 * @param textField  the text field to incorporate into the label
	 */
	public AddPrinterLabel(String title, JTextField textField)
	{
		m_Title_L = new JLabel(title, JLabel.CENTER);
		m_Component = textField;
		m_Component.setName("TextField");
		designLabel();
		addComponents();
	}
	
	/**
	 * Creates a JLabel with the specified title and combo box.
	 * 
	 * @param title     the String with the combo box's title
	 * @param comboBox  the combo box to incorporate into the label
	 */
	public AddPrinterLabel(String title, JComboBox<String> comboBox)
	{
		m_Title_L = new JLabel(title, JLabel.CENTER);
		m_Component = comboBox;
		m_Component.setName("ComboBox");
		designLabel();
		addComponents();
	}
	
	/**
	 * Sets the label's layout and size.
	 */
	public void designLabel()
	{
		this.setLayout(new GridLayout(2,1,5,5));
		this.setPreferredSize(new Dimension(100,45));
	}
	
	/**
	 * Adds the specified title and component to the label.
	 */
	public void addComponents()
	{
		this.add(m_Title_L);
		this.add(m_Component);
	}

	/**
	 * Sets a text field component's text.
	 * 
	 * @param text  the String containing text for the text field
	 */
	public void setM_ComponentText(String text){
		((JTextField) m_Component).setText(text);
	}

	/**
	 * Returns a String of the item specified in a combo box component.
	 * 
	 * @return  the String representing the item in the combo box
	 */
	@SuppressWarnings("unchecked")
	public String getSelectedItem()
	{
		return (String) ((JComboBox<String>) m_Component).getSelectedItem();
	}
	
	/**
	 * Retrieves the text from a text field component.
	 * 
	 * @return  the String containing the text field text
	 */
	public String getInput()
	{
		if(!m_Component.equals(null))
			return (String) ((JTextField) m_Component).getText();
		else
			return "";
	}
	/**
	 * @return the m_Component
	 */
	public Component getComponent() {
		return m_Component;
	}
	/**
	 * @param m_Component the m_Component to set
	 */
	public void setComponent(Component m_Component) {
		this.m_Component = m_Component;
	}
}
