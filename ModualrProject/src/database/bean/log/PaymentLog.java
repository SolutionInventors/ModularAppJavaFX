package database.bean.log;

import java.sql.Date;

public class PaymentLog implements Log
{
    private int regId;
    private LogType operationType;
    private Date dateOfOperation;
    private String studentId ;
    private String moduleName;
    private double amount;
   
    public PaymentLog(){}
    
    public PaymentLog( int regId, String operationType, Date operationDate,
	    String studId, String modName, double amount )
    {
	setRegId(regId);
	setOperationType(LogType.getLogType(operationType ));
	setDateOfOperation(operationDate);
	setStudentId(studId);
	setModuleName(modName);
	setAmount(amount);
    }
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
  

    public String getStudentId()
    {
        return studentId;
    }

    public void setStudentId(String studentId)
    {
        this.studentId = studentId;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public int getRegId()
    {
        return regId;
    }

    public void setRegId(int regId)
    {
        this.regId = regId;
    }
}
