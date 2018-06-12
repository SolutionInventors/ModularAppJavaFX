package database.bean.student;

import database.bean.Bean;
import utils.ValidationType;

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
    
    private static final long serialVersionUID = -732018392175828811L;
    private String studentID;
    private String number;
    
    /**
     * Initializes this {@code Phone} by specifying the phone number of the
     * student and his student id .
     * @param studentID the id of the {@code student}
     * @param phoneNumber the phone number as {@code String}. This is validated via
     * call to {@link Bean#isPhoneValid(String)} beofore it is inserted into the 
     * database.
     */
    public Phone( String studentID , String phoneNumber ){
	setStudentId( studentID );
	setNumber(phoneNumber);
	
    }

    /**
     * Gets the studentID attribute stored by this
     * @return a {@code String } containing the id card number of the 
     * referenced {@code Student}
     */
    public String getStudentID()
    {
	return studentID;
    }

    /**
     * Sets the student id attribute of this {@code Phone} by first removing any 
     * unnecessary space in the containing {@code String}
     * @param studentID
     */
    public void setStudentId(String studentID)
    {
	this.studentID = Bean.removeExtraSpaces(studentID).toUpperCase();
    }

    /**
     * Gets the phone number stored in this {@code Phone}
     * @return
     */
    public String getNumber()
    {
	return number;
    }

    /**
     * Sets the phone number stored in this {@code Phone}. Note that the
     * phone number may start with a plus as in +2341231231234 or without
     * 2341231231234.  Any space is removed before the phone number is set.
     * @param number the number as {@code String}
     */
    public void setNumber(String number)
    {
	this.number =number.replaceAll("\\s" , "");
    } 
    
    /**
     * Checks that the format of this {@code Phone} is valid. If this value is  
     * {@code ValidationType.NEW_BEAN} then the phone number and studentId is checked
     * but if it is {@code ValidationType.EXISTING_BEAN} then only the studentId
     * is checked. These validations are done via call to 
     * @param phone the {@code Phone} object to check
     * @return {@code true} if the object is valid
     */
    public  boolean isValid(ValidationType type){
	
	switch (type)
	{
	    case NEW_BEAN:
		return validateStudentID()  && validatePhoneNumber();
	    case EXISTING_BEAN:
		return validateStudentID();
	}
	return false;
    }
    
    /**
     * Checks the format of the studentID by ensuring that it is not an empty
     * {@code String}
     * @return {@code true } if the studentID attribute of this object is valid
     */
    public boolean validateStudentID(){
	String studID = getStudentID();
	return  studID != null && studID.length() > 0;
    }
    
    
    /**
     * Checks the format of the phoneNumber via call to 
     * {@link Bean#isPhoneValid(String)}
     * @return {@code true } if the studentID attribute of this object is valid
     */
    public boolean validatePhoneNumber(){
	return Bean.isPhoneValid( getNumber());
    }
}
