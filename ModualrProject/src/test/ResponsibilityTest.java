package test;

import database.bean.Admin;
import database.managers.DatabaseManager;

public class ResponsibilityTest
{

    public static void main(String[] args)
    {
	DatabaseManager.setCurrentAdmin(new Admin("Chidiebere", "Fred" ) );
	int expId = 1;
	
    }

}
