package database.managers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import database.bean.Sponsor;
import database.bean.student.Student;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import utils.ValidationType;

public class SponsorManager
{
    public static boolean insert( Sponsor sponsor) 
	    throws SQLException, InvalidAdminException, InvalidBeanException
    {
	if( !DatabaseManager.validateAdmin()) throw new InvalidAdminException();
	if( !sponsor.isValid(ValidationType.NEW_BEAN)) throw new InvalidBeanException();

	try( CallableStatement statement =  DatabaseManager.getCallableStatement
		("{call addSponsor(?,?, ?, ? , ?, ?) }", sponsor.getStudentId(), 
			sponsor.getFirstName() , sponsor.getLastName(), sponsor.getAddress(), 
			sponsor.getTelephone(), sponsor.getEmail()) ; )
	{
	    int affected = statement.executeUpdate();
	    if( affected >0 ) return true ;
	}
	return false;
    }

    public static boolean update( Sponsor sponsor){
	return false;
    }

    public static Sponsor[] getSponsors(Student student) throws SQLException
    {
	List<Sponsor> list = new LinkedList<>();
	String sql = "SELECT * FROM sponsor WHERE StudentID = ? ";
	ResultSet result = null;
	try( PreparedStatement stmt = 
		DatabaseManager.getPreparedStatement(sql, student.getIdCardNumber()))
	{
	    result = stmt.executeQuery();
	    Sponsor temp;
	    while( result.next() ){
		temp = new Sponsor(result.getString("StudentID"), 
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

}
