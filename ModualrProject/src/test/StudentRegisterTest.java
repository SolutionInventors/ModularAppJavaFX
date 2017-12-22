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
import database.managers.DatabaseManager;
import database.managers.StudentManager;
import exception.InvalidAdminException;
import utils.ValidationType;

public class StudentRegisterTest
{

    public static void main(String[] args) throws  SQLException, InvalidAdminException, ParseException
    {
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat" );
	DatabaseManager.setCurrentAdmin(currentAdmin);
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Select the image of the student");
	chooser.showOpenDialog(null);
	File image = chooser.getSelectedFile();
	final String studentId =TestUtils.getStringInput("Input Student id: ");

	Student student = new Student(studentId, "Stream 2", "email@email.com", image);

	DateFormat df =  new SimpleDateFormat( "dd-mm-yyyy" );
	Date birth = null;
	Date begin = null;
	Date end = null;
	birth = new Date( df.parse( "10-10-2012").getTime());
	begin = new Date( df.parse( "10-10-2010").getTime());
	end = new Date( df.parse( "10-10-2016").getTime());


	Biodata bio = new Biodata(studentId, "Ogu", "Chi", "Joe", "Anam",
		"Nig", "Lagos", "Lagos", "M", birth, "Lagos", "Catholic", "Mr");

	System.out.println("-------Creating Educational Background object------");
	Set<EducationalBackground> educationSet = new HashSet<>();

	System.out.println("Input first School you attended" );

	String institution = TestUtils.getStringInput("Enter institution: ");
	String course = TestUtils.getStringInput("What did you study: " );
	String cert = TestUtils.getStringInput("What certificate did you receive: ");
	EducationalBackground eduBack= new EducationalBackground(studentId, begin, end, institution, course, cert);
	educationSet.add(eduBack);educationSet.add( eduBack);

	System.out.println("Do you want to input information about the next school you attended");
	String inputNext =  TestUtils.getStringInput("Input 1 if yes else quit");
	while( inputNext.trim().equals( "1"))
	{
	    institution = TestUtils.getStringInput("Enter institution: ");
	    course = TestUtils.getStringInput("What did you study: " );
	    cert = TestUtils.getStringInput("What certificate did you receive: ");
	    eduBack= new EducationalBackground(studentId, begin, end, institution, course, cert);
	    educationSet.add(eduBack);
	    System.out.println("Do you want to input information about the next school you attended");
	    inputNext =  TestUtils.getStringInput("Input 1 if yes else quit");
	}


	if( educationSet.stream().allMatch(e->e.isValid(ValidationType.NEW_BEAN)))
	    System.out.println("EducationBacground object can be inputed into the database" );
	else{
	    System.err.println("The EducationBackground is not valid" );
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
	    System.err.println("At least one phone number was invalid");
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
	    System.err.println("At least on Experience object in the list is invalid" );
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
		educationSet.toArray(new EducationalBackground[ educationSet.size()] );
	Phone[] phoneArray = phoneSet.toArray(new Phone[phoneSet.size()] );
	ProfessionalExperience[] expArray = expList.toArray( new ProfessionalExperience[ expList.size()] );
	MeanOfDiscovery[] meanArr  = meanList.toArray(new MeanOfDiscovery[meanList.size()] );


	StudentData studentData = 
		new StudentData(bio, eduArray,phoneArray,expArray, meanArr, sponsArray);


	if( StudentManager.registerStudent(student, studentData))
	    System.out.println("The new Student was registered successfully" );
	else
	    System.err.println("Student not registered for some reason" );

    }







}
