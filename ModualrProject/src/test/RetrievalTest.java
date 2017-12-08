package test;

import java.sql.SQLException;

import database.bean.Admin;
import database.bean.student.Biodata;
import database.bean.student.EducationalBackground;
import database.bean.student.MeanOfDiscovery;
import database.bean.student.Phone;
import database.bean.student.ProfessionalExperience;
import database.bean.student.Sponsor;
import database.bean.student.Student;
import database.bean.student.StudentData;
import database.managers.BiodataManager;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.managers.DiscoveryManager;
import database.managers.EducationManager;
import database.managers.ExperienceManager;
import database.managers.PhoneManager;
import database.managers.SponsorManager;
import database.managers.StudentManager;
import exception.InvalidAdminException;
import exception.InvalidBeanException;

public class RetrievalTest
{
    public static void main(String[] args) throws InvalidBeanException, InvalidAdminException
    {
	System.out.println( DatabaseManager.getDate());
	    
	DatabaseManager.setCurrentAdmin(new Admin("Chidiebere", "Fred" ));
	Student stud = new Student() ;
	stud.setIdCardNumber("ETY-C3");
	try
	{
	    
	    Biodata data = BiodataManager.getBiodata( stud);
	    System.out.println( "Biodaata country: " +data.getCountry());
	    stud.setIdCardNumber("EYY-C232");
	    ProfessionalExperience[] exp = ExperienceManager.getExpriences(stud);
	    System.out.println("-------Experiences------" );
	    for (ProfessionalExperience ex : exp)
	    {
		System.out.println("Employer: " + ex.getEmployer());
		System.out.println("Job Title: " + ex.getJobTitle());
		System.out.println("-------------------");
	    }
	    System.out.println("-----EducationInfo-----" );
	    EducationalBackground[] eduArray = EducationManager.getEducationInfo(stud);
	    
	    for (EducationalBackground edu : eduArray)
	    {
		System.out.println("Institution:  " + edu.getInstitution());
		System.out.println("CourseRead: " + edu.getCourseRead());
	    }
	    System.out.println( " ------- ");
	    MeanOfDiscovery[] allMeans = DiscoveryManager.getDiscoveryMeans(stud);
	    for (MeanOfDiscovery mean : allMeans)
	    {
		System.out.println("Means: " + mean.getMeans());
	    }
	    
	    Phone[] numbers = PhoneManager.getPhoneNumber(stud.getIdCardNumber());
	    
	    for (Phone phone : numbers)
	    {
		System.out.println(phone.getNumber());
	    }
	    
	    Sponsor[] spons = SponsorManager.getSponsors(stud);
	    for (Sponsor sponsor : spons)
	    {
		System.out.println( sponsor.getFirstName() + " " + sponsor.getLastName());
	    }
	    
	    StudentData studData = StudentManager.retrieveStudentData(stud);
	    System.out.println(studData);
	}
	catch (SQLException e)
	{
	   e.printStackTrace();
	}
	
	ConnectionManager.close();
    }

}
