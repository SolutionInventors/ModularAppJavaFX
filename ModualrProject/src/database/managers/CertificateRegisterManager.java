package database.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

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
	    String sql =
		    "INSERT INTO `certificateRegister`(`certificateName`, `moduleName`) "
		    + "VALUES ( TRIM(?), TRIM(?) );" ;
	    
	    try( PreparedStatement statement = 
		    DatabaseManager.getPreparedStatement
		    (sql, certModule.getCertificateName(), certModule.getModuleName() ))
	    {
		int affected = statement.executeUpdate();
		if( affected > 0 ) return true;

	    }
	}
	return false;
    }


    /**
     * This method adds multiple modules to the requirement of a specified 
     * certificate. 
     * @param certName the certificate name as {@code String}
     * @param modules the array of moduleName as {@code String}
     * @return {@code true} if all the modules were successfully added
     * @throws SQLException when a connection error occurs
     */
    public static boolean addMultipleModules(final String certName, String... modules) throws SQLException{

	ConnectionManager.setAutoCommiting(false); 

	boolean success = Arrays.stream(modules)
		.allMatch( module-> { 
		    try
		    {
			return true == addModuleToCertificate(
				new CertificateRegister(certName, module)
				) ; 
		    }
		    catch (InvalidAdminException| SQLException e)
		    {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		    }


		}); 
	if(success){
	    ConnectionManager.commit (); 
	}else{
	    ConnectionManager.rollback(); 
	}
	ConnectionManager.setAutoCommiting(true);
	return success; 

    }



    /**
     * This method removes multiple modules to the requirement of a specified 
     * certificate. 
     * @param certName the certificate name as {@code String}
     * @param modules the array of moduleName as {@code String}
     * @return {@code true} if all the modules were successfully added
     * @throws SQLException when a connection error occurs
     */
    public static boolean removeMultipleModules(final String certName, String... modules) throws SQLException{

	ConnectionManager.setAutoCommiting(false); 

	boolean success = Arrays.stream(modules)
		.allMatch( module-> { 
		    try
		    {
			return true == removeModules(
				new CertificateRegister(certName, module)
				) ; 
		    }
		    catch (InvalidAdminException| SQLException e)
		    {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		    }


		}); 
	if(success){
	    ConnectionManager.commit (); 
	}else{
	    ConnectionManager.rollback(); 
	}
	ConnectionManager.setAutoCommiting(true);
	return success; 

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
    public static boolean removeModules( CertificateRegister certModule ) 
	    throws  SQLException, InvalidAdminException
    {

	if( certModule.isValid( ValidationType.EXISTING_BEAN)){
	    String sql = 
		    "DELETE FROM `certificateRegister` "
		    + "WHERE `certificateName` = ? AND `moduleName` = ? ; ";

	    try( PreparedStatement statement = DatabaseManager.getPreparedStatement
		    (sql, certModule.getCertificateName(), certModule.getModuleName() ))
	    {
		int affected = statement.executeUpdate();

		if( affected > 0 ) return true;

	    }
	}
	return false;
    }


    public static String[] getModules(String certificateName, boolean required) throws SQLException{
	String sql = ""; 
	if(required){
	    return getModulesRequired(certificateName); 
	}else{
	    sql = "SELECT DISTINCT module.name as ModuleName from module " +
		    "LEFT JOIN certificateregister as certReg " + 
		    " ON module.name = certReg.moduleName " + 
		    " WHERE UCASE(certReg.certificateName) != UCASE(?) OR certReg.certificateName IS NULL "+ 
		    " ORDER BY module.name ASC ; " ;
	    return getModulesHelper(sql, certificateName); 
	}

	
    }
    
    public static boolean clearAllModules(String certName) throws SQLException, InvalidAdminException{
	
	String sql = "DELETE FROM certificateregister "
		+ "WHERE certificateName = ? "; 
	 try( PreparedStatement statement = 
		 DatabaseManager.getPreparedStatement(sql, certName))
	    {
	     
		statement.executeUpdate();
		return true; 
	    }
    }
    private static String[] getModulesHelper(String sql, Object ... arguments) throws SQLException
    {
	ResultSet result = null;
	ArrayList<String> list = new ArrayList<>(30);

	try( PreparedStatement statement = DatabaseManager.getPreparedStatement
		(sql, arguments ))
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


    /**
     * Gets an array of   {@code Module}s  that contains  are required to 
     * get a particular certificate
     * @param certificateName the certificate name
     * @return an array of {@code Module}s
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static String[] getModulesRequired(String certificateName) throws SQLException{
	String sql  = ""
		+ "SELECT  moduleName "
		+ "	FROM certificateRegister "
		+ "" +  
		"  WHERE UCASE(certificateName) = UCASE(?) ";

	return getModulesHelper(sql, certificateName); 
    }


}
