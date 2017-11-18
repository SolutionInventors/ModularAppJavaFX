package test;

import java.sql.SQLException;
import java.util.Scanner;

import database.bean.Admin;
import database.managers.AdminManager;
import database.managers.DatabaseManager;
import exception.InvalidAdminException;
import exception.InvalidBeanException;

public class AdminTest
{
    public static void main(String[] args) throws InvalidBeanException, InvalidAdminException, SQLException
    {
	Admin currentAdmin = new Admin("Enoch", "Ayobami" );
	DatabaseManager.setCurrentAdmin(currentAdmin); 	
	
	Scanner input = new Scanner( System.in);
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
    }

}
