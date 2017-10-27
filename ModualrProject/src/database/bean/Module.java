package database.bean;

/**
 * This class represents the attributes of the {@code Module} table in the database
 * @author Oguejiofor Chidiebere
 *
 */
public class Module
{
    private String name;
    private int numberOfUnits;
    private int id;
    
    /**
     * Gets the name of this {@code Module}
     * @return {@code String}
     */
    public String getName()
    {
        return name;
    }
    /** 
     * Sets the name of this {@code Module}
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Gets the number of units in this {@code Module}
     * @return {@code int}
     */
    public int getNumberOfUnits()
    {
        return numberOfUnits;
    }
    
    /**
     * Sets the number of units in this {@code Module}
     * @param numberOfUnits
     */
    public void setNumberOfUnits(int numberOfUnits)
    {
        this.numberOfUnits = numberOfUnits;
    }
    
    /**
     * Gets the id of this {@code Module}
     * @return
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Sets the id of this {@code Module}
     * @param the int value id
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
}
