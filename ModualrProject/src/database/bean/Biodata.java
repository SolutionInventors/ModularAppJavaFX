package database.bean;

import java.io.File;
import java.sql.Date;

import exception.InvalidImageFormatException;

public class Biodata implements Bean
{
    private String firstName;
    private String lastName;
    private String studentId;
    private String homeAddress;
    private String stateOfOrigin;
    private String country;
    private String gender;
    private Date dateOfBirth;
    private String placeOfBirth;
    private File image;
    
    public Biodata(){}

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String fName)
    {
        this.firstName = Bean.removeExtraSpaces( fName);
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setlName(String lName)
    {
        this.lastName = Bean.removeExtraSpaces( lName);
    }

    public String getStudentId()
    {
        return studentId;
    }

    public void setStudentId(String studentId)
    {
        this.studentId = Bean.removeExtraSpaces(studentId);
    }

    public String getHomeAddress()
    {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress)
    {
        this.homeAddress = Bean.removeExtraSpaces(homeAddress );
    }

    public String getStateOfOrigin()
    {
        return stateOfOrigin;
    }

    public void setStateOfOrigin(String stateOfOrigin)
    {
        this.stateOfOrigin = Bean.removeExtraSpaces( stateOfOrigin);
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = Bean.removeExtraSpaces(country );
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = Bean.removeExtraSpaces( gender);
    }

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
    
    
    public static boolean isValid( Biodata data ){
	if( data.getDateOfBirth() != null && validateName( data ) && 
	    data.getCountry() != null  && data.getStudentId()!= null &&
	    validateGender( data.getGender()))
	{
	    return true;
	}
	return false;
    }

    private static boolean validateGender(String gender)
    {
	if( gender!= null && ( gender.toLowerCase().equals("male" ) || 
		gender.toLowerCase().equals( "female" ) )) {
	    return true;
	}
	return false;
    }
    
    private static boolean validateName(Biodata data)
    {
	if( data.getFirstName().length() <=  50 && 
		data.getLastName().length() <= 50 && 
		data.getFirstName().matches( "[A-Za-z]*") && 
		data.getLastName().matches( "[A-Za-z]*" )){
	    return true;
	}
	return false;
    }

    @Override
    public boolean isValid(ValidationType type)
    {
	// TODO Auto-generated method stub
	return false;
    }
}
