package database.bean.log;

import java.sql.Date;

public class CertificateLog implements Log
{
    private Date dateOfOperation;
    private int id;
    private LogType operationType;
    
    public CertificateLog(){}

    public Date getDateOfOperation()
    {
        return dateOfOperation;
    }

    public void setDateOfOperation(Date dateOfOperation)
    {
        this.dateOfOperation = dateOfOperation;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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
