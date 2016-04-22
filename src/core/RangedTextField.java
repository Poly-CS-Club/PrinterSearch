package core;
import java.awt.Dimension;
import javax.swing.*;

/**
 * A type of text field that has a min and a max
 * @author Joshua Becker, Marcinina Alvaran
 *
 */
public class RangedTextField<N extends Number> extends JPanel{
	//These are types of number ranges
	public static final int INTEGER = 1;
	public static final int DOUBLE = 2;
	//this is for JComponent
	private static final long serialVersionUID = 1L;
	
	private JSpinner m_Min_TF, m_Max_TF;
	private SpinnerNumberModel
	        maxModel = new SpinnerNumberModel(0.000, 0.000, 10.00, 1.000),
	        minModel = new SpinnerNumberModel(0.000, 0.000, 10.00, 1.000);
	
	/**
	 * Creates generic ranged text field with spinners from 0 to 10
	 * incremented by 1.
	 */
	public RangedTextField()
	{
		setMin(new JSpinner());
		setMax(new JSpinner());
		
		// Set up minimum value spinner
		m_Min_TF.setMinimumSize(new Dimension(50,25));
		m_Min_TF.setPreferredSize(new Dimension(50,25));
		m_Min_TF.setModel(minModel);
		//m_Min_TF.setEditor(new JSpinner.NumberEditor(m_Min_TF));
		
		// Set up maximum value spinner
		m_Max_TF.setMinimumSize(new Dimension(50,25));
		m_Max_TF.setPreferredSize(new Dimension(50,25));
		m_Max_TF.setModel(maxModel);
		//m_Max_TF.setEditor(new JSpinner.NumberEditor(m_Max_TF));
		
		// Include spinners in UI
		add(new JLabel("Min"));
		add(m_Min_TF);
		add(new JLabel("Max"));
		add(m_Max_TF);
	}
	
	// TODO: Commented out due to making class a generic type for numbers
	
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
	
	
	/**
	 * Creates a ranged text field with specified minimum, maximum, and
	 * increment values.
	 * 
	 * @param max       the maximum value of the number range
	 * @param min       the minimum value of the number range
	 * @param stepSize  the increment of number within the range
	 */
	/*
	public RangedTextField(Number max, Number min, Number stepSize)
	{
		setMin(new JSpinner());
		setMax(new JSpinner());
		
		// Set up minimum value spinner
		m_Min_TF.setMinimumSize(new Dimension(50,25));
		m_Min_TF.setPreferredSize(new Dimension(50,25));
		minModel.setValue(min);
		minModel.setStepSize(stepSize);
		minModel.setMinimum((Comparable<?>)min);
		minModel.setMaximum((Comparable<?>)max);
		m_Min_TF.setModel(minModel);
		//m_Min_TF.setEditor(new JSpinner.NumberEditor(m_Min_TF));
		
		// Set up maximum value spinner
		m_Max_TF.setMinimumSize(new Dimension(50,25));
		m_Max_TF.setPreferredSize(new Dimension(50,25));
		maxModel.setValue(max);
		maxModel.setStepSize(stepSize);
		maxModel.setMinimum((Comparable<?>)min);
		maxModel.setMaximum((Comparable<?>)max);
		m_Max_TF.setModel(maxModel);
		//m_Max_TF.setEditor(new JSpinner.NumberEditor(m_Max_TF));
		
		// Include spinners in UI
		add(new JLabel("Min"));
		add(m_Min_TF);
		add(new JLabel("Max"));
		add(m_Max_TF);
	}
	*/
	public Number getMin() {
		return (Number)m_Min_TF.getValue();
	}
	
	public Number getMax() {
		return (Number)m_Max_TF.getValue();
	}
	

	public JSpinner getMinSpinner() {
		return m_Min_TF;
	}

	public void setMin(JSpinner m_Min_TF) {
		this.m_Min_TF = m_Min_TF;
	}

	public JSpinner getMaxSpinner() {
		return m_Max_TF;
	}

	public void setMax(JSpinner m_Max_TF) {
		this.m_Max_TF = m_Max_TF;
	}
	
	public Number getMaxStep() {
		return maxModel.getStepSize();
	}
	
	public void setMaxStep(Number stepSize) {
		maxModel.setStepSize(stepSize);
	}
	
	public Number getMinStepSize() {
		return minModel.getStepSize();
	}
	
	public void setMinStepSize(Number stepSize) {
		minModel.setStepSize(stepSize);
	}
}
