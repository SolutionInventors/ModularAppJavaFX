package database.bean;

import java.sql.Date;

/**
 * 
 * @author Chidiebere
 *
 */
public class Student  implements Bean 
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**Foreign key to Modular Class table*/
    private  String modClass;
    private String idCardNumber;
    private boolean active;
    private String emailAddress;
    private String certificateIssued;
    private Date dateAdmitted;
    
    public Student(String id, String className,  String mail, boolean active)
    {
	setModClass(className);
	setIdCardNumber(id);
	setEmailAddress(mail);
	setActive(active);
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
	// TODO Auto-generated method stub
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
}
