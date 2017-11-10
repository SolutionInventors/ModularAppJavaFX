package test;

public class StringMatchTest
{

    public static void main(String[] args)
    {
	String phoneNumber = "+08023218361";
	
	System.out.println( phoneNumber.matches("[+|0-9][0-9]{1,}" ));
    
    }

}
