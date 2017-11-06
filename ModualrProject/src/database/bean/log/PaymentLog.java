package database.bean.log;

import java.sql.Date;

public class PaymentLog implements Log
{
    private LogType operationType;
    private Date dateOfOperation;
    private int id ;
    
    public PaymentLog(){}
    
    public LogType getOperationType()
    {
        return operationType;
    }
    public void setOperationType(LogType operationType)
    {
        this.operationType = operationType;
    }
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
}
