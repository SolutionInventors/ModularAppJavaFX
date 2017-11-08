/**
 * 
 */
package exception;

/**
 * Signals that an illegal {@code Admin} is trying to perform transaction on
 *  the database
 * @author Oguejofor Chidiebere
 *
 */
public class InvalidAdminException extends Exception
{

    private static final long serialVersionUID = 2098503733882902275L;

    public InvalidAdminException()
    {
	super( "The admin is not in the database and "
		+ "thus cannot make changes to it" );
    }

    /**
     * @param message the message that woukd be displayed in when this exception
     *   is thrown
     */
    public InvalidAdminException(String message)
    {
	super(message);
	
    }
}
