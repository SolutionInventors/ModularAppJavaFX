package database.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.bean.Admin;
import database.bean.Bean;
import database.bean.Module;
import database.bean.ModuleStatus;
import database.bean.Phone;
import database.bean.Student;
import exception.InvalidBeanException;
import exception.InvalidCompositeKeyException;
import exception.InvalidPrimaryKeyException;

public class DatabaseManager
{
    public static CallableStatement getCallableStatement(String sqlCall, Object ... arguments ) throws SQLException{
	Connection conn = ConnectionManager.getInstance().getConnection();

	CallableStatement statement =  conn.prepareCall(
		sqlCall,
		ResultSet.TYPE_FORWARD_ONLY,
		ResultSet.CONCUR_READ_ONLY, Statement.RETURN_GENERATED_KEYS);


	for( int i =  0 ; i < arguments.length ; i++ )
	    statement.setObject( i+1 , arguments[ i ] );
	return statement;
    }




    public static <E extends Bean >boolean insert( Admin currenAdmin , E bean ) throws InvalidPrimaryKeyException, InvalidBeanException
    {
	try{
	    if( AdminManager.isInDatabase( currenAdmin ) )
	    {
		switch( bean.getClass().getSimpleName() ) {
		    case "Admin" :
			return AdminManager.insert( (Admin) bean );
		    case  "Module":
			return ModuleManager.insert( (Module) bean );
		    case "ModuleStatus":
			return ModuleStatusManager.insert( (ModuleStatus) bean );
		    case "Phone":
			return PhoneManager.insert( (Phone) bean );
		    case "Student":
			return StudentManager.insert( (Student) bean);
		    default:
			return false;

		}
	    }
	}
	catch( SQLException  e ){
	    e.printStackTrace();
	}

	return false;
    }



    public static <E extends Bean >boolean update( Admin currenAdmin , E oldBean, E newBean ) throws InvalidPrimaryKeyException, 
    	InvalidCompositeKeyException, InvalidBeanException
    {
	try{
	    ConnectionManager.getInstance().getConnection().setAutoCommit( false );;

	    if( !oldBean.getClass().equals( newBean.getClass() ))
		throw new InvalidBeanException("The two bean objects must be of the same type");

	    if( AdminManager.isInDatabase( currenAdmin ) )
	    {
		switch( oldBean.getClass().getSimpleName() ) {
		    case "Admin" :
			return AdminManager.update( (Admin) oldBean, (Admin) newBean );
		    case  "Module":
			return ModuleManager.update(  (Module) oldBean, (Module) newBean );
		    case "ModuleStatus":
			return ModuleStatusManager.update(  (ModuleStatus) oldBean ,(ModuleStatus)  newBean );
		    case "Phone":
			return PhoneManager.update( (Phone) oldBean, (Phone) newBean );
		    case "Student":
			return StudentManager.update( (Student) oldBean, (Student)  newBean );
		    default:
			return false;

		}
	    }
	}
	catch( SQLException  e ){
	    e.printStackTrace();
	}
	return false;
    }


    /**
     * Gets the first 30 occurence of a specified {@code BeanType} in the database
     * This method can retrieve info from all the tables in the database. Data returned
     * can be manipulated via the beaType
     * Returns an empty array when the index is too large
     * should be used with method getTotalAdmin* Gets 
     * @param startIndex
     * @return
     */
    public static <T extends Bean> T[] getAllBean( BeanType beanType, int startIndex)
    {
	try {
	    switch (beanType)
	    {
		case ADMIN:
		    return  (T[]) AdminManager.getAllAdmin( startIndex );

		default:
		    return null;

	    }
	}
	catch( SQLException e )
	{

	}
	return null;
    }
}
