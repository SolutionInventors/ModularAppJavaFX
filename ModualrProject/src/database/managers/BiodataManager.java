package database.managers;

import java.sql.SQLException;

import database.bean.Biodata;
import exception.InvalidAdminException;

public class BiodataManager
{
    public boolean  insert(Biodata data ) throws SQLException, InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	return false;
    }
    
    public boolean update( Biodata data) throws InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	return false;
    }

}
