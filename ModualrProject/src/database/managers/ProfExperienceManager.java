package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import database.bean.student.JobResponsibility;
import database.bean.student.ProfessionalExperience;
import database.bean.student.Student;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import utils.ValidationType;

public final class ProfExperienceManager
{
    public static boolean insert(ProfessionalExperience experience) 
	    throws SQLException, InvalidAdminException, InvalidBeanException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( !experience.isValid(ValidationType.NEW_BEAN ) ) throw new InvalidBeanException();
	
	try(
		Connection conn = ConnectionManager.getInstance().getConnection();
		CallableStatement statement =  DatabaseManager.getCallableStatement
			("{call addExperienceRecord(?,?,?,?, ? ,?) }", experience.getStudentId(), 
				experience.getStartDate(), experience.getEndDate(), 
				experience.getEmployer(), experience.getJobTitle() ) ; )
	{
	    conn.setAutoCommit(false);
	    int affected = statement.executeUpdate();
	    statement.registerOutParameter(6, Types.INTEGER);
	    JobResponsibility res;
	    boolean successful = true;
	    if( affected >0 ){
		final int expId =statement.getInt( 6 );
		final String[] duties = experience.getDuties();

		for( int i =0 ; i< duties.length; i++ ){
		    res = new JobResponsibility(expId, duties[i] );
		    if( !ResponsibilityManager.insert( res ) ){
			successful = false;
			break;
		    }
		}

		if( successful){
		    conn.commit();
		    return true;
		}
		else{
		    conn.rollback();
		    return false;
		}
	    }
	    conn.setAutoCommit(true);

	}

	return false;
    }

    public static boolean update(Student existingStudent, ProfessionalExperience experience)
	    throws InvalidBeanException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin()) throw new InvalidAdminException();
	if( !experience.isValid(ValidationType.EXISTING_BEAN) ) 
	    throw new InvalidBeanException("The Experience cannot be updated");
	return false;
    }




}
