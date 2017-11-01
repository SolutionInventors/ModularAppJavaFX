package database.managers;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bean.Admin;
import database.bean.ModuleStatus;
import exception.InvalidCompositeKeyException;

public class ModuleStatusManager
{
    public static boolean insert( ModuleStatus newStatus) throws  SQLException{
	CallableStatement  statement = null;
	ResultSet result = null;
	try
	{
	    statement = DatabaseManager.getCallableStatement( 
	    		"{CALL insertModuleStatus(?, ? ) } ", 
	    		newStatus.getModuleName(), newStatus.getStudentId());

	    int affected  = statement.executeUpdate();

	    if( affected > 0 ){
	        result = statement.getGeneratedKeys();
	        if( result.next() )
	    	newStatus.setId( result.getInt(1 ) );
	        return true;
	    }
	}
	finally
	{
	   if( result != null ) result.close();
	   if( statement != null ) statement.close();
	}
	return false;
    }

    public static boolean update( ModuleStatus old, ModuleStatus newStatus ) 
	    throws SQLException, InvalidCompositeKeyException
    {
	boolean valid = old.getModuleName().equals( newStatus.getModuleName() ) && 
		old.getStudentId().equals( newStatus.getStudentId() ) ;

	if( !valid )  {
	    throw new InvalidCompositeKeyException( "The two ModuleStatus obejects do not have "
		    + "the same foreign key values");
	}
	CallableStatement statement = null;
	try
	{
	    statement = DatabaseManager.getCallableStatement( 
	    	"{CALL updateModuleStatus(?, ?, ?,?,? ) } ", 
	    	newStatus.getModuleName() , newStatus.getStudentId(), 
	    	newStatus.hasPaid(), newStatus.hasBooked(), newStatus.getResult() );

	    int affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	}
	finally
	{
	   if( statement != null ) statement.close();
	}
	return false;
    }

    public static ModuleStatus[] getModuleStatusByDate( Date date, int startIndex ) throws SQLException{
	CallableStatement  statement = null;
	ResultSet result = null;
	ArrayList<ModuleStatus> list = new ArrayList<ModuleStatus>();;
	try
	{
	    statement = DatabaseManager.getCallableStatement( 
	    		"{CALL getModuleStatusByDate(?,?) } " , date, startIndex);
	    result = statement.executeQuery();
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
	}
	finally
	{
	   if( result != null ) result.close();
	   if( statement!= null ) statement.close();
	}
	
	return list.toArray( new ModuleStatus[ list.size()] );

    }
    public static boolean delete ( ModuleStatus status ) throws SQLException
    {
	CallableStatement statement =null;
	try
	{
	    statement = DatabaseManager.getCallableStatement( 
	    	"{CALL deleteModuleStatus(? , ? ) } ", 
	    	status.getModuleName(), status.getStudentId() );

	    int affected = statement.executeUpdate();
	    if( affected > 0 ) return true;
	}
	finally
	{
	    if( statement!= null) statement.close();
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
	CallableStatement statement = null;
	ResultSet result  =null;
	try
	{
	    statement = DatabaseManager.getCallableStatement( 
	    	"{CALL getModuleStatus(? , ? ) } ", 
	    	status.getModuleName(), status.getStudentId() );

	    result  = statement.executeQuery();
	    if( result.next() ){
	        status.setId( result.getInt("id" ));
	        status.setDateRegistered( result.getDate("dateRegistered"));
	        status.setModuleName(result.getString("module_name"));
	        status.setPaymentStatus(result.getBoolean( "paymentStatus"));
	        status.setBookingStatus( result.getBoolean( "bookingStatus" ) );
	        status.setResult( result.getString("result" ) );
	    }
	}
	finally
	{
	   if( result != null) result.close();
	   if( statement != null ) statement.close();
	}
	return false;
    }
}
