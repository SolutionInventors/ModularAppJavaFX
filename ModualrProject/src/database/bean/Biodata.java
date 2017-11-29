package database.bean;

import java.io.File;
import java.sql.Date;

import exception.InvalidImageFormatException;

/** 
 * This object represents a single entity of the {@code Biodata} table in the
 * database. The {@code Biodata}  table stores the information about a particular
 * {@code Student}. The {@code Student }is link via the {@code studentId} attribute
 * of this object. 
 * @author Chidiebere
 *
 */
public class Biodata implements Bean
{
    private static final long serialVersionUID = -6215729185466161547L;
    
    private String title;
    private String middleName;
    private String surname;
    private String lastName;
    private String studentId;
    private String permanentAddress;
    private String currentAddress;
    private String stateOfOrigin;
    private String country;
    private String gender;
    private Date dateOfBirth;
    private String placeOfBirth;
    private String religion;
    
    private File image;

    public Biodata(){}

    /**
     * This constructor initializes this object with the studentId. This object
     * can be used to check if a {@code Biodata} exists in the database
     * @param studentId the studentId  in the {@code Student} table
     */
    public Biodata( String studentId){
	setStudentId(studentId);

    }

    /**
     * This constructor initializes this object with all the required parameters
     * for it to be inserted into database. This object can be used to 
     * check if a {@code Biodata} exists in the database

     * @param fName the first name of the {@code Student}
     * @param lName the last name of the {@code Student}
     * @param sutdentId the student id card number
     * @param address the address of the {@code Student}
     * @param state the state of origin of the {@code Student}
     * @param country the country of origin of the {@code Student}
     * @param gender the sgender of the {@code Student}
     * @param birth the {@link java.sql.Date} of birth of the student
     * @param placeOfBirth the place the {@code Student} was born
     * @param image a {@link java.io.File } containing the image of the {@code Student}
     */
    public Biodata( String midName, String lName, String sutdentId , String address,String state, 
	    String country, String gender, Date birth, String placeOfBirth,File image)
    {
	setMiddleName(midName);
	setLastName(lName);
	setStudentId(sutdentId);
	setHomeAddress(address);
	setStateOfOrigin(state);
	setCountry(country);
	setGender(gender);
	setDateOfBirth(birth);
	setPlaceOfBirth(placeOfBirth);
	setImage(image);
    }

    /**
     * Gets the middleName stored in this object. 
     * @author Oguejiofor Chidiebere 
     * @return a {@code String} object containing the first name of the {@code Student}
     */
    public String getMiddleName()
    {
	return middleName;
    }

    /**
     * Sets the name of this object by removing any extra spaces that
     * may exist anywhere in the {@code String}
     * @param fName the middleName of the {@code Student}
     */
    public void setMiddleName(String midName)
    {
	this.middleName = Bean.removeExtraSpaces( midName);
    }

    /**
     * Gets the lastName stored in this object. 
     * @author Oguejiofor Chidiebere 
     * @return a {@code String} object containing the last name of the {@code Student}
     */
    public String getLastName()
    {
	return lastName;
    }

    /**
     * Sets the last name of this object by removing any extra spaces that
     * may exist anywhere in the {@code String}
     * @param lName the lastName of the {@code Student}
     */
    public void setLastName(String lName)
    {
	this.lastName = Bean.removeExtraSpaces( lName);
    }


    /**
     * Gets the student id that is linked to this {@code Biodata} object
     * The  StudentId returned is the link between this {@code Biodata} and
     * the {@code Student} table
     * @return a {@code String } containing the StudentId
     */
    public String getStudentId()
    {
	return studentId;
    }

    /**
     * Sets the student id of this {@code Biodata} object
     * Any extra spaces are removed in the {@code String} before the
     * Student id is set
     * @return a {@code String } containing the StudentId
     */
    public void setStudentId(String studentId)
    {
	this.studentId = Bean.removeExtraSpaces(studentId);
    }

    /**
     * Gets the home address stored in this {@code Biodata}.
     * @return a {@code String} containing the student address.
     */
    public String getPermanentAddress()
    {
	return permanentAddress;
    }

    /**
     * Sets the home address of this object. It first removes any extra spaces 
     * in its argument before it is set
     * @param homeAddress
     */
    public void setHomeAddress(String address )
    {
	this.permanentAddress = Bean.removeExtraSpaces(address );
    }

    /**
     * Gets the state of origin stored in this object 
     * @return a {@code String} containing the {@code Student}'s state of origin
     */
    public String getStateOfOrigin()
    {
	return stateOfOrigin;
    }


