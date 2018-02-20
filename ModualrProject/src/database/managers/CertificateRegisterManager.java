package database.managers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bean.CertificateRegister;
import database.bean.Module;
import exception.InvalidAdminException;
import utils.ValidationType;

/**
 * This class contains static methods that are used to perform operations 
 * on the {@code CertificateRegister} table in the database. This table is
 * used to add {@code ModuleTabTable}s to a {@code Certificate} requirement.
 * @author Oguejiofor Chidiebere
 * @see database.bean.CertificateRegister
 *
 */
public final class CertificateRegisterManager
{
    /**
     * Adds a {@code ModuleTabTable} to a certificate requirement. 
     * @param certModule the {@code CertificateRegister} object that encapsulate
     * contains the Certificate name and the {@code ModuleTabTable } name. 
     * @return {@code true } if the operation was successful.
     * @throws SQLException
     * @throws InvalidAdminException if the {@code Admin } that wants to make the 
     * change is invalid
     */
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

    /**
     * Removes a {@code ModuleTabTable } from a Cerificate requirement
     * @param certModule the certificateRegister object that encapsulates the 
     * name of the {@code Certificate} and {@code ModuleTabTable}
     * @return {@code true } if removal was successful. If nothing was removed
     * returns {@code false}.
     * @throws SQLException
     * @throws InvalidAdminException
     */
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

    /**
     * Gets an array of   {@code Module}s  that contains  are required to 
     * get a particular certificate
     * @param certificateName the certificate name
     * @return an array of {@code Module}s
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static String[] getModulesRequired(String certificateName) throws SQLException{
	ResultSet result = null;
	ArrayList<String> list = new ArrayList<>(30);
	String sql  = ""
		+ "SELECT  moduleName "
		+ "	FROM certificateRegister "
		+ "" +  
		  "  WHERE certificateName = ? ";
	
	try( PreparedStatement statement = DatabaseManager.getPreparedStatement
		(sql, certificateName ))
	{
	    result = statement.executeQuery() ;

	    while( result.next() )
	    {
		list.add(result.getString(1));
	    }
	}
	finally{
	    if( result != null ) result.close();
	}
	return list.toArray( new String[ list.size() ] );
    }
    
    
}
