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

import database.bean.student.Biodata;
import database.bean.student.Student;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import utils.ValidationType;

public class BiodataManager
{   

    public static boolean insert(Biodata data) 
	    throws SQLException, InvalidAdminException, InvalidBeanException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( !data.isValid(ValidationType.NEW_BEAN )) throw new InvalidBeanException();


	try( FileInputStream inStream = new FileInputStream( data.getImage());
		CallableStatement statement =  DatabaseManager.getCallableStatement
			("{call insertBiodata(?,?,?,?, ?,?,?, ?, ?,?,?, ?, ?,?) }", 
				data.getStudentId(),data.getTitle(), data.getSurname(),  
				data.getMiddleName(), data.getLastName(), data.getPermanentAddress(), 
				data.getCurrentAddress(), data.getReligion(), data.getStateOfOrigin(),
				data.getCountry(), data.getGender(), data.getDateOfBirth(), data.getPlaceOfBirth(),
				inStream ); ) 

	{
	    int affected = statement.executeUpdate();
	    if( affected >0 ) return true ;
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	return false;
    }

    public static boolean update(Student existingStudent, Biodata data) 
	    throws InvalidAdminException, InvalidBeanException, SQLException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( !(data.isValid(ValidationType.NEW_BEAN ) && 
		data.getStudentId().equals(existingStudent.getIdCardNumber()))){
	    throw new InvalidBeanException();
	}

	try( FileInputStream inStream = new FileInputStream( data.getImage());
		CallableStatement statement =  DatabaseManager.getCallableStatement
			("{call updateBiodata(?,?,?,?, ?,?,?, ?, ?,?,?, ?, ?,?) }", 
				data.getStudentId(),data.getTitle(), data.getSurname(),  
				data.getMiddleName(), data.getLastName(), data.getPermanentAddress(), 
				data.getCurrentAddress(), data.getReligion(), data.getStateOfOrigin(),
				data.getCountry(), data.getGender(), data.getDateOfBirth(), data.getPlaceOfBirth(),
				inStream ); ) 

	{
	    int affected = statement.executeUpdate();
	    if( affected >0 ) return true ;	
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
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


    private static boolean  junk(Student newStudent) throws SQLException{
	FileInputStream inputStream = null ;
	CallableStatement  statement= null;
	
	try
	{
	    inputStream = new FileInputStream(new File(""));
	    statement = DatabaseManager.getCallableStatement( 
		    "{CALL insertStudent(?, ?, ?, ?, ?, ? ) } ", newStudent.getIdCardNumber(),
		    newStudent.getDateAdmitted(), newStudent.getEmailAddress() ,
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
	catch (SQLException e)
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

}
