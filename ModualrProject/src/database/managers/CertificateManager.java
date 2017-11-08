package database.managers;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

import database.bean.Admin;
import database.bean.Certificate;
import exception.InvalidBeanException;

public class CertificateManager
{
    /**
     * Inserts a new {@code Certificate } into the database and returns true if the 
     * operation was successful
     * @param cert the {@code Certificate  object to be added to the database
     * @return {@code true } if the object was inserted successfjlly
     * @throws SQLException when an error occurs in the database level
     * @throws InvalidBeanException signals that the {@code Certificate } name is 
     * invalid
     */
    public boolean insert(  Certificate cert) 
	    throws SQLException, InvalidBeanException
    {
	
	checkCertObject(cert);

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
     * @param cert
     * @throws InvalidBeanException
     */
    public void checkCertObject(Certificate cert) throws InvalidBeanException
    {
	if( ! Certificate.isValid( cert)){
	    throw new InvalidBeanException
	    ("The certificate object name must be only letters");
	}
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
     */
    public boolean delete( Certificate cert ) throws InvalidBeanException, SQLException{
	checkCertObject(cert);

	try(CallableStatement statement = DatabaseManager.getCallableStatement
		("{call removeCertificarte(?)}", cert.getName())){
	    
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
     */
    public boolean update( Certificate oldCert, Certificate newCert ) 
	    throws SQLException, InvalidBeanException
    {
	checkCertObject( newCert);
	try( CallableStatement statement = DatabaseManager.getCallableStatement
		("{call updateCertificate(?,?)", oldCert.getName() , newCert.getName()))
	{
	    int affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	}
	return false;
    }

}
