package database.bean.student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import database.bean.Bean;
import utils.ValidationType;

/**
 * The {@code Student} table contains only the frequently required information
 * about a {@code Student} including  his id card number, email, className etc.
 * The {@code idCardNumber} attribute is this table's primary key thus it is
 * referenced by other tables.<br>
 * Other Informations about a {@code Student} ( such as his phone number, 
 * {@code ProfessionalExperience} etc.)can be retrieved from his {@code StudentData} 
 * object.
 * @author Oguejiofor Chidiebere
 *
 */
public class Student  implements Bean 
{

    private static final long serialVersionUID = 1L;

    private static File defaultImage;

    /**Foreign key to Modular Class table*/
    private  String modClass;

    private String idCardNumber; //primary key

    private String firstName; //gotten from Biodata table for convenience
    private String lastName; //gotten from Biodata table for convenience


    private boolean active;
    private String emailAddress;
    private String certificateIssued;
    private Date dateAdmitted;

    private File image;

    /**
     * This initializes a this {@code Student} by specifyiing the required data to 
     * input this object into the database. 
     * This {@code Student } can be inputed if the parameters inputed are valid. The
     * Validity can be checked via call to {@link isValid}
     * @param id the id_card_number of the {@code Student}
     * @param className the {@code ModularClass} that the student would be inputed
     * into
     * @param mail the email address of this {@code Student}
     * @param firstName the first name of this {@code Student} 
     * @param lastName the last name of this {@code Student}
     */
    public Student(String firstName, String lastName, String id, String className,  String mail,  File image)
    {
	setFirstName(firstName);
	setLastName(lastName);
	setModClass(className);
	setIdCardNumber(id);
	setEmailAddress(mail);
	setImage( image);
    }

   

    /**
     * Initializes this {@code Student } by specifying only the id card number
     * Once this object is initialized with this constructor, it can then be
     * used to test if a {@code Student} exists using the id card number. It
     * can also be used to retrieve a {@code Student } from the database
     * @param studId
     */
    public Student(String idCardNumber)
    {
	setIdCardNumber(idCardNumber);
    }

    /**
     * Gets the {@code idCardNumber } of this student
     * @return a {@code String} representing the id card number of this student
     */
    public String getIdCardNumber()
    {
	return idCardNumber;
    }

    /**
     * Sets the {@code idCardNumeber} of this student
     * @param idCardNumber
     */
    public void setIdCardNumber(String idCardNumber)
    {
	this.idCardNumber = Bean.removeExtraSpaces(idCardNumber).toUpperCase();
    }

    /**
     * Returns {@code true } if this {@code Student }is active
     * @return {@code boolean}
     */
    public boolean isActive()
    {
	return active;
    }

    /**
     * Sets {@code active} attribute contained in this {@code Student}
     * @param active
     */
    public void setActive(boolean active)
    {
	this.active = active;
    }

    /**
     * Gets this {@code Student}'s email address 
     * @return {@code String} containing email
     */
    public String getEmailAddress()
    {
	return emailAddress;
    }

    /**
     * Sets the email address by first removing any spaces
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress)
    {
	this.emailAddress = emailAddress.replaceAll("\\s", "");
    }



    /**
     * Gets the date this {@code Student } was admitted 
     * @param date
     */
    public void setDateAdmitted(Date date)
    {
	dateAdmitted = date;
    }

    /**
     * Gets the date this {@code Student} was admitted
     * @return
     */
    public Date getDateAdmitted()
    {
	return dateAdmitted ;
    }

    /**
     * Gets the certificate that this {@code Student} was issued as
     * a {@code String}.
     * @return
     */
    public String getCertificateIssued()
    {
	return certificateIssued;
    }

    /**
     * Set the certificate that this {@code Student} was issued
     * @param certificateIssued
     */
    public void setCertificateIssued(String certificateIssued)
    {
	this.certificateIssued = certificateIssued != null ? 
		Bean.capitalizeWords( certificateIssued): "None"; 
    }

