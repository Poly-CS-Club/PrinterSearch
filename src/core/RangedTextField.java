package core;
import java.awt.Dimension;
import javax.swing.*;

/**
 * A type of text field that has a min and a max
 * 
 * @author  Joshua Becker, Marcinina Alvaran
 * @version 1.0
 */
public class RangedTextField<N extends Number> extends JPanel{
	//These are types of number ranges
	public static final int INTEGER = 1;
	public static final int DOUBLE = 2;
	
	// Minimum/Maximum indicator constants
	public static final int MIN = 1;
	public static final int MAX = 2;
	private int limitType;
	
	//this is for JComponent
	private static final long serialVersionUID = 1L;
	
	private JSpinner m_Min_TF, m_Max_TF;
	private SpinnerNumberModel
	        maxModel = new SpinnerNumberModel(0.000, 0.000, 10.00, 1.000),
	        minModel = new SpinnerNumberModel(0.000, 0.000, 10.00, 1.000);
	
	/**
	 * Creates generic ranged text field with both min and max fields from 0
	 * to 10 incremented by 1.
	 */
	public RangedTextField()
	{
        Dimension defaultSize = new Dimension(50, 25);
		setMin(new JSpinner());
		setMax(new JSpinner());
		
		// Set up minimum value spinner
		m_Min_TF.setMinimumSize(defaultSize);
		m_Min_TF.setPreferredSize(defaultSize);
		m_Min_TF.setModel(minModel);
		//m_Min_TF.setEditor(new JSpinner.NumberEditor(m_Min_TF));
		
		// Set up maximum value spinner
		m_Max_TF.setMinimumSize(defaultSize);
		m_Max_TF.setPreferredSize(defaultSize);
		m_Max_TF.setModel(maxModel);
		//m_Max_TF.setEditor(new JSpinner.NumberEditor(m_Max_TF));
		
		// Include spinners in UI
		add(new JLabel("Min"));
		add(m_Min_TF);
		add(new JLabel("Max"));
		add(m_Max_TF);
	}
    
	/**
	 * Creates a ranged text field with specified min and max fields that are 
	 * incremented either by 0.001 if Double or 1 if Integer.
	 * <p>
	 * Accepted <b>type</b> values: <b>1</b> if Integer, <b>2</b> if Double
	 * 
	 * @param max   the maximum value of the range limit
	 * @param min   the minimum value of the range limit
	 * @param type  the int representing the numerical data type of the range
	 */
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
	 * Creates a single-field range text field with specified range limit that 
     * is incremented either by 0.001 if Double or 1 if Integer.
     * <p>
     * Accepted <b>type</b> values: <b>1</b> if Integer, <b>2</b> if Double
	 * 
	 * @param max         the maximum value of the range limit
	 * @param min         the minimum value of the range limit
	 * @param numberType  the int representing the numerical data type of the range
	 * @param limitType   the int representing the limit type of the range
	 */
	public RangedTextField(double max, double min, int numberType, int limitType)
	{
	    Dimension defaultSize = new Dimension(75, 25);
	    
	    this.limitType = limitType;
	    
	    // Set up appropriate spinner
	    if (limitType == RangedTextField.MAX)
	    {
	        setMax(new JSpinner());
	        m_Max_TF.setMinimumSize(defaultSize);
	        m_Max_TF.setPreferredSize(defaultSize);
	        if(numberType == RangedTextField.DOUBLE)
	            m_Max_TF.setModel(new SpinnerNumberModel(0.000, min, max, 0.001));
	        else
	            m_Max_TF.setModel(new SpinnerNumberModel(0, min, max, 1));
            add(new JLabel("MAX"));
            add(m_Max_TF);
	    }
	    else
	    {
	        setMin(new JSpinner());
	        m_Min_TF.setMinimumSize(defaultSize);
	        m_Min_TF.setPreferredSize(defaultSize);
            if(numberType == RangedTextField.DOUBLE)
                m_Min_TF.setModel(new SpinnerNumberModel(0.000, min, max, 0.001));
            else
                m_Min_TF.setModel(new SpinnerNumberModel(0, min, max, 1));
            add(new JLabel("MIN"));
            add(m_Min_TF);
	    }
	}
	
