package database.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import database.bean.student.AspiringStudent;
import database.bean.student.AspiringStudentData;
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
	   
	    
	    
	    String dateSql = ""
	    	+ " SELECT dateAdmitted FROM student  "
	    	+ "WHERE id_card_number = ?;";
	    
	     String sql = ""
	     	+ " UPDATE `student` "
	     	+ "SET `certificateIssued`= ?, "
	     	+ "`active`=? ,`emailAddress`=? "
	     	+ "WHERE studentId = ?; ";
	     ResultSet dateResult = null;
	    try( PreparedStatement  statement =
		    DatabaseManager.getPreparedStatement( 
		    sql, newStudent.getCertificateIssued(), 
		    newStudent.isActive(), 
		    newStudent.getEmailAddress(), 
		    existingStudent.getIdCardNumber());
		 PreparedStatement dateStatement = 
			 DatabaseManager.getPreparedStatement(
			 dateSql, existingStudent.getIdCardNumber()))
	    {
		int affected = statement.executeUpdate();
		
		if( affected == 1 ){
		    dateResult =dateStatement.executeQuery();
		    dateResult.next();
		    newStudent.setDateAdmitted( dateResult.getDate(0) );
		    return true;
		};
	    }finally{
		if(dateResult != null){
		    dateResult.close();
		}
	    }
	}

	return false;
    }

    public static boolean exists(String studentId) throws SQLException
    {
	String sql = "SELECT id_card_number FROM student WHERE UCASE(id_card_number) = UCASE(?) ";
	ResultSet result = null ; 
	try( PreparedStatement  stmt = DatabaseManager.getPreparedStatement( 
		sql,studentId);)
	{

	    result = stmt.executeQuery(); 
	    return result.next() ;

	}finally{
	    if( result!=null) result.close();
	}
    }


    public static boolean updateImage( Student existingStudent, File image) 
	    throws SQLException, InvalidAdminException
    {
	if( existingStudent.isValid(ValidationType.EXISTING_BEAN) ) {
	   String sql = ""
	   	+ "UPDATE student "
	    	+ "SET student.image = ? WHERE id_card_number = ?";
	    
	    try( 
		    FileInputStream inStream = new FileInputStream(image) ;
		    PreparedStatement stmt = DatabaseManager.getPreparedStatement(
			    sql, inStream,existingStudent.getIdCardNumber()))
	    {
		int affected = stmt.executeUpdate();
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
	    String sql = ""
	    	+ " UPDATE student  "
	    	+ "SET emailAddress = ? "
	    	+ " WHERE id_card_number = ?; ";
	    try( PreparedStatement  statement = DatabaseManager.getPreparedStatement( 
		    sql, mail, existingStudent.getIdCardNumber());)
	    {
		return statement.executeUpdate() >= 1;
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
	ResultSet otherInfoResulSet = null; 
	try{
	    if( student.isValid(ValidationType.EXISTING_BEAN) ){
		Biodata data = BiodataManager.getBiodata( student.getIdCardNumber());
		EducationalBackground[] edu = EducationManager.getEducationInfo(student);
		Phone[] phoneNumbers = PhoneManager.getPhoneNumber(student.getIdCardNumber());
		ProfessionalExperience[] experiences = ExperienceManager.getExpriences( student);
		MeanOfDiscovery[] meansOfDisc = DiscoveryManager.getDiscoveryMeans( student);
		Sponsor[] spons =  SponsorManager.getSponsors( student);

		otherInfoResulSet = getOtherInfo(student.getIdCardNumber()); 


		if( otherInfoResulSet.next()){
		    String higestEducation =  otherInfoResulSet.getString("HighestEducation"); 
		    String currentWorkPlace =  otherInfoResulSet.getString("currentWorkPlace" ); 
		    String courseRead = otherInfoResulSet.getString("CourseRead"); 
		    int yearsExperience = otherInfoResulSet.getInt("yearsExperience"); 
		    String lastInstituteAttended = otherInfoResulSet.getString("lastInstituteAttended"); 
		    studData = new StudentData(
			    higestEducation, currentWorkPlace, courseRead, yearsExperience,
			    lastInstituteAttended, data, edu, phoneNumbers,experiences, meansOfDisc, spons);

		}else{
		    studData = new StudentData(null, null, null, -1, null, 
			    data, edu, phoneNumbers,experiences, meansOfDisc, spons);
		}
	    }

	}finally{
	    if(otherInfoResulSet != null ) otherInfoResulSet.close();
	}

	return studData.isValid(ValidationType.EXISTING_BEAN) ? studData : null;
    }

    private static ResultSet getOtherInfo(String studentID) throws SQLException
    {
	String sql = "select * from otherStudentInfo "
		+ "where UCASE(id_card_number) = UCASE(?) "; 


	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, studentID)){
	    return stmt.executeQuery();
	}

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

    @SuppressWarnings("resource")
    public static boolean registerStudent( Student newStudent, StudentData studentData) 
	    throws SQLException, InvalidAdminException
    {
	Connection conn = ConnectionManager.getInstance().getConnection(); 
	conn.setAutoCommit(false);

	boolean success =  registrationHelper(newStudent, studentData);

	if(  success ){
	    conn.commit();  
	}else{
	    conn.rollback();
	}
	conn.setAutoCommit(true);

	return success;
    }

    /**
     * @param newStudent
     * @param studentData
     * @return
     * @throws SQLException
     * @throws InvalidAdminException
     */
    private static boolean registrationHelper(Student newStudent, StudentData studentData)
	    throws SQLException, InvalidAdminException
    {
	if( newStudent.isValid(ValidationType.NEW_BEAN))
	{
	    String insertStudent = ""
		    	+ " INSERT INTO `student`(`id_card_number` , `dateAdmitted`, `active`,"
		    	+ " `emailAddress`, `className`, `image`, `FirstName`, `LastName`) "
		    	+ " VALUES (?,  NOW(),true,?,?,?, ?, ?);";

	    String otherInfoSql = ""
	    	+ "INSERT INTO other_student_info"
	    	+ "(`StudentID`, `HighestQualification`, `CurrentWorkPlace`,"
	    	+ "`YearsWorkingExperience`, `LastCourseRead`) "
	    	+ "VALUES(?, ?, ? , ?, ?)";
	    
	    try( FileInputStream inStream = new FileInputStream( newStudent.getImage());
		    
		   PreparedStatement statement = 
			    DatabaseManager.getPreparedStatement(
				    insertStudent , 
				    newStudent.getIdCardNumber(), newStudent.getEmailAddress(),
				    newStudent.getModClassName(), inStream,
				    newStudent.getFirstName(),  newStudent.getLastName()
				   ) ; 
		    PreparedStatement otherInfoStmt = 
			    DatabaseManager.getPreparedStatement(otherInfoSql,
				    studentData.getStudentID(), 
				    studentData.getHighestQualificationAttained(), 
				    studentData.getCurrentWorkPlace(), 
				    studentData.getYearsWorkingExperience(), 
				    studentData.getLastCourseRead()); )
	    {
		int rowAffected1 = statement.executeUpdate();
		int rowAffected2 = otherInfoStmt.executeUpdate(); 
		if( rowAffected2 > 0 && rowAffected1 > 0  && StudentDataManager.insert( studentData) )
		{
		    newStudent.setDateAdmitted(ConnectionManager.getCurrentDate());
		   return true;
		}
		return false; 
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

    
    @SuppressWarnings("resource")
    public static boolean registerStudent(
	    String studID, String className, 
	    AspiringStudent aspStudent, 
	    AspiringStudentData aspData) throws SQLException, InvalidAdminException{
	Biodata bio = new Biodata(studID, aspStudent.getStateOfOrigin(), 
		aspStudent.getCountry(), aspStudent.getCurrentAddress(), 
		aspStudent.getPermanentAddress(), aspStudent.getGender(),
		aspStudent.getDateOfBirth(), aspStudent.getPlaceOfBirth(),
		aspStudent.getReligion(), aspStudent.getTitle()); 

	Student newStudent = new Student(aspStudent.getFirstName(), aspStudent.getLastName(),
		studID, className, aspStudent.getEmail(), aspStudent.getImage()); 

	StudentData studData = new StudentData(
		aspStudent.getHighestQualification(),aspStudent.getCurrentWorkPlace(), 
		aspStudent.getCourseRead(), aspStudent.getYearsExperience(), 
		aspStudent.getLastInstituteAttended(), bio,aspData.getEducation(),aspData.getPhoneNumbers(), 
		aspData.getExperiences(),aspData.getMeansOfDiscovery(),
		aspData.getSponsors() 
		); 
	studData.setAllID(studID);
	Connection conn = ConnectionManager.getInstance().getConnection(); 
	conn.setAutoCommit(false);
	boolean success = registrationHelper(newStudent, studData) && 
		AspiringStudentManager.delete(aspStudent.getId()); 

	if(success) conn.commit();
	else conn.rollback();
	conn.setAutoCommit(true);

	return success; 
	
    }
    public static boolean registerStudent(String studID, String className, AspiringStudent aspStudent) throws SQLException, InvalidAdminException{
	AspiringStudentData aspData = AspiringStudentManager.getData(aspStudent); 
	
	return registerStudent( studID,  className,  aspStudent, aspData);
	
    }

    /**
     * Gets all the {@code Student}s in the database and sorts them by classname without 
     * checking if they are active or not
     * @param startIndex using to create a pagging system
     * @return an array of {@code Student}s
     * @throws SQLException
     */
    public static Student[] getStudents(int startIndex) throws SQLException{
	String sql = String.format("SELECT * FROM student "
		+ "ORDER BY className "
		+ "LIMIT ?, 30 "  );

	return getStudentHelper(sql, startIndex); 
    }


    public static Student[] search(String studentId) throws SQLException{
	String sql = String.format("SELECT * FROM student "
		+ "ORDER BY className "
		+ "WHERE ucase(student.id_card_number) LIKE ucase(?) ");

	return getStudentHelper(sql, studentId) ; 
    }
    /**
     * Gets the {@code Student}s in the databasse based on whether they are active 
     * or not
     * @param active
     * @param startIndex
     * @return
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static Student[] getStudents(boolean active , int startIndex ) throws SQLException, InvalidAdminException
    {
	String sql = String.format("SELECT * FROM student "
		+ "WHERE active = %d "
		+ "LIMIT ?, 30", active ? 1:0);

	return getStudentHelper(sql,  startIndex); 

    }

    private static Student[] getStudentHelper(String sql , Object... args) throws SQLException{

	ResultSet result  = null;
	List<Student> list = new ArrayList<>(30);
	try(  PreparedStatement  statement = DatabaseManager.getPreparedStatement
		( sql, args);)
	{
	    result = statement.executeQuery();
	    Student stud;
	    while( result.next() ){
		String id = result.getString("id_card_number");
		String fName = result.getString("FirstName"); 
		String lName = result.getString("LastName"); 
		File file = Student.getImageFromStream(id, result.getBinaryStream("image"));

		stud = new Student(fName, lName, id, result.getString("className"),
			result.getString("emailAddress"), file);
		stud.setDateAdmitted(result.getDate("dateAdmitted"));

		stud.setCertificateIssued(result.getString("certificateIssued")); 
		list.add(stud);
	    }
	}
	finally
	{
	    if( result!= null ) result.close();
	}
	return list.toArray( new Student[list.size()] );
    }


    private static final class StudentDataManager
    {
	public static  boolean insert( StudentData studData)
		throws InvalidAdminException, SQLException
	{
	    if( !studData.isValid(ValidationType.NEW_BEAN) ){
		System.out.println("StudentData validation Failed");
		return false;
	    }

	    System.out.println("StudentData validation Passed");

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
