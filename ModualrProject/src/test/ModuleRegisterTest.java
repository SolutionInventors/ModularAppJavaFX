package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.bean.ModuleRegister;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.managers.ModuleRegisterManager;
import exception.InvalidAdminException;
import utils.BeanType;
import utils.ValidationType;

public class ModuleRegisterTest
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
	    System.out.println("----AVAILABLE MODULES----");
	    TestUtils.displayBean( BeanType.MODULE , 0);
	    System.out.println("----AVAILABLE STUDENTS----");
	    TestUtils.displayBean( BeanType.STUDENT , 0);

	    
	    System.out.println("---------------REGISTERING STUDENT FOR MODULE--------------");
	    String studId = TestUtils.getStringInput
		    ("Enter the existing Student ID: ");
	    String modName = TestUtils.getStringInput
		    ("Enter the existing module name: ");
	    ModuleRegister modReg = new ModuleRegister(modName, studId);

	    if( ModuleRegisterManager.registerForModule(modReg)) {
		System.out.println("Successfully registered Student for module and  "
			+ "also gave updated the dateCreated attribute.");
		TestUtils.displayBean( BeanType.CERTIFICATE_MODULE , 0);

	    }
	    else if( !modReg.isValid(ValidationType.NEW_BEAN))
		System.err.println( "The format of the ModuleRegister was invalid" );
	    else
	    {
		System.out.println("Was Unsuccessful for unknown reasons!!!");
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
