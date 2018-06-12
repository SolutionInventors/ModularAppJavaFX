package database.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.bean.student.Biodata;
import exception.InvalidAdminException;
import utils.ValidationType;

/**
 * The {@code BiodataManager} contains static methods that are used for 
 * inserting and updating a {@code Student}'s biodata. Method {@link #insert(Biodata)}
 * is protected to ensure that a {@code Biodata} can only be inserted during 
 * registration
 * @author Oguejiofor Chidiebere
 *
 */
public class BiodataManager
{   
    /**
     * Inserts a new {@code Biodata } object into the {@code Biodata} table. 
     * It returns {@code true } if the insertion was successful
     * @param data the {@code Biodata} object to be inserted
     * @return {@code true} if the insertion was successful
     * @throws SQLException when an error occured in the database
     * @throws InvalidAdminException when the {@code Admin} that wants to make
     * the change is invalid
     */
    protected static boolean insert(Biodata data) 
	    throws SQLException, InvalidAdminException
    {
	if( data.isValid(ValidationType.NEW_BEAN )){
	    String sql = 
		    "INSERT INTO biodata"
		    + "( studentId, Title,  PermanentAddress, CurrentAddress, "
		    + "Religion, stateOfOrigin, country, gender, dateOfBirth, "
		    + "placeOfBirth)"
		    
			+  "VALUES( ?, ?, ?,  ? ,"
			+ "?, ?, ?, ?, ?,   ?); ";

	    try( PreparedStatement statement =  DatabaseManager.getPreparedStatement
		    (sql, data.getStudentID(),data.getTitle(), data.getPermanentAddress(), 
			    data.getCurrentAddress(), data.getReligion(), data.getStateOfOrigin(),
			    data.getCountry(), data.getGender(), data.getDateOfBirth(), 
			    data.getPlaceOfBirth()); ) 

	    {
		int affected = statement.executeUpdate();
		if( affected >0 ) return true ;
	    }
	}
	return false;
    }

    /**
     * Updates an existing {@code Student}'s {@code Biodata}. It uses the {@code studentID} 
     * attribute stored in the {@code Biodata} object to locate the row in the
     * {@code Biodata} table that would be updated since the studentID is unique in that
     * table.
     * @param data the {@code Biodata} object to be inserted into the database.
     * @return {@code true} if the update was successful.
     * @throws InvalidAdminException if the {@code Admin} that wants to make the change is invalid
     * @throws SQLException when a exception occurs like 
     */
    public static boolean update(Biodata data) 
	    throws InvalidAdminException, SQLException
    {
	if( (data.isValid(ValidationType.NEW_BEAN ) ))
	{
	    String sql = 
		    "UPDATE biodata SET Title= ?, "
		    + "PermanentAddress= ?, "
		    + "CurrentAddress = ?, Religion = ?, "
		    + "stateOfOrigin = ? , country = ?, "
		    + "gender = ?, dateOfBirth = ?, placeOfBirth =? "
		    + "WHERE  studentId = ? ;";
	    try( PreparedStatement statement =  DatabaseManager.getPreparedStatement
		    (sql, data.getTitle(),data.getPermanentAddress(),data.getCurrentAddress(), 
			    data.getReligion(),data.getStateOfOrigin(),
			    data.getCountry(), data.getGender(), data.getDateOfBirth(),
			    data.getPlaceOfBirth(), data.getStudentID()); ) 
	    {
		int affected = statement.executeUpdate();
		if( affected >0 ) return true ;	
	    }
	}
	return false;
    }

    /**
     * Retrieves a {@code Student}'s  {@code Biodata} object from the database using
     * the id card number of the {@code Student}
     * @param studentID the id card number of the student
     * @return a {@code Biodata} that contains the specified {@code Student}'s
     * bio data or returns {@code null } if nothing was found
     * @throws SQLException when a database exception occured
     * @throws InvalidAdminException when the {@code Admin} that wants to make
     * the change is invalid
     */
    public static Biodata getBiodata(String studentID) 
	    throws SQLException, InvalidAdminException
    {
	ResultSet result = null;
	try( PreparedStatement statement =  DatabaseManager.getPreparedStatement
		("SELECT * FROM biodata WHERE studentId = ?", studentID);)
	{
	    if( statement.execute()){
		result = statement.getResultSet();
		if( result.next()) 
		    return new Biodata(result.getString("studentId"),
			    result.getString("stateOfOrigin"), result.getString("country"),
			    result.getString("CurrentAddress"), result.getString("PermanentAddress"),
			    result.getString("gender"), result.getDate("dateOfBirth"), 
			    result.getString("placeOfBirth"), result.getString("religion") , 
			    result.getString("title"));
	    }
	}
	finally{
	    if( result != null ) result.close();
	}
	return null ;
    }

}
