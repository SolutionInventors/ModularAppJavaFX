package database.bean;

import utils.ValidationType;

/**
 * An {@code Admin } object represents a single row in the Admin table in the 
 * database. The Admin table stores the Admin username and his password and 
 * contains getteers and setters to retrieve them.<br>
 * The password of this class is first hashed by the {@link AdminManager#insert} 
 * method before it is inserted into the database.<br>
 * 
 * @author Oguejiofor Chidiebere
 *
 */
public class Admin implements Bean
{
   
    private static final long serialVersionUID = 1656252569926714108L;
    private String username;
    private String password;
    private String emailAddress;
    
    /**
     * This constructor initialises this object with the {@code username},
     * {@code password} and {@code email} of the {@code Admin}. {@code Admin}s 
     * created with this constructor can be inserted into the database if the data
     *  inputed is valid. 
     * @param username the {@code Admin}'s username
     * @param password the password to be stored
     * @param email the email of the Admin
     */
    public Admin( String username, String password , String email)
    {
	setUsername(username);
	setPassword(password);
	setEmailAddress( email );
    }
    
    
    /**
    * This constructor initialises this object with the {@code username} and
    * {@code password} of the {@code Admin}. {@code Admin}s created with this
    * constructor can be inserted into the database if the data inputed is
    * valid. This can be used when validating an Admin but cannot be used to insert
    * an {@code Admin} because the emial is required.
    * @param username the {@code Admin}'s username
    * @param password the password to be stored
    */
   public Admin( String username, String password )
   {
	setUsername(username);
	setPassword(password);
	setEmailAddress( null  );
   }
   
    
    
    /**
     * Gets this object's username
     * @return String representing the username
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * Sets this {@code Admin}'s username by first removing any extra space
     * in between the argument supplied to it.<br>
     * Note that this username must be at least 5 characters before it
     * can be inserted into the database.
     * @param username - a {@code String } representing the username
     */
    public void setUsername(String username)
    {
        this.username = Bean.removeExtraSpaces( username);
    }
    
    /**
     * Gets the password of this {@code Admin}
     * @return a {@code String} representing the password
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     * Sets this {@code Admin}'s password with the supplied {@code String}.
     * Note that password must be alphanumeric before this {@code Admin} can be inserted into 
     * database successfully
     * @param  a String containing the new password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    /**
     * Returns true if the{@code Admin} object specified as an arguement can
     * be added to the database <br>
     * It checks that the {@code Admin } object is not {@code null } and 
     * the username does not contain a space<br>
     * Note that this method performs thesame checks irrespective of 
     * the {@code ValidationType} passed as its argument.
     * @param admin the {@code Admin } object
     * @return {@code true } when the {@code Admin } is valid
     */
    public  boolean isValid (ValidationType type){
	
	switch (type)
	{
	    case EXISTING_BEAN:
		return validateUsername() && validatePassword();
	    case NEW_BEAN:
		return validateEmail() && validateUsername() &&
			validatePassword();
	}
	return false;
    }

    /**
     * Checks that the email contained in this object is valid by checking that
     * it is not {@code null} and it is not an empty {@code String}
     * @return {@code boolean}
     */
    public boolean validateEmail()
    {
	String mail = getEmailAddress();
	return mail !=null && mail.length() > 0;
    }


    /**
     * Validates this {@code Admin}'s password by checking that it has at least
     * 5 characters and that it is alphanumeric and not {@code null }. 
     * @return {@code true} if the password is valid
     */
    public boolean validatePassword()
    {
	String pass = getPassword();
	return  pass!= null && pass.length() >=8 ;
    }

    /**
     * Validates this {@code Admin}'s 	username by checking that the username 
     * has at least 5 characters 
     * @return {@code true} if the username of this object is valid 
     */
    public boolean validateUsername()
    {
	String username = getUsername();
	return username!= null && username.length() >=4;
    }


    public String getEmailAddress()
    {
	return emailAddress;
    }


    public void setEmailAddress(String emailAddress)
    {
	this.emailAddress = emailAddress;
    }
}
