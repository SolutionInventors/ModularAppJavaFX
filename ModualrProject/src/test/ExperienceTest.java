package test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import database.bean.Admin;
import database.bean.student.ProfessionalExperience;
import database.managers.DatabaseManager;
import database.managers.ExperienceManager;
import exception.InvalidAdminException;

public class ExperienceTest
{

    public static void main(String[] args) throws ParseException, SQLException
    {
	DatabaseManager.setCurrentAdmin( new Admin("Chidiebere", "Fred") );
	String studId = TestUtils.getStringInput("Input Student Id: ");
	System.out.println("-------ADDING PROFESSIONAL EXPERIENCE------");

	DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
	String startString = TestUtils.getStringInput("Input start Date in the format( dd-mm-yyyy): ");
	String endString = TestUtils.getStringInput("Input end Date in the format( dd-mm-yyyy): ");

	Date startDate = new Date( df.parse(startString).getTime() );
	Date endDate = new Date( df.parse(endString).getTime() );

	String jobTitle = TestUtils.getStringInput("Input the job title: ");
	String employer = TestUtils.getStringInput("Input the company or place you worked: ");

	

	System.out.println("Please input ");
	ArrayList<String> list = new ArrayList<>();

	String duty = TestUtils.getStringInput
		("Please input a responsibity or -1 to quit: ");
	while( !duty.trim().equals("-1") ){
	   list.add( duty);
	    duty = TestUtils.getStringInput
		    ("Please input the next responsibity or -1 to quit: ");
	}

	
	String[] duties =  list.toArray( new String[list.size()] ) ;
	ProfessionalExperience experience = new 
		ProfessionalExperience(studId, startDate, endDate,
			jobTitle, employer, duties);
	try
	{
	    if( ExperienceManager.insert(experience) ){
		System.out.println("Insertion was successful" );
	    }
	    else
		System.out.println("Unsuccessful");
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	catch (InvalidAdminException e)
	{
	    e.printStackTrace();
	}
	
    }

}
