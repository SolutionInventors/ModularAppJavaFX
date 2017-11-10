package database.bean;

import java.sql.Date;

public class Payment implements Bean 
{
    private int id;
    private String moduleRegisterId;
    private double amount;
    private String bankName;
    private String tellerNumber;
    private Date paymentDate;
    
    public Payment(){}

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getModuleRegisterId()
    {
        return moduleRegisterId;
    }

    public void setModuleRegisterId(String moduleRegisterId)
    {
        this.moduleRegisterId = Bean.removeExtraSpaces( moduleRegisterId );
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = Bean.removeExtraSpaces( bankName);
    }

    public String getTellerNumber()
    {
        return tellerNumber;
    }

    public void setTellerNumber(String tellerNumber)
    {
        this.tellerNumber = Bean.removeExtraSpaces(tellerNumber );
    }

    public Date getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    @Override
    public boolean isValid(ValidationType type)
    {
	return false;
    }

}
