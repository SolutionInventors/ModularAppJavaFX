package database.bean.log;

import java.sql.Date;

/**
 * A {@code PaymentLog } represents a single row in the {@code PaymentLog} table
 * The  values in this table are updated via triggers in the {@code Payment} table
 * This object must be retrieved from t {@link database.managers.LogManager} for it
 * to be accurate
 * @author Oguejiofor Chidiebere 
 * @see database.bean.Payment
 */
public class PaymentLog extends Log
{
    private final int REG_ID;
    private final String STUDENT_ID ;
    private final String MODULE_NAME;
    private final double AMOUNT;
    private final String BANK_NAME;
    
    /**
     * Initializes this object with the required parameters. 
     * @param operationDate the date the transaction took place
     * @param transactionType the type of transaction that took place
     * @param regId the regId that was paid for
     * @param studId the id card number of the student that paid for the course
     * @param moduleName the name of the module that was paid for
     * @param bankName the name of the bank
     * @param amount the amount that was paid for
     */
    
    public PaymentLog( Date operationDate,TransactionType transactionType, int regId,  
	    String studId, String moduleName, String bankName,  double amount )
    {
	super( operationDate, transactionType);
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

    /**
     * Gets the amount that was paid for as {@code double}
     * @return
     */
    public double getAmount()
    {
	return AMOUNT;
    }

    /**
     * Gets the regId that is being paid for. 
     * @return an {@code int } representing the regId
     */
    public int getRegID()
    {
	return REG_ID;
    }

    
    /**
     * Gets the name of the module that is being paid for
     * @return a {@code String}
     */
    public String getModuleName()
    {
        return MODULE_NAME;
    }

    
    /**
     * Getst the name of the bank in which the payment was made
     * @return a {@code String}
     */
    public String getBankName()
    {
        return BANK_NAME;
    }

    @Override
    public String logDescription()
    {
	switch(getOperationType()){
	    case INSERT:
		return String.format("NGN%s was paid in %s bank on %s for regID %s ", 
			AMOUNT, BANK_NAME, getDateAsString(), REG_ID); 
	    default:
		return String.format("Some operation was performed on regID %s on %s " , 
			REG_ID, getDateAsString());
	    
	}
    }

}
