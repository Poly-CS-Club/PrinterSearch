package core;

/**
 * Represents the situation in which an invalid range limit is referenced
 * for a RangedTextField.
 * 
 * @author  Marcinina Alvaran
 * @version 1.0
 * @see     RangedTextField
 */
public class RangeLimitException extends RuntimeException
{
    private static final long serialVersionUID = -1260365946452785047L;

    /**
     * Creates a default exception that notifies of an invalid range limit.
     */
    public RangeLimitException()
    {
        super("Invalid range limit for ranged text field.");
    }
    
    /**
     * Creates an exception that specifies the invalid limit.
     * 
     * @param limit the String with the invalid limit
     */
    public RangeLimitException(String limit) {
        super("The ranged text field lacks a " + limit + ".");
    }
}
