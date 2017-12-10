package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bean.CertificateModule;
import exception.InvalidAdminException;
import utils.ValidationType;

public final class CertificateModuleManager
{

    public static boolean addModuleToCertificate( CertificateModule certModule) 
	    throws SQLException,  InvalidAdminException
    {
	
	if( certModule.isValid( ValidationType.NEW_BEAN) ){
	    try( CallableStatement statement = DatabaseManager.getCallableStatement
		    ("{call addModToCert(?,?) }", certModule.getCertificateName(), 
			    certModule.getModuleName() ))
	    {
		int affected = statement.executeUpdate();
		if( affected > 0 ) return true;

	    }
	}

	return false;
    }

    public static boolean removeModuleFromCertificate( CertificateModule certModule ) 
	    throws  SQLException, InvalidAdminException
    {

	if( !certModule.isValid( ValidationType.EXISTING_BEAN)){
	    try( CallableStatement statement = DatabaseManager.getCallableStatement
		    ("{call removeModFromCert(?,?) }", certModule.getCertificateName(), 
			    certModule.getModuleName() ))
	    {
		int affected = statement.executeUpdate();

		if( affected > 0 ) return true;

	    }
	}
	return false;
    }


    public static CertificateModule[] getCertificateModules( int  startIndex )
	    throws SQLException, InvalidAdminException
    {
	ResultSet result = null;
	ArrayList<CertificateModule> list = new ArrayList<>();
	String sql  = "SELECT * FROM certificatemodule" + 
		" LIMIT ?, 30 ";
	
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		(sql, startIndex ))
	{
	    result = statement.executeQuery() ;

	    while( result.next() )
	    {
		CertificateModule tempCert = 
			new CertificateModule(result.getString("certificateName") , 
				result.getString("moduleName"));
		list.add(tempCert );

	    }
	}
	finally{
	    if( result != null ) result.close();
	}
	return list.toArray( new CertificateModule[ list.size() ] );

    }

}
