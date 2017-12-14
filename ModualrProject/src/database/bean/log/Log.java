package database.bean.log;

import java.sql.Date;

public abstract class  Log
{
    private final Date DATE_OF_OPERATION;
    private final LogType OPERATION_TYPE;
    
    public Log(Date dateOfOperation, String type ){
	DATE_OF_OPERATION = dateOfOperation;
	OPERATION_TYPE = LogType.getLogType(type);
    }

    public Date getDateOfOperation()
    {
        return DATE_OF_OPERATION;
    }

    public LogType getOperationType()
    {
        return OPERATION_TYPE;
    }
    
}
