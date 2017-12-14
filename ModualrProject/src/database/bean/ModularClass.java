package database.bean;

import java.sql.Date;

import database.managers.ModularClassManager;
import utils.ValidationType;

/**
 * This object represents single  {@code ModularClass} table in the database. 
 * The {@code ModularClass} table contains basic information about a class
 * namely the class's name and when it was created. <br>
 * The date of creation is automatically set in the database and is updated on the
 * {@code ModularClass} objects when a transaction is made. <br>
 * A {@code ModularClass } is later used to group {@code Student}s in the 
 * database 
 * @author Oguejiofor Chidiebere
 * @see ModularClassManager
 * @since v1.0
 */
public class ModularClass implements Bean
{
    
    private static final long serialVersionUID = -8749403889379183132L;
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
    
    /**
     * Gets the name of this {@code ModularClass} as a {@code String}
     * @return a {@code String} containing this {@code ModularClass} name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Removes unwanted space in its argument, capitalises the first letter of each 
     * word  and uses that to set the name of this {@code ModularClass}
     * @param name a {@code String} containing the name of this {@code ModularClass}
     */
    public void setName(String name)
    {
        this.name = Bean.capitalizeWords( Bean.removeExtraSpaces( name));
    }

    /**
     * Gets the date in which this {@code ModularClass} was created in the database
     * Note that this information must be updated by the {@link ModularClassManager}
     * before it can be correct.
     * @return a {@link java.sql.Date} representing the date this class was created.
     */
    public Date getDateCreated()
    {
        return dateCreated;
    }

    /**
     * Sets the date this object was created 
     * @param dateCreated a {@link java.sql.Date} object that contains the
     * date this object was created.
     */
    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    /**
     * Returns {@code true } if the name of this modular class contains only 
     * alphabets or/and numbers. The name must contain at least one 
     * alphabet and must begin with an alphabet.
     */
    public  boolean isValid(ValidationType type)
    {
	return getName().matches("[A-Za-z]{1,}[\\s|A-Za-z|0-9]*");
    }

    
}
