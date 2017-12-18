package utils;

public class PaymentInfo
{
    private double totalAmountPaid ;
    private double totalToPay;
    
    public double getTotalAmountPaid()
    {
        return totalAmountPaid;
    }
    public void setTotalAmountPaid(double totalAmountPaid)
    {
        this.totalAmountPaid = totalAmountPaid;
    }
    public double getTotalToPay()
    {
        return totalToPay;
    }
    public void setTotalToPay(double totalToPay)
    {
        this.totalToPay = totalToPay;
    }
    
    public double getAmountLeft(){
	return getTotalAmountPaid() - getTotalToPay();
    }
    
}
