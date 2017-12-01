package test;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;

import database.bean.Admin;
import database.bean.student.Biodata;
import database.bean.student.EducationalBackground;
import database.bean.student.Student;
import database.managers.DatabaseManager;
import exception.InvalidBeanException;
import utils.ValidationType;

public class StudentTest
{

    public static void main(String[] args) throws InvalidBeanException
    {
	DatabaseManager.setCurrentAdmin(new Admin( "Chidiebere", "Fred" ) );
	System.out.println("--------REGISTERING A NEW STUDENT TEST---------");
	
	System.out.println("You'll need to fill the registeration form" ); 
	
	System.out.println("\n----Creating Student Object---" );
	JFileChooser chooser = new JFileChooser();
	chooser.showOpenDialog(null);
	chooser.setDialogTitle("Select the image of the student");
	
	String studentId = TestUtils.getStringInput("Input Student Id card number: ");
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
	String midName = TestUtils.getStringInput("Input Student MiddleName");
	String lName= TestUtils.getStringInput("Input Student last name: ");
	String studId = TestUtils.getStringInput("Input Student ID CARD Number: ");
	String state = TestUtils.getStringInput("Input the student state of origin: ");
	String country = TestUtils.getStringInput("Input Country of origin: ") ;
	String currAddr = TestUtils.getStringInput("Input current address: ");
	String permAddr = TestUtils.getStringInput("Input Permanent address: ");
	String gender = TestUtils.getStringInput("Input gender: ");
	String birthString = TestUtils.getStringInput("Input birth date in the format(dd-mm-yyyy): ");
	String birthPlace = TestUtils.getStringInput("Input Place of birth: ");
	String religion = TestUtils.getStringInput("What's your religion? " );
	DateFormat df =  new SimpleDateFormat( "dd-mm-yyyy" );
	Date birth = null;
	try
	{
	    birth = new Date( df.parse( birthString).getTime());
	}
	catch (ParseException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	Biodata bio = new Biodata(studId, surname, midName, lName, state, country, 
		currAddr, permAddr, gender, birth, birthPlace, religion);
	
	if( bio.isValid(ValidationType.NEW_BEAN)) 	
	    System.out.println("The bio data is valid" );
	else
	   throw new InvalidBeanException("The bio data object is invalid" );
	
	System.out.println("-------Creating Educational Background obbject------");
	
//	
//	EducationBackground eduBack = new EducationalBackground(studId, 
//		begin, end, institution, course, qualification);
    
    }

}
