package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.bean.EducationalBackground;
import database.bean.Student;
import database.bean.ValidationType;
import exception.InvalidAdminException;
import exception.InvalidBeanException;

public final class EducationManager
{
    protected static boolean insert(EducationalBackground education) 
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
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();

	if( !( student.isValid(ValidationType.EXISTING_BEAN) ))
	{
	    throw new InvalidBeanException();
	}
	List<EducationalBackground> list = new ArrayList<>();

	ResultSet result =  null;
	try( CallableStatement statement =  DatabaseManager.getCallableStatement
		("{call updateEducationRecord(? ) }", student.getIdCardNumber()); )
	{
	    result = statement.executeQuery();

	    EducationalBackground temp;
	    while( result.next())
	    {
		temp = new EducationalBackground(result.getString("student_id"), 
			result.getDate("beginDate"), result.getDate("endDate"),
			result.getString("institute") , result.getString("courseRead") ,
			result.getString( "qualification" ) );
		list.add( temp);

	    }
	}
	finally{
	    if( result != null && !result.isClosed()) result.close();
	}
	return list.toArray( new EducationalBackground[ list.size() ]);
    }
}
