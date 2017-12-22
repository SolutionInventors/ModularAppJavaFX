package test;

import java.sql.SQLException;
import java.util.Scanner;

import database.bean.Admin;
import database.managers.AdminManager;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import exception.InvalidAdminException;

public class AdminUpdateTest
{

    public static void main(String[] args)
    {
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	DatabaseManager.setCurrentAdmin(currentAdmin); 	
	Scanner input = new Scanner( System.in);

	System.out.println("-----------------UPDATIND A NEW ADMIN PASSWORD---------------");
	String  name = TestUtils.getStringInput("Input existing username: ");
	String pass = TestUtils.getStringInput("Input existing password: ");
	Admin existingAdmin = new Admin( name,pass);

	String newPass = TestUtils.getStringInput("Input new password");

	try
	{
	    if( AdminManager.updatePassword(existingAdmin, newPass) ){
		System.out.println("Update Successful");
		System.out.println( existingAdmin.getPassword());

	    }
	    else
		System.err.println("Wasn't Updated");

	    existingAdmin.setPassword(newPass);

	    System.out.println( "--------UPDATING EMAIL -----------");
	    String email = TestUtils.getStringInput( "Enter new email: " );
	    if( AdminManager.updateMail(existingAdmin, email) ){
		System.out.println("Update Successful");
		existingAdmin.setPassword(newPass);
	    }
	    else
		System.err.println("Wasn't Updated");

	    System.out.println( "--------CHANGING USERNAME -----------");
	    String newUsername = TestUtils.getStringInput( "Enter new username: " );
	    if( AdminManager.changeUsername(existingAdmin, newUsername) ){
		System.out.println("Update Successful");
	    }
	    else
		System.err.println("Wasn't Updated");
	}
	catch (SQLException | InvalidAdminException e)
	{
	    e.printStackTrace();
	}
	finally{
	    input.close();
	    ConnectionManager.close();
	}
    }
}
