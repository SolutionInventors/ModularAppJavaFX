package database.managers;

import java.sql.CallableStatement;
import java.sql.SQLException;

import database.bean.ProfessionalExperience;
import database.bean.Student;
import database.bean.ValidationType;
import exception.InvalidAdminException;
import exception.InvalidBeanException;

public final class ProfExperienceManager
{
    public static boolean insert(ProfessionalExperience experience) 
	    throws SQLException, InvalidAdminException, InvalidBeanException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( !experience.isValid(ValidationType.NEW_BEAN )) throw new InvalidBeanException();

	try( CallableStatement statement =  DatabaseManager.getCallableStatement
		("{call addExperienceRecord(?,?,?,?, ?,?) }", experience.getStudentId(), 
			experience.getStartDate(), experience.getEndDate(), 
			experience.getEmployer(), experience.getJobTitle(), 
			experience.getQualification() ) ; )
	{
	    int affected = statement.executeUpdate();
	    if( affected >0 ) return true ;
	}
	return false;
    }

    public static boolean update(Student existingStudent, ProfessionalExperience experience2)
    {
	// TODO Auto-generated method stub
	return false;
    }

}
