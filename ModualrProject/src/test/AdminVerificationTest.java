package test;

import database.bean.Admin;
import database.managers.DatabaseManager;

public class AdminVerificationTest
{

    public static void main(String[] args)
    {
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	DatabaseManager.setCurrentAdmin(currentAdmin);
	
	System.out.println(DatabaseManager.validateAdmin());
	Admin admin = DatabaseManager.getCurrentAdmin(); 
	System.out.println(admin.canWrite());
	System.out.println(admin.getAccessType());

    }

}
