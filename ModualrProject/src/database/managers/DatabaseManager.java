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
	if ( bean instanceof Admin ) return AdminManager.insert( (Admin) bean );
	
	return false;
	
    }

    /**
     * Gets the first 30 occurence of a specified {@code BeanType} in the database
     * This method can retrieve info from all the tables in the database. Data returned
     * can be manipulated via the beaType
     * Returns an empty array when the index is too large
     * should be used with method getTotalAdmin* Gets 
     * @param startIndex
     * @return
     */
    public static <T extends Bean> T[] getAllBean( BeanType beanType, int startIndex)
    {
	switch (beanType)
	{
	    case ADMIN:
		return  (T[]) AdminManager.getAllAdmin( startIndex );
	    
	    default:
		return null;
		
	}
	
    }
}
