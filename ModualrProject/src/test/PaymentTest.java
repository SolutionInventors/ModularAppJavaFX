package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import exception.InvalidAdminException;
import utils.BeanType;

public class PaymentTest
{

    public static void main(String[] args)
    {
	DatabaseManager.setCurrentAdmin(new Admin("Chidiebere", "Fred" )); 	

	//	Rest of the code
	try
	{
	    System.out.println("----AVAILABLE MODULES----");
	    TestUtils.displayBean( BeanType.MODULE_REGISTER , 0);

	    //	    System.out.println("----AVAILABLE STUDENTS----");
	    //	    TestUtils.displayBean( BeanType.STUDENT , 0);
	    //	    
	    System.out.println("---------------REGISTERING STUDENT FOR MODULE--------------");

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


