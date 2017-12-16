package test;

public class StringMatchTest
{

    public static void main(String[] args)
    {
	String phoneNumber = "+0802       3218        3      61";
	String string = "32223";
	System.out.println( string.matches("[0-9]{1,}"));
	System.out.println( phoneNumber.matches("[+|0-9][0-9]{1,}" ));
	System.out.println( phoneNumber.replaceAll("\\s{2,}", " "));
    }

}
