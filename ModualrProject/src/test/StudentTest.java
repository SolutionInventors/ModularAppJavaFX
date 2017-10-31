package test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;

import database.bean.Student;
import database.managers.StudentManager;
import exception.InvalidBeanException;

public class StudentTest
{

    public static void main(String[] args) throws SQLException, InvalidBeanException, ClassNotFoundException, IOException
    {
	TestUtils.displayAllActiveStudents( StudentManager.getAllActiveStudents(0) );
	//**Insert a student..
	
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.showOpenDialog( null );
	
	File file = fileChooser.getSelectedFile() ;
	String fName =  TestUtils.getStringInput( "Input First name: " );
	String lName =  TestUtils.getStringInput( "Input last name: " );
	String mail =  TestUtils.getStringInput( "Input e-mail: " );
	String id = TestUtils.getStringInput("Input unique student id: " );
	Student student = new Student("id", fName, lName, mail, false );
	
	student.setImage( file );
	StudentManager.insert( student );
	
	
	
    }

}
