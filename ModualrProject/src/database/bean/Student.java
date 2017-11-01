package database.bean;

import java.io.File;
import java.sql.Date;

import exception.InvalidImageFormatException;

public class Student  implements Bean 
{
    private String idCardNumber;
    private String firstName;
    private String lastName;
    private boolean active;
    private String emailAddress;
    private File image;
    private Date dateAdmitted;
    private Phone[] phoneNumbers;
    
    public Student(String id, String fName, String lName, String mail, boolean active)
    {
	setIdCardNumber(id);
	setFirstName(fName);
	setLastName(lName);
	setEmailAddress(mail);
	setActive(active);
    }
    
    public Student(){}
    
    
    public String getName(){
	return getFirstName() + " " + getLastName();
    }
    public String getIdCardNumber()
    {
	return idCardNumber;
    }
    public void setIdCardNumber(String idCardNumber)
    {
	this.idCardNumber = idCardNumber;
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
    public boolean isActive()
    {
	return active;
    }
    public void setActive(boolean active)
    {
	this.active = active;
    }
    public String getEmailAddress()
    {
	return emailAddress;
    }
    public void setEmailAddress(String emailAddress)
    {
	this.emailAddress = emailAddress;
    }

    public static boolean isValid( Student student ){
	if( student != null && student.getEmailAddress()  != null && 
		validateName( student ) 	)
	{
	    return true;
	}
	return false;
    }
    private static boolean validateName(Student student)
    {
	if( student.getFirstName().matches( "[A-Za-z]*") && 
		student.getLastName().matches( "[A-Za-z]*" )){
	    return true;
	}
	return false;
    }

    public File getImage()
    {
	return image;
    }

    public void setImage(File image) throws InvalidImageFormatException
    {
	
	String name = image.getName();
	name = name.substring(name.lastIndexOf( "."), name.length() );
	
	boolean isValid = name.toLowerCase().matches( ".jpg|.png|.jpeg|.bmp");
	
	if( isValid)
	    this.image = image;
	else
	    throw new InvalidImageFormatException("The student image is inalid and thus cannot be set");
	
    }

    public Phone[] getPhoneNumbers()
    {
	return phoneNumbers;
    }

    public void setPhoneNumbers(Phone[] phoneNumbers)
    {
	this.phoneNumbers = phoneNumbers;
    }

    public void setDateAdmitted(Date date)
    {
	dateAdmitted = date;
    }
    
    public Date getDateAdmitted()
    {
	return dateAdmitted ;
    }
    

}
