package exception;

public class InvalidImageFormatException extends RuntimeException
{

   
    private static final long serialVersionUID = 7851200542629207342L;

    public InvalidImageFormatException()
    {
	super("The image format is invalid" );
    }

    public InvalidImageFormatException(String message)
    {
	super(message);
	// TODO Auto-generated constructor stub
    }

   
}
