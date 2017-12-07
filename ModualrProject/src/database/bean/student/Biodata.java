package database.bean.student;

import java.sql.Date;

import database.bean.Bean;
import utils.ValidationType;

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


    public Biodata(){
	this("", "", "", "", "", "", "", "", "", null, "", "", "");

    }

    /**
     * This constructor initializes this object with the studentId. This object
     * can be used to check if a {@code Biodata} exists in the database
     * @param studentId the studentId  in the {@code Student} table
     */
    public Biodata( String studentId){
	this(studentId, "", "", "", "", "", "", "", "", null, "", "", "");

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
    public Biodata( String studID ,String surname,  String midName, String lName, 
	    String state, String country, String currAddr, String permanentAddr, 
	    String gender, Date birth, String placeOfBirth, String religion, String title)
    {
	setStudentId( studID);
	setSurname(surname);
	setMiddleName(midName);
	setLastName(lName);
	setStudentId(studID);
	setPermanentAddress(permanentAddr);
	setCurrentAddress(currAddr);
	setStateOfOrigin(state);
	setCountry(country);
	setGender(gender);
	setDateOfBirth(birth);
	setPlaceOfBirth(placeOfBirth);
	setReligion(religion);
	setTitle(title);
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
	String temp = Bean.removeExtraSpaces( midName);
	this.middleName = temp !=null ? temp : "";
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
	String temp = Bean.removeExtraSpaces( lName);
	this.lastName =temp!=null ? temp : "";
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
	String temp= Bean.removeExtraSpaces(studentId);
	this.studentId = temp!=null ? temp : "";
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
    public void setPermanentAddress(String address )
    {
	String temp = Bean.removeExtraSpaces(address );
	this.permanentAddress = temp!=null ? temp: "";
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
	String temp = Bean.removeExtraSpaces(stateOfOrigin );
	this.stateOfOrigin = temp != null ? temp : "";
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
	String temp = Bean.removeExtraSpaces(country );
	this.country = temp!=null ? temp : "";
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
	if( gender != null ){
	    gender = gender.toUpperCase().equals("M") ? "Male": gender;
	    gender = gender.toUpperCase().equals("F") ? "Female": gender;
	    this.gender = gender;
	}
	else
	    this.gender = "";

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
	String temp = Bean.removeExtraSpaces( placeOfBirth);
	this.placeOfBirth = temp!=null ? temp : "";
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
		getSurname().matches( "[A-Za-z]{1,}") && 
		getLastName().matches( "[A-Za-z]{1,}" ) )
	{
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
		return getStudentId()!= null && getStudentId().length() > 0;
	    case NEW_BEAN:
		return getDateOfBirth() != null && validateName() && 
		getCountry().length() > 0  && getStudentId().length() > 0 &&
		getLastName().length() >0 && getMiddleName().length() >0 && 
		getSurname().length() >0 && getPermanentAddress().length() >0 &&
		getCurrentAddress().length() >0 && getTitle().length() >0 &&
		validateGender() ;
	}
	return false;
    }

   

    public String getTitle()
    {
	return title;
    }

    public void setTitle(String title)
    {
	String temp = Bean.removeExtraSpaces( title);
	this.title = temp!=null ? temp : "";
    }

    public String getSurname()
    {
	return surname;
    }

    public void setSurname(String surname)
    {
	String temp = Bean.removeExtraSpaces( surname);
	this.surname = temp != null ? temp : "";
    }

    public String getCurrentAddress()
    {
	return currentAddress;
    }

    public void setCurrentAddress(String currAddr)
    {
	String temp = Bean.removeExtraSpaces(currAddr );
	this.currentAddress = temp!=null ? temp : "";
    }

    public String getReligion()
    {
	return religion;
    }

    public void setReligion(String religion)
    {
	String temp = Bean.removeExtraSpaces(religion );
	this.religion = temp != null ? temp : "";
    }
}
