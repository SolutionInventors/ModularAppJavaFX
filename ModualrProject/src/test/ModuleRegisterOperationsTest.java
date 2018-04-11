package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.managers.ModuleRegisterManager;
import database.managers.PaymentManager;
import exception.InvalidAdminException;
import utils.BeanType;

public class ModuleRegisterOperationsTest
{

    public static void main(String[] args)
    {
	//	First and very important step is to specify the Admin that is 
	//	making the change as follows

	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	DatabaseManager.setCurrentAdmin(currentAdmin);
	
	//	Rest of the code
	try
	{
	    System.out.println("----MODULE REGISTER DATA----");
	    TestUtils.displayBean( BeanType.MODULE_REGISTER , 0);
	   
	    System.out.println("---------------BOOKING STUDENT FOR MODULE--------------");
	    String studId = TestUtils.getStringInput
		    ("Enter  Student ID: ");
	    int modRegId = Integer.parseInt( TestUtils.getStringInput
		    ("Enter the existing module register id: "));
	    String moduleName = TestUtils.getStringInput
		    ("Enter the module name: ");
	    
	    
	    if( ModuleRegisterManager.bookModule(modRegId, true)) {
		System.out.println("Successfully booked Student for module and  "
			+ "also gave updated the dateCreated attribute.");
		TestUtils.displayBean( BeanType.MODULE_REGISTER , 0);

	    }
	    else if( !PaymentManager.isPaymentComplete( modRegId)){
		System.err.println("Was unsuccessful because Payment is incomplete!");
	    }
	     else
	    {
		System.err.println("Was Unsuccessful for unknown reasons!!! ");
	    }

	    
	    System.out.println("---------------SET ATTENDANCE STATUS IN MODREG--------------");
	    studId = TestUtils.getStringInput
		    ("Enter  Student ID: ");
	    modRegId = Integer.parseInt( TestUtils.getStringInput
		    ("Enter the existing module register id: "));
	    moduleName = TestUtils.getStringInput
		    ("Enter the module name: ");
	    
	    
	    if( ModuleRegisterManager.setAttendance(modRegId,  true)) {
		System.out.println("Attendance status updated.");
		TestUtils.displayBean( BeanType.MODULE_REGISTER , 0);

	    }
	    else if( !PaymentManager.isPaymentComplete( modRegId)){
		System.err.println("Was unsuccessful because Payment is incomplete!");
	    }
	     else
	    {
		System.err.println("Was Unsuccessful for unknown reasons!!! ");
	    }

	    System.out.println("---------------SET RESULT IN MODREG--------------");
	    studId = TestUtils.getStringInput
		    ("Enter  Student ID: ");
	    modRegId = Integer.parseInt( TestUtils.getStringInput
		    ("Enter the existing module register id: "));
	    moduleName = TestUtils.getStringInput
		    ("Enter the module name: ");
	    
	    String result = TestUtils.getStringInput
		    ("Enter the Student Result: ");
	    if( ModuleRegisterManager.setResultForModule(modRegId, result)) {
		System.out.println("REsult has been set.");
		TestUtils.displayBean( BeanType.MODULE_REGISTER , 0);
	    }
	    else if( !PaymentManager.isPaymentComplete( modRegId)){
		System.err.println("Was unsuccessful because Payment is incomplete!");
	    }else{
		System.err.println("Was Unsuccessful for unknown reasons!!! ");
	    }

	}
	catch ( InvalidAdminException e)
	{
	    System.err.println( e );
	    System.err.println("The Admin was invalid");
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	finally{
	    //	    This is also very important. Close the ConnectionManager
	    ConnectionManager.close();
	}
    }



}
