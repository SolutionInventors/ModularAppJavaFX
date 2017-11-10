package database.bean;

import java.sql.Date;

public class ModularClass implements Bean
{
    private String name;
    private Date dateCreated;
    
    public ModularClass(){}

    /**
     * Initialises this object by setting the name of this {@code ModularClass}.
     * Should be used when inserting a new {@code ModularClass}
     * @param name
     */
    public ModularClass( String name ) {
	setName( name );
    }
    public ModularClass( String name , Date dateCreated ){
	setName( name );
	setDateCreated(dateCreated);
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = Bean.removeExtraSpaces( name);
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public  boolean isValid(ValidationType type)
    {
	return getName().matches("[A-Za-z]{1,}[\\s|A-Za-z|0-9]*");
    }

    
}
