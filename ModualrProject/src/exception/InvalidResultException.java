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
    public InvalidResultException( String message )
    {
	super( message );
    }
}
