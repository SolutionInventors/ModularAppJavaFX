package database.bean.student;

import java.io.File;
import java.sql.Date;

import database.bean.Bean;
import exception.InvalidImageFormatException;
import utils.ValidationType;

/**
 * 
 * @author Chidiebere
 *
 */
public class Student  implements Bean 
{

    private static final long serialVersionUID = 1L;

    /**Foreign key to Modular Class table*/
    private  String modClass;
    private String idCardNumber;
    private boolean active;
    private String emailAddress;
    private String certificateIssued;
    private Date dateAdmitted;

    private File image;

    /**
     * This initialises a this {@code Student} by specifyiing the required data to 
     * input this object into the database. 
     * This {@code Student } can be inputed if the parameters inputed are valid. The
     * Validity can be checked via call to {@link isValid}
     * @param id
     * @param className
     * @param mail
     */
    public Student(String id, String className,  String mail, File image)
    {
	setModClass(className);
	setIdCardNumber(id);
	setEmailAddress(mail);
	setImage( image);
    }

    public Student(){ }

    public String getIdCardNumber()
    {
	return idCardNumber;
    }
    public void setIdCardNumber(String idCardNumber)
    {
	this.idCardNumber = idCardNumber;
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

    public static boolean isValid(ValidationType type,  Student student ){
	if( student != null && student.getEmailAddress()  != null)
	{
	    return true;
	}
	return false;
    }

    public void setDateAdmitted(Date date)
    {
	dateAdmitted = date;
    }

    public Date getDateAdmitted()
    {
	return dateAdmitted ;
    }

    public String getCertificateIssued()
    {
	return certificateIssued;
    }

    public void setCertificateIssued(String certificateIssued)
    {
	this.certificateIssued = certificateIssued;
    }


    @Override
    public boolean isValid(ValidationType type)
    {
	switch (type)
	{
	    case NEW_BEAN:
		return getEmailAddress() != null && getEmailAddress().length() >0 &&
		getIdCardNumber() != null && getModClassName() != null;

	    case EXISTING_BEAN:
		return getIdCardNumber() != null && getIdCardNumber().length() >0;

	}
	return false;
    }

    public String getModClassName()
    {
	return modClass;
    }

    public void setModClass(String modClass)
    {
	this.modClass = Bean.removeExtraSpaces(modClass);
    }

    public File getImage(){
	return this.image;
    }
    public void setImage(File image) throws InvalidImageFormatException
    {
	if( image != null ){
	    String name = image.getName();
	    name = name.substring(name.lastIndexOf( "."), name.length() );

	    boolean isValid = name.toLowerCase().matches( ".jpg|.png|.jpeg|.bmp|.gif");

	    if( isValid)
		this.image = image;
	    else
		throw new InvalidImageFormatException("The student image is inalid and thus cannot be set");

	}

    }


}
