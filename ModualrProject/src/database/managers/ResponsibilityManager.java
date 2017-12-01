package database.managers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import database.bean.student.JobResponsibility;

public class ResponsibilityManager
{
    public static boolean insert( JobResponsibility res ) throws SQLException
    {
	try( CallableStatement  statement  = 
		DatabaseManager.getCallableStatement(  "{CALL insertDuty(?, ?, ? ) } ", 
			res.getExpId(), res.getDuty());)
	{
	    statement.registerOutParameter(3, Types.INTEGER);
	    int affected = statement.executeUpdate();
	    if( affected > 0 ){
		res.setId( statement.getInt(3));
		return true; 
	    } 
	}
	return false;
    }
}

