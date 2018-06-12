package database.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bean.Module;
import exception.InvalidAdminException;
import utils.ValidationType;

public final class ModuleManager
{
    public static boolean  addNewModule( Module newModule ) 
	    throws SQLException,  InvalidAdminException
    {

	String sql = 
		"INSERT INTO `module`(`dateCreated`, `name`, `units`, `amountPerUnit`) "
		+ "VALUES (NOW(),?,?,?);";
	if( newModule.isValid( ValidationType.NEW_BEAN ) ){
	    try( PreparedStatement statement = DatabaseManager.getPreparedStatement
		    (sql , newModule.getName() , 
			    newModule.getNumberOfUnits(),
			    newModule.getAmountPerUnit());)
	    {
		return statement.executeUpdate() > 0 ;
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
	    String sql = "UPDATE `module` "
	    	+ "SET `name`= ?,`units`= ?,`amountPerUnit`=? "
	    	+ "WHERE module.name =  ?;";

	
	    try( PreparedStatement statement = DatabaseManager.getPreparedStatement
		    (sql, newModule.getName() , newModule.getNumberOfUnits(),
			    newModule.getAmountPerUnit(), existingModule.getName() );)
	    {
		return statement.executeUpdate() > 0;
		
	    }
	}
	return false;

    }

    public static boolean removeModule( Module existingModule) 
	    throws SQLException, InvalidAdminException
    {
	if( existingModule.isValid( ValidationType.EXISTING_BEAN) ){
	    String sql = "DELETE FROM `module` WHERE module.name = ?;";
	    try( PreparedStatement statement = DatabaseManager.getPreparedStatement
		    (sql , existingModule.getName() );)
	    {
		return statement.executeUpdate()> 0;
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
	return getModule(moduleName) != null; 
    }
    
    
    public static Module getModule(String moduleName) throws SQLException{
	String sql = "SELECT name, units, amountPerUnit FROM module WHERE name = ? ";
	ResultSet result = null ; 
	try( PreparedStatement  stmt = DatabaseManager.getPreparedStatement( 
		sql,moduleName);)
	{
	   
	   result = stmt.executeQuery(); 
	  if(result.next()){
	      return new Module(
		      result.getString("name"),
		      result.getInt("units"), 
		      result.getDouble("amountPerUnit")); 
	  }else{
	      return null ; 
	  }
	    
	}finally{
	    if( result!=null) result.close();
	}
    }
}
