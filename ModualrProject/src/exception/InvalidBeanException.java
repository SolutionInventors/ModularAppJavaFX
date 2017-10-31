package exception;

public class InvalidBeanException extends Exception
{

    private static final long serialVersionUID = 2068954224558286896L;

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
