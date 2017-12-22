package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.bean.Admin;
import exception.InvalidAdminException;
import utils.ValidationType;

/**
 * This contains common methods that retrieve are used to retrieve, insert, update
 * and delete data from the database. It also contains a {@code currentAdmin }
 * attribute which stores the currrently signed {@code Admin}. This {@code Admin}
 * is validated before any transaction occurs in the database. It also stores
 * the {@code currentDate} in the database which is updated anytime a 
 * connection is made. 
 * @author Oguejiofor Chidiebere
 *
 */
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
     * @throws InvalidAdminException 
     */
    @SuppressWarnings("resource")
    public static CallableStatement getCallableStatement(String sqlCall, Object ... arguments ) throws SQLException, InvalidAdminException{
	Connection conn = ConnectionManager.getInstance().getConnection();

	if(  !validateAdmin() ) 
	    throw new InvalidAdminException("The Admin that wants to make the change is not in database");
	CallableStatement statement =  conn.prepareCall(
		sqlCall,
		ResultSet.TYPE_FORWARD_ONLY,
		ResultSet.CONCUR_READ_ONLY);
	
	
	
	for( int i =  0 ; i < arguments.length ; i++ )
	    statement.setObject( i +1, arguments[ i ] );
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

    /**
     * Sets the {@code currentAdmin} that is logged into the system.
     * @param currentAdmin the Admin that is logged to the system
     */
    public static void setCurrentAdmin(Admin currentAdmin)
    {
	DatabaseManager.currentAdmin = currentAdmin;
    }

    /**
     * Connects to the database and checks if the Admin is in the database. 
     * @return
     */
    public static boolean  validateAdmin(){
	if( currentAdmin != null && currentAdmin.isValid( ValidationType.EXISTING_BEAN) )
	    return AdminManager.validateAdmin( currentAdmin);
	return false;
    }

 
    

}
