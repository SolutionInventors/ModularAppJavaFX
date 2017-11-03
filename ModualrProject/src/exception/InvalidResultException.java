/**
 * 
 */
package exception;

import database.bean.ModuleStatus;

/**
 * This exception signals that the value of the result attribute of 
 * {@link ModuleStatus} is invalid
 * @author Oguejiofor Chidiebere
 *
 */
public class InvalidResultException extends Exception
{
    
    private static final long serialVersionUID = -1695247483014292241L;

    public InvalidResultException( String message )
    {
	super( message );
    }
}
