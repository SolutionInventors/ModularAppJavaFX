package database.bean;

import java.sql.Date;

import utils.ValidationType;

/**
 * This object represents single  {@code ModularClass} table in the database. 
 * The {@code ModularClass} table contains basic information about a class
 * namely the class's name and when it was created. <br>
 * The date of creation is automatically set in the database. <br>
 * A {@code ModularClass } is later used to group {@code Student}s in the 
 * database 
 * @author Chidiebere
 * @since v1.0
 */
public class ModularClass implements Bean
{
    private String name;
    private Date dateCreated;
    
    public ModularClass(){}

    /**
     * Initializes this object by setting the name of this {@code ModularClass}.
     * Should be used when inserting a new {@code ModularClass}
     * @param name
     */
    public ModularClass( String name ) {
	setName( name );
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
