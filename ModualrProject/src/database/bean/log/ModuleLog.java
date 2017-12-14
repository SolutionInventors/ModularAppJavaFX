package database.bean.log;

import java.sql.Date;

public class ModuleLog extends Log
{
    private String moduleName;


    public ModuleLog(String modName, String operType, Date operationDate)
    {
	super( operationDate , operType);
	setModuleName(modName);
    }

    public String getModuleName()
    {
	return moduleName;
    }

    public void setModuleName(String moduleName)
    {
	this.moduleName = moduleName;
    }
}
