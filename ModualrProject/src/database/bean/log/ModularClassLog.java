package database.bean.log;

import java.sql.Date;

public class ModularClassLog  extends Log
{
    private final String NEW_NAME;
    private final String OLD_NAME;
   
    public ModularClassLog( Date operationDate, String operationType, 
	    String newName, String oldName )
    {
	super(operationDate, operationType );
	NEW_NAME = newName;
	OLD_NAME = oldName;
	
    }

    public String getNewName()
    {
        return NEW_NAME;
    }

    public String getOldName()
    {
        return OLD_NAME;
    }

}
