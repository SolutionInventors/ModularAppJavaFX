package test;

import java.sql.SQLException;

import database.managers.StudentManager;

public class StudentTest
{

    public static void main(String[] args) throws SQLException
    {
	TestUtils.displayAllActiveStudents( StudentManager.getAllInactiveStudents(0) );
	
    }

}
