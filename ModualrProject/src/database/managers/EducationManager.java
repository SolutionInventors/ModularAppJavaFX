package database.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import database.bean.student.AspiringStudent;
import database.bean.student.EducationalBackground;
import database.bean.student.Student;
import exception.InvalidAdminException;
import utils.ValidationType;

public final class EducationManager
{
    private EducationManager(){}

    public static boolean insert(EducationalBackground education) 
	    throws SQLException, InvalidAdminException
    {
	if( education.isValid(ValidationType.NEW_BEAN )){
	    String sql = 
		    "INSERT INTO `educational_background`"
		    + "(`StudentId`, `BeginDate`, `EndDate`, `Institution`, "
		    + "`CourseRead`, `QualificationName`) "
		    + "VALUES (? , ? ,? ,? ,? ,?);";
	    
	    try( PreparedStatement statement =  DatabaseManager.getPreparedStatement
		    (sql, education.getStudentId(), 
			    education.getBeginDate(), education.getEndDate(),
			    education.getInstitution(), education.getCourseRead(), 
			    education.getQualification()); )
	    {
		int affected = statement.executeUpdate();
		if( affected >0 ) return true ;
	    }

	}
	return false;

    }

    public static boolean update(EducationalBackground existing, EducationalBackground newBean) 
	    throws SQLException, InvalidAdminException
    {
	//Ensures that the two objects are valid and that the both have the same 
	//student id card number
	if(  ( newBean.isValid(ValidationType.NEW_BEAN ) &&
		existing.isValid(ValidationType.EXISTING_BEAN) && 
		newBean.getStudentId().equals( newBean.getStudentId()) ))
	{
	    final String sql = 
	    "UPDATE educational_background "
	    + "SET StudentId = ? , Institution = ?  ,"
	    + " BeginDate = ?, EndDate = ?, CourseRead = ?  "
	    + "WHERE StudentId = ? AND Institution = ? AND BeginDate = ?"
	    + " AND EndDate = ? AND  CourseRead = ?; ";

	    try( PreparedStatement statement =  DatabaseManager.getPreparedStatement
		    (sql, newBean.getStudentId(), newBean.getInstitution(), newBean.getBeginDate(), 
			    newBean.getEndDate(), newBean.getCourseRead() , existing.getStudentId(), 
			    existing.getInstitution(), existing.getBeginDate(), existing.getEndDate(), 
			    existing.getCourseRead())
		    )
	    {
		int affected = statement.executeUpdate();
		if( affected >0 ) return true ;
	    }
	}


	return false;
    }

    public static EducationalBackground[] getEducationInfo( Student student ) 
	    throws SQLException, InvalidAdminException
    {
	if( ( student.isValid(ValidationType.EXISTING_BEAN) ))
	{
	    List<EducationalBackground> list = new LinkedList<>();

	    ResultSet result =  null;
	    String sql = 
		    "SELECT * FROM educational_background "
		    + "WHERE  studentId = ? ";
	    try( PreparedStatement statement =  
	    	DatabaseManager.getPreparedStatement(sql, student.getIdCardNumber()); )
	    {
		result = statement.executeQuery();

		EducationalBackground temp;
		while( result.next())
		{
		    temp = new EducationalBackground(result.getString("StudentId"), 
			    result.getDate("beginDate"), result.getDate("endDate"),
			    result.getString("institution") , result.getString("CourseRead") ,
			    result.getString( "QualificationName" ) );
		    list.add( temp);

		}
	    }
	    finally{
		if( result != null && !result.isClosed()) result.close();
	    }
	    return list.toArray( new EducationalBackground[ list.size() ]);
	}
	return null;

    }
    
    public static EducationalBackground[] 
	    getEducationInfo( AspiringStudent student ) throws SQLException {
	 List<EducationalBackground> list = new LinkedList<>();

	    ResultSet result =  null;
	    String sql = 
		    "SELECT * FROM aspiringstudenteducation "
		    + "WHERE  AspID = ? ";
	    
	    try( PreparedStatement statement =  
	    	DatabaseManager.getPreparedStatement(
	    		sql, student.getId()); )
	    {
		result = statement.executeQuery();

		EducationalBackground temp;
		while( result.next())
		{
		    temp = new EducationalBackground(
			    result.getString("AspID"), 
			    result.getDate("beginDate"), result.getDate("endDate"),
			    result.getString("institution") , result.getString("CourseRead") ,
			    result.getString( "Qualification" ) );
		    list.add( temp);

		}
	    }
	    finally{
		if( result != null && !result.isClosed()) result.close();
	    }
	    return list.toArray( new EducationalBackground[ list.size() ]);
	

	
    }
    
}
