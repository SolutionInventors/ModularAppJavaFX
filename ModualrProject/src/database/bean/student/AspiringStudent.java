package database.bean.student;

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
    
}
