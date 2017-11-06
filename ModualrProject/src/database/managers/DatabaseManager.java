package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.bean.Admin;
import database.bean.Bean;
import database.bean.Module;
import database.bean.ModuleRegister;
import database.bean.Phone;
import database.bean.Student;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import exception.InvalidCompositeKeyException;
import exception.InvalidPrimaryKeyException;

public class DatabaseManager
{

    /** 
     * Creates a {@code CallableStatement } object with its the specified arguments and
     * returns the {@code CallableStatement} object
     * @param sqlCall a String representing the statement that calls a procedure
     * @param arguments the arguments that would be passed into the sql procedure object as an argument.
     * This can be ignpred if there are no  arguments
     * @return a {@code CallableStatement} object that can be executed
     * @throws SQLException when an error occurs at the database level.
     */
    protected static CallableStatement getCallableStatement(String sqlCall, Object ... arguments ) throws SQLException{
	Connection conn = ConnectionManager.getInstance().getConnection();

	CallableStatement statement =  conn.prepareCall(
		sqlCall,
		ResultSet.TYPE_FORWARD_ONLY,
		ResultSet.CONCUR_READ_ONLY, Statement.RETURN_GENERATED_KEYS);


	for( int i =  0 ; i < arguments.length ; i++ )
	    statement.setObject( i+1 , arguments[ i ] );
	return statement;
    }



    /**
     * Inserts a {@code Bean } into the database by checking the bean type and inserting
     * it into the correct table. This method returns {@code true} if the operation
     * was successful.<br>
     * This method ensures that the {@code Admin} object passed to it as an argument is
     * valid before the transaction is executed.<br>
     * 
     * @param currenAdmin
     * @param bean
     * @return true if the insertion was successful
     * @throws InvalidPrimaryKeyException  signals that the primary key of the specified 
     * {@code Bean } is not valid
     * @throws InvalidBeanException when the {@code Bean} format is not valid
     * For example, if the username of an {@code Admin} is null or contains space.<br>
     * This can be prevented by calling the {@code Bean } object's static method sisValid 
     * @throws InvalidAdminException when the current Admin is not in the database
     * @throws SQLException 
     */
    public static <E extends Bean >boolean insert( Admin currentAdmin , E bean ) throws InvalidPrimaryKeyException, 
    	InvalidBeanException, InvalidAdminException, SQLException
    {
	validateAdmin( currentAdmin );
	try{
	    switch( bean.getClass().getSimpleName() ) {
		case  "Module":
		    return ModuleManager.insert( (Module) bean );
		case "ModuleRegister":
		    return ModuleRegisterManager.insert( (ModuleRegister) bean );
		case "Phone":
		    return PhoneManager.insert( (Phone) bean );
		case "Student":
		    return StudentManager.insert( (Student) bean);
	    }

	}
	catch( SQLException  e ){
	    e.printStackTrace();
	}
	return false;
    }


    /**
     * Updates an existing {@code Bean } in the database with a new {@code Bean }object
     * Returns true if the update was successful.<br>
     * This method checks if the {@code Admin} object that wants to make the change is
     * in the database.<br>
     * Note that the two {@code Bean}s must be of the same type
     * @param currenAdmin the {@code Admin} that wants to make the change
     * @param oldBean the oldBean object
     * @param newBean the updated {@code Bean} object
     * @return true when the transaction was successful
     * @throws InvalidPrimaryKeyException when the primary key is invalid
     * @throws InvalidCompositeKeyException when the composite key is invalid. Can be thrown
     * when working with {@code ModuleRegister} objects
     * @throws InvalidBeanException when the two {@code Bean} objects are of different
     * type
     * @throws InvalidAdminException when the Admin that wants to make the change is not in
     * the database
     */
    public static <E extends Bean >boolean update( Admin currenAdmin , E oldBean, E newBean ) throws InvalidPrimaryKeyException, 
    InvalidCompositeKeyException, InvalidBeanException, InvalidAdminException
    {
	try{
	    ConnectionManager.getInstance().getConnection().setAutoCommit( false );;
	    validateAdmin(currenAdmin);
	    if( !oldBean.getClass().equals( newBean.getClass() ))
		throw new InvalidBeanException("The two bean objects must be of the same type");


	    switch( oldBean.getClass().getSimpleName() ) {
		case "Admin" :
		    return AdminManager.update( (Admin) oldBean, (Admin) newBean );
		case  "Module":
		    return ModuleManager.update(  (Module) oldBean, (Module) newBean );
		case "ModuleRegister":
		    return ModuleRegisterManager.update(  (ModuleRegister) oldBean ,(ModuleRegister)  newBean );
		case "Phone":
		    return PhoneManager.update( (Phone) oldBean, (Phone) newBean );
		case "Student":
		    return StudentManager.update( (Student) oldBean, (Student)  newBean );
	    }
	}
	catch( SQLException  e ){
	    e.printStackTrace();
	}
	return false;
    }



    /**
     * @param currenAdmin
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static void validateAdmin(Admin currenAdmin) throws SQLException, InvalidAdminException
    {
	if( !AdminManager.isInDatabase( currenAdmin ) ) 
	    throw new InvalidAdminException("This Admin does not exists" );
    }

    /**
     * Deletes an existing {@code Bean } from the database and returns true if the
     * operation was successful.
     * @param currenAdmin
     * @param beanToDelete
     * @return {@code true }when the delete was successful
     * @throws InvalidAdminException when the Adin that wants to perform the 
     * transavtion is not in the database
     */
    public static <E extends Bean >boolean delete( Admin currenAdmin , E beanToDelete ) throws InvalidAdminException
    {
	try{
	    ConnectionManager.getInstance().getConnection().setAutoCommit( false );;
	    validateAdmin(currenAdmin);

	    switch( beanToDelete.getClass().getSimpleName() ) {
		case  "Module":
		    return ModuleManager.delete(  (Module) beanToDelete );
		case "ModuleRegister":
		    return ModuleRegisterManager.delete(  (ModuleRegister) beanToDelete );
		case "Phone":
		    return PhoneManager.delete( (Phone) beanToDelete );
		case "Student":
		    return StudentManager.delete( (Student) beanToDelete);
		default:
		    return false;
	    }
	}
	catch( SQLException  e ){
	    e.printStackTrace();
	}
	return false;
    }

    public Bean[] getAll( int startIndex, QueryType queryType ) throws SQLException
    {
	switch( queryType )
	{
	    case MODULES:
		return  ModuleManager.getAll(startIndex);
	    case ALL_ACTIVE_STUDENTS:
		return  StudentManager.getAllActiveStudents(startIndex);
	    case ALL_INACTIVE_STUDENTS:
		return  StudentManager.getAllInactiveStudents( startIndex);
	    default:
		break;
	}
	return null;
    }
    
    
}
