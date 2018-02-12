package database.bean.student;

import java.io.File;
import java.sql.Date;

public class AspiringStudent
{
    private int id; 
    private String firstName; 
    private String lastName; 
    private String title; 
    private Date dateOfBirth; 
    private String placeOfBirth; 
    private String religion; 
    private String permanentAddress; 
    private String email;
    private String stateOfOrigin; 
    private String country; 
    private String currentAddress; 
    private String gender; 
    private File image; 
    
    public AspiringStudent(int id, String firstName, String lastName, 
	    String title, Date birthDate, String birthPlace, String religion, 
	    String permAddress, String currentAddress, String mail, String state, String country, 
	    String gender, File image){
	setId(id);
	setFirstName(firstName);
	setLastName(lastName);
	setTitle(title);
	setDateOfBirth(birthDate);
	setPlaceOfBirth(birthPlace);
	setReligion(religion);
	setPermanentAddress(permAddress);
	setEmail(mail);
	setCountry(country);
	setStateOfOrigin(state);
	setGender(gender);
	setImage(image); 
    }
    
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
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
        this.placeOfBirth = placeOfBirth;
    }
    public String getReligion()
    {
        return religion;
    }
    public void setReligion(String religion)
    {
        this.religion = religion;
    }
    public String getPermanentAddress()
    {
        return permanentAddress;
    }
    public void setPermanentAddress(String permanentAddress)
    {
        this.permanentAddress = permanentAddress;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getStateOfOrigin()
    {
	return stateOfOrigin;
    }
    public void setStateOfOrigin(String stateOfOrigin)
    {
	this.stateOfOrigin = stateOfOrigin;
    }
    public String getCountry()
    {
	return country;
    }
    public void setCountry(String country)
    {
	this.country = country;
    }
    public String getCurrentAddress()
    {
	return currentAddress;
    }
    public void setCurrentAddress(String currentAddress)
    {
	this.currentAddress = currentAddress;
    }
    public String getGender()
    {
	return gender;
    }
    public void setGender(String gender)
    {
	this.gender = gender;
    }
    
    public File getImage()
    {
	return image;
    } 
    public void setImage(File image)
    {
	this.image = image !=null ? image : Student.getdefaultImage();
	
    }
    
}
