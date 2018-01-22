package junitTests.AdminTest;

import static org.junit.Assert.*;

import org.junit.Test;

import database.managers.AdminManager;
import test.TestUtils;

public class ResetAdminPasswordTest
{

    @Test
    public  void retriveForgottenPassword(){
	String username = TestUtils.getStringInput("Enter username: ");

	String email = AdminManager.getEmailAddress(username); //returns null if not found
	assertNotNull(email); 

	try{
	    AdminManager.generateNumber(); //generates a random number that would be sent to the admin
	    assertTrue( AdminManager.sendMail(email)); //mail sent successful
	    String confirmationNumber = TestUtils.getStringInput("A confirmation number has been sent to " + email+ 
		    ". \nPlease input the number: "); 
	    assertTrue( AdminManager.testNumber(confirmationNumber)); 
	    String newPassword = TestUtils.getStringInput("Input new Password: "); 
	    assertTrue(AdminManager.resetPassword( username, newPassword, confirmationNumber));
	}
	catch( Exception e ){
	    e.printStackTrace();
	}


    }

}
