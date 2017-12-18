package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.bean.student.Biodata;
import database.bean.student.Student;
import exception.InvalidAdminException;
import utils.ValidationType;

public class BiodataManager
{   
    public static boolean insert(Biodata data) 
	    throws SQLException, InvalidAdminException
    {
	if( data.isValid(ValidationType.NEW_BEAN )){
	    try( CallableStatement statement =  DatabaseManager.getCallableStatement
		    ("{call insertBiodata(?,?,?,?, ?,?,?, ?, ?,?,?, ?, ?) }", 
			    data.getStudentID(),data.getTitle(), data.getSurname(),  
			    data.getMiddleName(), data.getLastName(), data.getPermanentAddress(), 
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

    public static boolean update(Student existingStudent, Biodata data) 
	    throws InvalidAdminException,  SQLException
    {
	if( (data.isValid(ValidationType.NEW_BEAN ) && 
		data.getStudentID().equals(existingStudent.getIdCardNumber())))
	{
	    try( CallableStatement statement =  DatabaseManager.getCallableStatement
		    ("{call updateBiodata(?,?,?,?, ?,?,?, ?, ?,?,?, ?, ?) }", 
			    data.getStudentID(),data.getTitle(), data.getSurname(),  
			    data.getMiddleName(), data.getLastName(), data.getPermanentAddress(), 
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
     * Retrieves a {@code Student}'s  {@code Biodata} object from the database
     * @param student
     * @return
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static Biodata getBiodata(Student student) throws SQLException, InvalidAdminException
    {
	ResultSet result = null;
	try( CallableStatement statement =  DatabaseManager.getCallableStatement
		("{call getBiodata(?) }", student.getIdCardNumber());)
	{
	    if( statement.execute()){
		result = statement.getResultSet();
		if( result.next()) 
		    return new Biodata(result.getString("studentId"),result.getString("surname"),
			    result.getString("MiddleName"),result.getString("LastName"),
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
	return new Biodata("");
    }

}
