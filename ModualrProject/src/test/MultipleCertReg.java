package test;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import database.bean.Admin;
import database.managers.CertificateRegisterManager;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import exception.InvalidAdminException;
import utils.BeanType;

public class MultipleCertReg
{
    public static void main(String[] args) throws SQLException, InvalidAdminException
    {
	try{
	    Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	    DatabaseManager.setCurrentAdmin(currentAdmin);
	    System.out.println("----------MODULES---------");
	    TestUtils.displayBean(BeanType.MODULE, 0);
	    System.out.println("---------CERTIFICATES------");
	    TestUtils.displayBean(BeanType.CERTIFICATE, 0);

	    String selectedCert = TestUtils.getStringInput("Input a certificate name: ");

	    viewCertDetails(selectedCert);

	    System.out.println("--------ADDING MULTIPLE CERT TO MODULE------");
	    Set<String> set = new HashSet<>(); 
	    String userInput = TestUtils.getStringInput("Enter Module name or -1 to quit: "); 

	    while(!userInput.equals("-1")){
		set.add(userInput ); 
		userInput = TestUtils.getStringInput("Enter Module name or -1 to quit: "); 

	    }

	    boolean success = CertificateRegisterManager.addMultipleModules(
		    selectedCert, set.toArray(new String[set.size()])); 

	    if(success){
		System.out.printf("Successfully inserted all the modules to %s\n", selectedCert);; 
	    }else{
		System.err.printf("Error occured while inserting some modules to %s\n", selectedCert);; 
	    }

	   
	    viewCertDetails(selectedCert);

	    System.out.println("--------REMOVING MULTIPLE CERT TO MODULE------");
	    
	    set = new HashSet<>(); 
	    userInput = TestUtils.getStringInput("Enter Module name or -1 to quit: "); 

	    while(!userInput.equals("-1")){
		set.add(userInput ); 
		userInput = TestUtils.getStringInput("Enter Module name or -1 to quit: "); 

	    }

	    success = CertificateRegisterManager.removeMultipleModules(
		    selectedCert, set.toArray(new String[set.size()])); 

	    if(success){
		System.out.printf("Successfully inserted all the modules to %s\n", selectedCert);; 
	    }else{
		System.err.printf("Error occured while inserting some modules to %s\n", selectedCert);; 
	    }

	}finally{
	    ConnectionManager.close(); 
	}


    }

    /**
     * @param selectedCert
     * @throws SQLException
     */
    public static void viewCertDetails(String selectedCert) throws SQLException
    {
	String[] moduleRequired = CertificateRegisterManager.getModules(selectedCert, true); 
	String[] moduleNotRequired = CertificateRegisterManager.getModules(selectedCert, false); 

	System.out.printf("--------MODULES REQUIRED FOR %S------\n",selectedCert );
	for (String module : moduleRequired)
	{
	System.out.println(module);
	}

	System.out.printf("\n--------MODULES NOT REQUIRED FOR %S------\n",selectedCert );
	for (String module : moduleNotRequired)
	{
	System.out.println(module);
	}
    }

}
