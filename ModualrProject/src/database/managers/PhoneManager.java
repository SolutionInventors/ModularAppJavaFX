package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bean.Phone;
import database.bean.Student;

public class PhoneManager
{
    public static boolean insert( Phone phone ) throws SQLException {

	CallableStatement  statement = 
		DatabaseManager.getCallableStatement( 
			"{CALL insertPhone(?, ? ) } ", 
			phone.getStudentId(), phone.getNumber());

	int affected = statement.executeUpdate();
	statement.close();
	ConnectionManager.close();
	if( affected > 0 )
	    return true;

	return false;
    }

    /**
     * Updates a Student's {@code Phone} number in the database. Note that the 
     * oldPhone and newPhone must have thesame studentId in order for it
     * to update
     * @param oldPhone the existing {@code Phone} object in the database
     * @param newPhone the new {@code Phone} object that would replace an existing one
     * @return {@code true } when the update is successful
     * @throws SQLException when an a database exception occurs
     */
    public boolean updatePhoneNumber( Phone oldPhone, Phone newPhone ) throws SQLException{
	if( isValid( oldPhone ) && oldPhone.getStudentId().equals( newPhone.getStudentId())){
	    CallableStatement statement = 
		    DatabaseManager.getCallableStatement( 
			    "{CALL updatePhone(?, ? ) } ", 
			    oldPhone.getStudentId(), oldPhone.getNumber(), 
			    newPhone.getNumber() );
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
     */
    public static boolean delete( Phone phone ) throws SQLException{
	if( !isValid( phone ) ) return false;
	CallableStatement  statement = 
		DatabaseManager.getCallableStatement( 
			"{CALL deletePhone(?, ? ) } ", 
			phone.getStudentId(), phone.getNumber());

	int affected = statement.executeUpdate();
	statement.close();
	ConnectionManager.close();
	if( affected > 0 )
	    return true;

	return false;
    }

    /**
     * Returns {@code true} if the Phone object can be put in the database
     * @param phone the {@code Phone} object to check
     * @return boolean
     */
    public static boolean isValid( Phone phone ){
	if( phone != null && phone.getStudentId() != null && phone.getNumber() != null)
	    return true;

	return false;
    }

    /**
     * Gets an array of {@code Phone} that contains all the number of a particular {@code student}
     * in the database. The {@code Student}'s id card number is used to locate the student
     * in the database.
     * @param student the {@code Student} whosw phone number would be retrieved
     * @return an array of of {@code Phone} 
     * @throws SQLException when a database specific error occurs.
     */
    public static Phone[] getPhoneNumber( Student student ) throws SQLException{
	if( student.getIdCardNumber()  ==  null ){
	    return null;
	}
	CallableStatement  statement = 
		DatabaseManager.getCallableStatement( 
			"{CALL getPhoneAllPhone(? ) } ", 
			student.getIdCardNumber());

	statement.setString( 1, student.getIdCardNumber() );
	ResultSet result  = statement.executeQuery();
	ArrayList<Phone> list = new ArrayList<Phone>();

	while(  result.next() )
	    list.add( new Phone( result.getString("student_id") , 
		    result.getString("phoneNumber" )));

	statement.close();
	ConnectionManager.close();
	return list.toArray( new Phone[ list.size() ] );

    }


}
