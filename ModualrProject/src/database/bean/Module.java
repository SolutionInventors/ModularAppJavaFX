package database.bean;

import java.sql.Date;

import database.managers.ModuleManager;
import utils.ValidationType;

/**
 * An {@code ModuleTabTable} object represents a single row of the {@code ModuleTabTable} table
 *  in the database. A  {@code ModuleTabTable} contains the module name, number of units, 
 *  amount per unit and the date it was created. The date a {@code ModuleTabTable } was
 *  created is generated automatically in the database. Thus it must be gotten via
 *   {@link ModuleManager} for it to be accurate
 * @author Oguejiofor Chidiebere

 */
public class Module  implements Bean
{

    private static final long serialVersionUID = -6397945406914315666L;
    private String name;
    private int numberOfUnits;
    private double amountPerUnit;
    private Date dateCreated;

    /**
     * Initializes this {@code ModuleTabTable } by specifying the name, units and amount.
     * This constructor contains the major requirements for inserting s{@code ModuleTabTable} 
     * in the database. These values must be present before this {@code ModuleTabTable} 
     * can be added successfully
     * @param name this {@code ModuleTabTable } name as {@code String}
     * @param units the number of units in this {@code ModuleTabTable} 
     * @param amountPerUnit  the price of one unit in this {@code ModuleTabTable}
     */
    public Module( String name , int units, double amountPerUnit ){
	setName( name );
	setNumberOfUnits(units);
	setAmountPerUnit(amountPerUnit);
    }

    public Module(){}

    /**
     * Initializes this {@code ModuleTabTable } with only this {@code ModuleTabTable} name
     * This constructor can be used when we want to remove/ delete a {@code ModuleTabTable}
     *  from the database
     * @param name the name of this {@code ModuleTabTable} object
     */
    public Module( String name){
	setName( name );
    }
    
    /**
     * Gets the name of this {@code ModuleTabTable}
     * @return {@code String}
     */
    public String getName()
    {
	return name;
    }
    /** 
     * Sets the name of this {@code ModuleTabTable}
     * @param name
     */
    public void setName(String name)
    {
	this.name = Bean.removeExtraSpaces( name ) ;
    }

    /**
     * Gets the number of units in this {@code ModuleTabTable}
     * @return {@code int}
     */
    public int getNumberOfUnits()
    {
	return numberOfUnits;
    }

    /**
     * Sets the number of units in this {@code ModuleTabTable}
     * @param numberOfUnits
     */
    public void setNumberOfUnits(int numberOfUnits)
    {
	this.numberOfUnits = numberOfUnits;
    }

    /**
     * Gets the amount per unit of this {@code ModuleTabTable} as a {@code double} value 
     * @return a {@code double} containing the amount per unit
     */
    public double getAmountPerUnit()
    {
	return amountPerUnit;
    }
    
    /**
     * Sets the amount per unit for this {@code ModuleTabTable} 
     * @param amountPerUnit a {@code double} containing the amount per unit
     */
    public void setAmountPerUnit(double amountPerUnit)
    {
	this.amountPerUnit = amountPerUnit;
    }
    /** 
     * Checks that a {@code ModuleTabTable } object by ensuring that the object is not null,
     * its name attribute is not {@code null} and its units is greater than 0.
     * Note that it does not connect to the database but ensures that the {@code ModuleTabTable} 
     * object can be put perform transactions on the database
     * 
     * @param module the {@code ModuleTabTable} object to be validated
     * @return true when the {@code ModuleTabTable } is valid
     */
    public  boolean isValid( ValidationType validation )
    {
	switch (validation)
	{
	    case EXISTING_BEAN:
		return validateName();
	    case NEW_BEAN:
		return ( validateName() && validateAmount());
	}
	return false;
    }

    /**
     * Checks if the amountPerUnit stored in this {@code ModuleTabTable} is greater
     * than 0 
     * @return {@code true} if amount is greater than 0
     */
    public boolean validateAmount()
    {
	return getNumberOfUnits() >0;
    }

    /**
     * Checks if the name of this object is not {@code null }, that is begins with
     * a letter  and that it contains only letters or/and numbers.
     * @return
     */
    public boolean validateName()
    {
	String name = getName();
	return name != null && Bean.containsEitherAlphaNum(name) && 
		name.substring(0,1).matches("[A-Za-z]");
    }
    
    /**
     * Gets the date in which this {@code ModuleTabTable } was created. Note that this must be
     * retrieved from the database before it can be accurate.
     * @return a {@link java.sql.Date} containing the date this module was inserted 
     * into the database
     */ 
    public Date getDateCreated()
    {
	return dateCreated;
    }
    
    /**
     * Sets the date the module was created. This should be called only by the 
     * {@link ModuleManager}
     * @param dateCreated the date this object was created
     */
    public void setDateCreated(Date dateCreated)
    {
	this.dateCreated = dateCreated;
    }

}
