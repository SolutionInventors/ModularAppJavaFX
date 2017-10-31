package database.managers;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bean.Admin;
import database.bean.ModuleStatus;
import database.bean.Phone;
import database.bean.Student;
import exception.UpdateException;

public class ModuleStatusManager
{
    public static boolean insert( ModuleStatus newStatus) throws  SQLException{
	CallableStatement  statement = 
		DatabaseManager.getCallableStatement( 
			"{CALL insertModuleStatus(?, ? ) } ", 
			newStatus.getModuleName(), newStatus.getStudentId());

	int affected  = statement.executeUpdate();

	if( affected > 0 ){
	    ResultSet result = statement.getGeneratedKeys();
	    if( result.next() )
		newStatus.setId( result.getInt(1 ) );
	    return true;
	}
	return false;
    }

    public static boolean update( ModuleStatus old, ModuleStatus newStatus ) 
	    throws SQLException, UpdateException
    {
	boolean valid = old.getModuleName().equals( newStatus.getModuleName() ) && 
		old.getStudentId().equals( newStatus.getStudentId() ) ;

	if( !valid )  {
	    throw new UpdateException( "The two ModuleStatus do not have "
	    	+ "the same foreign key values");
	}
	CallableStatement statement = DatabaseManager.getCallableStatement( 
		"{CALL updateModuleStatus(?, ?, ?,?,? ) } ", 
		newStatus.getModuleName() , newStatus.getStudentId(), 
		newStatus.hasPaid(), newStatus.hasBooked(), newStatus.getResult() );

	int affected = statement.executeUpdate();
	if( affected > 0 ) return true;


	return false;
    }
    
    public static ModuleStatus[] getModuleStatusByDate( Date date, int startIndex ) throws SQLException{
	CallableStatement  statement = 
		DatabaseManager.getCallableStatement( 
			"{CALL getModuleStatusByDate(?,?) } " , date, startIndex);
	ResultSet result = statement.executeQuery();

	ArrayList<ModuleStatus > list = new ArrayList<ModuleStatus>();

	while ( result.next() )
	{
	    ModuleStatus status = new ModuleStatus();

	    status.setId( result.getInt( "Id" ) );
	    status.setDateRegistered( result.getDate( "dateRegistered" ) );
	    status.setModuleName( result.getString( "module_name" ) );
	    status.setStudentId( result.getString( "student_id" ) );
	    status.setPaymentStatus(result.getBoolean( "paymentStatus" ) );
	    status.setBookingStatus(result.getBoolean( "bookingStatus" ) );
	    status.setResult( result.getString("result") );
	    list.add(status);
	    
	}
	return list.toArray( new ModuleStatus[ list.size()] );
	
    }
    public static boolean delete ( Admin currentAdmin , ModuleStatus status ) throws SQLException{
	
	if ( AdminManager.isInDatabase( currentAdmin )) {
	    CallableStatement statement = DatabaseManager.getCallableStatement( 
			"{CALL deleteModuleStatus(? , ? ) } ", 
			status.getModuleName(), status.getStudentId() );

		int affected = statement.executeUpdate();
		if( affected > 0 ) return true;
	}
	return false;
    }
    
    /**This method uses the student id and module name of the {@code ModuleStatus}
     * passed to it as an argument to determine if that {@code ModuleStatus } object is
     * in the database. It also updates the status passed to it as an argument
     * 
     * @param status the {@code ModuleStatus } to be checked. This object would be updated if
     * the {@code ModuleStatus} if this method returns {@code true}
     * @return {@code true} if the {@code ModuleStatus } is in the database
     * @throws SQLException when an error occurs
     */
    public static boolean exists( ModuleStatus status ) throws SQLException{
	CallableStatement statement = DatabaseManager.getCallableStatement( 
		"{CALL getModuleStatus(? , ? ) } ", 
		status.getModuleName(), status.getStudentId() );
		
	ResultSet result  = statement.executeQuery();
	if( result.next() ){
	    status.setId( result.getInt("id" ));
	    status.setDateRegistered( result.getDate("dateRegistered"));
	    status.setModuleName(result.getString("module_name"));
	    status.setPaymentStatus(result.getBoolean( "paymentStatus"));
	    status.setBookingStatus( result.getBoolean( "bookingStatus" ) );
	    status.setResult( result.getString("result" ) );
	    
	}
	return false;
    }
}
