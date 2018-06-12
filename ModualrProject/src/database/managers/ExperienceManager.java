package database.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import database.bean.student.AspiringStudent;
import database.bean.student.JobResponsibility;
import database.bean.student.ProfessionalExperience;
import database.bean.student.Student;
import exception.InvalidAdminException;
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
    public static boolean insert(final ProfessionalExperience experience) 
	    throws SQLException, InvalidAdminException
    {
	if( experience.isValid(ValidationType.NEW_BEAN ) ){
	    final Connection conn = ConnectionManager.getInstance().getConnection();
	    final String sql = 
		    "INSERT INTO `professional_experience`"
		    + "(`StudentId`, `StartDate`, `EndDate`, `Employer`, `Job Title`) "
		    + " VALUES( ?, ? ,? ,? ,?)";
	    try(
		    PreparedStatement statement =  DatabaseManager.getPreparedStatement
		    (sql, experience.getStudentId(), experience.getStartDate(), 
			    experience.getEndDate(), experience.getEmployer(), experience.getJobTitle() ) ; )
	    {
		conn.setAutoCommit(false);
		
		final int affected = statement.executeUpdate();
		JobResponsibility res;
		boolean successful = true;
		final ResultSet result =  statement.getGeneratedKeys();
		if( affected >0 && result.next()){
		   
		    final int expId = result.getInt(1);
		    final String[] duties = experience.getDuties();

		    for( final String duty : duties ){
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
	    }
	}
	return false;
    }

    public static boolean update(final Student existingStudent, final ProfessionalExperience experience)
	    throws  InvalidAdminException
    {
	if( !experience.isValid(ValidationType.EXISTING_BEAN) ) return false;
	return false;
    }

    
    public static ProfessionalExperience[] getExpriences(final AspiringStudent student) throws SQLException, InvalidAdminException {
	final String sql = 
		"SELECT * FROM `aspiringexperience` "+ 
		"WHERE AspId = ?;"	;

	return getExperienceHelper(sql, String.valueOf(student.getId()), true );
    }
    
    
    public static ProfessionalExperience[] getExperienceHelper(String sql, String id, boolean isAspStudent)
		    throws SQLException, InvalidAdminException{
        ResultSet result = null;
        List<ProfessionalExperience> list = new LinkedList<>();
        
        try( PreparedStatement statement =  DatabaseManager.getPreparedStatement
        	(sql,id);)
        {
            String idCol = isAspStudent ? "ID": "studentId";
        
            result = statement.executeQuery();
            ProfessionalExperience temp; 
            while( result.next()){
        	String[] duties = 
        		ResponsibilityManager.getDuties( 
        			result.getInt(idCol), isAspStudent);
        	temp = new ProfessionalExperience(result.getString(idCol),
        		result.getDate("BeginDate"), result.getDate("EndDate"),
        		result.getString("Job Title"), result.getString("Employer"), duties);
        	list.add( temp );
            }
        
        }
        finally{
            if( result != null ) result.close();
        }
        return list.toArray( new ProfessionalExperience[list.size()] );
    }
    public static ProfessionalExperience[] getExpriences(final Student student) 
	    throws SQLException, InvalidAdminException
    {
	final String sql = "SELECT * FROM `professional_experience` "+ 
			"WHERE StudentId = ?;"	;

	return getExperienceHelper(sql, student.getIdCardNumber(), false);
    }
}
