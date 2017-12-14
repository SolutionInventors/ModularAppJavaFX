package database.bean.log;

import java.sql.Date;

public class PaymentLog extends Log
{
    private int regId;
    private String studentId ;
    private String moduleName;
    private double amount;

    public PaymentLog( int regId, String operationType, Date operationDate,
	    String studId, String modName, double amount )
    {
	super( operationDate, operationType);
	setRegId(regId);
	setStudentId(studId);
	setModuleName(modName);
	setAmount(amount);
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
