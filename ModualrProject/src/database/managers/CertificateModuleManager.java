package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bean.CertificateModule;
import exception.InvalidAdminException;
import exception.InvalidBeanException;

public class CertificateModuleManager
{

    public static boolean addModuleToCertificate( CertificateModule certModule) 
	    throws SQLException, InvalidBeanException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	
	if( !CertificateModule.isValid( certModule ) ) 
	    throw new InvalidBeanException("A CertificateModule data is invalid");
	
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		("{call addModToCert(?,?) }", certModule.getCertificateName(), 
			certModule.getModuleName() ))
	{
	    int affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	    
	}
	return false;
    }

    public static boolean removeModuleFromCertificate( CertificateModule certModule ) 
	    throws InvalidBeanException, SQLException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	
	if( !CertificateModule.isValid( certModule ) ) 
	    throw new InvalidBeanException("A CertificateModule data is invalid");
	
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		("{call removeModFromCert(?,?) }", certModule.getCertificateName(), 
			certModule.getModuleName() ))
	{
	    int affected = statement.executeUpdate();
	    
	   if( affected > 0 ) return true;
	    
	}
	return false;
    }

    
    public static CertificateModule[] getCertificateModules( int  startIndex )
	    throws SQLException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();

	ArrayList<CertificateModule> list = new ArrayList<>();
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		("{call getCertModulesByIndex(?) }", startIndex ))
	{
	    ResultSet result = statement.executeQuery() ;

	    while( result.next() )
	    {
		CertificateModule tempCert = 
			new CertificateModule(result.getString("certificateName") , 
				result.getString("moduleName"));
		list.add(tempCert );

	    }
	}
	return list.toArray( new CertificateModule[ list.size() ] );

    }
    public static boolean isInDatabase( CertificateModule certModule ) 
	    throws InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	
	return false;
    }
}