package test;

import java.sql.SQLException;
import java.util.Scanner;

import database.bean.Admin;
import database.managers.AdminManager;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import exception.InvalidAdminException;

public class AdminTest
{
    public static void main(String[] args) throws InvalidAdminException, SQLException
    {
	Admin currentAdmin = new Admin("Jacob", "Jerry" );
	DatabaseManager.setCurrentAdmin(currentAdmin); 	
	Scanner input = new Scanner( System.in);
	
	System.out.println("-----------------INSERTING A NEW ADMIN---------------");
	System.out.print("Input username: ");
	String  name = input.nextLine();
	System.out.print("Input password: ");
	String pass = input.nextLine();
	Admin newAdmin = new Admin( name,pass);
	
	if( AdminManager.insert(newAdmin) ){
	    System.out.println("Insertion Successful");
	}
	else
	    System.err.println("Wasn't Inserted");
	
	input.close();
	ConnectionManager.close();
    }
}
