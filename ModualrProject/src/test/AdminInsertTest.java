package test;

import database.bean.Admin;
import database.managers.AdminManager;

public class AdminInsertTest
{

    public static void main(String[] args)
    {
	Admin theAdmin = new Admin();

	theAdmin.setUsername("Og");
	theAdmin.setPassword( "Chidi@chi.com");
	boolean added = AdminManager.insertAdmin( theAdmin );
	if ( added )
	    System.out.println( "Added successfully" );
	else
	    System.out.println("Insert Unsuccessful");

    }

}
