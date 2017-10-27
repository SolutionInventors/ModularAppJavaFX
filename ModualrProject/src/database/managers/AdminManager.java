package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.bean.Admin;

public class AdminManager
{
    /**This is the query for that inserts an {@code} Admin to the database*/
    private static final String INSERT_SQL = "{CALL insertAdmin(?, ? ) } ";

    /**This is the query for that update an {@code} Admin to the database*/
    private static final String UPDATE_PASSWORD = "{CALL updateAdminPassword(?, ?, ? ) } ";

    /**This is the query for that update an {@code} Admin to the database*/
    private static final String UPDATE_USERNAME = "{CALL updateAdminUserName(?, ?, ? ) } ";

    
    /**
     * Inserts a new {@code Admin } to the database.<br>
     * Returns {@code true } only when the insert was successful. 
     * @param admin the {@code Admin} object to be inserted
     * @return {@code true } only when the {@code Admin} was added successfully
     */
    public static boolean insertAdmin( Admin admin  )
    {
	if ( admin != null )
	{
	    Connection conn = ConnectionManager.getInstance().getConnection();

	    try(
		    CallableStatement statement =  
		    conn.prepareCall(
			    INSERT_SQL,
			    ResultSet.TYPE_FORWARD_ONLY, 
			    ResultSet.CONCUR_READ_ONLY);


		    )
	    {
		statement.setString(1, admin.getUsername() );
		statement.setString(2, admin.getPassword() );

		int affected = statement.executeUpdate();

		if( affected > 0 )
		    return true;

	    }
	    catch (SQLException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	    }
	    finally {
		ConnectionManager.close();
	    }

	}
	return false;
    }

    /**
    * Updates the password of an existing  {@code Admin }.<br>
    * Returns {@code true } only when the update was successful. 
    * Note that the changes would not be commited if the connection object's 
    * auto-commiting is set to {@code false}
    * @param admin the {@code Admin} object to be inserted
    * @return {@code true } only when the {@code Admin} was added successfully
    */
    public static boolean updateAdminPassword( Admin oldAdmin, String newPassword )
    {
	if ( oldAdmin != null )
	{
	    Connection conn = ConnectionManager.getInstance().getConnection();

	    try(
		    CallableStatement statement =  conn.prepareCall(
			    UPDATE_PASSWORD,
			    ResultSet.TYPE_FORWARD_ONLY, 
			    ResultSet.CONCUR_READ_ONLY);
		    )
	    {
		statement.setString(1, oldAdmin.getUsername() );
		statement.setString(2, oldAdmin.getPassword() );
		statement.setString(3, newPassword );

		int affected = statement.executeUpdate();

		if( affected > 0 )
		    return true;

	    }
	    catch (SQLException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	    }
	    finally {
		ConnectionManager.close();
	    }

	}
	return false;
    }

    /**
     * Updates the username of an existing {@code Admin} 
     */
    public static boolean updateAdminUsername( Admin oldAdmin, String newUsername )
    {
	if ( oldAdmin != null )
	{
	    Connection conn = ConnectionManager.getInstance().getConnection();

	    try(
		    CallableStatement statement =  conn.prepareCall(
			    UPDATE_USERNAME,
			    ResultSet.TYPE_FORWARD_ONLY, 
			    ResultSet.CONCUR_READ_ONLY);
		    )
	    {
		statement.setString(1, oldAdmin.getUsername() );
		statement.setString(2, oldAdmin.getPassword() );
		statement.setString(3, newUsername );

		int affected = statement.executeUpdate();

		if( affected > 0 )
		    return true;

	    }
	    catch (SQLException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	    }
	    finally {
		ConnectionManager.close();
	    }

	}
	return false;
    }

}
