package test;

import java.sql.SQLException;
import java.util.Arrays;

import database.bean.Admin;
import database.bean.log.CertificateLog;
import database.bean.log.CertificateRegisterLog;
import database.bean.log.ModularClassLog;
import database.bean.log.ModuleLog;
import database.bean.log.ModuleRegisterLog;
import database.bean.log.PaymentLog;
import database.bean.log.StudentLog;
import database.bean.log.TransactionType;
import database.managers.DatabaseManager;
import database.managers.LogManager;

public class LogTest
{
    public static void main(String[] args) throws SQLException
    {
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	DatabaseManager.setCurrentAdmin(currentAdmin);
	
	CertificateLog[] certLog =  LogManager.getLog(CertificateLog.class,
		TransactionType.INSERT, 0);
	System.out.println("--------CertificateLogs----------");
	Arrays.stream(certLog).forEach( log ->{
	    System.out.println("Operation Date: " + log.getDateOfOperation());
	    System.out.println("OldCertificateNmae: " + log.getOldCertificateName());
	    System.out.println("NewCertificateNmae: " + log.getNewCertificateName());
	    
	    System.out.println("Operation Type: " + log.getOperationType());
	    System.out.println("Description: " + log);
	});
	
	ModuleLog[] modLog =  LogManager.getLog(ModuleLog.class,
		TransactionType.ALL, 0);
	System.out.println("-----ModuleLogs--------" );
	Arrays.stream(modLog).forEach( log ->{
	    System.out.println("Operation Date: " + log.getDateOfOperation());
	    System.out.println("Old ModuleTabTable Name: " + log.oldModuleName());
	    System.out.println("New ModuleTabTable Name: " + log.newModuleName());
	    
	    System.out.println("Operation Type: " + log.getOperationType());
	    System.out.println("Desctiption:  "  + log);
	    System.out.println( ">>");
	    
	});
	
	PaymentLog[] payLog =  LogManager.getLog(PaymentLog.class,
		TransactionType.ALL, 0);
	System.out.println("-----PaymentLogs--------" );
	Arrays.stream(payLog).forEach( log ->{
	    System.out.println("Operation Date: " + log.getDateOfOperation());
	    System.out.println("ModuleTabTable Name: " + log.getModuleName());
	    System.out.println("Operation Type: " + log.getOperationType());
	    System.out.println("Description: " + log);
	});
	
	System.out.println("<<<<<<<<<< >>>>>>>>>>>>>>");
	StudentLog[] studLog =  LogManager.getLog(StudentLog.class,
		TransactionType.INSERT, 0);
	System.out.println("-----StudentLogs--------" );
	Arrays.stream(studLog).forEach( log ->{
	   System.out.println("-Description: " + log);
	});
	
	CertificateRegisterLog[] certRegLog = 
		LogManager.getLog(CertificateRegisterLog.class, 
		TransactionType.ALL, 0);
	
	System.out.println( "---CertRegisterLog----" );
	Arrays.stream(certRegLog).forEach( log ->{
	   System.out.println("-Description: " + log);
	});
	
	ModularClassLog[] modularClassLog = 
		LogManager.getLog(ModularClassLog.class, 
		TransactionType.ALL, 0);
	
	System.out.println( "\n<<<<<<<Modular ClassLog>>>>>" );
	Arrays.stream(modularClassLog).forEach( log ->{
	  System.out.println("-Description: " + log);
	});
	
	ModuleRegisterLog[] moduleRegisterLog = LogManager.getLog(ModuleRegisterLog.class, 
		TransactionType.ALL, 0); 
	
	System.out.println( "\n<<<<<<<ModuleRegisterLog>>>>>>" );
	Arrays.stream(moduleRegisterLog).forEach( log ->{
	    System.out.println("-Description: " + log);
	});
	
    }

}
