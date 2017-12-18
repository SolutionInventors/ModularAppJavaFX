package test;

import java.sql.SQLException;
import java.util.Arrays;

import database.bean.Admin;
import database.bean.log.CertificateLog;
import database.bean.log.CertificateRegisterLog;
import database.bean.log.TransactionType;
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
		TransactionType.INSERT, 0);
	System.out.println("--------CertificateLogs----------");
	Arrays.stream(certLog).forEach( log ->{
	    System.out.println("Operation Date: " + log.getDateOfOperation());
	    System.out.println("OldCertificateNmae: " + log.getOldCertificateName());
	    System.out.println("NewCertificateNmae: " + log.getNewCertificateName());
	    
	    System.out.println("Operation Type: " + log.getOperationType());
	    
	});
	
	ModuleLog[] modLog =  LogManager.getLog(ModuleLog.class,
		TransactionType.NONE, 0);
	System.out.println("-----ModuleLogs--------" );
	Arrays.stream(modLog).forEach( log ->{
	    System.out.println("Operation Date: " + log.getDateOfOperation());
	    System.out.println("Old Module Name: " + log.getOldModuleName());
	    System.out.println("New Module Name: " + log.getNewModuleName());
	    
	    System.out.println("Operation Type: " + log.getOperationType());
	    System.out.println( ">>");
	    
	});
	
	PaymentLog[] payLog =  LogManager.getLog(PaymentLog.class,
		TransactionType.NONE, 0);
	System.out.println("-----PaymentLogs--------" );
	Arrays.stream(payLog).forEach( log ->{
	    System.out.println("Operation Date: " + log.getDateOfOperation());
	    System.out.println("Module Name: " + log.getModuleName());
	    System.out.println("Operation Type: " + log.getOperationType());
	});
	
	StudentLog[] studLog =  LogManager.getLog(StudentLog.class,
		TransactionType.INSERT, 0);
	System.out.println("-----StudentLogs--------" );
	Arrays.stream(studLog).forEach( log ->{
	    System.out.println("Operation Date: " + log.getDateOfOperation());
	    System.out.println("Old Student ID: " + log.getOldStudentID());
	    System.out.println("New Student ID: " + log.getNewStudentID());
	    System.out.println("Operation Type: " + log.getOperationType());
	});
	
	CertificateRegisterLog[] certRegLog = 
		LogManager.getLog(CertificateRegisterLog.class, 
		TransactionType.NONE, 0);
	
	System.out.println( "---CertRegisterLog----" );
	Arrays.stream(certRegLog).forEach( log ->{
	    System.out.println("New CertName: " + log.getNewCertificateName());
	    System.out.println("Old CertName: " + log.getOldCertificateName());
	    System.out.println("New Module Name: " + log.getNewModuleName());
	    System.out.println("Old Module Name:: " + log.getOldModuleName());
	});
	
    }

}
