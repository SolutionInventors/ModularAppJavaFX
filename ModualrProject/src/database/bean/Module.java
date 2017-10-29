package database.bean;

/**
 * An object of {@code Module} represents a single row of the {@code Module} table in the database
 * @author Oguejiofor Chidiebere
 *
 */
public class Module  implements Bean
{
    private String name;
    private int numberOfUnits;
   
    
    public Module( String name , int units ){
	setName( name );
	setNumberOfUnits(units);
    }
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
    
    
    
}
