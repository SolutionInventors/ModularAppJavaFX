package database.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.bean.Admin;
import database.bean.Payment;
import exception.InvalidAdminException;

public final class PaymentManager
{
    /**
     * Completely pays for the moduleRegister object.
     * 
     * @param regID the ModuleRegister id that is being paid for. 
     * @return true if it was successful else {@code false}
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static boolean makePayment(int regID) 
	    throws SQLException, InvalidAdminException
    {
	double remainingPayment = getRemaingPayment(regID);
	
	Admin currentAdmin = DatabaseManager.getCurrentAdmin(); 
	if( (currentAdmin.canWrite() ||currentAdmin.isAccountant()) && remainingPayment >0.0)
	{
	    String sql = ""
	    	+ "INSERT INTO `payment`( `RegId`, `amount`) "
	    	+ "VALUES (? ,? )";
	    try( PreparedStatement statement =  DatabaseManager.getPreparedStatement
		    (sql, regID, remainingPayment) ; )
	    {
		int affected = statement.executeUpdate();
		
		if( affected > 0 ) return true;
	    }
	}
	return false;
    }

    /**
     * Gets the amount of cash that is remaining for the payment of the specified
     * regID to be complete. A zero indicates that the payment is complete.
     * @param regID the {@code regID} in the {@code ModuleRegister} table
     * @return
     * @throws SQLException
     */
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
    /**
     * Gets an array of all the payments that has been made for the specified 
     * {@code regID}. 
     * @param regId
     * @return
     * @throws SQLException
     */
    public static Payment[] getPayments( int regId) 
	    throws SQLException
    {
	String sql = "SELECT id, amount FROM payment as pay "
		+ "WHERE pay.regID = ? ";
	ResultSet result= null;
	List<Payment> list = new ArrayList<>();
	try( PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, regId);){
	    result = stmt.executeQuery();
	    while( result.next() ){
		Payment pay = new Payment(result.getInt("id"), regId,
			result.getDouble("amount"));
		list.add( pay  );
	    }
	}
	finally{
	    if( result != null) result.close();
	}
	return list.toArray( new Payment[ list.size() ] ) ;
    }
}
