package test;

import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

public class StudentTest
{

    public static void main(String[] args) throws InvalidBeanException, SQLException, InvalidAdminException
    {
	DatabaseManager.setCurrentAdmin(new Admin( "Chidiebere", "Fred" ) );
	if( !DatabaseManager.validateAdmin())
	    throw new InvalidAdminException("The current Admin is invalid");
	System.out.println("--------REGISTERING A NEW STUDENT TEST---------");

	System.out.println("You'll need to fill the registeration form" ); 

	System.out.println("\n----Creating Student Object---" );
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Select the image of the student");
	chooser.showOpenDialog(null);
	
	final String studentId = TestUtils.getStringInput("Input Student Id card number: ");
	String mail =  TestUtils.getStringInput("Input the student emial address: ");
	String className = TestUtils.getStringInput("Input the student class: ");
	System.out.println("Select File" );

	File image = chooser.getSelectedFile();


	Student student = new Student(studentId, className, mail, image);

	if( student.isValid(ValidationType.NEW_BEAN)) 
	    System.out.println("The Student object is valid" );
	else
	    throw new InvalidBeanException("The Student is not valid");

	System.out.println("\n----Creating Biodata object------");
	String surname = TestUtils.getStringInput("Input Student Surname:" );
	String midName = TestUtils.getStringInput("Input Student MiddleName: ");
	String lName= TestUtils.getStringInput("Input Student last name: ");
	String state = TestUtils.getStringInput("Input the student state of origin: ");
	String country = TestUtils.getStringInput("Input Country of origin: ") ;
	String currAddr = TestUtils.getStringInput("Input current address: ");
	String permAddr = TestUtils.getStringInput("Input Permanent address: ");
	String gender = TestUtils.getStringInput("Input gender: ");
	String birthString = TestUtils.getStringInput("Input birth date in the format(dd-mm-yyyy): ");
	String birthPlace = TestUtils.getStringInput("Input Place of birth: ");
	String religion = TestUtils.getStringInput("What's your religion? " );
	String title = TestUtils.getStringInput("input your title(eg Mr, Mrs, Dr etc): ");
	DateFormat df =  new SimpleDateFormat( "dd-mm-yyyy" );
	Date birth = null;
	try
	{
	    birth = new Date( df.parse( birthString).getTime());
	}
	catch (ParseException e)
	{
	   e.printStackTrace();
	}

	Biodata bio = new Biodata(studentId, surname, midName, lName, state, country, 
		currAddr, permAddr, gender, birth, birthPlace, religion, title);

	if( bio.isValid(ValidationType.NEW_BEAN)) 	
	    System.out.println("The bio data is valid" );
	else
	    throw new InvalidBeanException("The bio data object is invalid" );

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
	ArrayList<Phone> phoneList = new ArrayList<>();
	phoneList.add(phone);
	System.out.println("Are there more phone numbers" );
	inputNext = TestUtils.getStringInput("Input 1 if there are more: " );
	while( inputNext.trim().equals( "1" ) )
	{
	    phone = new Phone(studentId, number);
	    phoneList.add(phone);
	    System.out.println("Are there more phone numbers" );
	    inputNext = TestUtils.getStringInput("Input 1 if there are more: " );

	}

	if( phoneList.stream().allMatch( pNum-> pNum.isValid(ValidationType.NEW_BEAN) ) )
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
	Phone[] phoneArray = phoneList.toArray(new Phone[phoneList.size()] );
	ProfessionalExperience[] expArray = expList.toArray( new ProfessionalExperience[ expList.size()] );
	StudentData studentData = 
		new StudentData(bio, eduArray,phoneArray,expArray);
	
	
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
	String endString = TestUtils.getStringInput("What date did you end in format( dd-mm-yyyy)")	;
	String startString = TestUtils.getStringInput("Input the start date in format(dd-mm-yyyy): " );

	Date startDate = null ;
	Date endDate = null;
	try
	{
	    startDate = new Date( df.parse(startString).getTime());
	    endDate = new Date(df.parse( endString).getTime());
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
