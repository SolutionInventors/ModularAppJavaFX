package test;

import java.sql.SQLException;
import java.util.Scanner;

import database.bean.Admin;
import database.managers.AdminManager;
import database.managers.DatabaseManager;
import exception.InvalidAdminException;

public class DeleteAdminTest
{

    public static void main(String[] args)
    {
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	DatabaseManager.setCurrentAdmin(currentAdmin); 	
	Scanner input = new Scanner( System.in);

	System.out.println("-----------------DELETING AN EXISTING ADMIN---------------");
	String  username = TestUtils.getStringInput("Enter existing admin username: ");
	
	try
	{
	    if(AdminManager.deleteAdmin(username)){
		System.out.println("Operation Successful!!!");
	    }else
		System.err.println("Failed");
	    
	}
	catch (SQLException | InvalidAdminException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	input.close(); 
    }

}
