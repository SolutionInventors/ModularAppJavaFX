package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bean.Module;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import exception.InvalidPrimaryKeyException;

public class ModuleManager
{
    public static boolean  insert( Module newModule ) throws SQLException, InvalidBeanException
    {
	
	if( Module.isValid( newModule ) )
	    throw new InvalidBeanException( "The bean is invalid" );
	CallableStatement statement = DatabaseManager.getCallableStatement
		("{call insertModule( ?,?,?,?}" , newModule.getName() , 
		    newModule.getNumberOfUnits(), newModule.getAmountPerUnit());
	int affected = statement.executeUpdate();
	if( affected > 0 ) return true;
	return false;
	
    }
    
    public static boolean update( Module newModule , Module oldModule ) throws SQLException
    {
	CallableStatement statement = DatabaseManager.getCallableStatement
		("{call updateModule( ?,?,?,?, ? }" , oldModule.getName() , 
		   newModule.getName() , newModule.getNumberOfUnits(),
		   newModule.getAmountPerUnit());
	
	int affected = statement.executeUpdate();
	if( affected > 0 ) return true;
	return false;
	
    }
    
    public static boolean delete( Module existingModule) throws SQLException 
    {
	CallableStatement statement = DatabaseManager.getCallableStatement
		("{call updateModule( ? }" , existingModule.getName() );
	
	int affected = statement.executeUpdate();
	if( affected > 0 ) return true;
	return false;
	
    }
}
