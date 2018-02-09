package database.managers;

import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

import database.bean.Admin;
import exception.InvalidAdminException;
import utils.ValidationType;

/**
 * An {@code AdminManager } contains static methods to insert update and 
 * manage the {@code Admin } table
 * @author Oguejiofor Chidiebere
 *
 */
public final class AdminManager 
{

    private static String  generatedString ;
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
    public static boolean insert( Admin newAdmin, String sentNum ) throws  InvalidAdminException, SQLException
    {
	Admin currentAdmin = DatabaseManager.getCurrentAdmin();
	if(   currentAdmin != null && currentAdmin.isSuper() && 
		testNumber(sentNum) && 
		newAdmin.isValid(ValidationType.NEW_BEAN) )
	{
	    HashClass.hashAdmin(newAdmin);
	    try(CallableStatement  statement = 
		    DatabaseManager.getCallableStatement( "{CALL insertAdmin(?, ?, ?,? ) } ", 
			    newAdmin.getUsername(),  newAdmin.getPassword() ,
			    newAdmin.getEmailAddress(), newAdmin.getAccessType()) ;)
	    {
		int affected = statement.executeUpdate();
		if( affected > 0 ) return true;
	    }
	}
	return false;
    }


    /**
     * Changes an existing Admin password to the {@code newPassword}. It first hashes the
     * new password before it is used to update a password.  
     * @param existingAdmin the Admin that is to be updated. The password of this attribute
     * is used to validate that the Admin exists before update proceeds.
     * @param newPassword 
     * @return
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static boolean updatePassword( Admin existingAdmin, String newPassword )
	    throws SQLException, InvalidAdminException
    {
	Admin newAdmin = new Admin(existingAdmin.getUsername(), newPassword);

	if( validateAdmin(existingAdmin) && newAdmin.validatePassword())
	{
	    HashClass.hashAdmin(newAdmin);
	    try(CallableStatement  statement = DatabaseManager.getCallableStatement( "{CALL updateAdmin(?, ?, ?  ) } ", 
		    existingAdmin.getUsername(), 
		    existingAdmin.getUsername() , newAdmin.getPassword());)
	    {
		int affected = statement.executeUpdate();
		if( affected > 0 ){
		    existingAdmin.setPassword(newAdmin.getPassword());
		    return true;
		}
	    }
	}
	return false;
    }

    public static boolean testNumber( String generatedNumber ){
	return generatedNumber.equals(generatedString);
    }


    @SuppressWarnings("resource")
    public static String getEmailAddress( String username){
	ResultSet result = null;
	Connection conn = ConnectionManager.getInstance().getConnection();

	try(  PreparedStatement  statement = 
		conn.prepareStatement("SELECT Email FROM admin where username = ? ");)
	{
	    statement.setString(1, username);
	    result = statement.executeQuery();

	    if ( result.next() ) return result.getString(1);

	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	return null ;
    }

    /**Returns true when the mail inputed as argument exists in the database
     * @param mail a {@code String} representation of the {@code Admin} mail
     * @return {@code true } when the mail is found in the admin table
     */
    public static boolean doesMailExist(String mail){
	ResultSet result = null; 

	try(  PreparedStatement  statement = 
		DatabaseManager.getPreparedStatement("SELECT Email FROM admin where email = ? ");)
	{
	    statement.setString(1, mail);
	    result = statement.executeQuery();

	    return result.next();
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}finally{
	    if( result!= null)
		try
	    {
		    result.close();
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
	    }
	}
	return false;
    }

    /**
     * Generates a random number that would be sent to an Admin mail when
     * verifying email address
     */
    public static void generateNumber(){
	char[] character = new char[6];
	Random gen = new Random();
	for( int i = 0 ; i < character.length ; i++ ){
	    character[i] = ( char )  (49 + gen.nextInt(9)) ;
	}

	generatedString = new String( character);
    }

    /**
     * This method sends an email to the specified email address. This messae
     * is used to confirm the admin email in case he loses it. 
     * @param emialAddress
     * @return
     */
    public static boolean sendMail( String to ){
	if( generatedString == null ) return false; 
	String body = String.format("This is your confirmation number: %s", generatedString);
	String title = "Confirmation Number no-reply ";
	String[] addr = {to}; 

	return ConnectionManager.sendMail(addr, body, title);
    }
    /**
     * 
     * @param existingAdmin
     * @param email
     * @return
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static boolean updateMail( Admin existingAdmin, String email )
	    throws SQLException, InvalidAdminException
    {

	if( validateAdmin(existingAdmin))
	{
	    try(CallableStatement  statement = DatabaseManager.getCallableStatement( "{CALL updateAdminMail(?, ? ) } ", 
		    existingAdmin.getUsername(), email);)
	    {
		int affected = statement.executeUpdate();
		if( affected > 0 ) return true;
	    }
	}
	return false;
    }

    /**
     * This method is used to reset the confirmation number that is usually sent to when a 
     * new Admin is about to be added to the database or when he forgets his passwword after
     * performing the operation. 
     * 
     */
    public static void resetNumber()
    {
	generatedString = null;
    }


