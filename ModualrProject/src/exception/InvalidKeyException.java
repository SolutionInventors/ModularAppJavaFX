package exception;

public class InvalidKeyException extends Exception
{

   
    private static final long serialVersionUID = -5010096565452968035L;

    public InvalidKeyException()
    {
	super("The key of the Bean is invalid" );
    }

    public InvalidKeyException(String message)
    {
	super(message);
    }

   

}
