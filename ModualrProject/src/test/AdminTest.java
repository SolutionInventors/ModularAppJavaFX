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
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	DatabaseManager.setCurrentAdmin(currentAdmin); 	
	Scanner input = new Scanner( System.in);

	System.out.println("-----------------INSERTING A NEW ADMIN---------------");
	String  name = TestUtils.getStringInput("Input username: ");
	String pass = TestUtils.getStringInput("Input password(At least 8 characters): ");
	String mail = TestUtils.getStringInput("Input Email: ");
	String accessType = TestUtils.getStringInput("Input 1 for SUPER access\n"
		+ "2 for READ_ONLY\n3 for WRITE ONLY\n4FOR READ_AND_WRITE:\n");

	Admin newAdmin = new Admin( name,pass, mail);
	accessType = accessType.matches("1") ? "SUPER" : 
	    accessType.matches("2") ? "READ" : 
		accessType.matches("3") ? "WRITE" : "READ_AND_WRITE";
	newAdmin.setAccessType(accessType);

	System.out.println("Attempting to verify mail.....");
	if( AdminManager.sendMail(mail)){
	    System.out.println("A verification number was sent to the mail..." );
	    String testNum = TestUtils.getStringInput("Input the number: ");
	    if( AdminManager.insert(newAdmin, testNum) ){
		System.out.println("Insertion Successful");
	    }
	    else
		System.err.println("Wasn't inserted");
	}
	else
	    System.err.println("Verification failed! Email probably doesn't exists");

	input.close();
	ConnectionManager.close();
    }
}
