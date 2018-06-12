package test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import database.bean.Admin;
import database.bean.Certificate;
import database.bean.CertificateRegister;
import database.bean.ModularClass;
import database.bean.Module;
import database.bean.ModuleRegister;
import database.bean.student.EducationalBackground;
import database.bean.student.MeanOfDiscovery;
import database.bean.student.Phone;
import database.bean.student.ProfessionalExperience;
import database.bean.student.Sponsor;
import database.managers.CertificateManager;
import database.managers.CertificateRegisterManager;
import database.managers.ModularClassManager;
import database.managers.ModuleManager;
import database.managers.ModuleRegisterManager;
import database.managers.PaymentManager;
import database.managers.PhoneManager;
import exception.InvalidAdminException;
import utils.BeanType;
import utils.OrderBy;

public class TestUtils
{

    /**
     * @param studentId
     * @return
     */
    public static Sponsor getSponsor(final String studentId)
    {
	System.out.println( "-------INPUT SPONSOR INFORMATIONS--- ");
	String fName = TestUtils.getStringInput("Input Sponsor First Name: ");
	String lName = TestUtils.getStringInput("Input Sponsor Last Name: ");
	String address = TestUtils.getStringInput("Imput Sponsor Address: " );
	String telephone =  TestUtils.getStringInput("Input Sponsor's Phone number: ");
	String mail = TestUtils.getStringInput("Input Sponsor's email address"	);
	Sponsor sponsor = new Sponsor(studentId, fName, lName, address, telephone, mail);
	return sponsor;
    }

    

    /**
     * @param studId
     * @param df
     * @return
     */
    public static ProfessionalExperience getExperience(String studId, DateFormat df)
    {
	ProfessionalExperience exp;
	String employer = TestUtils.getStringInput("Who eas your employer? : ");
	String jobTitle = TestUtils.getStringInput("What was your job title? : ");

	Date startDate = null ;
	Date endDate = null;
	try
	{
	    startDate = new Date( df.parse("10-10-2000").getTime());
	    endDate = new Date(df.parse( "10-10-2009").getTime());
	}
	catch (ParseException e1)
	{
	    e1.printStackTrace();
	}


	ArrayList<String> duties = new ArrayList<>();
	System.out.println("Input one of your role in the job");
	String duty = TestUtils.getStringInput("Input a role: ");
	duties.add( duty);
	System.out.println("Is there a next role" );

	String nextDuty = TestUtils.getStringInput("Input 1 for yes else no");
	while( nextDuty.equals("1") ){
	    duty = TestUtils.getStringInput("Input next role: ");
	    duties.add( duty);
	    System.out.println("Is there a next role?" );
	    nextDuty = TestUtils.getStringInput("Input 1 for yes else no");

	}
	exp = new ProfessionalExperience(studId, startDate,
		endDate, jobTitle, employer, duties.toArray( new String[duties.size()]));
	return exp;
    }

    
    public static String getStringInput( String prompt ){
	System.out.print( prompt );
	String userInput = null ;
	@SuppressWarnings("resource")
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
		ModularClass[] modClasses  =  ModularClassManager.getClasses(startIndex);
		for ( int i = 0 ; i < modClasses.length ; i++ ){
		    System.out.println( (i+1) + ". " + modClasses[i].getName() + "(" + 
			    modClasses[i].getDateCreated() + ")");
		}

		break;
	    case CERTIFICATE:
		Certificate[] certs  =  CertificateManager.getCertificates
					(startIndex, OrderBy.DATE_CREATED_ASC);
		for ( int i = 0 ; i < certs.length ; i++ ){

		    System.out.println( (i+1) + ". " + certs[i].getName() + "(" + 
			    certs[i].getDateCreated() + ")");
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
		ModuleRegister[] modRegs  =  ModuleRegisterManager.getRegisteredModules(0);
		for ( int i = 0 ; i < modRegs.length ; i++ ){
		    System.out.println("ModRegID: " + modRegs[i].getId());
		    System.out.println("ModuleTabTable Name: " + modRegs[i].getModuleName()); 	
		    System.out.println("StudentID: " + modRegs[i].getStudentId());
		    System.out.println("AmountPerUnit: " + modRegs[i].getAmountPerUnit());
		    System.out.println("Amount Paid: "+ PaymentManager.getAmountPaid(modRegs[i].getId()));
		    System.out.println("Has Paid: " + modRegs[i].paymentComplete());
		    System.out.println("Booked: " + modRegs[i].hasBooked());
		    System.out.println("Attended: " + modRegs[i].hasAttended());
		    System.out.println("Number of units: " + modRegs[i].getNumberOfUnits());
		    System.out.println("Result: " + modRegs[i].getResult());
		    
		    System.out.println("--->");
		}
		break;
	    case PAYMENT:
		break;
	    
	    case STUDENT:
		break;
	    default:
		break;


	}
	System.out.println();
    }

    /**
     * @param studentId
     * @return
     */
    public static MeanOfDiscovery getMeansOfDiscovery(String studentId)
    {
	String means = TestUtils.getStringInput("What is the first means by which you heard of the program ");
	MeanOfDiscovery discObj= new MeanOfDiscovery(studentId,  means);
	return discObj;
    }


    public static EducationalBackground getEducationBackground(String studId, DateFormat df)
    {
	String institution = TestUtils.getStringInput("Input Institution: ");
	String beginString = TestUtils.getStringInput("Input begin date in format(dd-mm-yyyy): ");
	String endString = TestUtils.getStringInput("Input end date in format(dd-mm-yyyy): ");
	String course = TestUtils.getStringInput("Input the course you read : ");
	String qualification = TestUtils.getStringInput("Input qualification received: ");

	Date begin = null;
	Date end = null;
	try
	{
	    begin = new Date( df.parse( beginString).getTime() );
	    end =  new Date(  df.parse(endString).getTime());
	}
	catch (ParseException e)
	{
	   e.printStackTrace();
	}
	EducationalBackground eduBack = new EducationalBackground(studId, begin, 
		end, institution, course, qualification);
	return eduBack;
    }
    
}
