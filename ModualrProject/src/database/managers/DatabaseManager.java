package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.bean.Admin;
import utils.ValidationType;

public final class DatabaseManager
{
    private static Admin currentAdmin = null ;
    
    
    /** 
     * Creates a {@code CallableStatement } object with its the specified arguments and
     * returns the {@code CallableStatement} object
     * @param sqlCall a String representing the statement that calls a procedure
     * @param arguments the arguments that would be passed into the sql procedure object as an argument.
     * This can be ignpred if there are no  arguments
     * @return a {@code CallableStatement} object that can be executed
     * @throws SQLException when an error occurs at the database level.
     */
    @SuppressWarnings("resource")
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

    /**
     * Gets a {@code PreparedStatement} object with all its attributes
     * set to the required value<br>
     * The object returned can be called immediately.
     * @param sqlCall the sql statement to be executed
     * @param arguments the arguments to that would fill the wild cards
     * @return a {@code PreparedStatement} with its wild cards filled
     * @throws SQLException
     */
    @SuppressWarnings("resource")
    public static PreparedStatement getPreparedStatement(String sqlCall, Object ... arguments ) throws SQLException{
   	Connection conn = ConnectionManager.getInstance().getConnection();

   	PreparedStatement statement =  conn.prepareStatement(
   		sqlCall,
   		ResultSet.TYPE_FORWARD_ONLY,
   		ResultSet.CONCUR_READ_ONLY);

   	for( int i =  0 ; i < arguments.length ; i++ )
   	    statement.setObject( i+1 , arguments[ i ] );
   	return statement;
       }

    
    public static Admin getCurrentAdmin()
    {
	return currentAdmin;
    }


    public static void setCurrentAdmin(Admin currentAdmin)
    {
	DatabaseManager.currentAdmin = currentAdmin;
    }
    
    public static boolean  validateAdmin(){
	if( currentAdmin != null && currentAdmin.isValid( ValidationType.EXISTING_BEAN) )
	    return AdminManager.validateAdmin( getCurrentAdmin());
	return false;
    }
    
    
    
}
