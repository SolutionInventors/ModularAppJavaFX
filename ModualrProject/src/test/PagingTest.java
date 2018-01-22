package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.bean.Certificate;
import database.bean.CertificateRegister;
import database.bean.ModularClass;
import database.bean.Module;
import database.bean.ModuleRegister;
import database.bean.student.Student;
import database.managers.CertificateManager;
import database.managers.CertificateRegisterManager;
import database.managers.DatabaseManager;
import database.managers.ModularClassManager;
import database.managers.ModuleManager;
import database.managers.ModuleRegisterManager;
import database.managers.StudentManager;
import exception.InvalidAdminException;
import utils.OrderBy;

public class PagingTest
{

    public static void main(String[] args) throws SQLException, InvalidAdminException
    {
	DatabaseManager.setCurrentAdmin(new Admin("Chidiebere", "Fred" ));
	
	Certificate[] certs = CertificateManager.getCertificates(0, OrderBy.DATE_CREATED_DESC);
	System.out.println("-------List of Certificates----------" );
	for (int i = 0; i < certs.length; i++)
	{
	    System.out.println("Cert Name:" +certs[i].getName());
	}
	
	CertificateRegister[] certsMods = CertificateRegisterManager.getCertificateRegisters(0);
	System.out.println("-------List of Certificates Modules----------" );
	for (int i = 0; i < certsMods.length; i++)
	{
	    System.out.println("Cert Name: " +certsMods[i].getCertificateName());
	    System.out.println("Required Module Name: " +certsMods[i].getModuleName());
	    System.out.println("->");
	}
	
	
	Module[] mods = ModuleManager.getModules(0); 
	System.out.println("-------List of  Modules----------" );
	for (int i = 0; i < mods.length; i++)
	{
	    System.out.println("Date Created: " + mods[i].getDateCreated());
	    System.out.println("Name: " +mods[i].getName());
	    System.out.println("AmountPerUnit: " +mods[i].getAmountPerUnit());
	    System.out.println("Number of units " + mods[i].getNumberOfUnits());
	    System.out.println("->");
	}
	
	ModularClass[] modClass =  ModularClassManager.getClasses(0); 
	System.out.println("-------List of  Classes----------" );
	for (int i = 0; i < modClass.length; i++)
	{
	   System.out.println("Class Name: " + modClass[i].getName());
	   System.out.println("Date Created: " + modClass[i].getDateCreated()); 
	    System.out.println("->");
	}
	
	
	ModuleRegister[] modRegs = ModuleRegisterManager.getRegisteredModules(0);
	System.out.println("-------List of  Mod Registers----------" );
	for (int i = 0; i < modRegs.length; i++)
	{
	    System.out.println("Date Registered: " + modRegs[i].getDateRegistered());
	    System.out.println("Mod Name: "+ modRegs[i].getModuleName());
	}
	
	Student[] studs = StudentManager.getStudents(true, 0);
	System.out.println("-------List of  Active Students----------" );
	for (int i = 0; i < studs.length; i++)
	{
	   System.out.println( "ID: " + studs[i].getIdCardNumber());
	   System.out.println("Image Exists: " + studs[i].getImage());
	}
	
	studs = StudentManager.getStudents(false, 0);
	System.out.println("-------List of  inactive Students----------" );
	for (int i = 0; i < studs.length; i++)
	{
	   System.out.println( "ID: " + studs[i].getIdCardNumber());
	}
	
	
    }

}
