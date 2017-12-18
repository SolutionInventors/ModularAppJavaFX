package test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import database.bean.Admin;
import database.bean.student.EducationalBackground;
import database.managers.DatabaseManager;
import database.managers.EducationManager;
import exception.InvalidAdminException;
import utils.ValidationType;

public class EducationBackgroundTest
{
    public static void main( String[] args ) throws ParseException{
	
	DatabaseManager.setCurrentAdmin(new Admin( "Chidiebere", "Fred", "email.com" ));
	String studId = TestUtils.getStringInput("Input student id: " );
	String beginString = TestUtils.getStringInput("Input begin date in format dd-mm-yyyy: ");
	String endString = TestUtils.getStringInput("Input end date in format dd-mm-yyyy: ");
	String institute = TestUtils.getStringInput("What institute did you attend: ");
	String course = TestUtils.getStringInput("input Course: ");
	String cert =TestUtils.getStringInput("What certificate was received: ");
	
	DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
	Date begin = new Date( df.parse(beginString).getTime());
	Date end = new Date( df.parse(endString).getTime());
	
	System.out.println(begin);
	System.out.println(end);
	
	EducationalBackground background = 
		new EducationalBackground(studId, begin, end, institute, course, cert);
	
	System.out.println(background.isValid(ValidationType.EXISTING_BEAN));
	try
	{
	    if( EducationManager.insert( background) ){
	        System.out.println( "Inserted successfully!");
	    }
	    else
		System.out.println("Failed to insert");
	}
	catch (SQLException e)
	{
	    System.err.println( e );
	  
	}
	catch (InvalidAdminException e)
	{
	    System.err.println( "The admin that want to make the change is invalid");
	}
	
    }
}
