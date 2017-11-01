package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.bean.Admin;
import exception.InvalidAdminException;
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
     * @throws SQLException 
     */
    public static boolean insert( Admin admin  ) throws InvalidPrimaryKeyException, SQLException
    {
	if( !Admin.isValid( admin ) )
	{
	    throw new InvalidPrimaryKeyException 
	    ( "The admin object cannot contain spaces and it cannot be null" );
	}
	CallableStatement  statement = null;

	try
	{
	    statement = DatabaseManager.getCallableStatement( 
		    "{CALL insertAdmin(?, ? ) } ", 
		    admin.getUsername(), admin.getPassword());
	    int affected = statement.executeUpdate();

	    if( affected > 0 )
		return true;
	}
	finally{
	    if( statement != null )
		statement.close();
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
     * @throws SQLException 
     */
    public static boolean update( Admin oldAdmin, Admin newAdmin) 
	    throws InvalidPrimaryKeyException, SQLException
    {
	if( !Admin.isValid( oldAdmin ) )
	{
	    throw new InvalidPrimaryKeyException
	    ( "The admin object cannot contain spaces and it cannot be null");
	}
	CallableStatement  statement =  null;

	try{
	    statement = DatabaseManager.getCallableStatement( "{CALL updateAdmin(?, ?, ?, ?  ) } ", 
		    oldAdmin.getUsername(),  oldAdmin.getPassword() , 
		    newAdmin.getUsername() , newAdmin.getPassword());
	    int affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	}
	finally
	{
	    if( statement != null ) statement.close();
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
     * @throws SQLException 
     */
    public static Admin[] getAllAdmin(int startIndex ) throws SQLException 
    {
	ResultSet result = null;
	CallableStatement  statement =  null;
	 ArrayList<Admin> list = new ArrayList<>();
	try
	{
	    statement = DatabaseManager.getCallableStatement( "{Call getAllAdminFrom(?) }", startIndex );
	    result = statement.executeQuery();
	    Admin admin;
	    while( result.next() )
	    {
	        admin = new Admin(result.getString("username"), result.getString( "password" ));
	        list.add( admin );
	    }
	    
	}
	finally
	{
	   if( statement != null ) statement.close();
	}
	return list.toArray( new Admin[ list.size() ] );
    }

    /** 
     * Gets the total {@code Admin} objects in the table
     * @return
     * @throws SQLException
     */
    public static int getTotalAdmin() throws SQLException{
	ResultSet result = null;
	 CallableStatement  statement = null;

	try
	{
	   statement = 
		   DatabaseManager.getCallableStatement("{Call getTotalAdmin() }");

	    result = statement.executeQuery();
	    if( result.next() )
	        return result.getInt( 1 );
	    return 0 ;
	}
	finally {
	    if( result != null ) result.close();
	    if( statement!= null ) statement.close();
	}


    }


    /**
     * Returns {@code true } if the {@code Admin } is in the database
     * @param admin the {@code Admin } to check for in the database
     * @return {@code true } when the {@code Admin } exists
     * @throws SQLException 
     */
    public static boolean isInDatabase( Admin admin ) throws SQLException
    {
	ResultSet result = null;
	CallableStatement  statement =  null;
	
	try
	{
	    statement = DatabaseManager.getCallableStatement
	    	( "{Call getAdmin( ? , ? ) }", admin.getUsername(), admin.getPassword() );
	    result = statement.executeQuery();
	    result.last();
	    if ( result.getRow() == 1 )
	        return true ;
	}
	finally 
	{
	   if( result !=null ) result.close();
	   if( statement!= null ) statement.close();
	}
	return false;
    }

    /**This method deletes a {@code Admin } from the databasse.
     * it returns true if the operation was successful.<br>
     * Note that the an {@code Admin } cannot delete himself
     * 
     * @param currentAdmin the {@code Admin }object that is logged in.
     * @param AdminToDelete the existing {@code Module} to delete from the database
     * @return {@code true} when delete was successful
     * @throws InvalidAdminException when the {@code Admin } object is not in the database 
     * @throws SQLException 
     */
    public static boolean delete( Admin currentAdmin, Admin adminToDelete ) throws InvalidAdminException, SQLException{

	if( !AdminManager.isInDatabase( currentAdmin )  ||
		currentAdmin.getUsername().equals( adminToDelete.getUsername())){
	    throw new InvalidAdminException();
	}
	CallableStatement  statement = null;
	try
	{
	    statement = DatabaseManager.getCallableStatement( "{call deleteAdmin(?)}" );
	    statement.setString( 1 , adminToDelete.getUsername() );
	    int affected = statement.executeUpdate();

	    if ( affected == 1 ) return true;
	}
	finally
	{
	    if(  statement != null ) statement.close();
	}

	return false;
    }



}
