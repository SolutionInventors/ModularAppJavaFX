package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.bean.Module;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.managers.ModuleManager;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import utils.BeanType;

public class ModuleTest
{
    public static void main(String[] args)
    {
	//	First and very important step is to specify the Admin that is 
	//	making the change as follows

	Admin currentAdmin = new Admin("Chidiebere", "Fred" );
	DatabaseManager.setCurrentAdmin(currentAdmin); 	

	//	Rest of the code
	try
	{
	    TestUtils.displayBean(BeanType.MODULE , 0 );

	    System.out.println("---------------CREATING A NEW MODULE TEST--------------");
	    Module newModule = getNewModule();
	    try
	    {
		if( ModuleManager.addNewModule(newModule) ) {
		    System.out.println("Successfully created a new module and "
			    + "also  updated the dateCreated attribute.");
		    TestUtils.displayBean(BeanType.MODULE , 0 );

		}
		else
		{
		    System.out.println("Was Unsuccessful for unknown reasons!!!");
		}
	    }
	    catch (InvalidBeanException e)
	    {
		e.printStackTrace();
		System.err.println( "The format of the Modular Class was invalid" );
	    }


	    System.out.println("---------------REMOVING AN EXISTING MODULE TEST--------------");
	    String name = TestUtils.getStringInput("Enter the existing module name: ");

	    /*This should be used when we want to remove an existing Module*/
	    Module existingModule = new Module(name);

	    try
	    {
		if( ModuleManager.removeModule(existingModule)){
		    System.out.println( "Module was removed succcessfullly!!!");
		    TestUtils.displayBean(BeanType.MODULE , 0 );
		}
		else
		{
		    System.out.println("Nothing was removed! "
			    + "Maybe the module name you inputed is not in the database");
		}
	    }
	    catch (InvalidBeanException e)
	    {
		System.err.println( "The format of the Module object  was invalid" );

	    }

	    System.out.println("-------------UPDATING AN EXISTING CLASS TEST--------------");

	    name = TestUtils.getStringInput("Enter the existing module name: ");

	    existingModule = new Module( name ); // ony name is required for existing module
	    newModule = getNewModule();// name, numberOfUnits and amount are all required for units
	    try
	    {
		if( ModuleManager.updateModule(newModule, existingModule) ){
		    System.out.println( "The Module was updated succcessfullly!!!");
		    TestUtils.displayBean(BeanType.MODULE, 0 );

		}
		else
		{
		    System.out.println("Nothing was removed! "
			    + "Maybe the module name you inputed is not in the database");
		}
	    }
	    catch (InvalidBeanException e)
	    {
		System.err.println( "The format one of the Module "
			+ "object(s) is invalid" );

	    }


	}
	catch ( InvalidAdminException e)
	{
	    System.err.println( e );
	    System.err.println("The Admin was invalid");
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	finally{
	    //	    This is also very important. Close the ConnectionManager
	    ConnectionManager.close();
	}
    }

    /**
     * @return
     * @throws NumberFormatException
     */
    public static Module getNewModule() throws NumberFormatException
    {
	String name = TestUtils.getStringInput("Enter the new module name: ");

	int numberOfUnits = Integer.parseInt(
		TestUtils.getStringInput("Enter the number of units for this module: "));


	double amount  = Double.parseDouble(
		TestUtils.getStringInput("Enter the amount per unit for this module: "));

	/*This constructor should be used when adding a new  Module*/
	Module newModule = new Module(name, numberOfUnits, amount);
	return newModule;
    }


}
