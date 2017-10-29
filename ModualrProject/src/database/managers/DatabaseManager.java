package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.bean.Admin;
import database.bean.Bean;
import exception.InvalidPrimaryKeyException;

public class DatabaseManager
{
    public static CallableStatement getCallableStatement(String sqlCall, Object ... arguments ) throws SQLException{
	Connection conn = ConnectionManager.getInstance().getConnection();
	
	CallableStatement statement =  conn.prepareCall(
		sqlCall,
		ResultSet.TYPE_FORWARD_ONLY,
		ResultSet.CONCUR_READ_ONLY);


	for( int i =  0 ; i < arguments.length ; i++ )
	    statement.setObject( i+1 , arguments[ i ] );
	return statement;
    }
    
    
    public static <E extends Bean >boolean insert( E bean ) throws InvalidPrimaryKeyException
    {
	if ( bean instanceof Admin ) return AdminManager.insertAdmin( (Admin) bean );
	
	return false;
	
    }


    public static Admin[] getAllAdmin(int startIndex)
    {
	return AdminManager.getAllAdmin( startIndex );
    }
}
