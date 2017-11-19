package database.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import database.bean.Phone;
import database.bean.Student;
import exception.InvalidAdminException;
import exception.InvalidBeanException;

public class StudentManager
{
    
    /** 
     * 
     * @param student
     * @return
     * @throws SQLException 
     * @throws InvalidBeanException 
     */
    public static boolean insert( Student newStudent ) 
	    throws SQLException, InvalidBeanException
    {
	if( ! DatabaseManager.validateAdmin() ) 
	    throw new InvalidAdminException( "The Admin that wants to make the change is ivalid");
	
	if( !Student.isValid(newStudent))
	{
	    throw new InvalidBeanException("The Student object cannot be inserted "
		    + "Please ensure that the email, firstName and other attributes are valid.");
	}

	FileInputStream inputStream = null ;
	CallableStatement  statement= null;
	try
	{
	    inputStream = new FileInputStream(newStudent.getBioData().getImage());
	    statement = DatabaseManager.getCallableStatement( 
			    "{CALL insertStudent(?, ?, ?, ?, ?, ? ) } ", newStudent.getIdCardNumber(),
			    newStudent.getFirstName(), newStudent.getLastName() ,
			    newStudent.getEmailAddress(), inputStream );

	    statement.setBinaryStream( "studentImage", inputStream);
	    statement.registerOutParameter("currentDate", Types.DATE );
	    int affected = statement.executeUpdate();

	    if( affected > 0 ){
		newStudent.setDateAdmitted( statement.getDate("currentDate" ));
		return true;
	    }
	    

	}
	catch (FileNotFoundException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	finally{

	    try
	    {
		if( inputStream != null ) inputStream.close();
	    }
	    catch (IOException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    if( statement!= null ) statement.close();
	}
	return false;
    }

    /**
     * Gets the first 30 {@code Student}s in the database that are active in the
     * modular program starting from a specified startIndex. 
     * It should be used to create  pages containing students in the application
     * @param startIndex the first index that the query would return. NOte that 
     * the first {@code Student} has index 0
     * @return an array of {@code Student } objects
     * @throws SQLException when a database {@code Exception} occurs
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static Student[] getAllActiveStudents( int startIndex) throws SQLException{
	ArrayList<Student> list;
	CallableStatement  statement = null;

	ResultSet result = null ;
	try
	{
	    statement = DatabaseManager.getCallableStatement( 
		    "{CALL getAllActiveStudents(?) } ");

	    statement.setInt( 1, startIndex );
	    result = statement.executeQuery();

	    list = new ArrayList<Student>();

	    while ( result.next() )
	    {
		String name[] = result.getString("Name" ).split(" " );
		Student student =new Student(result.getString("ID Card Number"),  name[0], name[1] , 
			result.getString("Email"), result.getBoolean( "Active"));
		student.setDateAdmitted( result.getDate( "dateAdmitted" )) ;
		File studentImage = new File(student.getName().replace(" ", "-")  + ".jpg");

		getImageFromStream(result, studentImage);
		student.setImage( studentImage );


		list.add( student );
	    }
	}
	finally
	{
	    if (statement!= null ) statement.close();
	    if( result != null ) result.close();
	} 
	return list.toArray( new Student[ list.size()] );
    }

    private static void updateStudentPhoneNumbers(Student student) 
    {
	ResultSet result = null;
	try(  CallableStatement statement = DatabaseManager.getCallableStatement( 
		"{CALL getStudentPhoneNumbers(?) } "); ) 
	{
	    statement.setString(0, student.getIdCardNumber() );
	    result = statement.executeQuery();
	    ArrayList<Phone> list = new ArrayList<>();
	    if( result.next() ){
		String[] numbers = result.getString(0).split( "," );
		for( int i = 0 ; i < numbers.length ; i++ )
		{
		    Phone phone = new Phone(student.getIdCardNumber()
			    , numbers[i].replaceAll(" ", "" ) );
		    list.add( phone );
		}
		student.setPhoneNumbers( list.toArray( new Phone[ list.size() ] ) );
	    }


	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	finally{
	    if( result != null )
		try
	    {
		    result.close();
	    }
	    catch (SQLException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	}

    }

    /**
     * @param result
     * @param studentImage
     * @throws SQLException
     * @throws IOException 
     */
    private static void getImageFromStream(ResultSet result, File studentImage) throws SQLException
    {
	InputStream input = null ;
	FileOutputStream output = null;
	try{
	    input = result.getBinaryStream("Image" );
	    output = new FileOutputStream( studentImage );

	    byte[] buffer = new byte[1024];
	    while( input.read( buffer) >0 ){
		output.write( buffer );
	    }
	}

	catch (IOException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	finally
	{
	    try
	    {
		if( input != null ) input.close();
		if( output != null ) output.close();
	    }
	    catch (IOException e)
	    {
		e.printStackTrace();
	    }
	}

    }

    /**
     * Gets the first 30 {@code Student}s in the database that are no longer in the
     * modular program starting from a specified startIndex. 
     * It should be used to create  pages containing students in the application
     * @param startIndex the first index that the query would return. NOte that 
     * the first {@code Student} has index 0
     * @return an array of {@code Student } objects
     * @throws SQLException when a database {@code Exception} occurs
     * 
     */
    public static Student[] getAllInactiveStudents( int startIndex) throws SQLException{
	CallableStatement  statement = null ;
	ResultSet result = null;
	ArrayList<Student> list = new ArrayList<Student>();;
	try
	{
	    statement = DatabaseManager.getCallableStatement( 
		    "{CALL getAllInactiveStudents(?) } ");
	    statement.setInt( 1, startIndex );
	    result = statement.executeQuery();

	    while ( result.next() )
	    {

		String name[] = result.getString("Name" ).split(" " );
		Student student =new Student(result.getString("ID Card Number"),  name[0], name[1] , 
			result.getString("Email"), result.getBoolean( "Active"));
		student.setDateAdmitted( result.getDate( "dateRegistered" )) ;
		File studentImage = new File(student.getName().trim().replace(" ", "-")  + ".jpg");

		getImageFromStream(result, studentImage);
		student.setImage( studentImage );
		updateStudentPhoneNumbers( student );
		student.setImage( studentImage );
		list.add( student );

	    }
	}
	finally 
	{
	    if( result!=null) result.close();
	    if( statement!= null ) statement.close();
	}
	return list.toArray( new Student[ list.size()] );
    }

    /**
     * Inserts a new @code Student into the database. Note that initially the {@code Student}
     * is active. However, you can update that value via call to method update.
     * @param student
     * @return
     * @throws SQLException 
     * @throws InvalidBeanException 
     */
    public static boolean update( Student existingStudent, Student updatedStudent ) throws SQLException, InvalidBeanException{

	if( !Student.isValid( existingStudent ) ) 
	    throw new InvalidBeanException("The student object contains some invalid values");

	else if( !existingStudent.getIdCardNumber().equals( updatedStudent.getIdCardNumber() ) )
	    throw new InvalidBeanException("The new student must have the same id as the existing Student");

	try( FileInputStream input =  new FileInputStream( updatedStudent.getImage( ));
		CallableStatement  statement = DatabaseManager.getCallableStatement( 
			"{CALL updateStudent(?, ?,?,?, ?) } ", updatedStudent.getIdCardNumber(),
			updatedStudent.getFirstName(), updatedStudent.getLastName(), updatedStudent.isActive(),
			updatedStudent.getEmailAddress(), input );)
	{
	    int affected = statement.executeUpdate();
	    if( affected == 1 ) return true;
	}
	catch ( IOException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return false;
    }

    /**
     * Gets the total number of active or inactive {@code student}s in database 
     * @param active if {@code true} returns active {@code Student}s else returns inactive {@code Student}s
     * @return
     * @throws SQLException
     */
    public static int getStudentsCount( boolean active) throws SQLException
    {
	String sql = active ? "{CALL getActiveStudentsCount() } " :"{CALL getInactiveStudentsCount() } ";
	ResultSet result  = null;
	try(  CallableStatement  statement = DatabaseManager.getCallableStatement( sql);)
	{
	    result = statement.executeQuery();
	    if( result.next() )
		return result.getInt( 1 ) ;
	}
	finally
	{
	    if( result!= null ) result.close();
	}
	return 0;

    }

    /**
     * This method deletes a {@code Student } from the database.
     * it uses the {@code Student}'s id card number to identify the {@code Student } to
     * be deleted.<br>
     * 
     * @param currentAdmin the {@code Admin }object that is logged in.
     * @param AdminToDelete the existing {@code Module} to delete from the database
     * @return {@code true} when delete was successful
     * @throws InvalidAdminException when the {@code Admin } object is not in the database 
     * @throws SQLException 
     */
    public static boolean delete( Student studentToDelete ) throws InvalidAdminException, SQLException{

	try(CallableStatement  statement = DatabaseManager.getCallableStatement( 
		"{call deleteStudent(?)}" );)
	{
	    statement.setString( 1 , studentToDelete.getIdCardNumber() );
	    int affected = statement.executeUpdate();
	    if ( affected == 1 ) return true;
	}

	return false;
    }
}
