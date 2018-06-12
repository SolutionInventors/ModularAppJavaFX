package database.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import database.bean.student.JobResponsibility;
import exception.InvalidAdminException;

public class ResponsibilityManager
{
    public static boolean insert( JobResponsibility res ) 
	    throws SQLException, InvalidAdminException
    {
	
	
	String sql = ""
		+ "INSERT into responsibilty(  experienceId, duty) "
		+ "VALUES(?, ? );";
	try( PreparedStatement  statement  = 
		DatabaseManager.getPreparedStatement(  
			sql, res.getExpId(), res.getRole());
		ResultSet idResult = 
			DatabaseManager.getPreparedStatement
			("SELECT LAST_INSERT_ID()").executeQuery())
	{
	    int affected = statement.executeUpdate();
	    if( affected > 0 ){
		idResult.next();
		res.setId( idResult.getInt(1));
		return true; 
	    } 
	}
	return false;
    }

    public static String[] getDuties(int expId, boolean aspStudent) 
	    throws SQLException, InvalidAdminException
    {
	List<String> list = new LinkedList<>();
	ResultSet result = null;
	String sql = "";
	if(aspStudent){
	    sql = ""
			+ "SELECT duty FROM aspiringjobresponsibility "
			+ "WHERE AspExpID = ?";
	} else {
	    sql = ""
			+ "SELECT duty FROM responsibility "
			+ "WHERE experienceId = ?";
	}
	try( PreparedStatement  statement  = 
		DatabaseManager.getPreparedStatement(  
			sql, expId);)
        {
	    result = statement.executeQuery();

	   while( result.next()){
		String data = result.getString(1);
		list.add(result.getString(1) );
	    }
        }
	finally{
	    if( result != null ) result.close();
	}
	return list.toArray( new String[ list.size()] );
    }
}

