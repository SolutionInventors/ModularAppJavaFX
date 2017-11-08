package database.managers;

import java.sql.CallableStatement;
import java.sql.SQLException;

import database.bean.Payment;

public class PaymentManager
{
    public boolean insert( Payment payment) throws SQLException{
	CallableStatement statement =  
		DatabaseManager.getCallableStatement
		("{call makePayment(?,?,?,?) }", payment.getModuleRegisterId(), 
		 payment.getAmount(), payment.getBankName() , payment.getTellerNumber() , 
		 payment.getPaymentDate() );
	
	int affected = statement.executeUpdate();
	if( affected > 0 ) return true;
	
	return false;
    }

    public boolean delete( Payment cert ){
	return false;
    }

    public boolean update( Payment  payment){
	return false;
    }

    public boolean isInDatabase( Payment cert ){
	return false;
    }
}
