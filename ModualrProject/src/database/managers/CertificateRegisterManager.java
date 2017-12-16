package database.managers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bean.CertificateRegister;
import exception.InvalidAdminException;
import utils.ValidationType;

public final class CertificateRegisterManager
{

    public static boolean addModuleToCertificate( CertificateRegister certModule) 
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

    public static boolean removeModuleFromCertificate( CertificateRegister certModule ) 
	    throws  SQLException, InvalidAdminException
    {

	if( certModule.isValid( ValidationType.EXISTING_BEAN)){
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


    public static CertificateRegister[] getCertificateModules( int  startIndex )
	    throws SQLException, InvalidAdminException
    {
	ResultSet result = null;
	ArrayList<CertificateRegister> list = new ArrayList<>(30);
	String sql  = "SELECT * FROM certificateRegister" +  
		  " ORDER BY certificateName " +
		" LIMIT ?, 30 ";
	
	try( PreparedStatement statement = DatabaseManager.getPreparedStatement
		(sql, startIndex ))
	{
	    result = statement.executeQuery() ;

	    while( result.next() )
	    {
		CertificateRegister tempCert = 
			new CertificateRegister(result.getString("certificateName") , 
				result.getString("moduleName"));
		list.add(tempCert );

	    }
	}
	finally{
	    if( result != null ) result.close();
	}
	return list.toArray( new CertificateRegister[ list.size() ] );

    }

}
