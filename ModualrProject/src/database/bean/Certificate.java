package database.bean;

import java.sql.Date;

public class Certificate
{
    private Date dateCreated;
    private String name;
    
    public Certificate(){}
    
    public  Certificate( int id , Date dateCreated, String name ) {
	setDateCreated( dateCreated );
	setName(name);
	
    }

   
    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public static boolean isValid(Certificate cert)
    {
	return Bean.hasOnlyLetters( cert.getName());
    }

    
}
