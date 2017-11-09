package test;

public class MoreStringTest
{

    public static void main(String[] args)
    {
	
	String word =  " ";
	
	System.out.println( word.matches( "[[A-za-z]{1,}\\s[A-Za-z]{0,}]{1,}") );
    }

}
