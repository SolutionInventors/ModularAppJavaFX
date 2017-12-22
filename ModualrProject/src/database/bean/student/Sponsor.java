package database.bean.student;

import database.bean.Bean;
import utils.ValidationType;

/**
 * This object represents the {@code Sponsor} table in the database.
 * This is inputed by a {@code Student} when he wants to register for
 * the program. It stores information about the {@code Student}'s sponsor and
 * how the sponsor can be reached.<br>
 * 
 * The studentId attribute serves as a link between this table and the
 * {@code Student}'s table
 * @author Oguejiofor Chidiebere
 * @since v1.0
 * @see Student
 *
 */
public class Sponsor implements Bean
{
    private static final long serialVersionUID = -1786280703325804515L;
    private String studentId;
    private String firstName ;
    private String lastName;
    private String address;
    private String telephone;
    private String email;
    
    public Sponsor(){}
    
    /**
     * Initializes this {@code Sponsor} with arguments. If this constructor
     * os used this object can immediately be inserted into the database
     * @param studId the foreign key linked to id card number in 
     * the {@code Student} table
     * @param name the name of theperson that would finf
     * @param address
     * @param phone
     * @param mail
     */
    public Sponsor( String studId , String fName , String lName, String address,
	    String phone, String mail )
    {
	setStudentId(studId);
	setFirstName( fName);
	setLastName( lName);
	setAddress(address);
	setTelephone(phone);
	setEmail(mail);
    }
    
    
    /**
     * Initializes this {@code Sponsor} by specifying enough data that can be used to 
     * check if the {@code Sponsor} is in the database. <br>
     * When this constructor is used we can use it as the first argument in
     * {@code SponsorManager.update} method
     * 
     * @param studId id card number of the {@code Student } to be added
     * @param fName the {@code Sponsor} first name
     * @param lName the {@code Sponsor} last name
     * @param mail the {@code Sponsor} email
     */
    public Sponsor(String studId, String fName, String lName, String mail)
    {
	this( studId, fName, lName, null, null, mail );
    }

    /**
     * Gets the address of the sponsor
     * @return
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     * Removes any extra space in its argument and uses the resulting 
     * {@code String} to Set the name of the {@code Student}'s sponsor address
     * @param name the name of the {@code Student} sponsor
     */
    public void setAddress(String address)
    {
        this.address = Bean.removeExtraSpaces( address);
    }

    /**
     * Gets the sponsor's telephone number
     * @return
     */
    public String getTelephone()
    {
        return telephone;
    }

    /**
     * Sets the sponsor's telephone number. The value is validated before it is
     * put into the database. The telephone must either be all numbers or
     * a '+' sign followed by all numbers. For example,
     * '+234739' is valid and '+234739' is also valid.<br>
     * Note that this method does not validate the phone number but the phone
     * number would be validated before it is put into the dataabase
     * @author Oguejiofor Chidiebere
     * @param telephone the phone nummber of the sponsor.
     */
    
    public void setTelephone(String telephone)
    {
        this.telephone = Bean.removeExtraSpaces( telephone);
    }

    /**
     * Gets the email address of the {@code Student} sponsor
     * @return a {@code String} containing the email of the Sponsor
     */
    public String getEmail()
    {
        return email;
    }

    
    /**
     * Removes extra spaces in its argument and uses the resulting {@code String}
     * to set the email address of the {@code Student }'s sponsor
     * @param email the email of the 
     */
    public void setEmail(String email)
    {
        this.email =email.replaceAll( "\\s", "");
    }

    /**
     * Gets the studentId which a foreign key from the {@code Student} table
     * @return studentId as a {@code String}.
     */
    public String getStudentId()
    {
	return studentId;
    }

    /**
     * Removes extra spaces and sets student id of this object.
     * @param studentId
     */
    public void setStudentId(String studentId)
    {
	this.studentId = studentId;
    }

    /**
     * Checks if this {@code Sponsor}  format is valid basesd on the specified
     * {@link ValidationType}
     * 
     */
    @Override
    public boolean isValid(ValidationType type)
    {
	switch (type)
	{
	    case EXISTING_BEAN:
		return getStudentId() != null && getStudentId().length() > 0 && 
		getFirstName() !=null && getLastName() != null && getEmail() !=null;
	    case NEW_BEAN:
		return getStudentId() != null && getAddress()!= null &&
		getEmail() != null && Bean.isPhoneValid( getTelephone()) && 
		validateName() ;
	}
	return false;
    }

    /**
     * Checks that the {@code firstName} and {@code lastName} attribute of
     * this object contains only letters
     * @return  {@code true } if the name is valid
     */
    public boolean validateName()
    {
	
	return Bean.hasOnlyLetters(getFirstName()) && 
		Bean.hasOnlyLetters(getLastName());
    }

    /**
     * Gets the first name of this {@code Sponsor}
     * @return
     */
    public String getFirstName()
    {
	return firstName;
    }

    /**
     * Sets the last name of this {@code Sponsor}
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
	this.firstName = Bean.capitalizeWords( firstName);
    }

    /**
     * Gets the last name of this {@code Sponsor}
     * @return
     */
    public String getLastName()
    {
	return lastName;
    }

    /**
     * Sets the last name of this {@code Sponsor}
     * @param lastName
     */
    public void setLastName(String lastName)
    {
	this.lastName = Bean.capitalizeWords(lastName);
    }

}
