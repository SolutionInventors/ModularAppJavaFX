package database.managers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import database.bean.Module;
import exception.InvalidAdminException;
import utils.ValidationType;

public final class ModuleManager
{
    public static boolean  addNewModule( Module newModule ) 
	    throws SQLException,  InvalidAdminException
    {

	if( newModule.isValid( ValidationType.NEW_BEAN ) ){
	    try( CallableStatement statement = DatabaseManager.getCallableStatement
		    ("{call createNewModule( ?,?,?,?)}" , newModule.getName() , 
			    newModule.getNumberOfUnits(), newModule.getAmountPerUnit());)
	    {
		statement.registerOutParameter(4, Types.DATE);

		int affected = statement.executeUpdate();
		if( affected > 0 ) {
		    newModule.setDateCreated( statement.getDate(4 ) );
		    return true;
		}
	    }
	}


	return false;
    }

    public static boolean updateModule( Module newModule , Module existingModule ) 
	    throws SQLException,  InvalidAdminException
    {
	if( ( existingModule.isValid( ValidationType.EXISTING_BEAN) &&
		newModule.isValid(  ValidationType.NEW_BEAN) ))
	{
	    try( CallableStatement statement = DatabaseManager.getCallableStatement
		    ("{call updateModule( ?,?,?,?, ?) }" , existingModule.getName() , 
			    newModule.getName() , newModule.getNumberOfUnits(),
			    newModule.getAmountPerUnit());)
	    {
		statement.registerOutParameter( 5, Types.DATE);
		int affected = statement.executeUpdate();
		if( affected > 0 ) {
		    newModule.setDateCreated( statement.getDate(5 ) );
		    return true;
		}
	    }

	}



	return false;

    }

    public static boolean removeModule( Module existingModule) 
	    throws SQLException, InvalidAdminException
    {
	if( existingModule.isValid( ValidationType.EXISTING_BEAN) ){
	    try( CallableStatement statement = DatabaseManager.getCallableStatement
		    ("{call removeModule( ? )}" , existingModule.getName() );)
	    {
		System.out.println("ModuleTo remove: "+ existingModule.getName());
		int affected =  statement.executeUpdate(); 
		if( affected > 0 ) return true;
	    }
	}



	return false;

    }

    public static Module[] getModules(int startIndex) throws SQLException, InvalidAdminException
    {
	String sql  = "SELECT * FROM module" + 
		" LIMIT ?, 30 ";
	ResultSet result = null;
	ArrayList<Module> list = new ArrayList<>();
	try( PreparedStatement statement = DatabaseManager.getPreparedStatement
		(sql, startIndex ))
	{
	    result = statement.executeQuery() ;
	    while( result.next() )
	    {
		Module tempModule = new Module( result.getString("name" ) ,
			result.getInt("units"), result.getDouble( "amountPerUnit"));
		tempModule.setDateCreated( result.getDate( "dateCreated" ) );

		list.add(tempModule );

	    }
	}
	finally{
	    if( result != null ) result.close();
	}
	return list.toArray( new Module[ list.size() ] );

    }

    /**
     * Checks if a MOdule is in the database by checking the moduleName attribute
     * @param moduleName the module name to be checked
     * @return {@code true } if the module is in the database
     * @throws SQLException
     */
    public static boolean exists(String moduleName) throws SQLException
    {
	String sql = "SELECT name FROM module WHERE name = ? ";
	ResultSet result = null ; 
	try( PreparedStatement  stmt = DatabaseManager.getPreparedStatement( 
		sql,moduleName);)
	{
	   
	   result = stmt.executeQuery(); 
	   return result.next() ;
	    
	}finally{
	    if( result!=null) result.close();
	}
    }
}
