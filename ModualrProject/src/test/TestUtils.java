package test;

import java.util.Scanner;

public class TestUtils
{

   public static String getStringInput( String prompt ){
       System.out.print( prompt );
       Scanner input = new Scanner( System.in);
       
       String userInput = input.nextLine();
       
       return userInput;
   }

}