	/**
	 * Returns a Number object representing the value in the minimum ranged
	 * text field.
	 * 
	 * @return  the Number representing the current minimum value
	 * @throws  RangeLimitException if the component does not have a
	 *          minimum ranged text field
	 */
	public Number getMin() throws RangeLimitException {
	    if (limitType == RangedTextField.MAX)
	        throw new RangeLimitException("minimum");
	    
		return (Number)m_Min_TF.getValue();
	}
	
	/**
     * Returns a Number object representing the value in the maximum ranged
     * text field.
     * 
     * @return  the Number representing the current maximum value
     * @throws  RangeLimitException if the component does not have a
     *          maximum ranged text field
     */
	public Number getMax() throws RangeLimitException  {
        if (limitType == RangedTextField.MIN)
            throw new RangeLimitException("maximum");
        
		return (Number)m_Max_TF.getValue();
	}
	
	/**
	 * Returns the JSpinner from the minimum ranged text field.
	 * 
	 * @return  the JSpinner from the minimum ranged text field.
     * @throws  RangeLimitException if the component does not have a
     *          minimum ranged text field
	 */
	public JSpinner getMinSpinner() throws RangeLimitException  {
        if (limitType == RangedTextField.MAX)
            throw new RangeLimitException("minimum");
        
		return m_Min_TF;
	}

	/**
	 * Sets the JSpinner for the minimum ranged text field.
	 * 
	 * @param   m_Min_TF the new JSpinner for the minimum ranged text field
     * @throws  RangeLimitException if the component does not have a
     *          minimum ranged text field
	 */
	public void setMin(JSpinner m_Min_TF) throws RangeLimitException  {
        if (limitType == RangedTextField.MAX)
            throw new RangeLimitException("minimum");
        
		this.m_Min_TF = m_Min_TF;
	}

	/**
     * Returns the JSpinner from the maximum ranged text field.
     * 
     * @return  the JSpinner from the maximum ranged text field.
     * @throws  RangeLimitException if the component does not have a
     *          maximum ranged text field
     */
	public JSpinner getMaxSpinner() throws RangeLimitException  {
        if (limitType == RangedTextField.MIN)
            throw new RangeLimitException("maximum");
        
		return m_Max_TF;
	}

	/**
     * Sets the JSpinner for the maximum ranged text field.
     * 
     * @param   m_Min_TF the new JSpinner for the maximum ranged text field
     * @throws  RangeLimitException if the component does not have a
     *          maximum ranged text field
     */
	public void setMax(JSpinner m_Max_TF) throws RangeLimitException  {
        if (limitType == RangedTextField.MIN)
            throw new RangeLimitException("maximum");
        
		this.m_Max_TF = m_Max_TF;
	}
	
	/**
	 * Returns a Number object with the increment amount for the maximum
	 * ranged text field.
	 * 
	 * @return  the Number representing the increment amount for the maximum
	 *          ranged text field
     * @throws  RangeLimitException if the component does not have a
     *          maximum ranged text field
	 */
	public Number getMaxStep() throws RangeLimitException  {
        if (limitType == RangedTextField.MIN)
            throw new RangeLimitException("maximum");
        
		return maxModel.getStepSize();
	}
	
	/**
	 * Sets the increment amount for the maximum ranged text field.
	 * 
	 * @param   stepSize the Number representing the increment amount for
	 *          the maximum ranged text field
     * @throws  RangeLimitException if the component does not have a
     *          maximum ranged text field
	 */
	public void setMaxStep(Number stepSize) throws RangeLimitException  {
        if (limitType == RangedTextField.MIN)
            throw new RangeLimitException("maximum");
        
		maxModel.setStepSize(stepSize);
	}
	
	/**
     * Returns a Number object with the increment amount for the minimum
     * ranged text field.
     * 
     * @return  the Number representing the increment amount for the minimum
     *          ranged text field
     * @throws  RangeLimitException if the component does not have a
     *          minimum ranged text field
     */
	public Number getMinStepSize() throws RangeLimitException  {
        if (limitType == RangedTextField.MAX)
            throw new RangeLimitException("minimum");
        
		return minModel.getStepSize();
	}
	
	/**
     * Returns a Number object with the increment amount for the minimum
     * ranged text field.
     * 
     * @return  the Number representing the increment amount for the minimum
     *          ranged text field
     * @throws  RangeLimitException if the component does not have a
     *          minimum ranged text field
     */
	public void setMinStepSize(Number stepSize) throws RangeLimitException  {
        if (limitType == RangedTextField.MAX)
            throw new RangeLimitException("minimum");
        
		minModel.setStepSize(stepSize);
	}
}
