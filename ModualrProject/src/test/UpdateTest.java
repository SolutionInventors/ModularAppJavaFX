package test;

import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;

import database.bean.Admin;
import database.bean.student.Biodata;
import database.bean.student.EducationalBackground;
import database.bean.student.MeanOfDiscovery;
import database.bean.student.Phone;
import database.bean.student.Sponsor;
import database.bean.student.Student;
import database.managers.BiodataManager;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.managers.DiscoveryManager;
import database.managers.EducationManager;
import database.managers.PhoneManager;
import database.managers.SponsorManager;
import database.managers.StudentManager;
import exception.InvalidAdminException;

public class UpdateTest
{

    public static void main(String[] args)
    {
	DatabaseManager.setCurrentAdmin(new Admin( "Chidiebere", "Fred" ));
	String studId = "Mec 12";
	DateFormat df = new SimpleDateFormat("dd-mm-yyyy");

	Date begin = null;
	Date end = null;
	Date birth = null;
	try
	{
	    begin = new Date( df.parse( "10-10-2012" ).getTime());
	    end = new Date( df.parse( "10-10-2018" ).getTime());
	    birth = new Date( df.parse("10-10-1999").getTime());
	}
	catch (ParseException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	String institution = "IIT";
	String course = "Electrotechnics";
	String qualification = "Proficiecy";

	EducationalBackground existing = new EducationalBackground(studId, 
		begin, end, institution, course, qualification);
	EducationalBackground newBean = new EducationalBackground(studId, 
		begin, end, "Unilag", course, qualification);
	Phone existingPhone = new Phone(studId, "08131352729");
	Phone newPhone = new Phone(studId, "+2343322");

	MeanOfDiscovery oldDisc = new MeanOfDiscovery(studId, "FAcebook");
	MeanOfDiscovery newDisc = new MeanOfDiscovery(studId, "IIT Website");

	Student oldStud = new Student(studId);

	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Choose new Student File: " );
	chooser.showOpenDialog(null );
	File file = chooser.getSelectedFile();

	Biodata newBio = new Biodata(studId,"New York", "USA", "LA", "New Jersey", "M", 
		birth, "Manchester", "Jew", "Prof");

	Sponsor oldSpon = new Sponsor(studId, "Lo", "Lasds", "email.com");
	Sponsor newSpon = new Sponsor(studId, "Main", "Man", "Lag", "920392",
		"mail@email.vam");
	
	try
	{
	    if( EducationManager.update(existing, newBean) 	)
		System.out.println("Phone Successfully Updated" );
	    else
		System.err.println("Failed. Old bean probably not found" );
	    if( PhoneManager.update(existingPhone, newPhone) )
		System.out.println("Phone updated" );
	    else
		System.err.println("Failed. Probalbly not in database");
	    if( DiscoveryManager.update(oldDisc, newDisc) )
		System.out.println("Means updated" );
	    else
		System.err.println("Failed. Probalbly not in database");

	    if( StudentManager.updateEmailAddress(oldStud, "newMail@email.com" ) )
		System.out.println("Email updated" );
	    else
		System.err.println("Failed. Probalbly not in database");

	    if( StudentManager.updateImage(oldStud, file ) )
		System.out.println("Image updated" );
	    else
		System.err.println("Failed. Probalbly not in database");

	    if( BiodataManager.update(newBio) )
		System.out.println("BioData updated" );
	    else
		System.err.println("Failed. Probalbly not in database");
	    
	   if( SponsorManager.update(oldSpon, newSpon) )
		System.out.println("Sponsor updated" );
	    else
		System.err.println("Failed. Probalbly not in database");

	}
	catch (SQLException | InvalidAdminException  e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	finally{
	    ConnectionManager.close();
	}
	
    }

}
