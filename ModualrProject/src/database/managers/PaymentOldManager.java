/*package database.managers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.bean.Admin;
import database.bean.Payment;
import exception.InvalidAdminException;
import utils.ValidationType;

public final class PaymentOldManager
{
    *//**
     * Makes a payment for a registered {@code ModuleTabTable}. The amount to be paid is 
     * validated by first checking that the amount remaining amount is not
     * more than zero. Also checks that the amount about to be paid is not 
     * greater than the remaining amount.
     * @param payment the {@code Payment} object containing information like the
     * bankName, amount , teller number etc.
     * @return {@code true} if the object was inserted successful.
     * @throws SQLException
     * @throws InvalidAdminException
     *//*
    public static boolean makePayment( Payment payment) 
	    throws SQLException, InvalidAdminException
    {
	double remainingPayment = getRemaingPayment(payment.getRegId());
	
	Admin currentAdmin = DatabaseManager.getCurrentAdmin(); 
	if( currentAdmin.isAccountant() && payment.isValid(ValidationType.NEW_BEAN )  && remainingPayment > 0  && 
		remainingPayment >= payment.getAmount() )
	{
	    try( CallableStatement statement =  DatabaseManager.getCallableStatement
		    ("{call makePayment(?,?,?,?,?) }", payment.getRegId(), 
			    payment.getAmount(), payment.getBankName() , payment.getTellerNumber() , 
			    payment.getPaymentDate() ) ; )
	    {
		int affected = statement.executeUpdate();
		if( affected > 0 ) return true;
	    }
	}
	return false;
    }

    *//**
     * Gets the amount of cash that is remaining for the payment of the specified
     * regID to be complete. A zero indicates that the payment is complete.
     * @param regID the {@code regID} in the {@code ModuleRegister} table
     * @return
     * @throws SQLException
     *//*
    public static double getRemaingPayment(int regID) throws SQLException
    {
	int factor = 10000; 
	double amountPaid = getAmountPaid( regID );
	double totalPrice = ModuleRegisterManager.getTotalPriceForModule(regID);
	double remainingPayment = ((totalPrice*factor) - (amountPaid*factor))/factor;
	return remainingPayment;
    }

    public static double getAmountPaid( int regId ) throws SQLException{
	String sql = "SELECT if( SUM( pay.amount) is null , 0, SUM( pay.amount)) "+ 
		" as 'Amount Paid' FROM payment as pay  " +
		" WHERE pay.RegId = ?;"; 
	ResultSet result= null;
	try( PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, regId);){
	    result = stmt.executeQuery();
	    if( result.next() ){
		return result.getDouble("Amount Paid");
	    }
	}
	finally{
	    if( result != null) result.close();
	}
	return 0 ;
    }

    
    public static boolean isPaymentComplete( int regId ) throws SQLException{
	return getRemaingPayment(regId) <= 0;
    }
    *//**
     * Gets an array of all the payments that has been made for the specified 
     * {@code regID}. 
     * @param regId
     * @return
     * @throws SQLException
     *//*
    public static Payment[] getPayments( int regId) 
	    throws SQLException
    {
	String sql = "SELECT * FROM payment as pay "
		+ "WHERE pay.regID = ? ";
	ResultSet result= null;
	List<Payment> list = new ArrayList<>();
	try( PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, regId);){
	    result = stmt.executeQuery();
	    if( result.next() ){
		Payment pay = new Payment(result.getInt("id"), result.getInt("regId"),
			result.getDouble("amount"), result.getString("bankName"),
			result.getString("tellerNumber"), 
			result.getDate("DateOfPayment"));
		list.add( pay  );
	    }
	}
	finally{
	    if( result != null) result.close();
	}
	return list.toArray( new Payment[ list.size() ] ) ;
    }
}
*/