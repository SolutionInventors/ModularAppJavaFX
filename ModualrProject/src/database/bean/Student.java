package database.bean;

public class Student {
    private String idCardNumber;
    private String firstName;
    private String lastName;
    private boolean active;
    private String emailAddress;
    
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
    
    
}
