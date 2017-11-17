package database.managers;

import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

import database.bean.Admin;
import database.bean.ValidationType;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import exception.InvalidPrimaryKeyException;

/**
 * An {@code AdminManager } contains static methods to insert update and 
 * manage the {@code Admin } table
 * @author Oguejiofor Chidiebere
 *
 */
public class AdminManager 
{
    
    public static boolean insert( Admin newAdmin) throws 
    	InvalidBeanException, InvalidAdminException, SQLException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( !newAdmin.isValid(ValidationType.NEW_BEAN) )
	{
	    throw new InvalidBeanException
	    ( "The admin object cannot contain spaces and it cannot be null");
	}
	
	HashClass hashInstance = HashClass.getInstance();
	hashInstance.hashAdmin(newAdmin);
	
	try(CallableStatement  statement = 
		DatabaseManager.getCallableStatement( "{CALL insertAdmin(?, ? ) } ", 
		newAdmin.getUsername(),  newAdmin.getPassword() ) ;)
	{
	    int affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	}

	return false;
    }
    
    
    /**
     *
     * Updates an existing {@code Admin } to a new {@code Admin} 
     * Note that the changes would not be commited if the connection object's 
     * auto-commiting is set to {@code true} <br>
     * This method also updates the {@code Admin } object that is passed as
     * an argument if the update was successful<br>
     * 
     *  Method first validates the current Admin parameter and returns {@code false } 
     *  if the {@code currentAdmin} is not in the database <br>
     *  
     *  @param currentAdmin the {@code Admin} that wants to make the change. This would be
     *  validated first before the update would be made.
     * @param admin the {@code Admin} object to be inserted
     * @return {@code true } only when the {@code Admin} was added successfully
     * @throws InvalidPrimaryKeyException  when the {@code Admin } is null or its username contians spaces
     * @throws SQLException 
     * @throws InvalidAdminException when the Admin that wants to make the change is
     * invalid
     * @throws InvalidBeanException 
     */
    public static boolean update(  Admin oldAdmin, Admin newAdmin) 
	    throws  SQLException, InvalidAdminException, InvalidBeanException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( !oldAdmin.isValid(ValidationType.EXISTING_BEAN) )
	{
	    throw new InvalidBeanException
	    ( "The admin object cannot contain spaces and it cannot be null");
	}
	
	
	try(CallableStatement  statement = DatabaseManager.getCallableStatement( "{CALL updateAdmin(?, ?, ?, ?  ) } ", 
		oldAdmin.getUsername(),  oldAdmin.getPassword() , 
		newAdmin.getUsername() , newAdmin.getPassword());)
	{
	    int affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	}

	return false;
    }


    /**
     * Gets the first 30 {@code Admin}  in from a specified index as an array.
     * Note that the first Admin has index 0 
     * Returns an empty array when the index is too large
     * should be used with method getTotalAdmin
     * 
     * @return an array of {@code Admin } object
     * @throws SQLException 
     * @throws InvalidAdminException signals that the {@code Admin} that wants to make
     * the change is invalid
     */
    public static Admin[] getAllAdmin(int startIndex ) throws SQLException, InvalidAdminException 
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();

	ArrayList<Admin> list = new ArrayList<>();
	try(CallableStatement statement = DatabaseManager.getCallableStatement
		( "{Call getAllAdminFrom(?) }", startIndex );)
	{
	    ResultSet result = statement.executeQuery();
	    Admin admin;
	    while( result.next() )
	    {
		admin = new Admin(result.getString("username"), result.getString( "password" ));
		list.add( admin );
	    }

	}
	return list.toArray( new Admin[ list.size() ] );
    }

    /** 
     * Gets the total {@code Admin} objects in the table
     * @return
     * @throws SQLException
     * @throws InvalidAdminException signals that the admin that wants to make the change
     * is invalid
     */
    public static int getTotalAdmin() throws SQLException, InvalidAdminException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	try(  CallableStatement statement = DatabaseManager.getCallableStatement
		("{Call getTotalAdmin() }");)
	{
	    ResultSet result = statement.executeQuery();
	    if( result.next() )
		return result.getInt( 1 );
	    return 0 ;
	}
    }

   
    /**
     * Returns {@code true } if the {@code Admin } is in the database
     * @param admin the {@code Admin } to check for in the database
     * @return {@code true } when the {@code Admin } exists
     * @throws SQLException 
     */
    protected static boolean validateAdmin( Admin admin )
    {
	ResultSet result = null;
	try(  CallableStatement  statement = DatabaseManager.getCallableStatement
		( "{Call getAdminByUsername( ?  ) }", admin.getUsername() );)
	{
	    result = statement.executeQuery();
	    
	    if ( result.next() ){
		String unHashedPass = admin.getPassword();
		String hashedPass = result.getString( "password" );
		return HashClass.getInstance().comparePassword(hashedPass, unHashedPass);
	    }
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	return false;
    }

    /**
     * This singleton class encapsulates the logic behind the hashing that goes 
     * on within the {@code Admin } class
     * @author Oguejifor Chidiebere
     *
     */
    private static final class HashClass
    {
	private  static  HashClass instance = null;
	private  final  SecureRandom GEN = new SecureRandom();

	private HashClass(){}

	/**
	 * This returns the only object of this singleton class and creates one if none
	 * exists
	 * @return a {@code HashClass} object
	 */
	public static HashClass getInstance(){
	    if ( instance ==  null )
		instance = new HashClass();
	    return instance;
	}



	public void hashAdmin( Admin admin){
	    final String salt = generateSalt();

	    String finalPassword = GEN.nextInt(10) + admin.getPassword() + salt;
	    finalPassword = DigestUtils.sha256Hex( finalPassword) + salt;
	    admin.setPassword(finalPassword);
	}

	private String generateSalt(){
	    char[] chars  = new char[3];
	    for( int i = 0 ; i < chars.length  ; i++){
		int randNum  =  65 + GEN.nextInt(58) ;
		if( randNum > 90 && randNum < 99 )
		    randNum += 6;
		chars[i] = (char) randNum ;
	    }
	    return new String( chars);
	}

	/**
	 * Checks if a hashed password  is equal to an unhased password. <br>
	 * This is handy when validating an {@code Admin } as the unhashed password supplied
	 * to this method is first hashed before being compared<br>
	 * Returns {@code true} if the two {@code unHashedPassword}  is equal to the password
	 * from the database before being hashed. 
	 * @param hashedPassword the hashedPassword
	 * @param unHashedPass
	 * @return {@code TRUE } if the two passwords are equal.
	 */
	public boolean comparePassword( final String hashedPassword, String unHashedPass){
	    final String salt = hashedPassword.substring( hashedPassword.length()-3);
	    final String hash = hashedPassword.substring( 0 ,  hashedPassword.length()-3);
	   
	    String hashedValue;
	    for( int i = 0 ; i< 10 ; i++ ){
		hashedValue = DigestUtils.sha256Hex( i + hash + salt);
		if( hashedPassword.equals(hashedValue )){

		    return true;
		}
	    }

	    return false;
	}
    }

}
