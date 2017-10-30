package exception;

public class InvalidBeanException extends Exception
{

    public InvalidBeanException()
    {
	super( "The bean cannot be put into the database because it has some "
		+ "invalid attributes");
    }

    public InvalidBeanException(String message)
    {
	super(message);
	
    }


}
