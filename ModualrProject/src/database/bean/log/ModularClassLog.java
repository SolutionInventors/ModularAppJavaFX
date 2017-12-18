package database.bean.log;

import java.sql.Date;

/**
 * A {@code ModularClassLog} keeps track of the transactions that are performed
 * in the {@code ModularClass} table in the database. The {@code ModularClassLog}
 * contains {@code null} values for old name if the transaction is an INSERT. 
 * It also contain {@code null} for newNam if the transaction is a DELETE
 * @author Oguejiofor Chidiebere
 * @see database.bean.ModularClass
 */
public class ModularClassLog  extends Log
{
    private final String NEW_NAME;
    private final String OLD_NAME;
   
    /**
     * Initializes this {@code ModularClassLog} with the required data
     * @param operationDate the date the transaction took place
     * @param transactionType the type of transaction that took place
     * @param newName the ModularClass name after the transaction
     * @param oldName the ModularClass name after the transaction took place
     */
    public ModularClassLog( Date operationDate, TransactionType transactionType, 
	    String newName, String oldName )
    {
	super(operationDate, transactionType );
	NEW_NAME = newName;
	OLD_NAME = oldName;
	
    }

    /**
     * Gets the name of the {@code ModularClass} after the transaction
     * @return
     */
    public String getNewName()
    {
        return NEW_NAME;
    }

    /**
     * Gets the name of the modular class after the transaction. 
     * @return {@code String} containing the old module name
     */
    public String getOldName()
    {
        return OLD_NAME;
    }

}
