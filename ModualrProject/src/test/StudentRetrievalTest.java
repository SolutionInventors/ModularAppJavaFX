package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.bean.student.Student;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.managers.StudentManager;
import exception.InvalidAdminException;

public class StudentRetrievalTest
{

    public static void main(String[] args)
    {
	//Step 1 :Create current Admin
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	DatabaseManager.setCurrentAdmin(currentAdmin);

	//Step 2: call getStudents method
	boolean isActive = true;
	try
	{
	    Student[] students = StudentManager.getStudents(0);
	    for(Student student : students) {
		System.out.println(student.getIdCardNumber());
		System.out.println(student.getEmailAddress());
		System.out.println(student.getModClassName());
		System.out.println(student.isActive());
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		//student.get
		System.out.println("->");
		
	    }
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}finally {
	    //very important! Close the connection when the user closes the app
	    ConnectionManager.close();
	}
	
	System.out.println(Student.getdefaultImage());
	
    }//end main

}
