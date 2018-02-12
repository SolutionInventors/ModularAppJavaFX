package test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import database.bean.Admin;
import database.managers.DatabaseManager;

public class CallableInsertTest
{

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	DatabaseManager.setCurrentAdmin(currentAdmin);
	
	Future<Boolean> future =  
		CompletableFuture.supplyAsync(() -> DatabaseManager.validateAdmin());
	
	if(future.isDone() )
	    System.out.println(future.get());
	
	Admin admin = DatabaseManager.getCurrentAdmin(); 
	System.out.println(admin.canWrite());
	System.out.println(admin.getAccessType());

    }

}
