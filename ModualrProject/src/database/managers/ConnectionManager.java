
package database.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class contains static methods that would be used to establish 
 * a connection to the MySql database. <br>
 * It uses the 
 * 
 * @author Oguejiofor Chidiebere
 *
 */
public class ConnectionManager
{


    private static final String MYSQLURL = "jdbc:mysql://localhost/modularappdatabase";
    private static final String USERNAME = "iitModularAppAdmin";
    private static final String PASSWORD = "O97G9JN>G=F6O?DHLM86";
    
    private static ConnectionManager instance = null ;
    private ConnectionManager(){}

    private static Connection conn = null;
   
    public static ConnectionManager getInstance() {
	if ( instance == null )
	{
	    instance =  new ConnectionManager();
	    
	}
	
	return instance;
    }

    private static boolean openConnection()
    {
	try
	{
	    conn = DriverManager.getConnection(MYSQLURL, USERNAME, PASSWORD );
	    return true;
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return false;
    }

    
    
    /**THis gets the only {@code COnnection } object in this project*/
    public Connection getConnection() 
    {
	if( conn == null ){
	    if( openConnection() )
		return conn;
	    return null;
	}
	    
	return conn;
    }
    
    /** This closes the Connection object*/
    public static void close() 
    {
	try
	{
	    if( conn != null )
		conn.close();
	    conn = null;
	   
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    }
}
