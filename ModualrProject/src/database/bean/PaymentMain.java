package database.bean;

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
public class PaymentMain implements Bean 
{

    private static final long serialVersionUID = 6495853015546110411L;
    private int id;
    private int regId;
    private double amount;
   


    public PaymentMain( int id , int modRegId, double amount){
	setId(id);
	setModuleRegisterId(modRegId);
	setAmount(amount);
	
    }

    public PaymentMain(  int modRegId, double amount){
	setModuleRegisterId(modRegId);
	setAmount(amount);
	
    }

    public PaymentMain(){}

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
     * Gets the regId stored in this object
     * @return
     */
    public int getRegId()
    {
	return regId;
    }

    /**
     * Sets the id module register id that is being referenced by
     * this {@code Payment } object
     * @param regId
     */
    public void setModuleRegisterId(int moduleRegisterId)
    {
	this.regId = moduleRegisterId;
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
	    case EXISTING_BEAN:
	    case NEW_BEAN:
		return  validateAmount() && validateModuleRegId()  ; 
	  
	}
	return false;
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
	return getRegId() > 0;
    }

    
}
