package exception;

public class InvalidCompositeKeyException extends InvalidKeyException
{

    public InvalidCompositeKeyException()
    {
	this( "The composite key is invalid") ;
    }

    public InvalidCompositeKeyException(String message)
    {
	super(message);
	// TODO Auto-generated constructor stub
    }

}
