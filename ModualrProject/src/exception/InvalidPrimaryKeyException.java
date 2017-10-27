package exception;

public class InvalidPrimaryKeyException extends Exception
{

    public InvalidPrimaryKeyException()
    {
	super("The primary key is invalid");
    }

    public InvalidPrimaryKeyException(String message)
    {
	super(message);
	// TODO Auto-generated constructor stub
    }

}
