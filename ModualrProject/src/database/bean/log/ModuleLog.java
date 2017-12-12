package database.bean.log;

import java.sql.Date;

public class ModuleLog implements Log
{
    private Date dateOfOperation;
    private String moduleName;
    private LogType operationType;
    
    
    public ModuleLog(String modName, String operType, Date operationDate)
    {
	setModuleName(modName);
	setOperationType( LogType.getLogType( operType) );
	setDateOfOperation(operationDate);
    }

    public Date getDateOfOperation()
    {
        return dateOfOperation;
    }

    public void setDateOfOperation(Date dateOfOperation)
    {
        this.dateOfOperation = dateOfOperation;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }

    public LogType getOperationType()
    {
        return operationType;
    }

    public void setOperationType(LogType operationType)
    {
        this.operationType = operationType;
    }

}
