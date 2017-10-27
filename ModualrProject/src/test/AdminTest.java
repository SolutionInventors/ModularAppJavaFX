package test;

import database.bean.Admin;
import database.managers.AdminManager;
import exception.InvalidPrimaryKeyException;

public class AdminTest
{

    public static void main(String[] args) 
    {
	displayAll( AdminManager.getAllAdmin() ) ;
	
	
	//Updating Admin username......
	Admin oldAdmin = new Admin();
	
	String oldUserName = TestUtils.getStringInput("Please input old user name: " );
	String oldPass = TestUtils.getStringInput("Please input old user password: ");
	String newUser = TestUtils.getStringInput("Please input new username: "); 
	
	oldAdmin.setUsername(oldUserName);
	oldAdmin.setPassword( oldPass );
	
	boolean succesful = false ;
	try
	{
	    succesful = AdminManager.updateAdminUsername( oldAdmin, newUser);
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

	displayAll( AdminManager.getAllAdmin() ) ;
    }

    private static void displayAll(Admin[] allAdmin)
    {
	System.out.println("----------------------------------\n");
	System.out.println("Admins:");
	for ( int i = 0 ; i < allAdmin.length ; i++ ){
	    System.out.println( "Username: " + allAdmin[i].getUsername() + " " + 
		    		"Password: " + allAdmin[i].getPassword());
	}
	System.out.println("\n----------------------------------");
	
    }

}
