package database.managers;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import database.bean.CertificateModule;
import database.bean.Class;
import exception.InvalidBeanException;

public class ClassManager
{
    public boolean insert( Class newClass) throws SQLException, InvalidBeanException{
	if( !Class.isValid( newClass ) ) 
	    throw new InvalidBeanException("A CertificateModule data is invalid");
	
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		("{call createNewClass(?) }", newClass.getName() ))
	{
	    int affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	    
	}
	return false;
    }

    public boolean delete( Class newClass ) 
	    throws InvalidBeanException, SQLException
    {
	if( !Class.isValid( newClass ) ) 
	    throw new InvalidBeanException("A CertificateModule data is invalid");
	
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		("{call removeClass(?, ?) }", newClass.getName() ))
	{
	    statement.registerOutParameter(2, Types.DATE);
	    int affected = statement.executeUpdate();
	    if( affected > 0 ){
		newClass.setDateCreated( statement.getDate(2) );
		return true;
	    }
	    
	}
	return false;
    }

    
    public boolean update( Class oldClass, Class newClass ){
	return false;
    }

    public boolean isInDatabase( Class cert ){
	return false;
    }
}
