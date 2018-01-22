package junitTests.AdminTest;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import database.bean.Admin;
import utils.ValidationType;

public class AdminJUnit
{

    @Test( timeout = 1000 )
    public void testGetters()
    {
	final String username = "Chidiebere";
	final String password = "Joshua" ;
	final String email = "chidi@gmail.com";
	Admin admin = new Admin(username, password, email);
	assertEquals( username, admin.getUsername());
	assertEquals(password, admin.getPassword());
	assertEquals( email, admin.getEmailAddress());
    }

    @Test
    public void testSetters()
    {
	Admin admin = new Admin("", "" ); 
	String username = " Ch idi debe re";
	String password = "xxxx";
	String mail = "email.com";

	admin.setUsername(username);
	admin.setPassword(password);
	admin.setEmailAddress(mail);

	assertEquals(username.replaceAll(" ", "" ) , admin.getUsername());
	assertEquals( password, admin.getPassword()); 
	assertEquals(mail, admin.getEmailAddress());

    }


    @Test
    public void testIsValid()
    {
	Admin admin = new Admin( "Chidiebere", "Fred", "John" ); 

	assertEquals( false, admin.isValid(ValidationType.NEW_BEAN));
	assertEquals(false, admin.isValid(ValidationType.EXISTING_BEAN));

	admin.setPassword("Fredinton");
	assertEquals(true,  admin.isValid(ValidationType.NEW_BEAN));

	admin.setEmailAddress("");
	assertEquals(false, admin.isValid(ValidationType.NEW_BEAN));

    }

    @Test
    public void testValidateEmail()
    {
	Admin admin = new Admin("", "" );
	//Email is valid is when it is less than 265 characters
	assertFalse( admin.validateEmail());
	
	char[] ch = new char[257]; 
	Arrays.fill(ch, (char) 89 );
	String mail = new String( ch); 
	admin.setEmailAddress(mail);
	
	assertFalse(admin.validateEmail());
	
	admin.setEmailAddress(admin.getEmailAddress().substring(0, 256)); //takes first 256 characters
	assertTrue( admin.validateEmail());
    }

    @Test
    public void testValidatePassword()
    {
	//password must be at least 7 characters. It can be anything
	//from only numbers to spaces in between
	Admin admin = new Admin( "Chidiebere", "Fred", "John" ); 

	assertEquals( false, admin.validatePassword()); 
	admin.setPassword("12345 678");
	assertTrue( admin.validatePassword());
    }

    @Test
    public void testValidateUsername()
    {
	
    }

   

}
