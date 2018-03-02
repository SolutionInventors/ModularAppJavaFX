package database.managers;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import database.bean.Certificate;
import exception.InvalidAdminException;
import utils.OrderBy;
import utils.ValidationType;

/**
 * This class contains static methods for performing transactions in the
 * {@code Certificate} table in the database. This can also be used to 
 * retrieve a {@code Certificate} for the database. 
 * 
 * @see database.bean.Certificate
 * @author Chidiebere
 *
 */
public final class CertificateManager
{
    /**
     * Inserts a new {@code Certificate } into the database and returns true if the 
     * operation was successful. It also updates its @c{@code Certificate}'s
     *  {@code dateCreated} attribute
     *  
     * @param cert the {@code Certificate  object to be added to the database
     * @return {@code true } if the object was inserted successfjlly
     * @throws SQLException when an error occurs in the database level
     * @throws InvalidAdminException when the {@code Admin} that wants to make the
     * change is invalid
     */
    public static boolean createCertificate(  Certificate cert) 
	    throws SQLException,  InvalidAdminException
    {
	if( cert.isValid( ValidationType.NEW_BEAN )){
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
	}
	return false;
    }


    /**
     *Removes an existing {@code Certificate } from the database. If the 
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
    public static boolean delete( Certificate cert ) 
	    throws  SQLException, InvalidAdminException
    {
	if( cert.isValid( ValidationType.EXISTING_BEAN )){
	    try(CallableStatement statement = DatabaseManager.getCallableStatement
		    ("{call removeCertificate(?)}", cert.getName())){

		int  affected = statement.executeUpdate();
		if( affected > 0 ) return true;
	    }
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
	    throws SQLException,  InvalidAdminException
    {
	if( newCert .isValid( ValidationType.NEW_BEAN ) ||
		!oldCert.isValid(ValidationType.EXISTING_BEAN) 	) 
	{
	    try( CallableStatement statement = DatabaseManager.getCallableStatement
		    ("{call updateCertificate(?,?)}", oldCert.getName() , newCert.getName()))
	    {
		int affected = statement.executeUpdate();
		if( affected > 0 ) return true;
	    }
	}

	return false;
    }

    /**
     * Gets the first 30 {@code Certificate}s in the datbase starting from a 
     * specified {@code startIndex}<br>
     * This method can be used to create a pages of Certificates
     * @param startIndex specifies the row that the retrieval would begin from
     * with 0 being the first row
     * @param order specifies the order in which the {@code Certificate}s would
     * be retrieved. If this is {@code OrerBy.FOREIGN_*} or {@code null} then
     * the data would be returned as is in the database. 
     * 
     * @return an array of {@code Certificate } that contains the 
     * certificates in the {@code Certificate} table 
     * @throws SQLException
     * @throws InvalidAdminException
     * @see OrderBy
     */
    public static Certificate[] getCertificates(int startIndex, OrderBy order ) 
	    throws SQLException, InvalidAdminException
    {
	String sql  = "SELECT name, dateCreated FROM certificate "
		+ getOrderByString(  order ) + 
		" LIMIT ?, 30 ";
	
	ResultSet result = null;
	ArrayList<Certificate> list = new ArrayList<>(30);
	try( PreparedStatement statement = DatabaseManager.getPreparedStatement
		(sql, startIndex ))
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
    /**
     * Gets a {@code String } that would be used in a query to retrive 
     * {@code Certifiacte}s from the {@code Certificate} table.
     * @param order the type of 
     * @return a {@code String} to be used in a query
     * @see OrderBy
     */
    private static String getOrderByString(OrderBy order)
    {
	switch( order ){
	    case DATE_CREATED_ASC:
		return "ORDER BY dateCreated ASC "; 
	    case DATE_CREATED_DESC:
		return "ORDER BY dateCreated DESC "; 
	    case PRIMARY_KEY_DESC:
		return "ORDER BY name DESC "; 
	    default: 
		return "ORDER BY name ASC "; 
	}
    }

}
