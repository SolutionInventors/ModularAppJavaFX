package test;

import database.bean.Admin;
import database.managers.DatabaseManager;
import exception.InvalidPrimaryKeyException;

public class AdminInsertTest
{

    public static void main(String[] args) throws InvalidPrimaryKeyException
    {
	Admin theAdmin = new Admin("Og", "Chidi@chi.com");

	boolean added = DatabaseManager.insert( theAdmin );
	if ( added )
	    System.out.println( "Added successfully" );
	else
	    System.out.println("Insert Unsuccessful");

    }

}
