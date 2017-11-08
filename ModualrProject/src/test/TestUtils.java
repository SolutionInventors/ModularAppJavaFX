package test;

import java.util.Scanner;

import database.bean.Admin;
import database.bean.Student;

public class TestUtils
{

    public static String getStringInput( String prompt ){
	System.out.print( prompt );
	String userInput = null ;
	Scanner input = new Scanner( System.in) ;

	userInput = input.nextLine();
	return userInput;
    }

    public static void displayAll(Admin[] allAdmin)
    {
	System.out.println("----------------------------------\n");
	System.out.println("Admins:");
	for ( int i = 0 ; i < allAdmin.length ; i++ ){
	    System.out.println( "Username: " + allAdmin[i].getUsername() + " " + 
		    "Password: " + allAdmin[i].getPassword());
	}
	System.out.println("\n----------------------------------");

    }

    public static void displayAllStudents(Student[] students)
    {

    }

}
