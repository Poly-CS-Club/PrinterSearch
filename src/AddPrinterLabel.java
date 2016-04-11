import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * 
 * @author Joshua Becker
 * creates a simple label and component table/grid, used in AddPrinterFrame.
 * @see AddPrinterFrame
 */

public class AddPrinterLabel extends JLabel
{
	/**
	 * gen serialVersionUID
	 */
	private static final long serialVersionUID = 7665624464908541652L;
	
	private JLabel m_Title_L;
	private Component m_Component;
	
	public AddPrinterLabel(String title, JTextField textField)
	{
		m_Title_L = new JLabel(title, JLabel.CENTER);
		m_Component = textField;
		m_Component.setName("TextField");
		designLabel();
		addComponents();
	}
	public AddPrinterLabel(String title, JComboBox<String> comboBox)
	{
		m_Title_L = new JLabel(title, JLabel.CENTER);
		m_Component = comboBox;
		m_Component.setName("ComboBox");
		designLabel();
		addComponents();
	}
	public void designLabel()
	{
		this.setLayout(new GridLayout(2,1,5,5));
		this.setPreferredSize(new Dimension(100,45));
	}
	public void addComponents()
	{
		this.add(m_Title_L);
		this.add(m_Component);
	}
	
	@SuppressWarnings("unchecked")
	public String getSelectedItem()
	{
		return (String) ((JComboBox<String>) m_Component).getSelectedItem();
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
