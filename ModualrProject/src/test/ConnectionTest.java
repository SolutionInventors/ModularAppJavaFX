package test;

import database.managers.ConnectionManager;

public class ConnectionTest
{

    public static void main(String[] args)
    {
	ConnectionManager.getInstance().getConnection();
	ConnectionManager.close();
	
    }

}
