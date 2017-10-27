package test;

import database.bean.Admin;
import database.managers.AdminManager;
import exception.InvalidPrimaryKeyException;

public class UpdateAdiminPasswordTest
{

    public static void main(String[] args) throws InvalidPrimaryKeyException
    {

	Admin theAdmin = new Admin();

	theAdmin.setUsername("Og");
	theAdmin.setPassword( "Fred");
	boolean added = AdminManager.updateAdminPassword( theAdmin, "chidi" );
	if ( added )
	    System.out.println( "Update successful" );
	else
	    System.out.println("Update Unsuccessful");

    }

}
