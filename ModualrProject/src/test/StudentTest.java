package test;

import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;

import database.bean.Admin;
import database.bean.student.Biodata;
import database.bean.student.EducationalBackground;
import database.bean.student.MeanOfDiscovery;
import database.bean.student.Phone;
import database.bean.student.ProfessionalExperience;
import database.bean.student.Sponsor;
import database.bean.student.Student;
import database.bean.student.StudentData;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.managers.StudentManager;
import exception.InvalidAdminException;
import utils.ValidationType;

public class StudentTest
{

    public static void main(String[] args) throws  SQLException, InvalidAdminException
    {
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	DatabaseManager.setCurrentAdmin(currentAdmin);
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
	else{
	    System.err.println("The Student is not valid");
	    System.exit(0);
	}

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
	else{
	    System.err.println("The bio data object is invalid");
	    System.exit(0);
	}

	System.out.println("-------Creating Educational Background object------");
	ArrayList<EducationalBackground> educationList = new ArrayList<>();

	System.out.println("Input first School you attended" );
	EducationalBackground eduBack = TestUtils.getEducationBackground(studentId, df);
	educationList.add( eduBack);

	System.out.println("Do you want to input information about the next school you attended");
	String inputNext =  TestUtils.getStringInput("Input 1 if yes else quit");
	while( inputNext.trim().equals( "1"))
	{
	    eduBack = TestUtils.getEducationBackground(studentId, df);
	    educationList.add(eduBack);
	    System.out.println("Do you want to input information about the next school you attended");
	    inputNext =  TestUtils.getStringInput("Input 1 if yes else quit");
	}


	if( educationList.stream().allMatch(e->e.isValid(ValidationType.NEW_BEAN)))
	    System.out.println("EducationBacground object can be inputed into the database" );
	else{
	    System.err.println("EducationBackground object is invalid");
	    System.exit(0);
	}

	System.out.println("-----Creating MeansOfDiscovery objects------" );
	ArrayList<MeanOfDiscovery> meanList = new ArrayList<>();
	MeanOfDiscovery discObj = TestUtils.getMeansOfDiscovery(studentId);
	meanList.add( discObj);

	System.out.println("Is there any other way you heard of the program" );
	inputNext = TestUtils.getStringInput("Input 1 to if so else 0 " );
	while( inputNext.equals( "1" ) ){
	    discObj = TestUtils.getMeansOfDiscovery(studentId);
	    meanList.add(discObj);
	    System.out.println("Is there any other way you heard of the program" );
	    inputNext = TestUtils.getStringInput("Input 1 to if so else 0 " );

	}

	if( meanList.stream().allMatch(mean->mean.isValid( ValidationType.NEW_BEAN) ) )	
	    System.out.println("All inputed discovery means are valid" );
	else{
	    System.err.println("A means of discvory was invalid" );
	    System.exit(0);
	}

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
	else{
	    System.err.println("At least one phone number was invalid") ;
	    System.exit(0);
	}

	System.out.println("-------Creating ProfessionalExperience object------" );
	System.out.println("Have you worked before? " );
	inputNext = TestUtils.getStringInput("Input 1 if you have: ");
	if( inputNext.equals( "1" ) )
	    System.out.println("input informations about your first job" );
	ProfessionalExperience exp;
	ArrayList<ProfessionalExperience> expList = new ArrayList<>();
	while( inputNext.equals("1") ){
	    exp = TestUtils.getExperience(studentId, df);
	    expList.add( exp);
	    System.out.println("Have you worked anywhere else?" );
	    inputNext = TestUtils.getStringInput("Input 1 if so: " );
	}

	if( expList.stream().allMatch( ex->ex.isValid( ValidationType.NEW_BEAN) ) )
	    System.out.println("The ProfessionalExperience object is valid" );
	else{
	    System.err.println("At least one Experience in the list  was invalid") ;
	    System.exit(0);
	}

	System.out.println("----Creating the Sponsor aray object--------");
	System.out.println("Do you have any sponsor? ");
	String nextInput = TestUtils.getStringInput("Type 1 if so else no: ");
	List<Sponsor> list = new LinkedList<>();
	while( nextInput.equals("1") ){
	    Sponsor sponsor = TestUtils.getSponsor(studentId);
	    list.add( sponsor);
	    System.out.println("Do you have any other sponsor? ");
	    nextInput = TestUtils.getStringInput("Type 1 if so else no: ");

	}
	Sponsor[] sponsArray = list.toArray(new Sponsor[list.size()] );


	EducationalBackground[] eduArray = 
		educationList.toArray(new EducationalBackground[ educationList.size()] );
	Phone[] phoneArray = phoneSet.toArray(new Phone[phoneSet.size()] );
	ProfessionalExperience[] expArray = expList.toArray( new ProfessionalExperience[ expList.size()] );
	MeanOfDiscovery[] meanArr  = meanList.toArray(new MeanOfDiscovery[meanList.size()] );
	StudentData studentData = 
		new StudentData(bio, eduArray,phoneArray,expArray, meanArr, sponsArray);

	System.out.println( "isStudData valid: " + studentData.isValid(ValidationType.NEW_BEAN));
	if( StudentManager.registerStudent(student, studentData))
	    System.out.println("The new Student was registered successfully" );
	else
	    System.err.println("Student not registered for some reason" );

	ConnectionManager.close();
    }
}
