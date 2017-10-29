package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.managers.AdminManager;
import database.managers.BeanType;
import database.managers.DatabaseManager;
import exception.InvalidPrimaryKeyException;

public class AdminTest
{

    public static void main(String[] args) throws SQLException 
    {
	TestUtils.displayAll( AdminManager.getAllAdmin(0) ) ;
	
	System.out.println( "Total Admin: "  + AdminManager.getTotalAdmin());
	//Updating Admin username......
	
	String oldUserName = TestUtils.getStringInput("Please input old user name: " );
	String oldPass = TestUtils.getStringInput("Please input old user password: ");
	String newUserName = TestUtils.getStringInput("Please input new username: "); 
	
	
	Admin oldAdmin = new Admin(oldUserName,oldPass );
	Admin newAdmin = new Admin(newUserName, oldAdmin.getPassword());
	
	boolean succesful = false ;
	try
	{
	    succesful = AdminManager.update( oldAdmin, newAdmin);
	}
	catch (InvalidPrimaryKeyException e){
	    System.err.println( "The new  username already exists and "
	    	+ "thus cannot be a primary key");
	    System.err.println( e.getMessage() );
	    System.exit(0);
	}
	
	if( succesful )
	    System.out.println("The username has been updated");
	else
	    System.out.println("Update failed! User old user probably not  in database");

	TestUtils.displayAll( DatabaseManager.getAllBean( BeanType.ADMIN, 0 ) ) ;
    }

    

}
