package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JFileChooser;

import database.bean.Admin;
import database.bean.student.Student;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import utils.ValidationType;

public class StudentInsertionTest
{

    public static void main(String[] args)
    {
	DatabaseManager.setCurrentAdmin( new Admin("Chidiebere", "Fred"));
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Select the image of the student");
	chooser.showOpenDialog(null);
	
	Student student = new Student("EYY-C32", "Stream 2", "email@email.com", chooser.getSelectedFile());
	try
	{
	    System.out.println("Registered: " + registerStudent(student));
	}
	catch (SQLException | InvalidBeanException | InvalidAdminException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    public static boolean registerStudent( Student newStudent ) 
	    throws SQLException, InvalidBeanException, InvalidAdminException
    {
	if( ! DatabaseManager.validateAdmin() ) 
	    throw new InvalidAdminException( "The Admin that wants to make the change is ivalid");

	if( !Student.isValid(ValidationType.NEW_BEAN, newStudent))
	{
	    throw new InvalidBeanException("The Student object cannot be inserted "
		    + "Please ensure that the email, firstName and other attributes are valid.");
	}

	try( InputStream inStream = new FileInputStream( newStudent.getImage());
		Connection conn =  ConnectionManager.getInstance().getConnection();
		CallableStatement statement = DatabaseManager.getCallableStatement("{call insertStudent(?,?,?, ?, ?)} ",
			newStudent.getIdCardNumber() , newStudent.getEmailAddress(), inStream  , 
			newStudent.getModClassName() ) ; )
	{
	    statement.setBinaryStream(3, inStream); 
	    statement.registerOutParameter("regDate",  Types.DATE);
	    conn.setAutoCommit( false);
	    int affected = statement.executeUpdate();
	    if( affected > 0   )
	    {
		newStudent.setDateAdmitted( statement.getDate(5));
		conn.commit();
		conn.setAutoCommit( true);
		newStudent.setDateAdmitted( statement.getDate( 5 ) );
		return true;
	    }
	    else
		conn.rollback();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	return false;
    }

    

}
