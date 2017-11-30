package database.bean.student;

import database.bean.Bean;
import database.bean.ValidationType;

/**This class represents a single row in the Phone table
 * The {@code Phone} table stores the {@code Student} phone numbers. It contains
 * a foreign key from the {@code Student} table. This table stores one or more
 * phone numbers of a single {@code Student}
 * 
 * @author Oguejiofor Chidiebere 
 * @since v1.0
 */
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
	this.number =number.replaceAll(" " , "");
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
