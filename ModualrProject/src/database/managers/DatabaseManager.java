package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.bean.Admin;
import database.bean.ValidationType;

public class DatabaseManager
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
    public static CallableStatement getCallableStatement(String sqlCall, Object ... arguments ) throws SQLException{
	Connection conn = ConnectionManager.getInstance().getConnection();

	CallableStatement statement =  conn.prepareCall(
		sqlCall,
		ResultSet.TYPE_FORWARD_ONLY,
		ResultSet.CONCUR_READ_ONLY, Statement.RETURN_GENERATED_KEYS);


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
	if( currentAdmin.isValid( ValidationType.EXISTING_BEAN) )
	    return AdminManager.validateAdmin( getCurrentAdmin());
	return false;
    }
    
    
    
}
