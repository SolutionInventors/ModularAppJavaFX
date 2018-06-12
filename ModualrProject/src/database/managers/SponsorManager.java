package database.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import database.bean.student.AspiringStudent;
import database.bean.student.Sponsor;
import database.bean.student.Student;
import exception.InvalidAdminException;
import utils.ValidationType;

public class SponsorManager
{
    public static boolean insert( Sponsor sponsor) 
	    throws SQLException, InvalidAdminException
    {
	String sql = ""
		+ "INSERT INTO `sponsor`(`StudentID`, `FirstName`, `LastName`,"
		+ " `Address`, `Telephone`, `Email`)   "
		+ "VALUES (?,?,?,?,?, ?);";
	if( sponsor.isValid(ValidationType.NEW_BEAN)){
	    try( PreparedStatement statement =  DatabaseManager.getPreparedStatement
		    (sql, sponsor.getStudentId(), 
			    sponsor.getFirstName() , sponsor.getLastName(), 
			    sponsor.getAddress(), 
			    sponsor.getTelephone(), sponsor.getEmail()) ; )
	    {
		int affected = statement.executeUpdate();
		if( affected >0 ) return true ;
	    }
	}


	return false;
    }

    /**
     * Updates a Sponsor object with new values. <br>
     * The first argument represents an existing {@code Sponsor} and must contain values 
     * for the studentId, firstName, last Name and Email. The second argument would be used to udpate
     * the first and must specify values for all its attributes.<br>
     * Note that the two arguments must have the same studentId attribute
     * 
     * @param oldSponsor the existing {@code Sponsor}  to be updated
     * @param newSpons the new {@code Sponsor} to be used to be update the first
     * @return {@code true} if the update was successful
     * @throws SQLException when a database error occurs
     * @throws InvalidAdminException when the current {@code Admin} making the change is invalid
     * @throws InvalidBeanException when either {@code Sponsor} object is invalid.
     */
    public static boolean update( Sponsor oldSponsor, Sponsor newSpons) 
	    throws SQLException, InvalidAdminException
    {
	if( ( oldSponsor.isValid(ValidationType.EXISTING_BEAN)  && 
		newSpons.isValid(ValidationType.NEW_BEAN) && 
		oldSponsor.getStudentId().equals(newSpons.getStudentId())) ) 
	{
	       
	    String sql = ""
	    	+ "UPDATE sponsor  SET  FirstName = ?, "
	    	+ "LastName = ?, Address = ? , "
	    	+ " Telephone = ?, Email = ?  "
	    	+ " WHERE StudentID = ? AND FirstName =? AND "
	    	+ "LastName = ? AND Email = ?";
	    try( PreparedStatement  statement = 
		    DatabaseManager.getPreparedStatement( 
			    sql, newSpons.getFirstName(), newSpons.getLastName(),
			    newSpons.getAddress(), newSpons.getTelephone(),
			    newSpons.getEmail(), newSpons.getStudentId(),
			    oldSponsor.getFirstName(), oldSponsor.getLastName(), 
			    oldSponsor.getEmail());)
	    {
		if( statement.executeUpdate() > 0 ) return true;
	    }
	}


	return false;
    }

    public static Sponsor[] getSponsors(Student student) throws SQLException
    {
	String sql = "SELECT * FROM sponsor WHERE StudentID = ? ";
	return getSponsorsHelper(sql, student.getIdCardNumber(), "StudentId");
    }

    private static Sponsor[] getSponsorsHelper(String sql , String id, String idCol) throws SQLException
    {
	List<Sponsor> list = new LinkedList<>();
	ResultSet result = null;
	try( PreparedStatement stmt = 
		DatabaseManager.getPreparedStatement(sql, id))
	{
	    result = stmt.executeQuery();
	    Sponsor temp;
	    while( result.next() ){
		temp = new Sponsor(result.getString(idCol), 
			result.getString("FirstName"), result.getString("LastName"),
			result.getString("Address"), result.getString("Telephone"),
			result.getString("Email"));
		list.add(temp);
	    }
	}finally{
	    if( result != null ) result.close();
	}
	return list.toArray(new Sponsor[list.size() ] );
    }
   
    public static Sponsor[] getSponsors(AspiringStudent aspStudent) throws SQLException
    {
	String sql = "SELECT * FROM aspiringsponsor WHERE AspID = ? ";
	return getSponsorsHelper(sql, String.valueOf(aspStudent.getId()), "AspID");
    }

}
