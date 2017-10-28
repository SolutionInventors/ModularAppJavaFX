package test;

import java.util.Scanner;

import database.bean.Admin;

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
   
}
