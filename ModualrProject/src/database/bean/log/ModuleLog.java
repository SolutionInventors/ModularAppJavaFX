package database.bean.log;

import java.sql.Date;

/**
 * The {@code ModuleLog} keeps track of the transactions that occur in the 
 * {@code ModuleTabTable} table in the database. These informations are updated via 
 * triggers assigned to the {@code ModuleTabTable} table.<br>
 * Once initialized a {@code ModuleLog } cannot data cannot be changed thus it 
 * is immutable
 * @author Oguejiofor Chidiebere
 * @see ModuleTabTable
 *
 */
public class ModuleLog extends Log
{
    private final String NEW_MODULE_NAME;
    private final String OLD_MODULE_NAME;

    /**
     * Initialises this {@code ModuleLog} with the required fields
     * @param operationDate the date the transaction took place
     * @param operType an {@link TransactionType	} that contains the type of
     * transaction that took place
     * @param oldModName the module name before the transaction
     * @param newModuleName the module name after the transaction
     */
    public ModuleLog( Date operationDate, TransactionType operType,String oldModName, 
	    String newModuleName)
    {
	super( operationDate , operType);
	NEW_MODULE_NAME = newModuleName;
	OLD_MODULE_NAME = oldModName;
    }
   
    /**
     * Gets the module name after the transaction occured. May
     * contain {@code null} for DELETEs
     * @return
     */
    public String getNewModuleName()
    {
        return NEW_MODULE_NAME;
    }

    /**
     * Gets the name of the module after the transaction occured. 
     * Note that this may contain {@code null} for INSERTs
     * @return
     */
    public String getOldModuleName()
    {
        return OLD_MODULE_NAME;
    }
}
