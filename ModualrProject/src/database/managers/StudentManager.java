package database.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import database.bean.student.Biodata;
import database.bean.student.EducationalBackground;
import database.bean.student.MeanOfDiscovery;
import database.bean.student.Phone;
import database.bean.student.ProfessionalExperience;
import database.bean.student.Sponsor;
import database.bean.student.Student;
import database.bean.student.StudentData;
import exception.InvalidAdminException;
import utils.ValidationType;

public final class StudentManager
{
    public static boolean update( Student existingStudent, Student newStudent ) throws SQLException,  InvalidAdminException{

	if( ( existingStudent.isValid(ValidationType.EXISTING_BEAN)  && 
		newStudent.isValid(ValidationType.NEW_BEAN)) ) 
	{
	    try( CallableStatement  statement = DatabaseManager.getCallableStatement( 
		    "{CALL updateStudent(?, ?,?,? ,?) } ", existingStudent.getIdCardNumber(), 
		    newStudent.getCertificateIssued(), newStudent.isActive(), 
		    newStudent.getEmailAddress());)
	    {
		statement.registerOutParameter(5, Types.DATE);
		int affected = statement.executeUpdate();
		newStudent.setDateAdmitted( statement.getDate(5) );
		if( affected == 1 ) return true;
	    }
	}

	return false;
    }

    public static boolean updateImage( Student existingStudent, File image) 
	    throws SQLException, InvalidAdminException
    {
	if( existingStudent.isValid(ValidationType.EXISTING_BEAN) ) {
	    String sql = "{call updateStudentImage( ?,? ,?)}";
	    try( 
		    FileInputStream inStream = new FileInputStream(image) ;
		    CallableStatement stmt = DatabaseManager.getCallableStatement(sql,
			    existingStudent.getIdCardNumber(), inStream))
	    {
		stmt.registerOutParameter(3, Types.DATE);
		int affected = stmt.executeUpdate();

		existingStudent.setDateAdmitted( stmt.getDate(3) );
		if( affected >= 1 ) return true;
	    }

	    catch (IOException e)
	    {
		e.printStackTrace();
	    }
	}

	return false;
    }


    public static boolean updateEmailAddress( Student existingStudent, String mail ) 
	    throws SQLException,  InvalidAdminException
    {
	if( ( existingStudent.isValid(ValidationType.EXISTING_BEAN) && mail.length() >0 ) ) 
	{
	    try( CallableStatement  statement = DatabaseManager.getCallableStatement( 
		    "{CALL updateMail(?, ?, ?) } ", existingStudent.getIdCardNumber(),mail);)
	    {
		statement.registerOutParameter(3, Types.DATE);
		int affected = statement.executeUpdate();

		existingStudent.setDateAdmitted( statement.getDate(3) );
		if( affected >= 1 ) return true;
	    }
	}

	return false;
    }


