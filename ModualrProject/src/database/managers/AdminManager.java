package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.bean.Admin;

public class AdminManager
{
    private static final String INSERT_SQL = "{CALL insertAdmin(?, ? ) } ";
    public static boolean insertAdmin( Admin admin  )
    {
	if ( admin != null )
	{
	    Connection conn = ConnectionManager.getInstance().getConnection();
	    ResultSet queryResult = null;
	    try(
		    CallableStatement statement =  
		    conn.prepareCall(
			    INSERT_SQL,
			    ResultSet.TYPE_FORWARD_ONLY, 
			    ResultSet.CONCUR_READ_ONLY);
		    
		    
		)
	    {
		statement.setString(1, admin.getUsername() );
		statement.setString(2, admin.getPassword() );
		
		int affected = statement.executeUpdate();
		
		if( affected > 0 )
		    return true;
		
	    }
	    catch (SQLException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	    }
	    finally {
		ConnectionManager.close();
	    }
	   
	}
	return false;
    }
}
