package database.bean;

import java.sql.Date;

import utils.ValidationType;

/**
 * A {@code Payment } object contains information about a single entity in 
 * Payment table in the database.<br> A {@code Payment } object contains information
 * about how the  payment was made including the studentId, the 
 * {@code ModuleRegiser} id, the amount that was paid, the bankName, the 
 * tellerNumber and the paymentDate. <br>
 * All these can be retrieved from the {@code Payment}'s getters.<br>
 * Note that multiple payments can be made for thesame module register id. Thus
 * this object contains an id attribute of its own
 * @author Oguejiofor Chidiebere
 * @see ModuleRegister
 *@since v1.0
 */
public class Payment implements Bean 
{
  
    private static final long serialVersionUID = 6495853015546110411L;
    private int id;
    private int moduleRegisterId;
    private double amount;
    private String bankName;
    private String tellerNumber;
    private Date paymentDate;
    
    
    public Payment( int id , int modRegId, double amount, String bank , 
	    String tellerNum , Date payDate ){
	setId(id);
	setModuleRegisterId(modRegId);
	setAmount(amount);
	setBankName(bank);
	setTellerNumber(tellerNum);
	setPaymentDate(payDate);
    }
    public Payment(){}
    
    /**
     * Gets the id of this {@code Payment} object as an {@code int}
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sets the id attribute stored in this {@code Payment}. This should
     * be gotten from the {@link database.managers.PaymentManager}
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Gets the moduleRegisterId stored in this object
     * @return
     */
    public int getModuleRegisterId()
    {
        return moduleRegisterId;
    }

    /**
     * Sets the id module register id that is being referenced by
     * this {@code Payment } object
     * @param moduleRegisterId
     */
    public void setModuleRegisterId(int moduleRegisterId)
    {
        this.moduleRegisterId = moduleRegisterId;
    }

    /**
     * Gets the amount that was paid in this {@code Payment} object
     * @return a {@code double} containing the amount paid
     */
    public double getAmount()
    {
        return amount;
    }

    /**
     * Sets the amount that was paid. 
     * @param amount a {@code double} containing the amount paid
     */
    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    /**
     * Gets the bank name in which the payment was made
     * @return a {@code String} containing 
     */
    public String getBankName()
    {
        return bankName;
    }
    
    /**
     * Capitalizes the first letter in each word in the {@code String} passed
     * as an argument and uses it to set the name of the bank in which this
     * {@code Payment} was made
     * @param bankName a {@code String} containing bank name 
     */
    public void setBankName(String bankName)
    {
        this.bankName = Bean.capitalizeWords( bankName);
    }

    /**
     * Gets  the teller number stored in this {@code Payment} 
     * @return a {@code String}
     */
    public String getTellerNumber()
    {
        return tellerNumber;
    }

    /**
     * Sets the teller number of this object.
     * @param tellerNumber the teller number with which the payment was made
     */
    public void setTellerNumber(String tellerNumber)
    {
        this.tellerNumber = tellerNumber.replaceAll("\\s{1,}", "");
    }

    /**
     * Gets the date in which the payment was made
     * @return the date in which the payment was made as {@link java.sql.Date}  
     */
    public Date getPaymentDate()
    {
        return paymentDate;
    }

    /**
     * Sets the date in which the payment was made. 
     * @param paymentDate the date in which the payment was made
     */
    public void setPaymentDate(Date paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    /**
     * Checks that this {@code Payment } according to the {@code ValidationType}
     * passed as an argument.<br>
     * If the type is {@code ValidationType.NEW_BEAN} then it tests that all the 
     * {@code tellerNumber, paymentDate, bankName and amountPaid}
     * are all valid. <br>
     * If the type is {@code ValidationType.EXISTING_BEAN} then it tests that the
     * {@code id} attribute is present.<br>
     * You can perform these validation via call to the different validat.. methods
     * in this object. 
     */
    @Override
    public boolean isValid(ValidationType type)
    {
	switch( type){
	    case NEW_BEAN:
		return validateTellerNumber() && validateAmount() && 
			validateModuleRegId() && validateBankName() &&
			validateId(); 
	    case EXISTING_BEAN:
		return validateId();
	}
	return false;
    }
    
    /**
     * Checks that the id attribute stored in this object is greater than 0
     * @return {@code true } if the id is greater than zero
     */
    public boolean validateId()
    {
	return getId() >  0;
    }

    /**
     * Checks that the teller number stored in this object contains only
     * numbers.
     * @return
     */
    public boolean validateTellerNumber(){
	return Bean.hasOnlyNumbers( getTellerNumber());
    }
    
    /**
     * Checks that the amount paid is greater than zero
     * @return {@code true} if the payment has been done
     */
    public boolean validateAmount(){
	return getAmount() > 0;
    }

    /**
     * Checks that the amount paid is greater than zero
     * @return {@code true} if the payment has been done
     */
    public boolean validateModuleRegId(){
	return getModuleRegisterId() > 0;
    }
    
    public boolean validateBankName(){
	String bankName = getBankName();
	return bankName !=null && bankName.length()>0;
	
    }
}
