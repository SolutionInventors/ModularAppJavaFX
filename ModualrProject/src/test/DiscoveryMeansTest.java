package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.bean.student.MeanOfDiscovery;
import database.managers.DatabaseManager;
import database.managers.DiscoveryManager;
import exception.InvalidAdminException;

public class DiscoveryMeansTest
{

    public static void main(String[] args)
    {
	DatabaseManager.setCurrentAdmin(new Admin( "Chidiebere", "Fred") );
	String studentId = TestUtils.getStringInput("Input Student Id: ");
	String means = TestUtils.getStringInput("How did you hear about IIT: ");
	MeanOfDiscovery disc =  new MeanOfDiscovery(studentId, means);
	try
	{
	    if( DiscoveryManager.insert( disc))
		System.out.println("Inserted Successfully" );
	    else
		System.out.println("Unsuccessful" );
	    
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	catch (InvalidAdminException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    }

}
