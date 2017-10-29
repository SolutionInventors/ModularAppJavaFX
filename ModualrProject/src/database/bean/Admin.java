package database.bean;

/**
 * This class represents a single row in the {@code Admin} table in the database
 * @author Oguejiofor Chidiebere
 *
 */
public class Admin implements Bean
{
    private String username;
    private String password;
    
    public Admin( String username, String password )
    {
	setUsername(username);
	setPassword(password);
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
     * Sets this {@code Admin}'s username
     * @param username - a {@code String } representing the username
     */
    public void setUsername(String username)
    {
        this.username = username;
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
     * Sets this {@code Admin}'s password
     * @param  a String containing the new password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
    
}
