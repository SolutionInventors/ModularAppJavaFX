package exception;

public class InvalidStudentException extends InvalidBeanException
{

   
    private static final long serialVersionUID = 8090121548496068772L;
    public InvalidStudentException(){
	this("The Student format is invalid" );
    }
    public InvalidStudentException(String message)
    {
	super(message);
    }
}	
