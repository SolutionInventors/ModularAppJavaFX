package database.bean.student;

import java.sql.Date;

import database.bean.Bean;
import utils.ValidationType;

/** 
 * A {@code Biodata} object represents a single row in the {@code Biodata} table in 
 * the database. The {@code Biodata} table contains column {@code studentId}
 * which is a foreign key to the {@code Student} table. Each {@code Student} can
 * only have one {@code Biodata} <br>
 * The{@code Biodata} object can then be used to insert and update a {@code Student}'s
 * biodata. 
 * @author Oguejiofor Chidiebere
 * @see Student
 *
 */
public class Biodata implements Bean
{
    private static final long serialVersionUID = -6215729185466161547L;

    private String title;
    private String studentId;
    private String permanentAddress;
    private String currentAddress;
    private String stateOfOrigin;
    private String country;
    private String gender;
    private Date dateOfBirth;
    private String placeOfBirth;
    private String religion;



    /**
     * This constructor initializes this object with the studentId. This object
     * can be used to check if a {@code Biodata} exists in the database
     * @param studentId the studentId  in the {@code Student} table
     */
    public Biodata( String studentId){
	this(studentId, "", "", "", "", "", null, "", "", "");

    }

    /**
     * This constructor initializes this object with all the required parameters
     * for it to be inserted into database.
     * @param sutdentId the student id card number
     * @param address the address of the {@code Student}
     * @param state the state of origin of the {@code Student}
     * @param country the country of origin of the {@code Student}
     * @param gender the sgender of the {@code Student}
     * @param birth the {@link java.sql.Date} of birth of the student
     * @param placeOfBirth the place the {@code Student} was born
     * @param image a {@link java.io.File } containing the image of the {@code Student}
     */
    public Biodata( String studID, String state, String country, String currAddr, 
	    String permanentAddr, String gender, Date birth, String placeOfBirth, String religion, String title)
    {
	setStudentId( studID);
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
     * Gets the student id that is linked to this {@code Biodata} object
     * The  StudentId returned is the link between this {@code Biodata} and
     * the {@code Student} table
     * @return a {@code String } containing the StudentId
     */
    public String getStudentID()
    {
	return studentId;
    }

    /**
     *Removes any extra spaces that exists in its argument and cpaitalises the
     *first letter of each word. Then uses the output {@code String} to set
     *this studentId attribute of this {@code Biodata}.
     * @return a {@code String } containing the StudentId
     */
    public void setStudentId(String studentId)
    {
	this.studentId = Bean.removeExtraSpaces(studentId).toUpperCase();
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
	this.permanentAddress =Bean.capitalizeWords(address) ;
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
	this.stateOfOrigin = Bean.capitalizeWords(stateOfOrigin) ;
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
	this.country = Bean.capitalizeWords(country);
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
     * to 'Female' Else it sets the gender to an empty {@code String} ""
     * 
     * @param gender the {@code String} argument.
     */
    public void setGender(String gender)
    {
	gender = Bean.removeExtraSpaces( gender);
	if( gender != null ){
	    gender = gender.equalsIgnoreCase("M") ? "Male": gender;
	    gender = gender.equalsIgnoreCase("F") ? "Female": gender;
	    this.gender = Bean.capitalizeWords( gender);
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

    /**
     * Sets the date of birth of the student that this {@code Biodata} references
     * @param dateOfBirth
     */ 
    public void setDateOfBirth(Date dateOfBirth)
    {
	this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the place this {@code Student } referenced by this {@code Biodata} was
     * born as a {@code String}.
     * @return a {@code String} containing the place the {@code Student} was born
     */
    public String getPlaceOfBirth()
    {
	return placeOfBirth;
    }

    /**
     * Sets the place of birth of this {@code Biodata} object
     * @param placeOfBirth
     */
    public void setPlaceOfBirth(String placeOfBirth)
    {
	this.placeOfBirth = Bean.capitalizeWords( placeOfBirth);

    }



    /**
     * Checks if the  gender stored is not an empty {@code String}
     * @param gender {@code String } representing the gender
     * @return {@code true} if the gender is valid
     */
    public  boolean validateGender()
    {
	if( gender  == null ) return false;
	return getGender().toLowerCase().matches("male|female");
    }

    
    /**
     * If the {@link ValidationType } passed as argument equals 
     * {@code ValidationType.NEW_BEAN} then this method checks if  
     * ensures that none of the arguments
     * are {@code null},  that the gender is either male or.<br><br>
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
		return getStudentID()!= null && getStudentID().length() > 0;
	    case NEW_BEAN:
		return getDateOfBirth() != null && 
		getCountry().length() > 0  && getStudentID().length() > 0 && 
		getPermanentAddress().length() >0 &&
		getCurrentAddress().length() >0 && getTitle().length() >0 &&
		validateGender() ;
	}
	return false;
    }


    /**
     * Gets the title of the {@code Student}  stored in this {@code Biodata} object
     * @return a {@code String} containing the title
     */
    public String getTitle()
    {
	return title;
    }

    /**
     * Sets the title stored in this of this {@code Biodata}.It truncates the 
     * {@code String } argument to 15 which is the maximum lenght of a title
     * @param title
     */
    public void setTitle(String title)
    {
	String temp = Bean.removeExtraSpaces( title);
	this.title = Bean.truncateString(temp, 15);
    }

   


    /**
     * Gets the current address of the {@code Student}
     * @return a {@code String } containing the current address of the referenced student
     */
    public String getCurrentAddress()
    {
	return currentAddress;
    }

    /**
     * Sets the current address of the referenced student
     * @param currAddr the current address as {@code String}
     */
    public void setCurrentAddress(String currAddr)
    {
	this.currentAddress= Bean.capitalizeWords(currAddr );
    }

    /**
     * Gets the religion of the referenced {@code Student} as {@code String}
     * @return a {@code String}
     */
    public String getReligion()
    {
	return religion;
    }

    /**
     * Sets the religion that the referenced {@code Student} believes in
     * @param religion a {@code String} containing the {@code Student} religion
     */
    public void setReligion(String religion)
    {
	this.religion = Bean.capitalizeWords(religion );
    }
}
