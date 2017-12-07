package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import database.bean.student.Phone;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import utils.ValidationType;

public final class PhoneManager
{
    /**
     * Inserts a new {@code Phone} into the databasse
     * @param newNumber
     * @return
     * @throws SQLException
     * @throws InvalidAdminException
     * @throws InvalidBeanException 
     */
    public static boolean insert( Phone newNumber) 
	    throws SQLException, InvalidAdminException, InvalidBeanException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	if( !newNumber.isValid(ValidationType.NEW_BEAN) )
	    throw new InvalidBeanException("The format of the phone number is invalid");

	try( CallableStatement  statement  = DatabaseManager.getCallableStatement( 
		"{CALL addPhoneNumber(?, ? ) } ", 
		newNumber.getStudentId(), newNumber.getNumber());)
	{

	    int affected = statement.executeUpdate();
	    if( affected > 0 )
		return true;
	}
	return false;
    }

    /**
     * Updates a Student's {@code Phone} number in the database. Note that the 
     * oldPhone and newPhone must have the same studentId in order for it
     * to update
     * @param oldPhone the existing {@code Phone} object in the database
     * @param newPhone the new {@code Phone} object that would replace an existing one
     * @return {@code true } when the update is successful
     * @throws SQLException when an a database exception occurs
     * @throws InvalidAdminException when the {@code Admin} that wants to make the change is
     * invalid 
     * @throws InvalidBeanException 
     */
    public static boolean update( Phone oldPhone, Phone newPhone ) 
	    throws SQLException, InvalidAdminException, InvalidBeanException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();

	if( !oldPhone.isValid(ValidationType.EXISTING_BEAN) || 
		!newPhone.isValid(ValidationType.NEW_BEAN)) 
	{
	    throw new InvalidBeanException("The format of either of the bean(s) is invalid");
	}
	try(CallableStatement statement  = DatabaseManager.getCallableStatement( 
		"{CALL updatePhone(?, ? ,?) } ", oldPhone.getStudentId(),
		oldPhone.getNumber(), newPhone.getNumber() ); )
	{

	    int affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	}

	return false;
    }

    /**Deletes a particular phone number from the database
     * Returns true if a value was deleted or false if the {@code Phone} object 
     * does not exist
     * @param phone the {@code Phone } object to be deleted
     * @return {@code true} when the value was successfully deleted
     * @throws SQLException when a special database error occurs
     * @throws InvalidAdminException  when the {@code Admin} that wants to make
     * the change is invalid
     * @throws InvalidBeanException 
     */
    public static boolean removePhone( Phone phone ) throws SQLException, InvalidAdminException, InvalidBeanException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();

	if( !phone.isValid( ValidationType.EXISTING_BEAN ) ){
	    throw new InvalidBeanException("The Phone format is invalid" );
	}
	try(  CallableStatement  statement  = DatabaseManager.getCallableStatement( 
		"{CALL removePhoneNumber(?, ? ) } ", phone.getStudentId(), phone.getNumber());)
	{
	    int affected = statement.executeUpdate();

	    if( affected > 0 )
		return true;
	}
	return false;
    }

    /**
     * Gets an array of {@code Phone} that contains all the number of a particular {@code student}
     * in the database. The {@code Student}'s id card number is used to locate the student
     * in the database.
     * @param student the {@code Student} whosw phone number would be retrieved
     * @return an array of of {@code Phone} 
     * @throws SQLException when a database specific error occurs.
     * @throws InvalidAdminException when the {@code Admin} that wants to access the
     * database the change is invalid
     */
    public static Phone[] getPhoneNumber( String studentId)
	    throws SQLException, InvalidAdminException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();

	if( studentId ==  null ){
	    return null;
	}
	
	ResultSet result  = null;
	List<Phone> list;
	try(CallableStatement statement = DatabaseManager.getCallableStatement( 
		    "{CALL getStudentPhone(? ) } ", studentId);
		)
	
	{
	    result  = statement.executeQuery();
	    list = new LinkedList<Phone>();

	    while(  result.next() )
		list.add( new Phone( result.getString("StudentId") , 
			result.getString("phone_number" )));
	}
	finally{
	    if( result != null )result.close();
	}
	
	return list.toArray( new Phone[ list.size() ] );
    }

    
    public static Phone[] getAllByIndex( int startIndex) 
	    throws SQLException, InvalidAdminException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();

	ResultSet result  = null;
	ArrayList<Phone> list;
	try(CallableStatement   statement = DatabaseManager.getCallableStatement( 
		    "{CALL getAllPhoneNumbers(? ) } ", startIndex) ;)
	{
	    result  = statement.executeQuery();
	    list = new ArrayList<Phone>();

	    while(  result.next() )
		list.add( new Phone( result.getString("student_id") , 
			result.getString("phone_number" )));
	}
	finally{
	    if( result != null ) result.close();
	}
	
	return list.toArray( new Phone[ list.size() ] );
	
    }

}
