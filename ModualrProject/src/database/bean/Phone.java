package database.bean;

/**This class represents a single row in the Phone table*/
public class Phone  implements Bean
{
    private String studentId;
    private String number;
    
    public Phone( String id , String phoneNumber ){
	setId( id );
	setNumber(phoneNumber);
	
    }

    public String getStudentId()
    {
	return studentId;
    }

    public void setId(String id)
    {
	this.studentId = Bean.removeExtraSpaces(id );
    }

    public String getNumber()
    {
	return number;
    }

    public void setNumber(String number)
    {
	this.number = Bean.removeExtraSpaces(number);
    } 
    
    /**
     * Returns {@code true} if the Phone object can be put in the database
     * @param phone the {@code Phone} object to check
     * @return boolean
     */
    public  boolean isValid(ValidationType type){
	
	switch (type)
	{
	    case NEW_BEAN:
		return  getStudentId() != null && getNumber() != null &&
			Bean.isPhoneValid( getNumber());
		    
	    case EXISTING_BEAN:
		return getStudentId()!= null ;
	    default:
		break;
	}
	

	return false;
    }
}
