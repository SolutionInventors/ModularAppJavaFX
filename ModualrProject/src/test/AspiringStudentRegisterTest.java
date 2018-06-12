package test;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import database.bean.student.AspiringStudent;
import database.bean.student.AspiringStudentData;
import database.managers.AspiringStudentManager;
import database.managers.StudentManager;
import exception.InvalidAdminException;

public class AspiringStudentRegisterTest
{

    public static void main(String[] args) throws SQLException, InvalidAdminException
    {
	Map<AspiringStudent, AspiringStudentData> aspStudents = 
		AspiringStudentManager.getAspiringStudents(0);
	
	Iterator<AspiringStudent > iter = aspStudents.keySet().iterator();
	AspiringStudent student = iter.next();
	
	String studentId = TestUtils.getStringInput("Enter new student id:  " );
	if(StudentManager.registerStudent(studentId, "Stream 2", student)){
	    System.out.println("Successfully registered aspiring student");
	} else {
	    System.err.println("Failed to register student");
	}
	
    }

}
