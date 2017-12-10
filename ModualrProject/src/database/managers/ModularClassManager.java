package database.managers;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import database.bean.ModularClass;
import exception.InvalidAdminException;
import utils.ValidationType;

/**
 * 
 * @author Chidiebere
 *
 */
public final class ModularClassManager
{
    public static boolean createNewClass( ModularClass newClass) 
	    throws SQLException,  InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();

	if( newClass.isValid(ValidationType.NEW_BEAN)){
	    try( CallableStatement statement = DatabaseManager.getCallableStatement
		    ("{call createNewClass(?,?) }", newClass.getName() ))
	    {
		statement.registerOutParameter(2, Types.DATE);
		int affected = statement.executeUpdate();
		if( affected > 0 ){
		    newClass.setDateCreated( statement.getDate(2)); 
		    return true;
		}

	    }
	}

	return false;
    }

    public static boolean removeClass( ModularClass existingClass ) 
	    throws  SQLException, InvalidAdminException
    {

	if( existingClass.isValid( ValidationType.EXISTING_BEAN) ){
	    try( CallableStatement statement = DatabaseManager.getCallableStatement
		    ("{call removeClass(?) }", existingClass.getName() ))
	    {
		int affected = statement.executeUpdate();
		if( affected > 0 ){
		    return true;
		}

	    }
	}


	return false;
    }


    public static boolean update( ModularClass oldClass, ModularClass newClass ) 
	    throws  SQLException, InvalidAdminException
    {
	if( ( oldClass.isValid(ValidationType.EXISTING_BEAN)&&
		newClass.isValid(ValidationType.NEW_BEAN) ))
	{
	    try( CallableStatement statement = DatabaseManager.getCallableStatement
		    ("{call updateClass(?, ?, ?) }", oldClass.getName(),
			    newClass.getName()))
	    {
		statement.registerOutParameter(3, Types.DATE);
		int affected = statement.executeUpdate();
		if( affected > 0 ){
		    newClass.setDateCreated( statement.getDate(3) );
		    return true;
		}

	    }

	}
	return false;
    }


    public static ModularClass[] getClasses( int startIndex) throws SQLException, InvalidAdminException {

	ResultSet result = null ;
	String sql  = "SELECT * FROM modular_class" + 
		" LIMIT ?, 30 ";
	
	ArrayList<ModularClass> list = new ArrayList<>(30);
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		(sql, startIndex ))
	{
	    result = statement.executeQuery() ;
	    while( result.next() )
	    {
		ModularClass tempClass =  new ModularClass
			( result.getString("name"));
		tempClass.setDateCreated(  result.getDate("dateCreated"));
		list.add(tempClass );
	    }
	}
	finally{
	    if( result !=null ) result.close();
	}
	return list.toArray( new ModularClass[ list.size() ] );

    }
    public static boolean isInDatabase( ModularClass cert ){
	return false;
    }
}
