package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.bean.Certificate;
import database.bean.Module;
import database.bean.student.Student;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.statistics.CertificateStats;
import database.statistics.ModuleStats;
import database.statistics.StatisticsManager;
import database.statistics.StudentStats;
import database.statistics.TableStats;

public class StatsTest
{

    public static void main(String[] args)
    {
	DatabaseManager.setCurrentAdmin( new Admin("Chidiebere", "Fred"));
	Student student = new Student("EYY-C232");
	Module mod = new Module("Motor Studies");
	Certificate cert = new Certificate("Electrotechnics");
	StudentStats studStats = null ;
	ModuleStats modStats =null;
	CertificateStats certStats = null;
	TableStats stat = null ;
	try
	{
	    studStats = StatisticsManager.retrieveStats(student);
	    modStats = StatisticsManager.retrieveStats(mod);
	    certStats = StatisticsManager.retrieveStats(cert);
	    stat = StatisticsManager.retrieveStats();
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	System.out.println("---Student Stats------");
	System.out.println(studStats);
	System.out.println("------Module Stats---------");
	System.out.println(modStats);
	System.out.println("-------Certificate Stats-----" );
	System.out.println( certStats);
	System.out.println("--------Database Stats--------");
	System.out.println(stat);
	ConnectionManager.close();
    }

}
