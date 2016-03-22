/**
 * a type of text field that has a min and a max
 * @author Joshua Becker
 *
 */
import java.awt.Dimension;

import javax.swing.*;

public class RangedTextField extends JPanel{
	//These are types of number ranges
	public static final int INTEGER = 1;
	public static final int DOUBLE = 2;
	//this is for JComponent
	private static final long serialVersionUID = 1L;
	
	private JSpinner m_Min_TF, m_Max_TF;
	
	public RangedTextField()
	{
		setMin(new JSpinner());
		setMax(new JSpinner());
		
		m_Min_TF.setMinimumSize(new Dimension(50,25));
		m_Min_TF.setPreferredSize(new Dimension(50,25));
		m_Min_TF.setModel(new SpinnerNumberModel(0.000, 0.000, 10.00, 0.001));
		//m_Min_TF.setEditor(new JSpinner.NumberEditor(m_Min_TF));
		
		
		m_Max_TF.setMinimumSize(new Dimension(50,25));
		m_Max_TF.setPreferredSize(new Dimension(50,25));
		m_Max_TF.setModel(new SpinnerNumberModel(0.000, 0.000, 10.000,0.001));
		//m_Max_TF.setEditor(new JSpinner.NumberEditor(m_Max_TF));
		
		add(new JLabel("Min"));
		add(m_Min_TF);
		add(new JLabel("Max"));
		add(m_Max_TF);
	}
	
	public RangedTextField(double max, double min, int type)
	{
		setMin(new JSpinner());
		setMax(new JSpinner());
		
		m_Min_TF.setMinimumSize(new Dimension(50,25));
		m_Min_TF.setPreferredSize(new Dimension(50,25));
		if(type == RangedTextField.DOUBLE)
			m_Min_TF.setModel(new SpinnerNumberModel(0.000, min, max, 0.001));
		else
			m_Min_TF.setModel(new SpinnerNumberModel(0, min, max, 1));
		
		
		m_Max_TF.setMinimumSize(new Dimension(50,25));
		m_Max_TF.setPreferredSize(new Dimension(50,25));
		if(type == RangedTextField.DOUBLE)
			m_Max_TF.setModel(new SpinnerNumberModel(0.000, min, max, 0.001));
		else
			m_Max_TF.setModel(new SpinnerNumberModel(0, min, max, 1));
		
		add(new JLabel("Min"));
		add(m_Min_TF);
		add(new JLabel("Max"));
		add(m_Max_TF);
	}

	public JSpinner getMin() {
		return m_Min_TF;
	}

	public void setMin(JSpinner m_Min_TF) {
		this.m_Min_TF = m_Min_TF;
	}

	public JSpinner getMax() {
		return m_Max_TF;
	}

	public void setMax(JSpinner m_Max_TF) {
		this.m_Max_TF = m_Max_TF;
	}
}
