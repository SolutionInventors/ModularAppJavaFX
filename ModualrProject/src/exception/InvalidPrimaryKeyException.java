package exception;

public class InvalidPrimaryKeyException extends Exception
{

    
    private static final long serialVersionUID = -2061687774970003142L;

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
