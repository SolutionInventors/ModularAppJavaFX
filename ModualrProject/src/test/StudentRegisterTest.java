package test;

import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;

import database.bean.Admin;
import database.bean.student.Biodata;
import database.bean.student.EducationalBackground;
import database.bean.student.MeanOfDiscovery;
import database.bean.student.Phone;
import database.bean.student.ProfessionalExperience;
import database.bean.student.Student;
import database.bean.student.StudentData;
import database.managers.DatabaseManager;
import database.managers.StudentManager;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import utils.ValidationType;

public class StudentRegisterTest
{

    public static void main(String[] args) throws InvalidBeanException, SQLException, InvalidAdminException
    {
	DatabaseManager.setCurrentAdmin(new Admin("Chidiebere", "Fred"));
	final String studentId = "ETY-C3";
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Select the image of the student");
	chooser.showOpenDialog(null);
	File image = chooser.getSelectedFile();
	Student student = new Student(studentId, "Stream 2", "email@email.com", image);
	
	DateFormat df =  new SimpleDateFormat( "dd-mm-yyyy" );
	Date birth = null;
	try
	{
	    birth = new Date( df.parse( "10-10-2012").getTime());
	}
	catch (ParseException e)
	{
	   e.printStackTrace();
	}

	Biodata bio = new Biodata(studentId, "Ogu", "Chi", "Joe", "Anam",
		"Nig", "Lagos", "Lagos", "M", birth, "Lagos", "Catholic", "Mr");
	
	System.out.println("-------Creating Educational Background object------");
	ArrayList<EducationalBackground> educationList = new ArrayList<>();

	System.out.println("Input first School you attended" );
	EducationalBackground eduBack = getEducationBackground(studentId, df);
	educationList.add( eduBack);

	System.out.println("Do you want to input information about the next school you attended");
	String inputNext =  TestUtils.getStringInput("Input 1 if yes else quit");
	while( inputNext.trim().equals( "1"))
	{
	    eduBack = getEducationBackground(studentId, df);
	    educationList.add(eduBack);
	    System.out.println("Do you want to input information about the next school you attended");
	    inputNext =  TestUtils.getStringInput("Input 1 if yes else quit");
	}


	if( educationList.stream().allMatch(e->e.isValid(ValidationType.NEW_BEAN)))
	    System.out.println("EducationBacground object can be inputed into the database" );
	else
	    throw new InvalidBeanException("EducationBackground object is invalid");

	System.out.println("-----Creating MeansOfDiscovery objects------" );
	ArrayList<MeanOfDiscovery> meanList = new ArrayList<>();
	MeanOfDiscovery discObj = getMeansOfDiscovery(studentId);
	meanList.add( discObj);

	System.out.println("Is there any other way you heard of the program" );
	inputNext = TestUtils.getStringInput("Input 1 to if so else 0 " );
	while( inputNext.equals( "1" ) ){
	    discObj = getMeansOfDiscovery(studentId);
	    meanList.add(discObj);
	    System.out.println("Is there any other way you heard of the program" );
	    inputNext = TestUtils.getStringInput("Input 1 to if so else 0 " );

	}

	if( meanList.stream().allMatch(mean->mean.isValid( ValidationType.NEW_BEAN) ) )	
	    System.out.println("All inputed discovery means are valid" );
	else
	    throw new InvalidBeanException("A means of discvory was invalid" );

	System.out.println("---Creating phone number objects for the Student------" );
	String number = TestUtils.getStringInput("Input phone number" );
	Phone phone = new Phone(studentId, number);
	Set<Phone> phoneSet = new HashSet<>();
	phoneSet.add(phone);
	System.out.println("Are there more phone numbers" );
	inputNext = TestUtils.getStringInput("Input 1 if there are more: " );
	while( inputNext.trim().equals( "1" ) )
	{
	    number = TestUtils.getStringInput("Input phone number" );
	    phone = new Phone(studentId, number);
	    phoneSet.add(phone);
	    System.out.println("Are there more phone numbers" );
	    inputNext = TestUtils.getStringInput("Input 1 if there are more: " );

	}

	if( phoneSet.stream().allMatch( pNum-> pNum.isValid(ValidationType.NEW_BEAN) ) )
	    System.out.println("All the phone numbers format are valid" );
	else
	    throw new InvalidBeanException("At least one phone number was invalid");

	System.out.println("-------Creating ProfessionalExperience object------" );
	System.out.println("Have you worked before? " );
	inputNext = TestUtils.getStringInput("Input 1 if you have: ");
	if( inputNext.equals( "1" ) )
	    System.out.println("input informations about your first job" );
	ProfessionalExperience exp;
	ArrayList<ProfessionalExperience> expList = new ArrayList<>();
	while( inputNext.equals("1") ){
	    exp = getExperience(studentId, df);
	    expList.add( exp);
	    System.out.println("Have you worked anywhere else?" );
	    inputNext = TestUtils.getStringInput("Input 1 if so: " );
	}

	if( expList.stream().allMatch( ex->ex.isValid( ValidationType.NEW_BEAN) ) )
	    System.out.println("The ProfessionalExperience object is valid" );
	else
	    throw new InvalidBeanException("At least on Experience in the list is invalid" );
	EducationalBackground[] eduArray = 
		educationList.toArray(new EducationalBackground[ educationList.size()] );
	Phone[] phoneArray = phoneSet.toArray(new Phone[phoneSet.size()] );
	ProfessionalExperience[] expArray = expList.toArray( new ProfessionalExperience[ expList.size()] );
	MeanOfDiscovery[] meanArr  = meanList.toArray(new MeanOfDiscovery[meanList.size()] );
	StudentData studentData = 
		new StudentData(bio, eduArray,phoneArray,expArray, meanArr);
	
	
	if( StudentManager.registerStudent(student, studentData))
	    System.out.println("The new Student was registered successfully" );
	else
	    System.err.println("Student not registered for some reason" );
	
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
	String beginString = "10-10-2001";
	String endString = "10-10-2007";
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
