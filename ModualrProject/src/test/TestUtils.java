package test;

import java.sql.SQLException;
import java.util.Scanner;

import database.bean.Admin;
import database.bean.Certificate;
import database.bean.CertificateModule;
import database.bean.ModularClass;
import database.bean.Module;
import database.managers.BeanType;
import database.managers.CertificateManager;
import database.managers.CertificateModuleManager;
import database.managers.ClassManager;
import database.managers.ModuleManager;
import exception.InvalidAdminException;

public class TestUtils
{

    public static String getStringInput( String prompt ){
	System.out.print( prompt );
	String userInput = null ;
	Scanner input = new Scanner( System.in) ;

	userInput = input.nextLine();

	return userInput;
    }

    public static void displayAll(Admin[] allAdmin)
    {
	System.out.println("----------------------------------\n");
	System.out.println("Admins:");
	for ( int i = 0 ; i < allAdmin.length ; i++ ){
	    System.out.println( "Username: " + allAdmin[i].getUsername() + " " + 
		    "Password: " + allAdmin[i].getPassword());
	}
	System.out.println("\n----------------------------------");

    }

    public static void displayBean( BeanType type, int startIndex ) throws SQLException, InvalidAdminException
    {
	System.out.println();
	switch (type)
	{
	    case MODULAR_CLASS:
		ModularClass[] modClasses  =  ClassManager.getClasses(startIndex);
		for ( int i = 0 ; i < modClasses.length ; i++ ){
		    System.out.println( (i+1) + ". " + modClasses[i].getName() + "(" + 
			    modClasses[i].getDateCreated() + ")");
		}

		break;
	    case CERTIFICATE:
		Certificate[] certs  =  CertificateManager.getCertificates(startIndex);
		for ( int i = 0 ; i < certs.length ; i++ ){

		    System.out.println( (i+1) + ". " + certs[i].getName() + "(" + 
			    certs[i].getDateCreated() + ")");
		}

		break;
	    case CERTIFICATE_MODULE:
		CertificateModule[] certMod  = 
		CertificateModuleManager.getCertificateModules(0);
		for ( int i = 0 ; i < certMod.length ; i++ ){

		    System.out.println( "*\tCert Name --->" + certMod[i].getCertificateName()+ 
			    "\n\tMod Name --->" + certMod[i].getModuleName());
		}

		break;
	    case MODULE:

		Module[] modules = ModuleManager.getModules( 0 );
		for ( int i = 0 ; i < modules.length ; i++ ){

		    System.out.println( "*\tMod Name: " + 
		    modules[i].getName() + " \n\tDate Created: " + modules[i].getDateCreated()+
		    "\n\tUnits: " + modules[i].getNumberOfUnits() +
		    "\n\tAmount Per Unit: " + modules[i].getAmountPerUnit() +"\n");
		}


		break;
	    case BIODATA:
		break;


	    case MODULE_REGISTER:
		break;
	    case PAYMENT:
		break;
	    case PHONE:
		break;
	    case STUDENT:
		break;
	    default:
		break;


	}
	System.out.println();
    }

}
