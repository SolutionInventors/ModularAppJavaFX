package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.bean.Bean;
import database.bean.CertificateRegister;
import database.managers.CertificateRegisterManager;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import exception.InvalidAdminException;
import utils.BeanType;
import utils.ValidationType;

public class CertificateRegisterTest
{

    public static void main(String[] args)
    {
	//	First and very important step is to specify the Admin that is 
	//	making the change as follows

	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	DatabaseManager.setCurrentAdmin(currentAdmin); 	

	//	Rest of the code
	try
	{
	    TestUtils.displayBean( BeanType.CERTIFICATE_MODULE , 0);

	    System.out.println("---------------ADDING A MODULE FROM CERTIFICATE REQUIREMENT--------------");
	    String certName = TestUtils.getStringInput
		    ("Enter the existing certificate name: ");
	    String modName = TestUtils.getStringInput
		    ("Enter the existing module name: ");
	    CertificateRegister certMod = new CertificateRegister(certName, modName);

	    System.out.println( "validCertName: " + certMod.validateCertificateName());
	    System.out.println( "validModName: " + certMod.validateModuleName());
	    System.out.println( "contains: " + Bean.containsEitherAlphaNum( certMod.getModuleName())  );
	    System.out.println( "First Lettr: " + certMod.getModuleName().substring(0,1).matches("[A-Za-z]"));;
	    if( CertificateRegisterManager.addModuleToCertificate( certMod)) {
		System.out.println("Successfully created a new certificate and  "
			+ "also gave updated the dateCreated attribute.");
		TestUtils.displayBean( BeanType.CERTIFICATE_MODULE , 0);

	    }
	    else if( !certMod.isValid(ValidationType.NEW_BEAN))
		System.err.println( "The format of the Certificate was invalid" );
	    else
	    {
		System.out.println("Was Unsuccessful for unknown reasons!!!");
	    }




	    System.out.println("---------------REMOVE MODULE FROM A CERTIFICATE'S REQUIREMENT--------------");
	    certName = TestUtils.getStringInput
		    ("Enter the existing certificate name: ");
	    modName = TestUtils.getStringInput
		    ("Enter the existing module name: ");
	    certMod = new CertificateRegister(certName, modName);


	    if( CertificateRegisterManager.removeModuleFromCertificate(certMod)){
		System.out.println( "CertModule was removed succcessfullly!!!");
		TestUtils.displayBean( BeanType.CERTIFICATE_MODULE , 0);
	    }
	    else if( !certMod.isValid(ValidationType.EXISTING_BEAN) ){
		System.err.println( "The format of the Modular Class was invalid" );
	    }
	    else
	    {
		System.out.println("Nothing was removed! "
			+ "Maybe the Certificate name you inputed is not in the database");
	    }


	}
	catch ( InvalidAdminException e)
	{
	    System.err.println( e );
	    System.err.println("The Admin was invalid");
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	finally{
	    //	    This is also very important. Close the ConnectionManager
	    ConnectionManager.close();
	}
    }



}
