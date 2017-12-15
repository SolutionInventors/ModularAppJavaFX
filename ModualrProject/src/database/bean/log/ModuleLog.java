package database.bean.log;

import java.sql.Date;

public class ModuleLog extends Log
{
    private final String NEW_MODULE_NAME;
    private final String OLD_MODULE_NAME;

    public ModuleLog( Date operationDate, String operType,String oldModName, 
	    String newModuleName)
    {
	super( operationDate , operType);
	NEW_MODULE_NAME = newModuleName;
	OLD_MODULE_NAME = oldModName;
    }
   
    public String getNewModuleName()
    {
        return NEW_MODULE_NAME;
    }

    public String getOldModuleName()
    {
        return OLD_MODULE_NAME;
    }
}
