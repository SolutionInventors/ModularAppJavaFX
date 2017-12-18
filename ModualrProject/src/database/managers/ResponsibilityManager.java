package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import database.bean.student.JobResponsibility;
import exception.InvalidAdminException;

public class ResponsibilityManager
{
    public static boolean insert( JobResponsibility res ) 
	    throws SQLException, InvalidAdminException
    {
	try( CallableStatement  statement  = 
		DatabaseManager.getCallableStatement(  "{CALL insertDuty(?, ?, ? ) } ", 
			res.getExpId(), res.getRole());)
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

    public static String[] getDuties(int expId) 
	    throws SQLException, InvalidAdminException
    {
	List<String> list = new LinkedList<>();
	ResultSet result = null;
	try( CallableStatement  statement  = 
		DatabaseManager.getCallableStatement(  "{CALL getDuties(?) } ", expId);)
        {
	    if( statement.execute() ){
		result = statement.getResultSet();
		while( result.next()){
		    list.add(result.getString(1) );
		}
	    }
        }
	finally{
	    if( result != null ) result.close();
	}
	return list.toArray( new String[ list.size()] );
    }
}

