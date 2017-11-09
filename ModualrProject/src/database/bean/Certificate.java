package database.bean;

import java.sql.Date;

public class Certificate
{
    private Date dateCreated;
    private String name;
    
    public Certificate(){}
    
    public  Certificate( Date dateCreated, String name ) {
	setDateCreated( dateCreated );
	setName(name);
	
    }

    /**
     * Creates a Certificate object by specifing only the name attribute. This object
     * can now be added to the database after this constructor is used.
     * @param name
     */
    public Certificate(String name)
    {
	setName( name );
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
        this.name = Bean.removeExtraSpaces( name );
    }

    public static boolean isValid(Certificate cert)
    {
	return Bean.hasOnlyLetters( cert.getName());
    }

    
}
