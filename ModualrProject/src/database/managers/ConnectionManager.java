
package database.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class contains static methods that would be used to establish 
 * a connection to the MySql database. <br>
 * It uses the 
 * 
 * @author Oguejiofor Chidiebere
 *
 */
public final class ConnectionManager
{


    private static final String MYSQLURL = "jdbc:mysql://localhost/modularappdatabase";
    private static final String USERNAME = "iitModularAppAdmin";
    private static final String PASSWORD = "O97G9JN>G=F6O?DHLM86";

    private static ConnectionManager instance = null ;
    private ConnectionManager(){}

    private static Connection conn = null;
    
    /**
     * Gets the only instance of the {@code ConnectionManager} or creates
     * it if none exists.
     * @return
     */
    public static ConnectionManager getInstance() {
	if ( instance == null )
	{
	    instance =  new ConnectionManager();
	}

	return instance;
    }

    /**
     * This creates a connection between the java application and the database. It
     * also sets the static currentDate attribute of the {@code DatabaseManager} to
     * the date in the database. Returns {@code true } if the connection 
     * was established.
     * @return
     */
    protected static boolean openConnection()
    {
	try
	{
	    conn = DriverManager.getConnection(MYSQLURL, USERNAME, PASSWORD );
	    ResultSet result = null;
	    try( Statement stmt = conn.createStatement();)
	    {
		result = stmt.executeQuery("SELECT NOW();");
		if( result.next()){
		    DatabaseManager.setCurrentDate( result.getDate(1) );
		    System.out.println( "Connected" );
		    return true;
		}
	    }
	    finally{
		if( result!= null ) result.close();
	    }
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	return false;
    }



    /**THis gets the only {@code Connection } object in this project*/
    public Connection getConnection() 
    {
	if( conn == null ){
	    if( openConnection() )
		return conn;
	    return null;
	}

	return conn;
    }

    /** This closes the Connection object and sets the static currentDate attribute
     * of the DatabaseManager class to null
     * 
     */
    public static void close() 
    {
	try
	{
	    if( conn != null )
		conn.close();
	    System.out.println("Disconnected!!");
	    conn = null;
	    DatabaseManager.setCurrentDate(null);
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}

    }
}
