package test;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

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

	Instant begin = Instant.now();
	Certificate[] certs = CertificateManager.getCertificates(0, OrderBy.DATE_CREATED_DESC);
	System.out.println("-------List of Certificates----------" );
	for (int i = 0; i < certs.length; i++)
	{
	    System.out.println("Cert Name:" +certs[i].getName());
	}

	String certName = TestUtils.getStringInput("Input Certificate Name from list: ");
	String[] modules = CertificateRegisterManager.getModulesRequired(certName); 
	System.out.printf("-------List of  Modules required for  %s ----------" , certName);
	for (String module : modules)
	{
	    System.out.println(module);

	}

	System.out.println("->");
	Module[] mods = ModuleManager.getModules(0); 
	System.out.println("-------List of  Modules----------" );
	for (Module mod : mods)
	{
	    System.out.println("Date Created: " + mod.getDateCreated());
	    System.out.println("Name: " +mod.getName());
	    System.out.println("AmountPerUnit: " +mod.getAmountPerUnit());
	    System.out.println("Number of units " + mod.getNumberOfUnits());
	    System.out.println("->");
	}

	ModularClass[] modClasses =  ModularClassManager.getClasses(0); 
	System.out.println("-------List of  Classes----------" );
	for (ModularClass modClass:  modClasses)
	{
	    System.out.println("Class Name: " +modClass.getName());
	    System.out.println("Date Created: " +modClass.getDateCreated()); 
	    System.out.println("->");
	}


	ModuleRegister[] modRegs = ModuleRegisterManager.getRegisteredModules(0);
	System.out.println("-------List of  Mod Registers----------" );
	for (ModuleRegister modReg : modRegs)
	{
	    System.out.println("Date Registered: " + modReg.getDateRegistered());
	    System.out.println("Mod Name: "+ modReg.getModuleName());
	    System.out.println(modReg.getStudentImage());
	    System.out.println(modReg.getStudentId());
	    System.out.println("--->");
	}

	Student[] studs = StudentManager.getStudents(true, 0);
	System.out.println("-------List of  Active Students----------" );
	for (Student stud  : studs)
	{
	    System.out.println( "ID: " + stud.getIdCardNumber());
	    System.out.println("Image Exists: " + stud.getImage());
	}

	studs = StudentManager.getStudents(false, 0);
	System.out.println("-------List of  inactive Students----------" );
	for (Student stud  : studs)
	{
	    System.out.println( "ID: " + stud.getIdCardNumber());
	}
	Instant end = Instant.now(); 

	Duration diff = Duration.between(begin, end); 
	System.out.println(diff.toMillis());

    }

}
