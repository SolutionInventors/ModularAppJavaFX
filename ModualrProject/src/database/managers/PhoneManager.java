package database.managers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import database.bean.student.AspiringStudent;
import database.bean.student.Phone;
import exception.InvalidAdminException;
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
	    throws SQLException, InvalidAdminException
    {
	if( newNumber.isValid(ValidationType.NEW_BEAN) ){
	    
	   
	    String sql =""
	    	+ "INSERT INTO `phone`(`StudentId`, `phone_number`)  "
	    	+ " VALUES (studId, number ); ";
	    
	    try( PreparedStatement  statement  = DatabaseManager.getPreparedStatement( 
		    sql, newNumber.getStudentID(), newNumber.getNumber());)
	    {

		int affected = statement.executeUpdate();
		if( affected > 0 )
		    return true;
	    }
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
	    throws SQLException, InvalidAdminException
    {
	if( (oldPhone.isValid(ValidationType.EXISTING_BEAN) &&
		newPhone.isValid(ValidationType.NEW_BEAN) && 
		newPhone.getStudentID().equals(oldPhone.getStudentID()))) 
	{
	   String sql = ""
	    	+ "UPDATE `phone`  SET `phone_number`= ? "
	    	+ " WHERE `studentId`= ?  AND `phone_number`= ?; ";
	    try(PreparedStatement statement  = 
		    DatabaseManager.getPreparedStatement( 
		    sql, newPhone.getNumber(),
		    oldPhone.getNumber() , oldPhone.getStudentID() ); )
	    {

		int affected = statement.executeUpdate();
		if( affected > 0 ) return true;
	    }
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
    public static boolean removePhone( Phone phone ) throws SQLException, InvalidAdminException
    {
	if( phone.isValid( ValidationType.EXISTING_BEAN ) ){
	   
		
	    String sql = ""
	    	+ " DELETE FROM phone  "
	    	+ "WHERE phone.student_id = ? AND phone.phone_number = ?;";
	    try(  PreparedStatement  statement  =
		    DatabaseManager.getPreparedStatement( 
		    sql, phone.getStudentID(), phone.getNumber());)
	    {
		int affected = statement.executeUpdate();

		if( affected > 0 )
		    return true;
	    }
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
	String sql = "SELECT * FROM phone " + 
		     "WHERE phone.studentId = ? ";
	ResultSet result  = null;
	List<Phone> list;
	try(PreparedStatement statement = DatabaseManager.getPreparedStatement( 
		sql , studentId);)

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


    public static Phone[] getAllByIndex( int startIndex, String studentId) 
	    throws SQLException, InvalidAdminException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();

	ResultSet result  = null;
	ArrayList<Phone> list;
	String sql = ""
		+ "SELECT student_id, phone_number "
		+ "FROM phone "
		+ "WHERE student_id = ? "
		+ "LIMIT ? , 20";
	try(PreparedStatement   statement = DatabaseManager.getPreparedStatement( 
		sql, studentId, startIndex) ;)
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

    public static Phone[] getPhoneNumber(AspiringStudent aspStudent) throws SQLException
    {
	ResultSet result  = null;
	ArrayList<Phone> list;
	String sql = ""
		+ "SELECT AspID, phone "
		+ "FROM aspiringstudentphone "
		+ "WHERE AspID = ? "
		+ "LIMIT ? , 20";
	try(PreparedStatement   statement = DatabaseManager.getPreparedStatement( 
		sql, aspStudent.getId(), 0) ;)
	{
	    result  = statement.executeQuery();
	    list = new ArrayList<Phone>();

	    while(  result.next() )
		list.add( new Phone( result.getString("AspId") , 
			result.getString("phone" )));
	}
	finally{
	    if( result != null ) result.close();
	}

	return list.toArray( new Phone[ list.size() ] );
    }

}
