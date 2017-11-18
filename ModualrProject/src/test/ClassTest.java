package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.bean.ModularClass;
import database.managers.BeanType;
import database.managers.ModularClassManager;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import exception.InvalidAdminException;
import exception.InvalidBeanException;

public class ClassTest
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
	    
	    TestUtils.displayBean(BeanType.MODULAR_CLASS , 0 );
	    
	    System.out.println("---------------CREATING A NEW CLASS TEST--------------");
	    String name = TestUtils.getStringInput("Enter the new class name: ");
	    
	    ModularClass modClass = new ModularClass(name );
	    try
	    {
		if( ModularClassManager.createNewClass(modClass) ) {
		    System.out.println("Successfully created a new class and "
		    	+ "also gave updated the dateCreated attribute.");
		    TestUtils.displayBean(BeanType.MODULAR_CLASS , 0 );
		    
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
	    
	    
	    System.out.println("---------------REMOVING AN EXISTING CLASS TEST--------------");
	    name = TestUtils.getStringInput("Enter the new class name: ");
	    
	    modClass = new ModularClass(name );
	    
	    try
	    {
		if( ModularClassManager.removeClass(modClass ) ){
		    System.out.println( "Class was removed succcessfullly!!!");
		    TestUtils.displayBean(BeanType.MODULAR_CLASS , 0 );
		}
		else
		{
		    System.out.println("Nothing was removed! "
		    	+ "Maybe the class name you inputed is not in the database");
		}
	    }
	    catch (InvalidBeanException e)
	    {
		System.err.println( "The format of the Modular Class was invalid" );
		    
	    }
	    
	    System.out.println("---------------UPDATING AN EXISTING CLASS TEST--------------");
	    
	    name = TestUtils.getStringInput("Enter the existing class name: ");
	    String newName = TestUtils.getStringInput("Enter the new class name: ");
	    
	    modClass = new ModularClass(name );
	    ModularClass newClass = new ModularClass( newName);
	    try
	    {
		if( ModularClassManager.update(modClass, newClass) ){
		    System.out.println( "Class was updated succcessfullly!!!");
		    TestUtils.displayBean(BeanType.MODULAR_CLASS , 0 );
		    
		}
		else
		{
		    System.out.println("Nothing was removed! "
		    	+ "Maybe the class name you inputed is not in the database");
		}
	    }
	    catch (InvalidBeanException e)
	    {
		System.err.println( "The format one of the ModularClass "
			+ "objectes is invalid" );
		    
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
    
    
}
