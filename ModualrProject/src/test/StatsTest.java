package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.bean.Module;
import database.bean.log.ModuleLog;
import database.bean.student.Student;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.statistics.ModuleStats;
import database.statistics.StatisticsManager;
import database.statistics.StudentStats;

public class StatsTest
{

    public static void main(String[] args)
    {
	DatabaseManager.setCurrentAdmin( new Admin("Chidiebere", "Fred"));
	Student student = new Student("EYY-C3");
	Module mod = new Module("Electrical Installation");
	StudentStats studStats = null ;
	ModuleStats modStats =null;
	try
	{
	    studStats = StatisticsManager.retrieveStats(student);
	    modStats = StatisticsManager.retrieveStats(mod);
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
	
	ConnectionManager.close();
    }

}
