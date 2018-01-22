package junitTests.AdminTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

import org.junit.Test;

import database.bean.Admin;
import database.managers.AdminManager;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import exception.InvalidAdminException;
import test.TestUtils;

public class AdminManagerJUnitTest
{

    @Test
    public void registerAdmin()
    {

	Scanner input = new Scanner( System.in );
	Admin newAdmin = new Admin("Joseph", "RAtatuiew", "chidioguejiofor@gmail.com" );

	//returns false if the number has not been generated 
	assertEquals(false,AdminManager.sendMail( newAdmin.getEmailAddress()) );
	AdminManager.generateNumber(); //when this happens

	assertEquals(true,AdminManager.sendMail( newAdmin.getEmailAddress()) ); // should return true
	System.out.println("Input number: " );

	String sentNum = input.nextLine();
	boolean success = false;
	try
	{
	    Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" ); //current logged admin
	    DatabaseManager.setCurrentAdmin(currentAdmin ); //sets the current logged Admin

	    success =  AdminManager.insert(newAdmin, sentNum);
	}
	catch( SQLIntegrityConstraintViolationException e1){
	    System.err.println("Failed! The admin probably already exists");
	}
	catch (InvalidAdminException | SQLException e)
	{
	    e.printStackTrace();
	}
	finally{
	    ConnectionManager.close();
	    input.close();
	}
	assertTrue( success );
	System.out.println(  success ? "Successfully Inputed. ": "");
	AdminManager.resetNumber();

    }

    @Test
    public  void updatePassword(){
	String username = TestUtils.getStringInput("Input existing username: ");
	String password = TestUtils.getStringInput("Input existing password: "); 
	String newPassword = TestUtils.getStringInput("Enter new password: ");

	Admin existingAdmin= new Admin(username, password); 

	try
	{
	    assertTrue( AdminManager.updatePassword(existingAdmin, newPassword)) ;
	}
	catch (SQLException | InvalidAdminException e)
	{
	    e.printStackTrace();
	}
    }

    
}
