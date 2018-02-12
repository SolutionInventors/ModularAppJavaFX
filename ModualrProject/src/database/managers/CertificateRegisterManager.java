package database.managers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bean.CertificateRegister;
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
     * Gets the first 30 {@code CertificateRegister}s in the 
     * {@code CertificateRegister} table  starting from a specified 
     * {@code startIndex}. This can be used to created a paging application 
     * that would contain all the rows in the {@code CertificateRegister}. The
     * rows are sorted by the Certificate name and then the module name in 
     * ascending order.  
     * @param startIndex
     * @return an array of {@code CertificateRegister }
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static CertificateRegister[] getCertificateRegisters( int  startIndex )
	    throws SQLException, InvalidAdminException
    {
	ResultSet result = null;
	ArrayList<CertificateRegister> list = new ArrayList<>(30);
	String sql  = "SELECT certificateName, moduleName FROM certificateRegister" +  
		  " ORDER BY certificateName ASC, moduleName DESC " +
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
