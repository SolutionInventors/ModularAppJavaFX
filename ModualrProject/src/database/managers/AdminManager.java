package database.managers;

import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;

import database.bean.Admin;
import exception.InvalidAdminException;
import exception.InvalidPrimaryKeyException;
import utils.ValidationType;

/**
 * An {@code AdminManager } contains static methods to insert update and 
 * manage the {@code Admin } table
 * @author Oguejiofor Chidiebere
 *
 */
public final class AdminManager 
{

    /**
     * This method inserts a new {@code Admin} into the database by first 
     * hashing the {@code Admin} password.<br>
     * The {@code Admin} format is first validated via a call to {@code Admin} method
     * isValid
     * @param newAdmin the new {@code Admin } to be inserted into the datbase
     * @return {@code true} if the operation is successful
     * @throws InvalidBeanException if the {@code Admin} object is invalid
     * @throws InvalidAdminException if the current {@code Admin} that is making the 
     * change is invalid
     * @throws SQLException when an SQLException is invalid
     */
    public static boolean insert( Admin newAdmin) throws  InvalidAdminException, SQLException
    {
	if( newAdmin.isValid(ValidationType.NEW_BEAN) )
	{
	    HashClass hashInstance = HashClass.getInstance();
	    hashInstance.hashAdmin(newAdmin);

	    try(CallableStatement  statement = 
		    DatabaseManager.getCallableStatement( "{CALL insertAdmin(?, ? ) } ", 
			    newAdmin.getUsername(),  newAdmin.getPassword() ) ;)
	    {
		int affected = statement.executeUpdate();
		if( affected > 0 ) return true;
	    }
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
	    throws  SQLException, InvalidAdminException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( oldAdmin.isValid(ValidationType.EXISTING_BEAN) )
	{
	    try(CallableStatement  statement = DatabaseManager.getCallableStatement( "{CALL updateAdmin(?, ?, ?, ?  ) } ", 
		    oldAdmin.getUsername(),  oldAdmin.getPassword() , 
		    newAdmin.getUsername() , newAdmin.getPassword());)
	    {
		int affected = statement.executeUpdate();
		if( affected > 0 ) return true;
	    }

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
	    result.close();
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
	ResultSet result =  null;
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	try(  CallableStatement statement = DatabaseManager.getCallableStatement
		("{Call getTotalAdmin() }");)
	{
	    result = statement.executeQuery();
	    if( result.next() )
		return result.getInt( 1 );
	    return 0 ;
	}
	finally{
	    if( result!= null ) result.close();
	}
    }


    /**
     * Returns {@code true } if the {@code Admin } is in the database
     * @param admin the {@code Admin } to check for in the database
     * @return {@code true } when the {@code Admin } exists
     * @throws SQLException 
     */
    @SuppressWarnings("resource")
    protected static boolean validateAdmin( Admin admin )
    {
	ResultSet result = null;
	Connection conn = ConnectionManager.getInstance().getConnection();
	try(  CallableStatement  statement = 
		conn.prepareCall("{Call getAdminByUsername( ?  ) }", ResultSet.TYPE_FORWARD_ONLY, 
			ResultSet.CONCUR_READ_ONLY);
		)
	{
	    statement.setString(1, admin.getUsername());
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
	finally{
	    if( result!= null )
		try
	    {
		    result.close();
	    }
	    catch (SQLException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	return false;
    }

    /**
     * This singleton class encapsulates the logic behind the hashing of the {@code Admin } 
     * password before it is added to the database.
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
	 * @author Oguejiofor Chidiebere
	 * @return a {@code HashClass} object
	 */
	public static HashClass getInstance(){
	    if ( instance ==  null )
		instance = new HashClass();
	    return instance;
	}


	/**
	 * This method hashes the {@code Admin} password. This is done before the 
	 * {@code Admin} is added into the database
	 * @param admin the {@code Admin} object to hash
	 */ 
	public void hashAdmin( Admin admin){
	    final String salt = generateSalt();

	    String finalPassword = GEN.nextInt(10) + admin.getPassword() + salt;
	    finalPassword = DigestUtils.sha256Hex( finalPassword) + salt;
	    admin.setPassword(finalPassword);
	}

	/**
	 * This method returns a random {@code String} literal.
	 * This is used by method hashAdmin to create a hash for the {@code Admin} password
	 * @return a randomly generated {@code String 
	 */
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
	public boolean comparePassword( final String hashedPassword, final String unHashedPass){
	    final String salt = hashedPassword.substring( hashedPassword.length()-3);
	    String hashedValue;
	    for( int i = 0 ; i< 10 ; i++ ){
		hashedValue = DigestUtils.sha256Hex( i + unHashedPass + salt)  + salt;
		if( hashedPassword.equals(hashedValue)){

		    return true;
		}
	    }
	    return false;
	}
    }
}