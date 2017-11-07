package database.bean;

import java.sql.Date;

public class Class
{
    private String name;
    private Date dateCreated;
    
    public Class(){}

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public static boolean isValid(Class newClass)
    {
	return Bean.hasOnlyLetters( newClass.getName());
    }

    
}
