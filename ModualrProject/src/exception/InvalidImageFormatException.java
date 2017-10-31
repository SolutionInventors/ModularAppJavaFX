package exception;

public class InvalidImageFormatException extends RuntimeException
{

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
