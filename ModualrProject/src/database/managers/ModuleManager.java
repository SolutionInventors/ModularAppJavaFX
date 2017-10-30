package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bean.Admin;
import database.bean.Module;
import exception.InvalidAdminException;
import exception.InvalidPrimaryKeyException;

public class ModuleManager
{

    /**
     * Inserts a new {@code Module } to the database.<br>
     * Returns {@code true } only when the insert was successful. 
     * @param newModule the {@code Module} object to be inserted
     * @return {@code true } only when the {@code Module} was added successfully
     * @throws InvalidPrimaryKeyException when the name attribute of the {@code Module } object 
     * already exists or is null
     */
    public static boolean insert( Module newModule  ) throws InvalidPrimaryKeyException
    {
	if( !isValid( newModule ) || !exists( newModule ))
	{
	    throw new InvalidPrimaryKeyException 
	    ( "The Module object is invalid. Ensure that the module does not exist" );
	}

	try(
		CallableStatement  statement = 
		DatabaseManager.getCallableStatement( 
			"{CALL insertModule(?, ? ) } ", 
			newModule.getName(), newModule.getNumberOfUnits());
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

    /** Checks if a {@code Module } is already in the database*/
    public static boolean exists( Module module )
    {
	ResultSet result = null;
	try
	( 
		CallableStatement  statement = 
		DatabaseManager.getCallableStatement( 
			"{Call getModule( ? ) }", module.getName());
		)
	{
	    result = statement.executeQuery();
	    result.last();
	    if ( result.getRow() == 1 )
	    {
		module.setNumberOfUnits( result.getInt("units"));
		return true ;
	    }
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

    /** 
     * Checks that a {@code Module } object by ensuring that the object is not null,
     * its name attribute is not {@code null} and its units is greater than 0.
     * Note that it does not connect to the database but ensures that the {@code Module} 
     * object can be put perform transactions on the database
     * 
     * @param module the {@code Module} object to be validated
     * @return true when the {@code Module } is valid
     */
    public static boolean isValid(Module module)
    {
	if( module != null && module.getName() != null  && module.getNumberOfUnits() >0)
	{
	    return true;
	}
	return false;
    }

    /**Updates an existing {@code Module } object with to the new one specified
     * in the second argument.<br>
     * Note that the primary key( that is the name of the {@code Module}) is all that is 
     * used to determine the row that would be updated. 
     * @param oldModule the existing module to be updated. The primary name attribute is used to 
     * determine the row to be updated
     * @param newModule  the {@code Module} object with the new values
     * @return {@code true} when the update is successful
     * @throws InvalidPrimaryKeyException
     */
    public static boolean update( Module oldModule, Module newModule ) throws InvalidPrimaryKeyException
    {
	if( !isValid( newModule ) )
	{
	    throw new InvalidPrimaryKeyException 
	    ( "The Module object is invalid. Ensure that the module does not contain  null values" );
	}

	try(
		CallableStatement  statement = 
		DatabaseManager.getCallableStatement( 
			"{CALL updateModule(?, ?, ? ) } ", 
			oldModule.getName() , 
			newModule.getName() , newModule.getNumberOfUnits());
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
     * Gets the total {@code Module} objects in the database
     * @return an int containing the totl {@code Module} objects in the database
     * @throws SQLException
     */
    public static int getTotalModule() throws SQLException{
	ResultSet result = null;
	try(
		CallableStatement  statement = 
			DatabaseManager.getCallableStatement( "{Call getTotalModule() }");

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

   /** Gets the first 30 {@code Module} in the Module Table from a specified index as an array.
    * Note that the first Module has index 0 
    * Returns an empty array when the index is too large
    * should be used with method getTotalAdmin
    * 
    * @return an array of {@code Module } objects
    */
    
    public static Module[] getAll( int startIndex ){
	ResultSet result = null;
	try(
		CallableStatement  statement = 
			DatabaseManager.getCallableStatement( "{call getAllModules()}", startIndex );

	   )
	{
	    result = statement.executeQuery();
	    ArrayList<Module> list = new ArrayList<>();
	    Module module;
	    while( result.next() )
	    {
		module = new Module( result.getString("name" ) , result.getInt( "units" ) );
		list.add( module );
	    }
	    return list.toArray( new Module[ list.size() ] );
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

    /**This method deletes a {@code Module } from the databasse.
     * it returns true if the operation was successful.<br>
     * 
     * @param currentAdmin the {@code Admin }object that is logged in.
     * @param moduleToDelete the existing {@code Module} to delete from the database
     * @return {@code true} when delete was successful
     * @throws InvalidAdminException when the {@code Admin } object is not in the database 
     */
    public static boolean delete( Admin currentAdmin, Module moduleToDelete ) throws InvalidAdminException{
	
	if( !AdminManager.exists( currentAdmin ) ) throw new InvalidAdminException();
	
	try(
		CallableStatement  statement = 
			DatabaseManager.getCallableStatement( "{call deleteModule(?)}" );
	   )
	{
	    statement.setString( 1 , moduleToDelete.getName() );
	    int affected = statement.executeUpdate();
	    
	    if ( affected == 1 ) return true;
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	return false;
    }
}
