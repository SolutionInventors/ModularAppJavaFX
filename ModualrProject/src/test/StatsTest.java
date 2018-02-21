package test;

import java.sql.SQLException;
import java.util.Arrays;

import database.bean.Admin;
import database.bean.Certificate;
import database.bean.Module;
import database.bean.student.Student;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.statistics.CertificateStats;
import database.statistics.ModuleStats;
import database.statistics.StatisticsManager;
import database.statistics.StudentModuleStats;
import database.statistics.StudentStats;
import database.statistics.TableStats;

public class StatsTest
{

    public static void main(String[] args)
    {
	DatabaseManager.setCurrentAdmin( new Admin("Chidiebere", "Fred"));
	Student student = new Student("EMY-C4340");
	Module mod = new Module("Electrical Installation");

	Certificate cert = new Certificate("Electrotechnics");
	StudentStats studStats = null ;//use for graph
	ModuleStats modStats =null;
	CertificateStats certStats = null;
	StudentModuleStats[]  studMods= null ; //use for the table
	TableStats stat = null ;
	try
	{
	    studStats = StatisticsManager.retrieveStats(student);
	    modStats = StatisticsManager.retrieveStats(mod);
	    certStats = StatisticsManager.retrieveStats(cert);
	    stat = StatisticsManager.retrieveStats();
	    studMods = StatisticsManager.retrieveStudentModuleStats(
		    student.getIdCardNumber()); 
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	System.out.println("---Student Stats------");
	System.out.println(studStats);
	System.out.println("------ModuleTabTable Stats---------");
	System.out.println(modStats);
	System.out.println("-------Certificate Stats-----" );
	System.out.println( certStats);
	System.out.println("--------Database Stats--------");
	System.out.println(stat);
	System.out.println(Arrays.toString(studMods)); 

	System.out.println("--------StudentModule Stats--------");
	if(studMods != null ){
	    for( StudentModuleStats studMod : studMods){
		System.out.printf("Date Regisetered: %s%nModule Name: %s%nAmount Paid : %.2f%n"
			+ "Booked: %s%nResult: %s%nPayment Status: %s%n->%n", 
			studMod.getDateRegistered(), studMod.getModuleName(), 
			studMod.getAmountPaid(), studMod.hasBooked(), studMod.getResult(), 
			studMod.isPaymentComplete()) ; 


	    }
	}else{
	    System.out.println("The student has not registered for any Module");
	}

	ConnectionManager.close();
    }

}