    /**
     * Sets the state of origin of this object. It first removes any extra spaces 
     * in its argument before it is set
     * @param homeAddress
     */
    public void setStateOfOrigin(String stateOfOrigin)
    {
	this.stateOfOrigin = Bean.removeExtraSpaces( stateOfOrigin);
    }


    /**
     * Gets the country that {@code Student} stored in this object
     * @return a {@code String } 
     */
    public String getCountry()
    {
	return country;
    }

    /**
     * Removes the empty spaces of its argument and stores it in this object
     * @param country the {@code String} to be used to set the country
     */
    public void setCountry(String country)
    {
	this.country = Bean.removeExtraSpaces(country );
    }

    /**
     * Gets the gender of the {@code Student}
     * @return
     */
    public String getGender()
    {
	return gender;
    }

    /**
     * Sets the gender of the of this object. It does this by first removing the
     * spaces in its argument and the setting the gender to Male or female or null.
     * <br>
     * <h3>Note: </h3>If the {@code String} argument is 'M', this method sets the gender
     * to 'Male'. Also if the {@code String} is 'F' this method sets the gender
     * to 'Female'
     * 
     * @param gender the {@code String} argument.
     */
    public void setGender(String gender)
    {
	gender = Bean.removeExtraSpaces( gender);
	gender = gender.toUpperCase().equals("M") ? "Male": gender;
	gender = gender.toUpperCase().equals("F") ? "Female": gender;

	this.gender = gender;
    }

    /**
     * Gets the date of birth stored in this object
     * @return a {@link java.sql.Date} representing the Date of birth stored
     * in this object
     */
    public Date getDateOfBirth()
    {
	return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)
    {
	this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth()
    {
	return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth)
    {
	this.placeOfBirth = Bean.removeExtraSpaces( placeOfBirth);
    }

    /**
     * Gets the image of the {@code Student} stored as a {@code File} object
     * @return a {@link java.io.File} object
     */
    public File getImage()
    {
	return image;
    }


    public void setImage(File image) throws InvalidImageFormatException
    {
	String name = image.getName();
	name = name.substring(name.lastIndexOf( "."), name.length() );

	boolean isValid = name.toLowerCase().matches( ".jpg|.png|.jpeg|.bmp|.gif");

	if( isValid)
	    this.image = image;
	else
	    throw new InvalidImageFormatException("The student image is inalid and thus cannot be set");

    }

    /**
     * Checks if the specified gender is valid
     * @param gender {@code String } representing the gender
     * @return {@code true} if the gender is valid
     */
    private  boolean validateGender()
    {
	if( getGender()!= null && ( getGender().toLowerCase().equals("male" ) || 
		getGender().toLowerCase().equals( "female" ) )) 
	{
	    return true;
	}
	return false;
    }

    private  boolean validateName()
    {
	if( getSurname().length() <=  50 && 
		getLastName().length() <= 50 && 
		getSurname().matches( "[A-Za-z]*") && 
		getLastName().matches( "[A-Za-z]*" )){
	    return true;
	}
	return false;
    }

    /**
     * If the {@link ValidationType } passed as argument equals 
     * {@code ValidationType.NEW_BEAN} then this method checks if  
     * ensures that none of the arguments
     * are {@code null},  that the gender is either male or female and
     *  that the {@code middleName}  and {@code lastName} length are less than 51
     *  and that they contain only letters.<br><br>
     *  
     *  If the {@link ValidationType } is set to {@code ValidationType.EXISTING_BEAN}
     *  then this method checks that the stuendentId is not {@code null}.
     *  
     *  <br>
     *  @return {@code true } if all the above conditions are met
     */

    @Override
    public boolean isValid(ValidationType type)
    {

	switch( type){
	    case EXISTING_BEAN:
		return getStudentId()!= null;
	    case NEW_BEAN:
		return getDateOfBirth() != null && validateName() && 
		getCountry() != null  && getStudentId()!= null &&
		validateGender();
	}
	return false;
    }

    public String getTitle()
    {
	return title;
    }

    public void setTitle(String title)
    {
	this.title = title;
    }

    public String getSurname()
    {
	return surname;
    }

    public void setSurname(String surname)
    {
	this.surname = surname;
    }

    public String getCurrentAddress()
    {
	return currentAddress;
    }

    public void setFullAddress(String fullAddress)
    {
	this.currentAddress = Bean.removeExtraSpaces(fullAddress );
    }

    public String getReligion()
    {
	return religion;
    }

    public void setReligion(String religion)
    {
	this.religion = religion;
    }
}
