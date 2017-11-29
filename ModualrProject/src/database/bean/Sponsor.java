package database.bean;

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
        this.telephone = Bean.removeExtraSpaces( telephone.replace("-", ""));
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
        this.email = Bean.removeExtraSpaces( email );
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
		return getStudentId() != null;
	    case NEW_BEAN:
		return getStudentId() != null && getAddress()!= null &&
		getEmail() != null && Bean.isPhoneValid( getTelephone()) && 
		validateName() ;
	}
	return false;
    }

    private boolean validateName()
    {
	
	return Bean.hasOnlyLetters(getFirstName()) && 
		Bean.hasOnlyLetters(getLastName());
    }

    public String getFirstName()
    {
	return firstName;
    }

    public void setFirstName(String firstName)
    {
	this.firstName = Bean.removeExtraSpaces( firstName);
    }

    public String getLastName()
    {
	return lastName;
    }

    public void setLastName(String lastName)
    {
	this.lastName = Bean.removeExtraSpaces(lastName);
    }

}
