package database.managers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import database.bean.ModularClass;
import database.bean.ModuleRegister;
import exception.InvalidAdminException;
import utils.ValidationType;

public final class ModuleRegisterManager
{
    /**
     * This registers a Student for a {@code Module}. The {@code ModuleRegister} 
     * object passed as an argument must contain a studentId and moduleName
     * before it can be used to register a {@code Student} for a {@code Module}.
     * 
     * @return {@code true } if the insertion was successful.
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static boolean insert(ModuleRegister modReg ) 
	    throws  SQLException, InvalidAdminException
    {
	if( modReg.isValid( ValidationType.NEW_BEAN )){
	    try( CallableStatement stmt = DatabaseManager.getCallableStatement
		    ("{call registerForModule(?,?,?)}", modReg.getStudentId(), modReg.getModuleName());)
	    {
		stmt.registerOutParameter(3, Types.DATE);
		if( stmt.executeUpdate() > 0 ) {
		    modReg.setDateRegistered(stmt.getDate(3));
		    return true;
		}
	    }
	}
	return false;
    }
    
    
    
    public static boolean update( ModuleRegister oldClass, ModuleRegister newClass ) 
	    throws  SQLException, InvalidAdminException
    {
	if( ( oldClass.isValid(ValidationType.EXISTING_BEAN)&&
		newClass.isValid(ValidationType.NEW_BEAN) ))
	{
	    try( CallableStatement statement = DatabaseManager.getCallableStatement
		    ("{call updateModRegister(?, ?, ?) }", oldClass.getName(),
			    newClass.getName()))
	    {
		statement.registerOutParameter(3, Types.DATE);
		int affected = statement.executeUpdate();
		if( affected > 0 ){
		    newClass.setDateCreated( statement.getDate(3) );
		    return true;
		}

	    }

	}
	return false;
    }
    
    
}
