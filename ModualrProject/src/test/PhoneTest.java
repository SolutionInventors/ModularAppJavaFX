package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.bean.student.Phone;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.managers.PhoneManager;
import exception.InvalidAdminException;
import utils.BeanType;

public class PhoneTest
{
    public static void main(String[] args)
    {
	//	First and very important step is to specify the Admin that is 
	//	making the change as follows

	Admin currentAdmin = new Admin("Chidiebere", "Fred" );

	DatabaseManager.setCurrentAdmin(currentAdmin); 	

	//	Rest of the code
	try
	{
	    TestUtils.displayBean(BeanType.PHONE , 0 );

	    System.out.println("---------------INSERTING A NEW PHONE TEST--------------");
	    Phone newPhone = getPhone("new");

	    if( PhoneManager.insert(newPhone)) {
		System.out.println("Successfully inserted a new phone number to "
			+ "the specified student.");
		TestUtils.displayBean(BeanType.PHONE , 0 );

	    }
	    else
	    {
		System.out.println("Was Unsuccessful Maybe the studentId does not exist!!!");
	    }




	    System.out.println("---------------REMOVING AN EXISTING PHONE TEST--------------");

	    /*This should be used when we want to remove an existing Phone*/
	    Phone existingPhone = getPhone("existing");

	    if( PhoneManager.removePhone(existingPhone)){
		System.out.println( "Phone was removed succcessfullly!!!");
		TestUtils.displayBean(BeanType.PHONE , 0 );
	    }
	    else
	    {
		System.out.println("Nothing was removed! "
			+ "Maybe the phone  object you inputed is not in the database");
	    }


	    System.out.println("-------------UPDATING AN EXISTING PHONE TEST--------------");

	    existingPhone =  getPhone("existing" );
	    String newNumber = TestUtils.getStringInput("Enter new phone number: ");

	    newPhone = new Phone( existingPhone.getStudentID(), newNumber );

	    if(  PhoneManager.update(existingPhone, newPhone) ){
		System.out.println( "The Phone was updated succcessfullly!!!");
		TestUtils.displayBean(BeanType.PHONE, 0 );

	    }
	    else
	    {
		System.out.println("Nothing was updated! "
			+ "Maybe the Phone pbject you inputed is not in the database");
	    }


	    System.out.println("-------------RETRIEVING STUDENT PHONE TEST--------------");

	    String studentId = TestUtils.getStringInput( "Enter Student id card number" );

	    Phone[] numbers = PhoneManager.getPhoneNumber(studentId);
	    if(numbers != null ){
		System.out.println( "The Student's phone numbers are: ");
		for( int i = 0 ; i< numbers.length ; i++ ){
		    System.out.println(numbers[i].getNumber());
		} 
	    }
	    else
		System.out.println("No numbers was found for the specified student");



	}
	catch ( InvalidAdminException e)
	{
	    System.err.println( e );
	    System.err.println("The Admin was invalid");
	}
	catch (SQLException e)
	{
	   e.printStackTrace();
	}
	finally{
	    // This is also very important. Close the ConnectionManager when you are done
	    //
	    ConnectionManager.close();
	}
    }

    /**
     * @return
     */
    public static Phone getPhone( String midString)
    {
	String studId = TestUtils.getStringInput("Enter " + midString+  " studentID: ");
	String number = TestUtils.getStringInput("Enter "  + midString + " phone number: ");

	return new Phone(studId, number);
    }





}
