package database.managers;

import java.sql.CallableStatement;
import java.sql.SQLException;

import database.bean.MeanOfDiscovery;
import database.bean.Student;
import database.bean.ValidationType;
import exception.InvalidAdminException;
import exception.InvalidBeanException;

public final class DiscoveryManager
{
    public static boolean insert( MeanOfDiscovery means) 
	    throws SQLException, InvalidAdminException, InvalidBeanException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( !means.isValid(ValidationType.NEW_BEAN )) throw new InvalidBeanException();

	try( CallableStatement statement =  DatabaseManager.getCallableStatement
		("{call addMeansOfDiscovery(?,?) }", means.getStudentId(), 
			means.getMeans()) ; )
	{
	    int affected = statement.executeUpdate();
	    if( affected >0 ) return true ;
	}

	return false;
    }

    public static boolean update(Student existingStudent, MeanOfDiscovery newMeans) 
	    throws InvalidBeanException, InvalidAdminException, SQLException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	//Ensures that the two objects are valid and that the both have the same 
	//student id card number
	if( ! ( newMeans.isValid(ValidationType.NEW_BEAN ) &&
		existingStudent.isValid(ValidationType.EXISTING_BEAN)&& 
		existingStudent.getIdCardNumber().equals( newMeans.getStudentId()) ))
	{
	    throw new InvalidBeanException();
	}

	try( CallableStatement statement =  DatabaseManager.getCallableStatement
		("{call updateDiscoveryRecord(? ,?) }", 
			newMeans.getStudentId(), newMeans.getMeans()); )
	{
	    int affected = statement.executeUpdate();
	    if( affected >0 ) return true ;
	}
	return false;
    }
}
