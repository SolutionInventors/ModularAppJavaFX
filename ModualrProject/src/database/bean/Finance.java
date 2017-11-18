package database.bean;

/**
 * This object represents the {@code Finance} table in the database.
 * This is inputed by a {@code Student} when he wants to register for
 * the program. It stores information about the {@code Student}'s sponsor
 * 
 * @author Oguejiofor Chidiebere
 *
 */
public class Finance implements Bean
{
    private String studentId;
    private String name ;
    private String address;
    private String telephone;
    private String email;
    
    public Finance(){}
    
    /**
     * Initializes this {@code Finance} with arguments. If this constructor
     * os used this object can immediately be inserted into the database
     * @param studId the foreign key linked to id card number in 
     * the {@code Student} table
     * @param name
     * @param address
     * @param phone
     * @param mail
     */
    public Finance( String studId , String name , String address,
	    String phone, String mail )
    {
	setStudentId(studId);
	setName( name );
	setAddress(address);
	setTelephone(phone);
	setEmail(mail);
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = Bean.removeExtraSpaces( name);
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = Bean.removeExtraSpaces( address);
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = Bean.removeExtraSpaces( telephone);
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = Bean.removeExtraSpaces( email );
    }

    public String getStudentId()
    {
	return studentId;
    }

    public void setStudentId(String studentId)
    {
	this.studentId = studentId;
    }

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
		Bean.hasOnlyLetters(getName()) ;
	}
	return false;
    }

}
