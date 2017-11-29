package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.bean.Sponsor;
import database.managers.DatabaseManager;
import database.managers.SponsorManager;
import exception.InvalidAdminException;
import exception.InvalidBeanException;

public class SponsorTest
{
    public static void main( String[] args){
	DatabaseManager.setCurrentAdmin(new Admin( "Chidiebere", "Fred" ));
	
	String studId = TestUtils.getStringInput("Input Student Id: ");
	System.out.println( "-------INPUT SPONSOR INFORMATIONS--- ");
	String fName = TestUtils.getStringInput("Input Sponsor First Name: ");
	String lName = TestUtils.getStringInput("Input Sponsor Last Name: ");
	String address = TestUtils.getStringInput("Imput Sponsor Address: " );
	String phone =  TestUtils.getStringInput("Input Sponsor's Phone number: ");
	String mail = TestUtils.getStringInput("Input Sponsor's email address"	);
	Sponsor sponsor = new Sponsor(studId, fName, lName, address, phone, mail);
	
	try
	{
	    if( SponsorManager.insert( sponsor) 	){
	        System.out.println("Successfully inserted" );
	        
	    }
	    else
		System.out.println("Unsuccessful");
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
	catch (InvalidBeanException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    }
}
