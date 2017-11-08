package test;

public class StringMatchTest
{

    public static void main(String[] args)
    {
	String number = "Stream                                0";
	System.out.println( number);
	number = number.trim();

	while( number.matches( "\\S*\\s{2,}\\S*" ) )
	    number =number.replaceAll("  " , " " ).replaceAll("  ", " ");
	
//	System.out.println("    ".matches("\\s{2,}"));
//	number = number.replace("\\s{2,}", " ");
	
	System.out.println( number);
	System.out.println( number.matches("[A-Za-z]* [A-Za-z|0-9]*"));
    }

}
