package test;

import database.bean.Admin;
import database.managers.AdminManager;
import database.managers.BeanType;
import database.managers.DatabaseManager;
import exception.InvalidPrimaryKeyException;

public class UpdateAdiminPasswordTest
{

    public static void main(String[] args) throws InvalidPrimaryKeyException
    {
	//Update Password test
	
	TestUtils.displayAll( DatabaseManager.getAllBean( BeanType.ADMIN,0));
	
	String oldUsername = TestUtils.getStringInput( "Input old admin username: ");
	String oldPass = TestUtils.getStringInput("Input old admin password: ");
	String newPass = TestUtils.getStringInput( "Input New Password: " ) ;
	

	Admin theAdmin = new Admin(oldUsername, oldPass);
	Admin newAdmin = new Admin( oldUsername, newPass );
	
	
	boolean added = AdminManager.update( theAdmin, newAdmin );
	if ( added )
	    System.out.println( "Update successful" );
	else
	    System.out.println("Update Unsuccessful");

    }

}
