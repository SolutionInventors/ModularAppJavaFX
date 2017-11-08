package database.managers;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import database.bean.CertificateModule;
import database.bean.ModularClass;
import exception.InvalidAdminException;
import exception.InvalidBeanException;

public class ClassManager
{
    public static boolean createNewClass( ModularClass newClass) 
	    throws SQLException, InvalidBeanException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	
	if( !ModularClass.isValid( newClass ) ) 
	    throw new InvalidBeanException("A CertificateModule data is invalid");
	
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
	return false;
    }

    public static boolean removeClass( ModularClass existingClass ) 
	    throws InvalidBeanException, SQLException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	
	if( !ModularClass.isValid( existingClass ) ) 
	    throw new InvalidBeanException("A CertificateModule data is invalid");
	
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		("{call removeClass(?) }", existingClass.getName() ))
	{
	    int affected = statement.executeUpdate();
	    if( affected > 0 ){
		return true;
	    }
	    
	}
	return false;
    }

    
    public static boolean update( ModularClass oldClass, ModularClass newClass ) throws InvalidBeanException, SQLException{
	if( !( ModularClass.isValid( oldClass) &&
		ModularClass.isValid(newClass) ))
	{
	   throw new InvalidBeanException("One of the two ModularClass object is"
	   	+ " invalid "); 
	}
	
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
	
	return false;
    }

    
    public static ModularClass[] getClasses( int startIndex) throws SQLException, InvalidAdminException {
	
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	
	ArrayList<ModularClass> list = new ArrayList<>();
	
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		("{call getClassesByIndex(? ) }", startIndex ))
	{
	    ResultSet result = statement.executeQuery() ;
	    
	    while( result.next() )
	    {
		ModularClass tempClass =  new ModularClass
			( result.getString("name"), result.getDate("dateCreated"));
		
		list.add(tempClass );
		
	    }
	}
	return list.toArray( new ModularClass[ list.size() ] );
    
    }
    public static boolean isInDatabase( ModularClass cert ){
	return false;
    }
}
