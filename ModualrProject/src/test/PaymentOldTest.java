/*package test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import database.bean.Admin;
import database.bean.Payment;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.managers.PaymentManager;
import exception.InvalidAdminException;
import utils.BeanType;
import utils.ValidationType;

public class PaymentOldTest
{

    public static void main(String[] args) throws ParseException
    {
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	DatabaseManager.setCurrentAdmin(currentAdmin);
	
	//	Rest of the code
	try
	{
	    System.out.println("----AVAILABLE PAYMENTs----");
	   TestUtils.displayBean( BeanType.MODULE_REGISTER , 0);
	    
	   int modRegId = Integer.parseInt(TestUtils.getStringInput("Enter ModuleRegister ID: ") );
	   double amount = Double.parseDouble(TestUtils.getStringInput("Input Amount Paod: " ));
	   String bank = TestUtils.getStringInput( "Enter Bank Name: " );
	   String tellerNum = TestUtils.getStringInput("Enter teller number: " );
	   String payDate = TestUtils.getStringInput("Input date of payment in format dd-mm-yyyy " );
	   DateFormat df = new SimpleDateFormat("dd-mm-yyyy" );
	   Date date = new Date( df.parse(payDate).getTime());
	   
	   Payment payment = new Payment( modRegId, amount, bank, tellerNum, date);
			
	   if( PaymentManager.makePayment(payment)){
	       System.out.println("Successfully made the required payment" );
	   }
	   else if( !payment.isValid(ValidationType.NEW_BEAN)){
	       System.err.println("The Payment object was invalid.");
	   }
	   else if( PaymentManager.isPaymentComplete(payment.getRegId())){
	       System.err.println("Payment unsuccessful because it is complete" );
	   }
	   else
	   {
	       System.err.println("Payment was unsuccessful");
	   }
	}
	catch ( InvalidAdminException e)
	{
	    System.err.println( e );
	    System.err.println("The Admin was invalid");
	}
	catch (SQLException e){
	    e.printStackTrace();
	}
	finally{
	    //	    This is also very important. Close the ConnectionManager
	    ConnectionManager.close();
	}
    }

}


*/