    /**
     * 
     */
    @Override
    public boolean isValid(ValidationType type)
    {
	switch (type)
	{
	    case NEW_BEAN:
		return isImageValid()  && isEmailValid() &&
			validateStudentID(getIdCardNumber()) && 
			getModClassName() != null;
	    case EXISTING_BEAN:
		return validateStudentID(getIdCardNumber());
	}
	return false;
    }


    public boolean isEmailValid()
    {
	return getEmailAddress() != null && getEmailAddress().length() >0;
    }
    /**
     * @return
     */
    public boolean isImageValid()
    {
	if( image == null ) return false;
	String name = image.getName();
	name = name.substring(name.lastIndexOf( "."), name.length() );
	boolean isImageValid = name.toLowerCase().matches( ".jpg|.png|.jpeg|.bmp|.gif");
	return isImageValid;
    }

    /**
     * Gets this {@code Student}'s class name
     * @return a {@code String}
     */
    public String getModClassName()
    {
	return modClass;
    }

    /**
     * Sets this {@code Student}'s class
     * @param modClass
     */
    public void setModClass(String modClass)
    {
	this.modClass = Bean.capitalizeWords(modClass);
    }

    /**
     * Gets the image of this {@code Student } as a {@code File}. 
     * @return
     */
    public File getImage(){
	return this.image;
    }

    /**
     * Sets the image of this {@code Student} as a {@code File}
     * @param image
     */
    public void setImage(File image) 
    {
	this.image = image != null ?image : defaultImage;
    }

    /**
     * Checks if the {@code String } passed as argument is a valid 
     * studentID. This method is used by other classes that reference
     * studentID
     * @param studentID
     * @return
     */
    public static boolean validateStudentID( String studentID ){
	return studentID != null && studentID.length() > 0;
    }

    /**
     * Gets the first name of this {@code Student} as a {@code String}
     * @return a {@code String} containing the student first naem;
     */
    public String getFirstName()
    {
	return firstName;
    }

    public void setFirstName(String firstName)
    {
	this.firstName = Bean.capitalizeWords(firstName);
    }

    public String getLastName()
    {
	return lastName;
    }

    public void setLastName(String lastName)
    {
	this.lastName = Bean.capitalizeWords(lastName);
    }

    /**
     * Checks that all the names( Surname and lastName ) contain at most 50 characters
     * and that they are only letters 
     * 
     * @return
     */
    public  boolean validateName()
    {
	return getFirstName().matches( "[A-Za-z]{1,50}") && 
		getLastName().matches( "[A-Za-z]{1,50}" ); 

    }

    public static void setDefaultImage(InputStream inStream)
    {
	defaultImage = getImageFromStream("default-image", inStream);
    }

    /**
     * Converts an {@code InputStream} to a {@code File} object
     * @param fileName output file name 
     * @param inputStream the input stream that contains the image
     * @return
     */
    public static File getImageFromStream( String fileName , InputStream inputStream)
    {
	File studentImage = null;
	FileOutputStream output = null;
	try{
	    if( inputStream.available() <=0 ) return defaultImage;
	    studentImage = new File( "res\\" + fileName + ".jpg" );
	    output = new FileOutputStream( studentImage );
	    
	    byte[] buffer = new byte[1024];
	    while( inputStream.read( buffer) >0 ){
		output.write( buffer );
	    }
	    studentImage.deleteOnExit();
	}

	catch (IOException e)
	{
	    e.printStackTrace();
	    return null;
	}
	finally
	{
	    try
	    {
		if( output !=null ) output.close();
		if( inputStream != null ) inputStream.close();
	    }
	    catch (IOException e)
	    {
		e.printStackTrace();
	    }
	}
	return studentImage;
    }

    public static File getdefaultImage()
    {
	return defaultImage;
    }




}
