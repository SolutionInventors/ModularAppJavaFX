package database.bean;

import java.sql.Date;

import utils.ValidationType;

/**
 * This subclass of @{@code Bean } represents one row in the {@code Certificate } table
 * in the database. This object is used by {@link CertificateManager} to perform
 * operations on the {@code Certificate} table in the database. <br>
 * A {@code Certificate} object contains useful getters and setters inputing and
 * retrieving data from the object
 * @author Oguejiofor Chidiebere
 *
 */
public class Certificate implements Bean
{
    
    private static final long serialVersionUID = 5927417714068250875L;
    private Date dateCreated;
    private String name;
    
    
    


    /**
     * Creates a Certificate object by specifing only the name attribute. If the 
     * {@code String} is valid then this object can now be added to the database after
     *  this constructor. 
     * @param name the name of the {@code Certificate}
     */
    public Certificate(String name)
    {
	setName( name );
    }

    /**
     * Retrieves the date in which this object was created. Note that this must be updated by
     * the {@link CertificateManager} class before it can contain a correct value.
     * @return a {@link java.sql.Date} object containing the date this class was created
     */
    public Date getDateCreated()
    {
        return dateCreated;
    }

    /**
     * Sets the date this object was created. This is used only by the {@link CertificateManager}
     * and should not be used anywhere else.
     * @param dateCreated the date this {@code Certificate} was created.
     */
    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    /**
     * Gets the name of this {@code Certificate} 
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * Removes unnecessary space in its argument  uses it to set this
     * object {@code name } attribute.
     * @param name
     */
    public void setName(String name)
    {
        this.name = Bean.capitalizeWords( Bean.removeExtraSpaces( name ) );
    }

    
    /**
     * Checks if this {@code Certificate } name attribute contains only leetters
     * @return {@code true} if the name attribute contains only letters
     */
    public  boolean isValid(ValidationType type)
    {
	return Bean.hasOnlyLetters( getName());
    }

    
}
