package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.bean.Admin;
import database.bean.student.Student;
import exception.InvalidAdminException;

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
		Statement.RETURN_GENERATED_KEYS);

	for( int i =  0 ; i < arguments.length ; i++ )
	    statement.setObject( i+1 , arguments[ i ] );
	return statement;
    }

    /**
     * Sets the {@code currentAdmin} that is logged into the system. It does
     * this by first cpnnecting to the database to check if the admin is exists. 
     * If the admin is in the database it uses it to set the currently logged 
     * admin. If not it sets it to null.
     * @param admin the Admin that is logged to the system
     */
    public static void setCurrentAdmin(Admin admin)
    {
	String email = admin.getEmailAddress();
	if(admin.getUsername() == null  && email != null )
	    admin.setUsername(AdminManager.getUsernameFromMail(email ));
	try
	{
	    if(AdminManager.validateAdmin(admin)) {
		DatabaseManager.currentAdmin = AdminManager.getAdmin(admin.getUsername()); 

	    }


	}
	catch (SQLException e)
	{
	    DatabaseManager.currentAdmin = null;
	    e.printStackTrace();
	}
    }

    /**Gets the currently logged in admin. Returns {@code null} if the admin has
     * not yet been set or if an invalid admin  was used to set the database
     * @return the currently logged in admin
     */
    public static Admin getCurrentAdmin(){
	return currentAdmin;
    }

    
    /**
     * Checks if the current {@code Admin} is in the database. 
     * This method does not connect to the database. The  {@code Admin} object has already 
     * been verified when {@link #setCurrentAdmin(Admin)} was called.
     * @return true if the currently logged {@code Admin} is valid
     */
    public static boolean  validateAdmin(){
	return currentAdmin != null; 
    }
}
