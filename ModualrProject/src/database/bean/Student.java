package database.bean;

import java.sql.Date;

public class Student  implements Bean 
{
    private String idCardNumber;
    private Biodata biodata;
    private boolean active;
    private String emailAddress;
    private String certificateIssued;
    private Date dateAdmitted;
    private Phone[] phoneNumbers;
    private Biodata bio;
    
    public Student(String id, Biodata data, String mail, boolean active)
    {
	setIdCardNumber(id);
	setBioData(data);
	setEmailAddress(mail);
	setActive(active);
    }
    
    public Student(){}
    
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

    public static boolean isValid( Student student ){
	if( student != null && student.getEmailAddress()  != null && 
		Biodata.isValid( student.getBioData() ) )
	{
	    return true;
	}
	return false;
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

    public Biodata getBioData()
    {
	return biodata;
    }

    public void setBioData(Biodata biodata)
    {
	this.biodata = biodata;
    }

    public String getCertificateIssued()
    {
	return certificateIssued;
    }

    public void setCertificateIssued(String certificateIssued)
    {
	this.certificateIssued = certificateIssued;
    }

    public Biodata getBio()
    {
	return bio;
    }

    public void setBio(Biodata bio)
    {
	this.bio = bio;
    }

    @Override
    public boolean isValid(ValidationType type)
    {
	// TODO Auto-generated method stub
	return false;
    }
}
