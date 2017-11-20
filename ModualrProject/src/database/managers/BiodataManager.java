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

import database.bean.Biodata;
import database.bean.Student;
import exception.InvalidAdminException;

public class BiodataManager
{   
    public boolean update( Biodata data) throws InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	return false;
    }

    public static boolean insert(Biodata biodata)
    {
	return false;
    }

    public static boolean update(Student existingStudent, Biodata biodata2)
    {
	// TODO Auto-generated method stub
	return false;
    }


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


    private static void  junk(){
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
    }

}