    /**
     * Retrieves a {@code Student } information from the database and returns
     * them in a {@code StudentData } object. This method depends on other 
     * more specific manager classes in order to retrieve each data from the
     * database.
     * @param student the {@code Student} object to be retrieved
     * @return a {@code StudentData} object if the retrieval was successful or
     * {@code null } if there was am error when retrieving a particular information.
     * @throws InvalidAdminException if the {@code Admin } that wants to make the
     * change is invalid
     * @throws SQLException when a database error occurs
     * @throws InvalidBeanException when the object {@code Student} object is invalid.
     * 
     */
    public static StudentData retrieveStudentData(Student student) 
	    throws InvalidAdminException, SQLException
    {
	StudentData studData= null;
	if( student.isValid(ValidationType.EXISTING_BEAN) ){
	    Biodata data = BiodataManager.getBiodata( student);
	    EducationalBackground[] edu = EducationManager.getEducationInfo(student);
	    Phone[] phoneNumbers = PhoneManager.getPhoneNumber(student.getIdCardNumber());
	    ProfessionalExperience[] experiences = ExperienceManager.getExpriences( student);
	    MeanOfDiscovery[] meansOfDisc = DiscoveryManager.getDiscoveryMeans( student);
	    Sponsor[] spons =  SponsorManager.getSponsors( student);
	    studData = new StudentData(data, edu, phoneNumbers,
		    experiences, meansOfDisc, spons);

	}

	return studData.isValid(ValidationType.EXISTING_BEAN) ? studData : null;
    }
    /**
     * Registers a new {@code Student} to the program by using the {@code Student} and 
     * {@code StudentData}. The insertion of the {@code Student} object and each 
     * {@code StudentData} attributes is done as one atomic operation, commits the changes then 
     * resets autoCommit to {@code true}.<br>
     *  Note that all the attributes of the {@code StudentData} object must have thesame student id 
     *  attribute and the {@code isValid} method for each bean is called before insertion <br>
     * This method returns {@code true } if all the info was inputed successfully
     *
     * @param newStudent the {@code Student} object to be added into the database
     * @param studentData a {@link  StudentData	} object containing info about the {@code Studnet}
     * @return {@code true} if the new {@code Student} was registered successfully
     * @throws SQLException when a database error occurs
     * @throws InvalidBeanException when any {@code StudentData} attribute isValid
     * @throws InvalidAdminException when the current {@code Admin} that wants to make the change is invalid
     */
    public static boolean registerStudent( Student newStudent, StudentData studentData) 
	    throws SQLException, InvalidAdminException
    {

	if( Student.isValid(ValidationType.NEW_BEAN, newStudent))
	{
	    try( FileInputStream inStream = new FileInputStream( newStudent.getImage());
		    Connection conn =  ConnectionManager.getInstance().getConnection();
		    CallableStatement statement = DatabaseManager.getCallableStatement("{call insertStudent(?,?,?, ?, ?) }",
			    newStudent.getIdCardNumber() , newStudent.getEmailAddress(), inStream  , 
			    newStudent.getModClassName()) ; )
	    {
		conn.setAutoCommit( false);
		statement.registerOutParameter(5,  Types.DATE);
		int affected = statement.executeUpdate();
		if( affected > 0  && StudentDataManager.insert( studentData) )
		{
		    conn.commit();
		    conn.setAutoCommit( true);
		    newStudent.setDateAdmitted( statement.getDate( 5 ) );
		    return true;
		}
		else
		    conn.rollback();
	    }
	    catch (IOException e)
	    {
		e.printStackTrace();
	    }
	}


	return false;
    }


    /**
     * Gets the total number of active or inactive {@code student}s in database 
     * @param active if {@code true} returns active {@code Student}s else returns inactive {@code Student}s
     * @return
     * @throws SQLException
     * @throws InvalidAdminException 
     */
    public static int getStudentsCount( boolean active) throws SQLException, InvalidAdminException
    {
	String sql = String.format("SELECT COUNT(*) FROM student "
		+ "WHERE active = %d" 	, active ? 1: 0);
	ResultSet result  = null;
	try(  PreparedStatement  statement = DatabaseManager.getPreparedStatement
		( sql);)
	{
	    result = statement.executeQuery();
	    if( result.next() )
		return result.getInt( 1 ) ;
	}
	finally
	{
	    if( result!= null ) result.close();
	}
	return 0;

    }

    public static Student[] getStudents(boolean active , int startIndex ) throws SQLException, InvalidAdminException
    {
	String sql = String.format("SELECT * FROM student "
		+ "WHERE active = %d "
		+ "LIMIT ?, 30" , active ? 1: 0);
	
	ResultSet result  = null;
	List<Student> list = new ArrayList<>(30);
	try(  PreparedStatement  statement = DatabaseManager.getPreparedStatement
		( sql, startIndex);)
	{
	    result = statement.executeQuery();
	    Student stud;
	    while( result.next() ){
		String id = result.getString("id_card_number");
		stud = new Student(id, result.getString("className"),
			result.getString("emailAddress"), 
			getImageFromStream(id, result.getBinaryStream("image")));
		list.add(stud);
	    }
	}
	finally
	{
	    if( result!= null ) result.close();
	}
	return list.toArray( new Student[list.size()] );
    }

