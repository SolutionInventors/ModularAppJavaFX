package test;

import java.sql.SQLException;
import java.util.Arrays;

import database.bean.Admin;
import database.bean.log.CertificateLog;
import database.bean.log.LogType;
import database.bean.log.ModuleLog;
import database.bean.log.PaymentLog;
import database.bean.log.StudentLog;
import database.managers.DatabaseManager;
import database.managers.LogManager;

public class LogTest
{
    public static void main(String[] args) throws SQLException
    {
	DatabaseManager.setCurrentAdmin( new Admin("Chidiebere", "Fred") );
	
	CertificateLog[] certLog =  LogManager.getLog(CertificateLog.class,
		LogType.INSERT, 0);
	System.out.println("--------CertificateLogs----------");
	Arrays.stream(certLog).forEach( log ->{
	    System.out.println("Operation Date: " + log.getDateOfOperation());
	    System.out.println("CertificateName: " + log.getCertificateName());
	    System.out.println("Operation Type: " + log.getOperationType());
	    
	    
	});
	
	ModuleLog[] modLog =  LogManager.getLog(ModuleLog.class,
		LogType.ALL, 0);
	System.out.println("-----ModuleLogs--------" );
	Arrays.stream(modLog).forEach( log ->{
	    System.out.println("Operation Date: " + log.getDateOfOperation());
	    System.out.println("Module Name: " + log.getModuleName());
	    System.out.println("Operation Type: " + log.getOperationType());
	    
	    
	});
	
	PaymentLog[] payLog =  LogManager.getLog(PaymentLog.class,
		LogType.ALL, 0);
	System.out.println("-----PaymentLogs--------" );
	Arrays.stream(payLog).forEach( log ->{
	    System.out.println("Operation Date: " + log.getDateOfOperation());
	    System.out.println("Module Name: " + log.getModuleName());
	    System.out.println("Operation Type: " + log.getOperationType());
	});
	
	StudentLog[] studLog =  LogManager.getLog(StudentLog.class,
		LogType.DELETE, 0);
	System.out.println("-----StudentLogs--------" );
	Arrays.stream(studLog).forEach( log ->{
	    System.out.println("Operation Date: " + log.getDateOfOperation());
	    System.out.println("Module Name: " + log.getStudentId());
	    System.out.println("Operation Type: " + log.getOperationType());
	});
	
    }

}
