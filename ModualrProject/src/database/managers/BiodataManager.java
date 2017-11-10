package database.managers;

import database.bean.Biodata;
import exception.InvalidAdminException;

public class BiodataManager
{   
    public boolean update( Biodata data) throws InvalidAdminException
    {
	if(!DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	return false;
    }

}
