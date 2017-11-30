package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import database.bean.Module;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import utils.ValidationType;

public final class ModuleManager
{
    public static boolean  addNewModule( Module newModule ) 
	    throws SQLException, InvalidBeanException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	
	if( !newModule.isValid( ValidationType.NEW_BEAN ) )
	    throw new InvalidBeanException( "The bean is invalid" );
	CallableStatement statement = DatabaseManager.getCallableStatement
		("{call createNewModule( ?,?,?,?)}" , newModule.getName() , 
		    newModule.getNumberOfUnits(), newModule.getAmountPerUnit());
	statement.registerOutParameter(4, Types.DATE);
	
	int affected = statement.executeUpdate();
	if( affected > 0 ) {
	    newModule.setDateCreated( statement.getDate(4 ) );
	    return true;
	}
	return false;
    }
    
    public static boolean updateModule( Module newModule , Module existingModule ) 
	    throws SQLException, InvalidBeanException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) 
	    throw new InvalidAdminException();
	if( !( existingModule.isValid( ValidationType.EXISTING_BEAN) &&
		newModule.isValid(  ValidationType.NEW_BEAN) ))
	{
	    throw new InvalidBeanException();
		 
	}
	    
	CallableStatement statement = DatabaseManager.getCallableStatement
		("{call updateModule( ?,?,?,?, ?) }" , existingModule.getName() , 
		   newModule.getName() , newModule.getNumberOfUnits(),
		   newModule.getAmountPerUnit());
	statement.registerOutParameter( 5, Types.DATE);
	int affected = statement.executeUpdate();
	if( affected > 0 ) {
	    newModule.setDateCreated( statement.getDate(5 ) );
	    return true;
	}
	return false;
	
    }
    
    public static boolean removeModule( Module existingModule) 
	    throws SQLException, InvalidAdminException, InvalidBeanException 
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( !existingModule.isValid( ValidationType.EXISTING_BEAN) )
	    throw new InvalidBeanException();
	
	CallableStatement statement = DatabaseManager.getCallableStatement
		("{call removeModule( ? )}" , existingModule.getName() );
	System.out.println("ModuleTo remove: "+ existingModule.getName());
	int affected =  statement.executeUpdate(); 
	if( affected > 0 ) return true;
	return false;
	
    }

    public static Module[] getModules(int startIndex) throws SQLException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();

	ArrayList<Module> list = new ArrayList<>();
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		("{call getModuleByIndex(?) }", startIndex ))
	{
	    ResultSet result = statement.executeQuery() ;

	    while( result.next() )
	    {
		Module tempModule = new Module( result.getString("name" ) ,
			result.getInt("units"), result.getDouble( "amountPerUnit"));
		tempModule.setDateCreated( result.getDate( "dateCreated" ) );
		
		list.add(tempModule );

	    }
	}
	return list.toArray( new Module[ list.size() ] );

    }
}
