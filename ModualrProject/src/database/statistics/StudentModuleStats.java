package database.statistics;

import java.sql.Date;

public class StudentModuleStats
{
    //string data
    private final String moduleName; 
    private final String result; 

    //numeric data
    private final double amountPaid; 

    //boolean data
    private final boolean booked;
    private final boolean attended; 
    private final boolean paymentComplete; 
    private final Date dateRegistered; 
    
    public StudentModuleStats(Date regDate, String modName, String result, boolean booked, boolean payment, 
	    boolean attended, double amount){
	this.result = result; 
	this.moduleName = modName; 
	this.amountPaid = amount; 
	this.booked = booked; 
	this.paymentComplete = payment;
	this.attended = attended; 
	this.dateRegistered =regDate; 
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public String getResult()
    {
        return result;
    }

    public double getAmountPaid()
    {
        return amountPaid;
    }

    public boolean hasBooked()
    {
        return booked;
    }

    public boolean hasAttended()
    {
        return attended;
    }

    public boolean isPaymentComplete()
    {
        return paymentComplete;
    }

    public Date getDateRegistered()
    {
	return dateRegistered;
    }



}
