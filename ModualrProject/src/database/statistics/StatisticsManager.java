package database.statistics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.bean.Bean;
import database.bean.Certificate;
import database.bean.Module;
import database.bean.student.Student;
import database.managers.DatabaseManager;
import utils.ValidationType;

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
     * @return a {@code Statistics } object
     * found in the database.
     * @throws SQLException when a database error occurs.
     */
    @SuppressWarnings("unchecked")
    public static < T extends Statistics> T retrieveStats( Bean bean ) 
	    throws SQLException
    {
	if( bean.isValid(ValidationType.EXISTING_BEAN )){
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
     * @throws SQLException 
     */
    public static TableStats retrieveStats() throws SQLException
    {
	String sql = 
		"SELECT COUNT( DISTINCT module.name) as 'Num of Modules', "+
			"	COUNT(DISTINCT reg.id) as 'Reg Module', " +
			"	COUNT(DISTINCT reg.id, if(reg.attendedStatus = 1 ,true , null)) as 'Total Mod Attended', "+
			"	COUNT(DISTINCT if(reg.result = 'Pass',true , null)) as 'Total Mod Passed', "+
			"	COUNT(DISTINCT if(reg.result = 'Fail',true , null)) as 'Total Mod Failed', "+
			"	COUNT(DISTINCT stud.id_card_number) as 'Num of Students' , "+
			"	COUNT(DISTINCT stud.id_card_number, IF(stud.active = true,1, null)) as 'Active Stud', "+ 
			"	COUNT(DISTINCT stud.id_card_number, IF(stud.active = false,1, null)) as 'Inactive Stud',  "+
			"	COUNT(DISTINCT stud.id_card_number, stud.certificateIssued) as 'Certified Stud', "+
			"	COUNT(DISTINCT cert.Name) as 'Total Certs', "+
			"	MAX(  stud.certificateIssued) AS 'Max certificate', "+
			"	COUNT(DISTINCT class.name) as 'Num of Class' "+

		"FROM student as stud \n"+

		"LEFT JOIN module  ON true "+
		"LEFT JOIN module_register as reg "+
		"	ON reg.student_id = stud.id_card_number AND reg.module_name = module.name "+
		"LEFT JOIN certificate as cert" +
		"	ON true \n"+
		"LEFT JOIN certificatemodule csMod"+
		"	ON csMod.moduleName = module.name AND cert.name = csMod.certificateName "+
		"LEFT JOIN modular_class as  class" +
		"	ON true;";
	ResultSet result = null;
	try( PreparedStatement stmt = DatabaseManager.getPreparedStatement
		(sql );)
	{
	    result =  stmt.executeQuery();
	    if( result.next() ){
		return new TableStats(result.getInt("Num of Modules"),
			result.getInt("Reg Module"), result.getInt("Total Mod Attended"),
			result.getInt("Total Mod Passed"),result.getInt("Total Mod Failed"),
			result.getInt("Num of Students"), result.getInt("Active Stud"),
			result.getInt("Inactive Stud"), result.getInt("Certified Stud"),
			result.getInt("Total Certs"), result.getString("Max certificate") , 
			result.getInt("Num of Class"));
	    }

	}
	finally{
	    if( result != null ) result.close();
	}

	return new TableStats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0);
    }

    private static StudentStats getStudentStats(ResultSet result, Student student) throws SQLException
    {
	String sql = 
		"SELECT student.id_card_number as ID,  " +
			"	COUNT( reg.id)  as 'Registered' , " +
			"	COUNT( isPaymentComplete(reg.id)) as 'Paid', "+
			"	COUNT( if( reg.bookingStatus = 1 , 1, NULL)) AS 'Booked',"+
			"	COUNT( if( reg.attendedStatus = 1,1,NULL))	 AS 'Attended',"+
			"	COUNT( IF( reg.result = 'Pass',1, NULL)) AS 'Passed',"+ 
			"	COUNT( IF( reg.result = 'Fail', 1, null)) as 'Failed' "+
			"FROM student \n"+ 

		"LEFT JOIN module_register as reg"+ 
		"	ON reg.student_id = student.id_card_number\n"+
		"WHERE student.id_card_number = ?"+
		"GROUP BY student.id_card_number";

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
	String sql = 
		"SELECT module.name as Name, "+
			"	COUNT( reg.id)  as 'Registered' , "+
			"	COUNT( IF( isPaymentComplete(reg.id)  , 1, NULL)) as 'Paid', "+
			"	COUNT( IF( reg.bookingStatus = 1 , 1, NULL)) AS 'Booked', "+
			"	COUNT( IF( reg.attendedStatus = 1,1,NULL))   AS 'Attended', "+
			"	COUNT( IF( reg.result = 'Pass',1, NULL)) AS 'Passed', "+ 
			"	COUNT( IF( reg.result = 'Fail', 1, null)) as 'Failed' "+
			"FROM module "+

	    	"LEFT JOIN module_register as reg " +
	    	"	ON reg.module_name = module.name " +
	    	"WHERE module.name =  ?" +
	    	"GROUP BY module.name;";


	try( PreparedStatement stmt = DatabaseManager.getPreparedStatement
		(sql, existinModule.getName()); )
	{
	    result =  stmt.executeQuery();
	    if( result.next() )
		return new ModuleStats(result.getInt("Registered"), 
			result.getInt("Paid"),result.getInt("Booked"),
			result.getInt("Attended"),result.getInt("Passed"),
			result.getInt("Failed"));
	    else
		return null;
	}

    }


    private static CertificateStats getCertificateStats( ResultSet result, Certificate cert) throws SQLException
    {
	String sql =  
		"SELECT  COUNT(DISTINCT csmod.moduleName) as 'Mod Reguired' , "+
			"COUNT(DISTINCT  stud.id_card_number) as 'Num Issued' "+
			"from certificate cs "+
			"LEFT JOIN certificatemodule as csMod "+
			"	ON csMod.certificateName = cs.Name "+
			"LEFT JOIN student as stud" +
			"	ON stud.certificateIssued = cs.Name "
			+ "WHERE cs.Name = ?"+ 
			"GROUP BY cs.Name;" ;


	try( PreparedStatement stmt = DatabaseManager.getPreparedStatement
		(sql, cert.getName()); )
	{
	    result =  stmt.executeQuery();
	    if( result.next() )
		return new CertificateStats(result.getInt(2), result.getInt(1));
	    else
		return null;
	}
	finally{
	    if( result !=null ) result.close();
	}
    }

}