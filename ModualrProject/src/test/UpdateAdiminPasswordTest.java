package test;

import database.bean.Admin;
import database.managers.AdminManager;
import exception.InvalidPrimaryKeyException;

public class UpdateAdiminPasswordTest
{

    public static void main(String[] args) throws InvalidPrimaryKeyException
    {
	//Update Password test
	Admin theAdmin = new Admin();
	Admin newAdmin = new Admin();
	
	TestUtils.displayAll( AdminManager.getAllAdmin(0));
	
	theAdmin.setUsername(TestUtils.getStringInput( "Input old admin username: "));
	theAdmin.setPassword( TestUtils.getStringInput( "Input old admin password: "));
	
	newAdmin.setPassword( TestUtils.getStringInput( "Input New Password: " ) );
	newAdmin.setUsername( theAdmin.getUsername());
	
	
	boolean added = AdminManager.updateAdmin( theAdmin, newAdmin );
	if ( added )
	    System.out.println( "Update successful" );
	else
	    System.out.println("Update Unsuccessful");

    }

}
