package test;

import java.util.Scanner;

import database.bean.Admin;
import database.bean.Student;

public class TestUtils
{

   public static String getStringInput( String prompt ){
       System.out.print( prompt );
       Scanner input = new Scanner( System.in);
       
       String userInput = input.nextLine();
       
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

    public static void displayAllActiveStudents(Student[] activeStudents)
    {
	System.out.println("----------------------------------\n");
	System.out.println("Students:");
	for ( int i = 0 ; i < activeStudents.length ; i++ ){
	    System.out.println( (i+1) + ".\tFirstName: " + activeStudents[i].getName() + 
		    		"\n\tIdCardNumber: " + activeStudents[i].getIdCardNumber() +"\n");
	}
	System.out.println("\n----------------------------------");
 
    }
   
}
