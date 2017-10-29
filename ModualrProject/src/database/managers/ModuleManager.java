package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.bean.Module;
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
    public static boolean insertModule( Module newModule  ) throws InvalidPrimaryKeyException
    {
	if( !validateModule( newModule ) || !exists( newModule ))
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

    /** Validates a {@code Module } obbject by ensuring that the object is not null,
     * its name attribute is not {@code null} and its units is greater than 0
     * 
     * @param module the {@code Module} object to be validated
     * @return true when the {@code Module } is valid
     */
    public static boolean validateModule(Module module)
    {
	if( module != null && module.getName() != null  && module.getNumberOfUnits() >0)
	{
	    return true;
	}
	return false;
    }
    
}
