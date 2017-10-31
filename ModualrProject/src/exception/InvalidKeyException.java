package exception;

public class InvalidKeyException extends Exception
{

    public InvalidKeyException()
    {
	super("The key of the Bean is invalid" );
    }

    public InvalidKeyException(String message)
    {
	super(message);
    }

   

}
