package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.bean.student.JobResponsibility;

public class ResponsibilityManager
{
    public static boolean insert( JobResponsibility res ) throws SQLException
    {
	ResultSet key = null;
	try( CallableStatement  statement  = 
		DatabaseManager.getCallableStatement(  "{CALL insertDuty(?, ? ) } ", 
			res.getExpId(), res.getDuty());)
	{
	    int affected = statement.executeUpdate();
	    if( affected > 0 ){
		key = statement.getGeneratedKeys();
		key.next();
		res.setExpId( key.getInt(0));
		return true; 
	    } 
	}
	return false;
    }
}

