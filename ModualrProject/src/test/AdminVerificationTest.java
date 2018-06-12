package test;

import database.bean.Admin;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;

public class AdminVerificationTest
{

    public static void main(String[] args)
    {
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	DatabaseManager.setCurrentAdmin(currentAdmin);
	
	System.out.println();
	if(DatabaseManager.validateAdmin()){
	    Admin admin = DatabaseManager.getCurrentAdmin(); 
		System.out.println(admin.canWrite());
		System.out.println(admin.getAccessType());
	}else {
	    System.err.println("Validation Failed!");
	}
	
	ConnectionManager.close();

    }

}
