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
     * Hello
     * @param bean
     * @return
     * @throws SQLException
     */
    public static Statistics retrieveStats( Bean bean ) 
	    throws SQLException
    {
	ResultSet result = null;
	try
	{
	    if( bean instanceof Module ){
		return getModuleStats(result , (Module) bean);
	    }
	    else if( bean instanceof Certificate){

		return getCertificateStats(result , (Certificate) bean);

	    }
	    else if( bean instanceof Student){
		return getStudentStats( result, (Student) bean );
	    }
	}
	finally{
	    if(result!=null ) result.close();
	}
	return null;
    }

    private static Statistics getStudentStats(ResultSet result, Student bean)
    {
//	StudentStats  studStats = new StudentStats();
	return null;
    }

    private static Statistics getModuleStats(ResultSet result , Module existinModule) 
	    throws SQLException
    {
	String sql = "SELECT reg.module_name as 'Module Name' , "
		+ "	COUNT(*) as 'Total Registered'" + 
		"	COUNT(if( isPaymentComplete(reg.id), 1, null)) as 'Num Paid'," +
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
		return new ModuleStats(result.getInt("Total Registered"), result.getInt("Num Paid"),
			result.getInt("Num Booked"), result.getInt("Num Attended"),
			result.getInt("Num Passed"), result.getInt("Num Failed"));
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