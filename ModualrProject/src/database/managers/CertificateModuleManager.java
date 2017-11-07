package database.managers;

import java.sql.CallableStatement;
import java.sql.SQLException;

import database.bean.CertificateModule;
import exception.InvalidBeanException;

public class CertificateModuleManager
{

    public boolean insert( CertificateModule certModule) throws SQLException, InvalidBeanException{
	if( !CertificateModule.isValid( certModule ) ) 
	    throw new InvalidBeanException("A CertificateModule data is invalid");
	
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		("{call addModuleToCertificate(?,?) }", certModule.getCertificateName(), 
			certModule.getModuleName() ))
	{
	    int affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	    
	}
	return false;
    }

    public boolean delete( CertificateModule certModule ) 
	    throws InvalidBeanException, SQLException
    {
	if( !CertificateModule.isValid( certModule ) ) 
	    throw new InvalidBeanException("A CertificateModule data is invalid");
	
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		("{call removeModuleFromCertificate(?,?) }", certModule.getCertificateName(), 
			certModule.getModuleName() ))
	{
	    int affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	    
	}
	return false;
    }

    

    public boolean isInDatabase( CertificateModule certModule ){
	return false;
    }
}
