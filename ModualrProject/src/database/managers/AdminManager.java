package database.managers;

import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
     * The {@code Admin} format is first validated via a call to 
     * {@link Admin#isValid(ValidationType)}
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
	    HashClass.hashAdmin(newAdmin);
	    try(CallableStatement  statement = 
		    DatabaseManager.getCallableStatement( "{CALL insertAdmin(?, ?, ? ) } ", 
			    newAdmin.getUsername(),  newAdmin.getPassword() ,
			    newAdmin.getEmailAddress()) ;)
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
    public static boolean update(  Admin existingAdmin, Admin newAdmin) 
	    throws  SQLException, InvalidAdminException
    {
	if( existingAdmin.isValid(ValidationType.EXISTING_BEAN) )
	{
	    try(CallableStatement  statement = DatabaseManager.getCallableStatement( "{CALL updateAdmin(?, ?, ?, ?  ) } ", 
		    existingAdmin.getUsername(),  existingAdmin.getPassword() , 
		    newAdmin.getUsername() , newAdmin.getPassword());)
	    {
		int affected = statement.executeUpdate();
		if( affected > 0 ) return true;
	    }
	}
	return false;
    }  

    /** 
     * Gets the total {@code Admin} objects in the table
     * @return an int containing the total Admin in the database.
     * @throws SQLException
     * @throws InvalidAdminException signals that the admin that wants to make the change
     * is invalid
     */
    public static int getTotalAdmin() throws SQLException, InvalidAdminException
    {
	ResultSet result =  null;
	String sql = "SELECT COUNT(*) FROM admin";
	try(  PreparedStatement statement = DatabaseManager.getPreparedStatement(sql);)
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
     * Returns {@code true } if the {@code Admin } is in the database. Note that this
     * validation is done using the username and password attribute stored in the Admin
     * object. 
     * @param admin the {@code Admin } to check for in the database
     * @return {@code true } when the {@code Admin } exists
     * @throws SQLException 
     */
    @SuppressWarnings("resource")
    protected static boolean validateAdmin( Admin admin )
    {
	ResultSet result = null;
	Connection conn = ConnectionManager.getInstance().getConnection();
	//Does not used DatabaseManager.getPreparedStatement because that method may call
	//validateAdmin
	try(  PreparedStatement  statement = 
		conn.prepareStatement("SELECT * FROM admin where username = ? ");)
	{
	    statement.setString(1, admin.getUsername());
	    result = statement.executeQuery();

	    if ( result.next() ){
		String unHashedPass = admin.getPassword();
		String hashedPass = result.getString( "password" );
		admin.setEmailAddress(result.getString("Email"));
		return HashClass.comparePassword(hashedPass, unHashedPass);
	    }
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	return false;
    }

    /**
     * This class contains only static methods that encapsulate the logic for hashing
     * an {@code Admin} object.<br>
     * <p><b>Warning: </b>
     * Care should be taken when modifying the methods in this class or they should not
     * be modified at all.</p>
     * @author Oguejifor Chidiebere
     *
     */
    private static final class HashClass
    {
	private  final  static SecureRandom GEN = new SecureRandom();
	private final static int SALT_LENGTH = 100;
	private final static int PEPPER_LENGTH = 1000;
	private HashClass(){}
	/**
	 * This method hashes the {@code Admin} password. This is done before the 
	 * {@code Admin} is added into the database
	 * @param admin the {@code Admin} object to hash
	 */ 
	public static void hashAdmin( Admin admin){
	    final String salt = generateSalt();

	    String finalPassword = GEN.nextInt(PEPPER_LENGTH) + admin.getPassword() + salt;
	    finalPassword = DigestUtils.sha512Hex( finalPassword) + salt;
	    admin.setPassword( finalPassword);
	}

	/**
	 * This method returns a random {@code String} literal.
	 * This is used by method hashAdmin to create a hash for the {@code Admin} password
	 * @return a randomly generated {@code String 
	 */
	private static String generateSalt(){
	    char[] chars  = new char[SALT_LENGTH];
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
	 * Returns {@code true} if the  {@code unHashedPassword}  is equal to the
	 * {@code hasedPpassword}. 
	 * @param hashedPassword the hashedPassword
	 * @param unHashedPass
	 * @return {@code TRUE } if the two passwords are equal.
	 */
	public static boolean comparePassword( final String hashedPassword, final String unHashedPass){
	    final String salt = hashedPassword.substring( hashedPassword.length()-SALT_LENGTH);
	    String hashedValue;
	    for( int i = 0 ; i< PEPPER_LENGTH ; i++ ){
		hashedValue = DigestUtils.sha512Hex( i + unHashedPass + salt)  + salt;
		if( hashedPassword.equals(hashedValue)){

		    return true;
		}
	    }
	    return false;
	}
    }
}