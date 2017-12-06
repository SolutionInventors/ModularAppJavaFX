package database.managers;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import database.bean.Certificate;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import utils.ValidationType;

public final class CertificateManager
{
    /**
     * Inserts a new {@code Certificate } into the database and returns true if the 
     * operation was successful
     * @param cert the {@code Certificate  object to be added to the database
     * @return {@code true } if the object was inserted successfjlly
     * @throws SQLException when an error occurs in the database level
     * @throws InvalidBeanException signals that the {@code Certificate } name is 
     * invalid
     * @throws InvalidAdminException when the {@code Admin} that wants to make the
     * change is invalid
     */
    public static boolean createCertificate(  Certificate cert) 
	    throws SQLException, InvalidBeanException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( !cert.isValid( ValidationType.NEW_BEAN )) 
	    throw new InvalidBeanException("The Certificate is invalid");
	
	try(CallableStatement statement = DatabaseManager.getCallableStatement
		("{call createCertificate(?,?)}", cert.getName());){
	    int affected = statement.executeUpdate();
	    statement.registerOutParameter( 2 , Types.DATE );
	    if( affected > 0 ){
		Date creationDate = statement.getDate(2);
		cert.setDateCreated( creationDate);
		return true;
	    }
	}

	return false;
    }


    /**
     * This removes an existing {@code Certificate } from the database. If the 
     * {@code Certificate} does not exists this method returns {@code false}.<br>
     * It returns {@code true} only when the {@code Certificate} was successfully 
     * deleted.
     * 
     * 
     * @param cert the existing object to be deleted
     * @return {@code true } if the delete was successful
     * @throws InvalidBeanException  when the certificate name is not valid
     * @throws SQLException  when an error occurs at the database level
     * @throws InvalidAdminException 
     */
    public static boolean delete( Certificate cert ) throws InvalidBeanException, SQLException, InvalidAdminException{
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( !cert.isValid( ValidationType.EXISTING_BEAN )) 
	    throw new InvalidBeanException("The Certificate is invalid");
	
	
	try(CallableStatement statement = DatabaseManager.getCallableStatement
		("{call removeCertificate(?)}", cert.getName())){

	    int  affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	}
	return false;
    }

    /**
     * Updates an existing {@code Certificate } object. It can only change
     * the {@code Certificate } name
     * @param oldCert the existing {@code Certificate} object
     * @param newCert the new {@code Certificate} object
     * @return {@code true} if the update was successful
     * @throws SQLException when a database error occurs
     * @throws InvalidBeanException when the newCert is invalid
     * @throws InvalidAdminException  when the Admin that wants to make the changel
     * is invalid
     */
    public static boolean update( Certificate oldCert, Certificate newCert ) 
	    throws SQLException, InvalidBeanException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( !newCert .isValid( ValidationType.NEW_BEAN ) ||
		!oldCert.isValid(ValidationType.EXISTING_BEAN) 	) 
	{
	    throw new InvalidBeanException("One of the two Certificates is invalid"); 
	}
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		("{call updateCertificate(?,?)}", oldCert.getName() , newCert.getName()))
	{
	    int affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	}
	return false;
    }

    /**
     * Gets the first 30 {@code Certificate}s in the datbase starting from a 
     * specified {@code startIndex}<br>
     * This method can be used to create a pages of Certificates
     * @param startIndex
     * @return
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static Certificate[] getCertificates(int startIndex) 
	    throws SQLException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	ResultSet result = null;
	ArrayList<Certificate> list = new ArrayList<>();
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		("{call getCertificatesByIndex(? ) }", startIndex ))
	{
	    result = statement.executeQuery() ;
	    while( result.next() )
	    {
		Certificate tempCert =  new Certificate(  result.getString("name"));
		tempCert.setDateCreated(  result.getDate("dateCreated"));
		list.add(tempCert );
	    }
	}
	finally{
	    if( result != null ) result.close();
	}
	return list.toArray( new Certificate[ list.size() ] );
    }

}
