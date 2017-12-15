package database.bean.log;

import java.sql.Date;

public class PaymentLog extends Log
{
    private final int REG_ID;
    private final String STUDENT_ID ;
    private final String MODULE_NAME;
    private final double AMOUNT;
    private final String BANK_NAME;
    
    public PaymentLog( Date operationDate,String operationType, int regId,  
	    String studId, String moduleName, String bankName,  double amount )
    {
	super( operationDate, operationType);
	REG_ID = regId;
	STUDENT_ID = studId;
	AMOUNT  = amount;
	MODULE_NAME = moduleName;
	BANK_NAME= bankName;
    }

    public String getStudentID()
    {
	return STUDENT_ID;
    }


    public double getAmount()
    {
	return AMOUNT;
    }


    public int getRegID()
    {
	return REG_ID;
    }

    

    public String getModuleName()
    {
        return MODULE_NAME;
    }

    

    public String getBankName()
    {
        return BANK_NAME;
    }

}
