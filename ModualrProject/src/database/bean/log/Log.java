package database.bean.log;

import java.sql.Date;
import java.util.Calendar;
import java.util.Locale;

import database.managers.LogManager;

/**
 * The Log class is a super class for all the  logging tables in the database. 
 * The logging tables keep track of transactions that are performed in the 
 * database. These transactions are tracked via triggers created in the database.<br>
 * Note that <b>TRANSACTIONS</b> are modifications to the table namely 
 * INSERTS, DELETES and UPDATES. This term is used throughout the comments<br>
 * One feature of a {@code Log } is that it is immutable. This means that once initialised
 * there is no way of changing the values that it contains. Also, they store the
 * date the transaction was made and the type of transaction that was performed. 
 * Note that {@code Log}s may contain {@code null} for values that are intuitively
 * empty. For instance, the {@code newName} and {@code oldName} attribute of
 * a {@link ModularClassLog} contains null for DELETE and INSERT transactions 
 * respectively <br>
 * All implementation of the {@link LogManager} as their manager class. 
 * @author Oguejifor Chidiebere
 *
 */
public abstract class  Log
{
    private final Date DATE_OF_OPERATION;
    private final TransactionType OPERATION_TYPE;
    
    /**
     * Initializes the date of operation and the operation type. This is common to all
     * implementations
     * @param dateOfOperation
     * @param type
     */
    public Log(Date dateOfOperation, TransactionType type ){
	DATE_OF_OPERATION = dateOfOperation;
	OPERATION_TYPE = type;
    }

    /**
     * Gets the date the transaction was performed
     * @return a {@link Date}
     */
    public Date getDateOfOperation()
    {
        return DATE_OF_OPERATION;
    }

    /**
     * Gets the type of operation that was performed. This should contain INSERT, 
     * DELETE or UPDATE
     * @see OpertionType
     * @return an {@code TransactionType } object
     */
    public TransactionType getOperationType()
    {
        return OPERATION_TYPE;
    }
    
    /**
     * This method returns a description of the operation that is stored in this
     * {@code Log }. This what is called in the {@link #toString()} method. 
     * Concrete implementations are found in the subclasses
     * @return a {@code String } containing a descrioption of the operation
     */
    public abstract String logDescription(); 
    
    
    /**
     * Converts the java.sql.Date object returned by {@link #getDateOfOperation()}
     * to a {@link Calendar} object
     * @return a {@code Calendar} containing a representation of the date of operation
     */
    private Calendar getOperationDateAsCalendar(){
	Calendar calendar = Calendar.getInstance();
	
	calendar.setTime(new java.util.Date(getDateOfOperation().getTime()));
	return calendar; 
	
    }
    
    /**
     * Converts the {@link Date} returned by {@link #getDateOfOperation()} to a
     * {@code String } representation in the format dd-mmm-yyyy
     * @return a {@code String } representation of Date
     */
    public String getDateAsString(){
	Calendar calendar = getOperationDateAsCalendar(); 
	return String.format("%s-%s-%s", 
		calendar.get(Calendar.DAY_OF_MONTH), 
		calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT_STANDALONE,Locale.ENGLISH), 
		calendar.get(Calendar.YEAR));
    }
    
    /**
     * Returns  {@link #logDescription()}
     */
    @Override
    public String toString(){
	return  logDescription(); 
    }
}
