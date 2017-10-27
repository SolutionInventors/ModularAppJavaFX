package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import database.bean.Admin;
import exception.InvalidPrimaryKeyException;

public class AdminManager
{
    private static final String UPDATE_USERNAME = "{CALL updateAdminUserName(?, ?, ? ) } ";
    

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
	    throw new InvalidPrimaryKeyException ( "The admin object cannot contain spaces and it cannot be null");
	}

	final String INSERT_CALL = "{CALL insertAdmin(?, ? ) } ";
	Connection conn = ConnectionManager.getInstance().getConnection();

	try(
		CallableStatement statement =  
		conn.prepareCall(
			INSERT_CALL,
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

	return false;
    }

    /**
     * Updates the password of an existing  {@code Admin }.<br>
     * Returns {@code true } only when the update was successful. 
     * Note that the changes would not be commited if the connection object's 
     * auto-commiting is set to {@code false} <br>
     * This method also updates the {@code Admin } object that is passed as
     * an argument if the update was successful
     * 
     * @param admin the {@code Admin} object to be inserted
     * @return {@code true } only when the {@code Admin} was added successfully
     * @throws InvalidPrimaryKeyException  when the {@code Admin } is null or its username contians spaces
     */
    public static boolean updateAdminPassword( Admin oldAdmin, String newPassword ) throws InvalidPrimaryKeyException
    {
	if( !validateAdmin( oldAdmin ) )
	{
	    throw new InvalidPrimaryKeyException
	    ( "The admin object cannot contain spaces and it cannot be null");
	}

	final String UPDATE_PASSWORD = "{CALL updateAdminPassword(?, ?, ? ) } ";
	    
	Connection conn = ConnectionManager.getInstance().getConnection();

	try(
		CallableStatement statement =  conn.prepareCall(
			UPDATE_PASSWORD,
			ResultSet.TYPE_FORWARD_ONLY, 
			ResultSet.CONCUR_READ_ONLY);
		)
	{
	    statement.setString(1, oldAdmin.getUsername() );
	    statement.setString(2, oldAdmin.getPassword() );
	    statement.setString(3, newPassword );

	    int affected = statement.executeUpdate();

	    if( affected > 0 ){
		oldAdmin.setPassword(newPassword);
		return true;
	    }


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

	return false;
    }

    /**
     * Updates the username of an existing {@code Admin}<br>
     * The method also updates the {@code Admin } object that is passed as
     * an argument if the update was successful 
     * @throws InvalidPrimaryKeyException 
     */
    public static boolean updateAdminUsername( Admin oldAdmin, String newUsername ) throws InvalidPrimaryKeyException
    {
	if( !validateAdmin( oldAdmin ) || newUsername.contains( " " ))
	{
	    throw new InvalidPrimaryKeyException
	    ( "The admin object cannot contain spaces and it cannot be null");
	}

	Connection conn = ConnectionManager.getInstance().getConnection();

	try(
		CallableStatement statement =  conn.prepareCall(
			UPDATE_USERNAME,
			ResultSet.TYPE_FORWARD_ONLY, 
			ResultSet.CONCUR_READ_ONLY);
		)
	{
	    statement.setString(1, oldAdmin.getUsername() );
	    statement.setString(2, oldAdmin.getPassword() );
	    statement.setString(3, newUsername );

	    int affected = statement.executeUpdate();

	    if( affected > 0 )
	    {
		oldAdmin.setUsername( newUsername );
		return true;
	    }


	}
	catch( SQLIntegrityConstraintViolationException e ){
	    throw new InvalidPrimaryKeyException( e.getMessage() );
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

	return false;
    }

    public static Admin[] getAllAdmin() 
    {
	Connection conn = ConnectionManager.getInstance().getConnection();
	ResultSet result = null;
	final String GET_ALL_ADMIN = "{Call getAllAdmin() }";
	
	try(
		CallableStatement statement =  conn.prepareCall(
			GET_ALL_ADMIN,
			ResultSet.TYPE_FORWARD_ONLY, 
			ResultSet.CONCUR_READ_ONLY);
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
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return null;
	}
	finally {
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
     * @return
     */
    public static boolean exists( Admin admin ){
	final String GET_ADMIN = "getAdmin(?,?) ";

	if( !validateAdmin( admin ) ) return false;
	
	Connection conn = ConnectionManager.getInstance().getConnection();
	ResultSet result = null ;
	try(
		CallableStatement callable = 
		conn.prepareCall(
			GET_ADMIN,
			ResultSet.TYPE_FORWARD_ONLY, 
			ResultSet.CONCUR_READ_ONLY);
		
		
		)
	{
	    result = callable.executeQuery();
	    result.last();
	    if( result.getRow() ==  0  ) return false;
	    
	    return true;
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return false;
	
	
    }
}
