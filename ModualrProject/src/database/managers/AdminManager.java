package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import database.bean.Admin;
import exception.InvalidPrimaryKeyException;

/**
 * An {@code AdminManager } contains static methods to insert update and 
 * manage the {@code Admin } table
 * @author Oguejiofor Chidiebere
 *
 */
public class AdminManager
{
    /**
     * Inserts a new {@code Admin } to the database.<br>
     * Returns {@code true } only when the insert was successful. 
     * @param admin the {@code Admin} object to be inserted
     * @return {@code true } only when the {@code Admin} was added successfully
     * @throws InvalidPrimaryKeyException when the {@code Admin } object is invalid
     */
    public static boolean insertAdmin( Admin admin  ) throws InvalidPrimaryKeyException
    {
	if( !validateAdmin( admin ) )
	{
	    throw new InvalidPrimaryKeyException 
	    ( "The admin object cannot contain spaces and it cannot be null" );
	}

	
	try(
		CallableStatement  statement = 
			getCallableStatement( 
				"{CALL insertAdmin(?, ? ) } ", 
				admin.getUsername(), admin.getPassword());
	 )
	{
	    int affected = statement.executeUpdate();

	    if( affected > 0 )
		return true;
	}
	catch (SQLException e)
	{

	    e.printStackTrace();
	    return false;
	}
	finally {
	    ConnectionManager.close();
	}

	return false;
    }

    /**
     * Updates an existing {@code Admin } to a new {@code Admin} 
     * Note that the changes would not be commited if the connection object's 
     * auto-commiting is set to {@code true} <br>
     * This method also updates the {@code Admin } object that is passed as
     * an argument if the update was successful
     * 
     * @param admin the {@code Admin} object to be inserted
     * @return {@code true } only when the {@code Admin} was added successfully
     * @throws InvalidPrimaryKeyException  when the {@code Admin } is null or its username contians spaces
     */
    public static boolean updateAdmin( Admin oldAdmin, Admin newAdmin) 
	    throws InvalidPrimaryKeyException
    {
	if( !validateAdmin( oldAdmin ) )
	{
	    throw new InvalidPrimaryKeyException
	    ( "The admin object cannot contain spaces and it cannot be null");
	}

	try(
		CallableStatement  statement = 
			getCallableStatement( 
				"{CALL updateAdmin(?, ?, ?, ?  ) } ", 
				oldAdmin.getUsername(),  oldAdmin.getPassword() , 
				newAdmin.getUsername() , newAdmin.getPassword());
	 )
	{
	    int affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	finally {
	    ConnectionManager.close();
	}

	return false;
    }


    /**
     * Gets the first 30 {@code Admin}  in from a specified index as an array.
     * Note that the first Admin has index 0 
     * Returns an empty array when the index is too large
     * should be used with method getTotalAdmin
     * 
     * @return an array of {@code Admin } object
     */
    public static Admin[] getAllAdmin(int startIndex ) 
    {
	ResultSet result = null;
	final String GET_ALL_ADMIN = "{Call getAllAdminFrom(?) }";
	try(
		CallableStatement  statement = 
			getCallableStatement( GET_ALL_ADMIN, startIndex );

	   )
	{
	    result = statement.executeQuery();
	    ArrayList<Admin> list = new ArrayList<>();
	    Admin admin;
	    while( result.next() )
	    {
		admin = new Admin();
		admin.setUsername( result.getString("username") );
		admin.setPassword( result.getString( "password" ) );
		list.add( admin );
	    }
	    return list.toArray( new Admin[ list.size() ] );
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	    return null;
	}
	finally {
	    ConnectionManager.close();
	}

    }


    public static int getTotalAdmin() throws SQLException{
	ResultSet result = null;
	try(
		CallableStatement  statement = 
			getCallableStatement( "{Call getTotalAdmin() }");

		)
	{
	    result = statement.executeQuery();
	    if( result.next() )
		return result.getInt( 1 );
	    return 0 ;
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	    throw e;
	}
	finally {
	    if( result != null )  result.close();
	    ConnectionManager.close();
	   
	}
    }
    /**
     * Returns true if the admin is valid. <br>
     * It checks that the {@code Admin } object is not {@code null } and 
     * it does not contain a space
     * @param admin the {@code Admin } object
     * @return {@code true } when the {@code Admin } is valid
     */
    public static boolean validateAdmin ( Admin admin ){
	if( admin !=  null  && admin.getUsername() != null && 
		admin.getPassword() != null && !admin.getUsername().contains( " " ) )
	{
	    return true;
	}
	return false;
    }

    /**
     * Returns {@code true } if the {@code Admin } is in the database
     * @param admin the {@code Admin } to check for in the database
     * @return {@code true } when the {@code Admin } exists
     */
    public static boolean exists( Admin admin )
    {
	final String GET_ADMIN = "{Call getAdmin( ? , ? ) }";
	ResultSet result = null;
	try
	( 
        	CallableStatement  statement = 
        		getCallableStatement( GET_ADMIN, admin.getUsername(), admin.getPassword() );)
	{
	    result = statement.executeQuery();
	    result.last();
	    if ( result.getRow() == 1 )
		return true ;
	}
	catch (SQLException e)
	{
	    e.printStackTrace();

	}
	finally {
	    ConnectionManager.close();
	}

	return false;
    }


    private static CallableStatement getCallableStatement(String sqlCall, Object ... arguments ) throws SQLException{
	Connection conn = ConnectionManager.getInstance().getConnection();
	
	CallableStatement statement =  conn.prepareCall(
		sqlCall,
		ResultSet.TYPE_FORWARD_ONLY,
		ResultSet.CONCUR_READ_ONLY);


	for( int i =  0 ; i < arguments.length ; i++ )
	    statement.setObject( i+1 , arguments[ i ] );
	return statement;
    }
}