    private static File getImageFromStream( String id , InputStream inputStream) throws SQLException
    {
	File studentImage = null;
	FileOutputStream output = null;
	try{
	    if( inputStream.available() <=0 ) return null;
	    studentImage = new File( "res\\" + id + ".jpg" );
	    output = new FileOutputStream( studentImage );
	    studentImage.deleteOnExit();
	    byte[] buffer = new byte[1024];
	    while( inputStream.read( buffer) >0 ){
		output.write( buffer );
	    }
	}

	catch (IOException e)
	{
	    e.printStackTrace();
	    return null;
	}
	finally
	{
	    try
	    {
		if( output !=null ) output.close();
		if( inputStream != null ) inputStream.close();
	    }
	    catch (IOException e)
	    {
		e.printStackTrace();
	    }
	}
	return studentImage;
    }

    /**
     * This method deletes a {@code Student } from the database.
     * it uses the {@code Student}'s id card number to identify the {@code Student } to
     * be deleted.<br>
     * 
     * @param currentAdmin the {@code Admin }object that is logged in.
     * @param AdminToDelete the existing {@code Module} to delete from the database
     * @return {@code true} when delete was successful
     * @throws InvalidAdminException when the {@code Admin } object is not in the database 
     * @throws SQLException 
     */
    public static boolean delete( Student studentToDelete ) throws InvalidAdminException, SQLException{

	try(CallableStatement  statement = DatabaseManager.getCallableStatement( 
		"{call deleteStudent(?)}" );)
	{
	    statement.setString( 1 , studentToDelete.getIdCardNumber() );
	    int affected = statement.executeUpdate();
	    if ( affected == 1 ) return true;
	}

	return false;
    }




    private static final class StudentDataManager
    {
	public static  boolean insert( StudentData studData)
		throws InvalidAdminException, SQLException
	{
	    if( !studData.isValid(ValidationType.NEW_BEAN) ) return false;

	    boolean edu = Arrays.stream( studData.getEducation() )
		    .allMatch( education-> {
			try
			{
			    return EducationManager.insert( education ) ;
			}
			catch (SQLException | InvalidAdminException  e)
			{
			    e.printStackTrace();
			    return false;
			}

		    } );

	    boolean discovery = Arrays.stream( studData.getMeansOfDiscovery() )
		    .allMatch( disc-> {
			try
			{
			    return DiscoveryManager.insert( disc ) ;
			}
			catch (SQLException | InvalidAdminException  e)
			{
			    e.printStackTrace();
			    return false;
			}
		    } );

	    boolean bio = BiodataManager.insert( studData.getBiodata());

	    boolean phone = Arrays.stream( studData.getPhoneNumbers())
		    .allMatch( newPhone-> {
			try
			{
			    return PhoneManager.insert( newPhone) ;
			}
			catch (SQLException | InvalidAdminException  e)
			{
			    e.printStackTrace();
			    return false;
			}
		    } );

	    boolean exp = Arrays.stream( studData.getExperiences() )
		    .allMatch( experience-> {
			try
			{
			    return ExperienceManager.insert( experience ) ;
			}
			catch (SQLException | InvalidAdminException e)
			{
			    e.printStackTrace();
			    return false;
			}
		    } );

	    boolean sponsor = Arrays.stream(studData.getSponsors()) 
		    .allMatch(spons-> {
			try
			{
			    return SponsorManager.insert(spons);
			}
			catch (SQLException | InvalidAdminException  e)
			{
			    e.printStackTrace();
			}
			return false;
		    });

	    if( edu && exp && bio && discovery && phone && sponsor){
		return true;
	    }
	    else
	    {
		return false;
	    }
	}



    }


}
