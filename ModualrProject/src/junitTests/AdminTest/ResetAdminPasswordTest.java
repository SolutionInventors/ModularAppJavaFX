package junitTests.AdminTest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import database.bean.Admin;
import database.managers.AdminManager;
import database.managers.DatabaseManager;
import exception.InvalidAdminException;
import test.TestUtils;

public class ResetAdminPasswordTest
{

    @Test
    public  void retriveForgottenPassword(){
	String username = TestUtils.getStringInput("Enter username: ");

	String email = AdminManager.getEmailAddress(username); //returns null if not found
	assertNotNull(email);
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" ); //current logged admin
	DatabaseManager.setCurrentAdmin(currentAdmin);
	try{
	    AdminManager.generateNumber(); //generates a random number that would be sent to the admin
	    assertTrue( AdminManager.sendMail(email)); //mail sent successful
	    String confirmationNumber = TestUtils.getStringInput("A confirmation number has been sent to " + email+ 
		    ". \nPlease input the number: "); 
	    assertTrue( AdminManager.testNumber(confirmationNumber)); 
	    String newPassword = TestUtils.getStringInput("Input new Password: "); 
	    assertTrue(AdminManager.resetPassword( username, newPassword, confirmationNumber));
	}
	catch(  InvalidAdminException e ){
	    //thrown when an invalid admin tries to make the change
	   
	    e.printStackTrace();
	}
	catch (SQLException e)
	{
	    // database error
	    e.printStackTrace();
	}


    }

}
