package database.bean;

import java.sql.Date;

/**
 * An object of {@code Module} represents a single row of the {@code Module} table in the database
 * @author Oguejiofor Chidiebere
 *
 */
public class Module  implements Bean
{
    private String name;
    private int numberOfUnits;
    private double amountPerUnit;
    private Date dateCreated;
    
    /**
     * Initialisses this {@code Module } by specifying the name, units and amount.
     * This constructor contains the major requirements for add or update a new {@code Module} in
     * the database. These values must be present before this {@code Module} 
     * can be added successfully
     * @param name this {@code Module } name as {@code String}
     * @param units the number of units in this {@code Module} 
     * @param amountPerUnit  the price of one unit in this {@code Module}
     */
    public Module( String name , int units, double amountPerUnit ){
	setName( name );
	setNumberOfUnits(units);
	setAmountPerUnit(amountPerUnit);
    }
    
    public Module(){}
    
    /**
     * Initialises this {@code Module } with only this {@code Module} name
     * This constructor can be used when we want to remove/ delete a {@code Module} from
     *  the database
     * @param name
     */
    public Module( String name){
	setName( name );
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
        this.name = Bean.removeExtraSpaces( name ) ;
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
    
    public double getAmountPerUnit()
    {
	return amountPerUnit;
    }
    public void setAmountPerUnit(double amountPerUnit)
    {
	this.amountPerUnit = amountPerUnit;
    }
    /** 
     * Checks that a {@code Module } object by ensuring that the object is not null,
     * its name attribute is not {@code null} and its units is greater than 0.
     * Note that it does not connect to the database but ensures that the {@code Module} 
     * object can be put perform transactions on the database
     * 
     * @param module the {@code Module} object to be validated
     * @return true when the {@code Module } is valid
     */
    public static boolean isValid(Module module, ValidationType validation )
    {
	boolean isNameValid   =  module != null && module.getName() != null ;
	boolean isUnitsValid = module.getNumberOfUnits() >0 ; 
	
	switch (validation)
	{
	    case EXISTING_BEAN_VALID:
		return isNameValid;
	    case NEW_BEAN_VALID:
		return ( isNameValid && isUnitsValid );
	    default:
		return false;
	}
	
    }
    public Date getDateCreated()
    {
	return dateCreated;
    }
    public void setDateCreated(Date dateCreated)
    {
	this.dateCreated = dateCreated;
    }
    
}
