package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.bean.Certificate;
import database.managers.CertificateManager;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import exception.InvalidAdminException;
import utils.BeanType;
import utils.ValidationType;

public class CertificateTest
{

    public static void main(String[] args)
    {
	//	First and very important step is to specify the Admin that is 
	//	making the change as follows

	Admin currentAdmin = new Admin("Chidiebere", "Fred" );
	DatabaseManager.setCurrentAdmin(currentAdmin); 	

	//	Rest of the code
	try{
	    TestUtils.displayBean( BeanType.CERTIFICATE , 0);

	    System.out.println("---------------CREATING A NEW CERTIFICATE TEST--------------");
	    String name = TestUtils.getStringInput("Enter the new certificate name: ");

	    Certificate certificate = new Certificate(name );

	    if( CertificateManager.createCertificate( certificate)) {
		System.out.println("Successfully created a new certificate and  "
			+ "also gave updated the dateCreated attribute.");
		TestUtils.displayBean( BeanType.CERTIFICATE,  0);

	    }
	    else
	    {
		System.out.println("Was Unsuccessful for unknown reasons!!!");
	    }

	    System.out.println("---------------REMOVING AN EXISTING CERTIFICATE TEST--------------");
	    name = TestUtils.getStringInput("Enter the new Certificate name: ");

	    certificate = new Certificate(name );

	    if( CertificateManager.delete(certificate)){
		System.out.println( "Certificate was removed succcessfullly!!!");
		TestUtils.displayBean( BeanType.CERTIFICATE , 0);
	    }
	    else
	    {
		System.out.println("Nothing was removed! "
			+ "Maybe the Certificate name you inputed is not in the database");
	    }


	    System.out.println("---------------UPDATING AN EXISTING CERTIFICATE TEST--------------");

	    name = TestUtils.getStringInput("Enter the existing class name: ");
	    String newName = TestUtils.getStringInput("Enter the new class name: ");

	    certificate = new Certificate(name );
	    Certificate newCert = new Certificate( newName);

	    if( CertificateManager.update(certificate, newCert) ){
		System.out.println( "Certificate was updated succcessfullly!!!");
		TestUtils.displayBean( BeanType.CERTIFICATE , 0);

	    }
	    else if( newCert.isValid(ValidationType.EXISTING_BEAN) && 
		    certificate.isValid(ValidationType.NEW_BEAN) ){
		System.err.println("Object format is invalid" );
	    }
	    else
	    {
		System.out.println("Nothing was updated! "
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
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	finally{
	    //	    This is also very important. Close the ConnectionManager
	    ConnectionManager.close();
	}
    }


}
