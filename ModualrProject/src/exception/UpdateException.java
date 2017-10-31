package exception;

public class UpdateException extends Exception
{
    public UpdateException(){
	this( "The old object must have the same key value as the new Object");
    }
    
    public UpdateException(String message ){
	super( message );
    }
}
