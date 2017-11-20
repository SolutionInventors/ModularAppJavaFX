package database.managers;

import java.sql.SQLException;

import database.bean.ModuleRegister;
import exception.InvalidAdminException;

public final class ModuleRegisterManager
{
    public static boolean insert( ModuleRegister register) 
	    throws  SQLException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	return false;
    }
    
    
}
