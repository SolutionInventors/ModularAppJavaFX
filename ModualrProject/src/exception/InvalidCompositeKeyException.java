package exception;

public class InvalidCompositeKeyException extends InvalidKeyException
{

    private static final long serialVersionUID = 2736438867312073455L;

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
