package database.managers;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bean.ModuleRegister;
import exception.InvalidCompositeKeyException;

public class ModuleStatusManager
{
    public static boolean insert( ModuleRegister newStatus) throws  SQLException{
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

    public static boolean update( ModuleRegister old, ModuleRegister newStatus ) 
	    throws SQLException, InvalidCompositeKeyException
    {
	boolean valid = old.getModuleName().equals( newStatus.getModuleName() ) && 
		old.getStudentId().equals( newStatus.getStudentId() ) ;

	if( !valid )  {
	    throw new InvalidCompositeKeyException( "The two ModuleRegister obejects do not have "
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

    public static ModuleRegister[] getModuleStatusByDate( Date date, int startIndex ) throws SQLException{
	CallableStatement  statement = null;
	ResultSet result = null;
	ArrayList<ModuleRegister> list = new ArrayList<ModuleRegister>();;
	try
	{
	    statement = DatabaseManager.getCallableStatement( 
	    		"{CALL getModuleStatusByDate(?,?) } " , date, startIndex);
	    result = statement.executeQuery();
	    while ( result.next() )
	    {
	        ModuleRegister status = new ModuleRegister();
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
	
	return list.toArray( new ModuleRegister[ list.size()] );

    }
    public static boolean delete ( ModuleRegister status ) throws SQLException
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

    /**This method uses the student id and module name of the {@code ModuleRegister}
     * passed to it as an argument to determine if that {@code ModuleRegister } object is
     * in the database. It also updates the status passed to it as an argument
     * 
     * @param status the {@code ModuleRegister } to be checked. This object would be updated if
     * the {@code ModuleRegister} if this method returns {@code true}
     * @return {@code true} if the {@code ModuleRegister } is in the database
     * @throws SQLException when an error occurs
     */
    public static boolean isInDatabase( ModuleRegister status ) throws SQLException{
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
