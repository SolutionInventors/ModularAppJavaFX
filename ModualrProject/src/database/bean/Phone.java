package database.bean;

/**This class represents a single row in the Phone table*/
public class Phone  implements Bean
{
    private String studentId;
    private String number;
    
    public Phone( String id , String phoneNumber ){
	setId( id );
	setNumber(phoneNumber);
	
    }

    public String getStudentId()
    {
	return studentId;
    }

    public void setId(String id)
    {
	this.studentId = id;
    }

    public String getNumber()
    {
	return number;
    }

    public void setNumber(String number)
    {
	this.number = number;
    } 
}
