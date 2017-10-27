package test;

import java.sql.Connection;
import java.sql.SQLException;

import database.managers.ConnectionManager;

public class ConnectionTest
{

    public static void main(String[] args)
    {
	ConnectionManager.getInstance().getConnection();
	ConnectionManager.close();
	
    }

}
