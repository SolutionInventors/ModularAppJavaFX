package database.bean;

import java.sql.Date;

public class Certificate implements Bean
{
    
    private static final long serialVersionUID = 5927417714068250875L;
    private Date dateCreated;
    private String name;
    
    /**
     * This initialise this {@code Certificate} with none of its parameters initialised
     * to {@code null }
     * @author Oguejiofor Chidiebere
     */
    public Certificate(){}
    


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

    public  boolean isValid(ValidationType type)
    {
	return Bean.hasOnlyLetters( getName());
    }

    
}