    /**
     * This method is used to reset the admin password when he forgets his password but can only
     * remember his username. A mail is first sent to the admin containing a {@code confirmationNumer}
     * that should then be inputed to this method. 
     * The method tests the number before changing the Admin password
     * @param username the existing username
     * @param newPassword the existing password
     * @param confirmationNumber the confirmation number that should be sent to the Admin via mail
     * @return {@code true } if the password was reseted successfully
     * 
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static boolean resetPassword(String username, String newPassword, String confirmationNumber) throws SQLException, InvalidAdminException
    {

	Admin updateAdmin = new Admin(username, newPassword);

	if( testNumber(confirmationNumber) && updateAdmin.validatePassword())
	{
	    HashClass.hashAdmin(updateAdmin);
	    @SuppressWarnings("resource")
	    Connection conn =  ConnectionManager.getInstance().getConnection(); 
	   
	    try( CallableStatement statement =  conn.prepareCall("{CALL updateAdmin(?, ?, ?  ) } ");)
	    {
		statement.setString(1, username);
		statement.setString(2, username);
		statement.setString(3, updateAdmin.getPassword());
		int affected = statement.executeUpdate();
		if( affected > 0 ) return true;

	    }
	}
	return false;
    }

    /**
     * Deletes an admin from the database. This operation can only be performed if
     * the currently logged in admin has super access else InvalidAdminException is 
     * thrown. 
     * 
     * @param oldAdmin the admin to be removed
     * @return true if the operation was successful
     * @throws SQLException when a db errror occurs
     * @throws InvalidAdminException when the admin that wants to make the change is invalid
     */
    public static boolean deleteAdmin(String existingAdminUsername) throws SQLException, InvalidAdminException{
	Admin currentAdmin = DatabaseManager.getCurrentAdmin() ;
	
	if(currentAdmin != null && 
		!currentAdmin.getUsername().matches(existingAdminUsername ) &&
		currentAdmin.isSuper()){
	    try( CallableStatement stmt =
		    DatabaseManager.getCallableStatement("removeAdmin(?)", existingAdminUsername); ){
		int affected = stmt.executeUpdate(); 
		if(affected > 0 ) return true; 
		else return false;
	    } 
	}
	throw new InvalidAdminException("This admin does not havve permission to do this!");
	
    }
    public static boolean changeUsername( Admin existingAdmin, String newUsername )
	    throws SQLException, InvalidAdminException
    {

	if( validateAdmin(existingAdmin))
	{
	    try(CallableStatement  statement = DatabaseManager.getCallableStatement( "{CALL updateAdminUsername(?, ? ) } ", 
		    existingAdmin.getUsername(), newUsername);)
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
	String sql = "SELECT COUNT(username) FROM admin";
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


    @SuppressWarnings("resource")
    protected static Admin getAdmin(String username) throws SQLException{
	ResultSet result = null;
	Connection conn = ConnectionManager.getInstance().getConnection();
	//Does not used DatabaseManager.getPreparedStatement because that method may call
	//validateAdmin


	try(  PreparedStatement  statement = 
		conn.prepareStatement("SELECT  email, password, accessType FROM admin where username = ? ");)
	{
	    statement.setString(1, username);
	    result = statement.executeQuery();

	    if ( result.next() ){
		Admin admin  = new Admin(username, result.getString( "password" ));
		admin.setEmailAddress(result.getString("email"));
		admin.setAccessType( result.getString("accessType"));

		return admin;
	    }
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	finally{
	    if( result!= null) result.close();
	}
	return null;
    }

    /**
     * Returns {@code true } if the {@code Admin } is in the database. Note that this
     * validation is done using the username and password attribute stored in the Admin
     * object. 
     * @param admin the {@code Admin } to check for in the database
     * @param accessType 
     * @return {@code true } when the {@code Admin } exists
     * @throws SQLException 
     */
    protected static boolean validateAdmin( Admin admin) throws SQLException
    {
	Admin adminFromDb = getAdmin(admin.getUsername()); 
	if( adminFromDb != null ){
	    boolean isValid=  
		    HashClass.comparePassword(
			    adminFromDb.getPassword(),
			    admin.getPassword());
	    if(isValid){
		admin = adminFromDb; 
		return true;
	    }
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