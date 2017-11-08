package database.managers;

import java.sql.CallableStatement;
import java.sql.SQLException;

import database.bean.CertificateModule;
import exception.InvalidAdminException;
import exception.InvalidBeanException;

public class CertificateModuleManager
{

    public boolean addModuleToCertificate( CertificateModule certModule) 
	    throws SQLException, InvalidBeanException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	
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

    public boolean removeModuleFromCertificate( CertificateModule certModule ) 
	    throws InvalidBeanException, SQLException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	
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

    

    public boolean isInDatabase( CertificateModule certModule ) 
	    throws InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	
	return false;
    }
}
