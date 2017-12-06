package database.managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

import database.bean.student.Student;
import database.bean.student.StudentData;
import exception.InvalidAdminException;
import exception.InvalidBeanException;
import utils.ValidationType;

public final class StudentManager
{
    public static boolean update( Student existingStudent, Student newStudent ) throws SQLException, InvalidBeanException{

	if( !( existingStudent.isValid(ValidationType.EXISTING_BEAN)  && 
		newStudent.isValid(ValidationType.NEW_BEAN)) ) 
	    throw new InvalidBeanException("The student object contains some invalid values");
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
	return false;
    }


    public static boolean updateStudentData( Student existingStudent, StudentData updatedData) 
	    throws InvalidBeanException, InvalidAdminException, SQLException
    {
	return StudentDataManager.update(existingStudent, updatedData) ;
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
	    throws SQLException, InvalidBeanException, InvalidAdminException
    {
	if( ! DatabaseManager.validateAdmin() ) 
	    throw new InvalidAdminException( "The Admin that wants to make the change is ivalid");

	if( !Student.isValid(ValidationType.NEW_BEAN, newStudent))
	{
	    throw new InvalidBeanException("The Student object cannot be inserted "
		    + "Please ensure that the email, firstName and other attributes are valid.");
	}

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
	return false;
    }


    /**
     * Gets the total number of active or inactive {@code student}s in database 
     * @param active if {@code true} returns active {@code Student}s else returns inactive {@code Student}s
     * @return
     * @throws SQLException
     */
    public static int getStudentsCount( boolean active) throws SQLException
    {
	String sql = active ? "{CALL getActiveStudentsCount() } " :"{CALL getInactiveStudentsCount() } ";
	ResultSet result  = null;
	try(  CallableStatement  statement = DatabaseManager.getCallableStatement( sql);)
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
		throws InvalidAdminException, InvalidBeanException, SQLException
	{
	    if( !DatabaseManager.validateAdmin())
		throw new InvalidAdminException();

	    if( !studData.isValid(ValidationType.NEW_BEAN) )
		throw new InvalidBeanException("Student object data inputed was not valid");

	    boolean edu = Arrays.stream( studData.getEducation() )
		    .allMatch( education-> {
			try
			{
			    return EducationManager.insert( education ) ;
			}
			catch (SQLException | InvalidAdminException | InvalidBeanException e)
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
			catch (SQLException | InvalidAdminException | InvalidBeanException e)
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
			catch (SQLException | InvalidAdminException | InvalidBeanException e)
			{
			    e.printStackTrace();
			    return false;
			}
		    } );

	    boolean exp = Arrays.stream( studData.getExperiences() )
		    .allMatch( experience-> {
			try
			{
			    return ProfExperienceManager.insert( experience ) ;
			}
			catch (SQLException | InvalidAdminException | InvalidBeanException e)
			{
			    e.printStackTrace();
			    return false;
			}
		    } );

	    if( edu && exp && bio && discovery && phone	){
		return true;
	    }
	    else
	    {
		return false;
	    }
	}


	public static  boolean update(Student existingStudent,  StudentData newData) 
		throws InvalidAdminException, InvalidBeanException, SQLException{
	    if( !DatabaseManager.validateAdmin()) throw new InvalidAdminException();
	    if( !( existingStudent.isValid(ValidationType.EXISTING_BEAN) && 
		    newData.isValid(ValidationType.NEW_BEAN)) ) 
	    {
		throw new InvalidBeanException("Some data is invalid");
	    }

	    boolean edu = !Arrays.stream( newData.getEducation()).anyMatch( education->{
		try
		{
		    return EducationManager.update( existingStudent,education) == false ;
		}
		catch (SQLException | InvalidAdminException | InvalidBeanException e)
		{
		    e.printStackTrace();
		    return true;
		}
	    });
	    boolean exp = !Arrays.stream( newData.getExperiences()).anyMatch( experience->{
		try
		{
		    return ProfExperienceManager.update( existingStudent, experience) == false;
		}
		catch (InvalidBeanException | InvalidAdminException e)
		{
		    e.printStackTrace();
		    return true;
		}
	    });

	    boolean bio = BiodataManager.update(  existingStudent, newData.getBiodata());
	    boolean discovery = !Arrays.stream( newData.getMeansOfDiscovery()).anyMatch( disc->{
		try
		{
		    return  DiscoveryManager.update( existingStudent, disc) == false;
		}
		catch (InvalidBeanException | InvalidAdminException | SQLException e)
		{
		    e.printStackTrace();
		    return true;
		}
	    });

	    if( edu && exp && bio && discovery 	){
		return true;
	    }
	    else
	    {
		return false;
	    }
	}

    }


}
