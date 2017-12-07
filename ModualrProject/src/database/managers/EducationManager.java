package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import database.bean.student.EducationalBackground;
import database.bean.student.Student;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import utils.ValidationType;

public final class EducationManager
{
    public static boolean insert(EducationalBackground education) 
	    throws SQLException, InvalidAdminException, InvalidBeanException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( !education.isValid(ValidationType.NEW_BEAN )) throw new InvalidBeanException();

	try( CallableStatement statement =  DatabaseManager.getCallableStatement
		("{call addEducationRecord(?,?,?,?, ?,?) }", education.getStudentId(), 
			education.getBeginDate(), education.getEndDate(),
			education.getInstitution(), education.getCourseRead(), 
			education.getQualification()); )
	{
	    int affected = statement.executeUpdate();
	    if( affected >0 ) return true ;
	}

	return false;

    }

    public static boolean update(Student existingStudent, EducationalBackground education) 
	    throws SQLException, InvalidAdminException, InvalidBeanException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	//Ensures that the two objects are valid and that the both have the same 
	//student id card number
	if( ! ( education.isValid(ValidationType.NEW_BEAN ) &&
		existingStudent.isValid(ValidationType.EXISTING_BEAN)&& 
		existingStudent.getIdCardNumber().equals( education.getStudentId()) ))
	{
	    throw new InvalidBeanException();
	}

	try( CallableStatement statement =  DatabaseManager.getCallableStatement
		("{call updateEducationRecord(? , ?,?,?,?, ?,?) }", 
			education.getStudentId(), education.getBeginDate(), 
			education.getEndDate(),education.getInstitution(),
			education.getCourseRead(), education.getQualification()); )
	{
	    int affected = statement.executeUpdate();
	    if( affected >0 ) return true ;
	}
	return false;
    }

    

    public static EducationalBackground[] getEducationInfo( Student student ) 
	    throws InvalidBeanException, SQLException, InvalidAdminException
    {
	if( !( student.isValid(ValidationType.EXISTING_BEAN) ))
	{
	    throw new InvalidBeanException();
	}
	List<EducationalBackground> list = new LinkedList<>();

	ResultSet result =  null;
	try( CallableStatement statement =  DatabaseManager.getCallableStatement
		("{call getEducationRecord(? ) }", student.getIdCardNumber()); )
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
}
