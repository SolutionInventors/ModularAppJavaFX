package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import database.bean.student.JobResponsibility;
import database.bean.student.ProfessionalExperience;
import database.bean.student.Student;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import exception.InvalidExperienceException;
import utils.ValidationType;

public final class ExperienceManager
{
    /**
     * This method inserts data into the {@code ProfessionalExperience} table an inserts the
     * different responsibilities that the {@code Student} had during the job into the
     * {@code Responsibility } table in the database.<br>
     * This method should be used only in the {@code StudentManager} object when registering a
     * {@code Student}. If it to be used outside that scope then a call to the {@code Connection}
     * object's commit method should be if this method returns {@code true}.
     * @param experience
     * @return
     * @throws SQLException
     * @throws InvalidAdminException
     * @throws InvalidBeanException
     */
    @SuppressWarnings("resource")
    public static boolean insert(ProfessionalExperience experience) 
	    throws SQLException, InvalidAdminException, InvalidBeanException
    {
	if( !experience.isValid(ValidationType.NEW_BEAN ) ) throw new InvalidExperienceException();

	Connection conn = ConnectionManager.getInstance().getConnection();
	try(
		CallableStatement statement =  DatabaseManager.getCallableStatement
		("{call addExperienceRecord(?,?,?,?, ? ,?) }", experience.getStudentId(), 
			experience.getStartDate(), experience.getEndDate(), 
			experience.getEmployer(), experience.getJobTitle() ) ; )
	{
	    conn.setAutoCommit(false);
	    statement.registerOutParameter(6, Types.INTEGER);
	    int affected = statement.executeUpdate();
	    JobResponsibility res;
	    boolean successful = true;
	    if( affected >0 ){
		final int expId =statement.getInt( 6 );
		final String[] duties = experience.getDuties();

		for( String duty : duties ){
		    res = new JobResponsibility(expId, duty );
		    if( !ResponsibilityManager.insert( res ) ){
			successful = false;
			break;
		    }
		}

		if( successful){
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
	if( !experience.isValid(ValidationType.EXISTING_BEAN) ) 
	    throw new InvalidExperienceException("The Experience cannot be updated");
	return false;
    }

    public static ProfessionalExperience[] getExpriences(Student student) 
	    throws SQLException, InvalidAdminException
    {
	
	ResultSet result = null;
	List<ProfessionalExperience> list = new LinkedList<>();

	try( CallableStatement statement =  DatabaseManager.getCallableStatement
		("{call getProfExperience(?) }", student.getIdCardNumber());)
	{

	    result = statement.executeQuery();
	    ProfessionalExperience temp; 
	    while( result.next()){
		String[] duties = ResponsibilityManager.getDuties( result.getInt("id"));
		temp = new ProfessionalExperience(result.getString("studentId"),
			result.getDate("StartDate"), result.getDate("EndDate"),
			result.getString("Job Title"), result.getString("Employer"), duties);
		list.add( temp );
	    }

	}
	finally{
	    if( result != null ) result.close();
	}
	return list.toArray( new ProfessionalExperience[list.size()] );
    }
}
