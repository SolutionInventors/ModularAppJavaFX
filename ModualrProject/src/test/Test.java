package test;

public class Test
{
    public void print(){
	System.out.println("Hello");
    }
    
    public static void main( String[] args ){
	String word = "Stream s   ";
	if( word.matches( "[[A-Za-z]* [A-Za-z]]*") ){
	    System.out.println("Matched");
	}else
	    System.out.println("did not match");
	
    }
}
