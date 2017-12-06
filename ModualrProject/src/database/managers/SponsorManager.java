package database.managers;

import java.sql.CallableStatement;
import java.sql.SQLException;

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

    public static Sponsor[] getSponsors(Student student)
    {
	// TODO Auto-generated method stub
	return null;
    }

}
