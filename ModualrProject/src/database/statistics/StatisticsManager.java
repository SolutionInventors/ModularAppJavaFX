package database.statistics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.bean.Bean;
import database.bean.Certificate;
import database.bean.Module;
import database.bean.student.Student;
import database.managers.DatabaseManager;

public class StatisticsManager
{

    /**
     * This method retrieves statistics of the argument inputed into it or {@code null} 
     * if the specified {@code Bean } does not exists in the database.
     * It does returns the Statistics based on the type of its argument.<br>
     * For example, if a {@code Module} is inputed as its argument then a
     * {@code ModuleStats} object would be returned etc.
     * 
     * @param bean a subclass of {@code Bean} to be retrieved.
     * @return a {@code Statistics } object or {@code null} if the {@code Bean } is not 
     * found in the database.
     * @throws SQLException when a database error occurs.
     */
    @SuppressWarnings("unchecked")
    public static < T extends Statistics> T retrieveStats( Bean bean ) 
	    throws SQLException
    {
	ResultSet result = null;
	try
	{
	    if( bean instanceof Module ){
		return (T) getModuleStats(result , (Module) bean);
	    }
	    else if( bean instanceof Certificate){
		return (T) getCertificateStats(result , (Certificate) bean);
	    }
	    else if( bean instanceof Student){
		return (T) getStudentStats( result, (Student) bean );
	    }
	}
	finally{
	    if(result!=null ) result.close();
	}
	return null;
    }

    /**
     * Retrieves statistics about the tables in the database.
     * The retrieved {@link TableStats} object contains statistics about the
     * all the {@code Module}s, {@code Student}s, {@code ModularClass} and
     * so on.<br>
     * 
     * @return
     */
    public static TableStats retrieveStats(){
	
	return null;
    }
    
    private static StudentStats getStudentStats(ResultSet result, Student student) throws SQLException
    {
	String sql = 
		"SELECT COUNT(*) as 'Registered', \n" + 
			"	COUNT(if( isPaymentComplete(reg.id), 1, null)) as 'Paid', " +
			"	COUNT(if( reg.bookingStatus = 1, 1, null)) as 'Booked', " + 
			"	COUNT(if( reg.attendedStatus =1, 1, null)) as 'Attended', " +
			"	COUNT(if( reg.result = 'Fail', 1, null)) as 'Failed', " +
			"	COUNT(if( reg.result = 'Passed', 1, null)) as 'Passed'  " +
			"FROM module_register as reg " + 
			"WHERE reg.student_id = ? \n"+
			"GROUP BY reg.student_id;";

	try( PreparedStatement stmt = DatabaseManager.getPreparedStatement
		(sql, student.getIdCardNumber()); )
	{
	    result =  stmt.executeQuery();
	    if( result.next() )
		return new StudentStats(result.getInt( "Registered") , 
			result.getInt("Paid" ), result.getInt("Booked"), 
			result.getInt("Attended"), 
			result.getInt("Passed"), result.getInt("Failed"));
	    else
		return null;
	}
    }

    private static ModuleStats getModuleStats(ResultSet result , Module existinModule) 
	    throws SQLException
    {
	String sql = "SELECT reg.module_name as 'Module Name' , "
		+ "	COUNT(*) as 'Total Registered', " + 
		"	COUNT(if( isPaymentComplete(reg.id), 1, null)) as 'Num Paid', " +
		"   	COUNT(if( reg.bookingStatus = 1, 1, null)) as 'Num Booked'," +
		"  	COUNT(if( reg.attendedStatus =1, 1, null)) as 'Num Attended'," + 
		" 	COUNT(if( reg.result = 'Fail', 1, null)) as 'Num Failed', "+ 
		" 	COUNT(if( reg.result = 'Passed', 1, null)) as 'Num Passed' "+
		"	FROM module_register as reg" +
		"	WHERE reg.module_name = ? " +
		"GROUP BY reg.module_name;";

	try( PreparedStatement stmt = DatabaseManager.getPreparedStatement
		(sql, existinModule.getName()); )
	{
	    result =  stmt.executeQuery();
	    if( result.next() )
		return new ModuleStats(result.getInt("Total Registered"), 
			result.getInt("Num Paid"),result.getInt("Num Booked"),
			result.getInt("Num Attended"),result.getInt("Num Passed"),
			result.getInt("Num Failed"));
	    else
		return null;
	}

    }


    private static CertificateStats getCertificateStats( ResultSet result, Certificate cert) throws SQLException
    {
	String sql =  "SELECT stud.certificateIssued as 'Certificate', " +   
		"		COUNT( DISTINCT stud.id_card_number) as 'Student Issued' , " +
		"		COUNT( csMod.moduleName) as 'Number of Modules Required' " +
		"		FROM student as stud" + 
		"	JOIN  certificatemodule as csMod" + 
		"		ON csMod.certificateName = stud.certificateIssued" +
		"	WHERE stud.certificateIssued = ? " + 
		"	GROUP BY csMod.certificateName;";


	try( PreparedStatement stmt = DatabaseManager.getPreparedStatement
		(sql, cert.getName()); )
	{
	    result =  stmt.executeQuery();
	    if( result.next() )
		return new CertificateStats(result.getInt(2), result.getInt(3));
	    else
		return null;
	}
	finally{
	    if( result !=null ) result.close();
	}
    }

}