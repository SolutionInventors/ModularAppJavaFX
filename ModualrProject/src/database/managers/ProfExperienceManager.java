package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.bean.JobResponsibility;
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
	ResultSet result = null;
	try(
		Connection conn = ConnectionManager.getInstance().getConnection();
		CallableStatement statement =  DatabaseManager.getCallableStatement
			("{call addExperienceRecord(?,?,?,?, ?) }", experience.getStudentId(), 
				experience.getStartDate(), experience.getEndDate(), 
				experience.getEmployer(), experience.getJobTitle() ) ; )
	{
	    conn.setAutoCommit(false);
	    int affected = statement.executeUpdate();
	    JobResponsibility res;
	    boolean successful = true;
	    if( affected >0 ){
		result = statement.getGeneratedKeys();
		if( result.next() ){
		    final int expId = result.getInt(0);
		    final String[] duties = experience.getDuties();
		    for( int i =0 ; i< duties.length; i++ ){
			res = new JobResponsibility(expId, duties[i] );
			if( !ResponsibilityManager.insert( res ) ){
			    successful = false;
			    break;
			}
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

	}finally{
	    if( result!=null ) result.close();
	}

	return false;
    }

    public static boolean update(Student existingStudent, ProfessionalExperience experience2)
    {
	// TODO Auto-generated method stub
	return false;
    }




}
