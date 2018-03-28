package test;

import java.sql.SQLException;
import java.text.ParseException;

import database.bean.Admin;
import database.bean.ModuleRegister;
import database.bean.Payment;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.managers.ModuleRegisterManager;
import database.managers.PaymentManager;
import exception.InvalidAdminException;
import utils.BeanType;
import utils.ValidationType;

public class PaymentTest2
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
	   
	   Payment payment = new Payment( modRegId, amount);
	   ModuleRegister modReg = ModuleRegisterManager.getModRegById(modRegId);
	   
	   if( PaymentManager.makePayment(payment)){
	       System.out.println("Successfully made the required payment" );
	   }
	   else if( !payment.isValid(ValidationType.NEW_BEAN)){
	       System.err.println("The Payment object was invalid.");
	   }
	   else if( PaymentManager.isPaymentComplete(payment.getRegId())){
	       System.err.println("Payment unsuccessful because it is complete" );
	   }
	   else if(!modReg.hasBooked() ){
	       System.err.println("Failed because module has not yet been booked");
	   }
	   else
	   {
	       System.err.println("Payment was unsuccessful for unknown reasons");
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


