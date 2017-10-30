package database.managers;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bean.Student;
import exception.InvalidBeanException;

public class StudentManager
{
    /** 
     * 
     * @param student
     * @return
     * @throws SQLException 
     * @throws InvalidBeanException 
     */
   public static boolean insert( Student newStudent ) throws SQLException, InvalidBeanException{
       
       if( !Student.isValid(newStudent))
       {
	   throw new InvalidBeanException("The Student object cannot be inserted "
	   	+ "Please ensure that the email, firstName and other attributes are valid.");
       }
       CallableStatement  statement = 
		DatabaseManager.getCallableStatement( 
			"{CALL insertStudent(?, ?, ?, ? ) } ", newStudent.getIdCardNumber(),
			newStudent.getFirstName(), newStudent.getLastName() ,
			newStudent.getEmailAddress());

	int affected = statement.executeUpdate();
	statement.close();
	ConnectionManager.close();
	if( affected > 0 )
	    return true;

	return false;
   }
   
   /**
    * Gets the first 30 {@code Student}s in the database that are active in the
    * modular program starting from a specified startIndex. 
    * It should be used to create  pages containing students in the application
    * @param startIndex the first index that the query would return. NOte that 
    * the first {@code Student} has index 0
    * @return an array of {@code Student } objects
    * @throws SQLException when a database {@code Exception} occurs
    */
   public static Student[] getAllActiveStudents( int startIndex) throws SQLException{
       CallableStatement  statement = 
		DatabaseManager.getCallableStatement( 
			"{CALL getAllActiveStudents(?) } ");
       
       statement.setInt( 1, startIndex );
       ResultSet result = statement.executeQuery();
       
       ArrayList<Student > list = new ArrayList<Student>();
       
       
       while ( result.next() )
       {
	   String name[] = result.getString("Name" ).split(" " );
	   list.add( new Student(result.getString("ID Card Number"),  name[0], name[1] , 
		   result.getString("Email"), result.getBoolean( "Active"))); 
       }
       return list.toArray( new Student[ list.size()] );
   }
   
   /**
    * Gets the first 30 {@code Student}s in the database that are no longer in the
    * modular program starting from a specified startIndex. 
    * It should be used to create  pages containing students in the application
    * @param startIndex the first index that the query would return. NOte that 
    * the first {@code Student} has index 0
    * @return an array of {@code Student } objects
    * @throws SQLException when a database {@code Exception} occurs
    */
   public static Student[] getAllInactiveStudents( int startIndex) throws SQLException{
       CallableStatement  statement = 
		DatabaseManager.getCallableStatement( 
			"{CALL getAllInactiveStudents(?) } ");
       
       statement.setInt( 1, startIndex );
       ResultSet result = statement.executeQuery();
       
       ArrayList<Student > list = new ArrayList<Student>();
       
       while ( result.next() )
       {
	   String name[] = result.getString("Name" ).split(" " );
	   list.add( new Student(result.getString("ID Card Number"),  name[0], name[1] , 
		   result.getString("Email"), result.getBoolean( "Active"))); 
       }
       return list.toArray( new Student[ list.size()] );
   }

   /**
    * Inserts a new @code Student into the database. Note that initially the {@code Student}
    * is active. However, you can update that value via call to method update.
    * @param student
    * @return
 * @throws SQLException 
 * @throws InvalidBeanException 
    */
   public static boolean update( Student existingStudent, Student updatedStudent ) throws SQLException, InvalidBeanException{
      
       if( !Student.isValid( existingStudent ) ) 
	   throw new InvalidBeanException("The student object contains some invalid values");
      
       else if( !existingStudent.getIdCardNumber().equals( updatedStudent.getIdCardNumber() ) )
	   throw new InvalidBeanException("The new student must have the same id as the existing Student");
      
       CallableStatement  statement = 
		DatabaseManager.getCallableStatement( 
			"{CALL insertStudent(?, ?,?,?, ?) } ");
       
       statement.setString( 1 ,  updatedStudent.getIdCardNumber());
       statement.setString( 2 ,  updatedStudent.getFirstName());
       statement.setString( 3,  updatedStudent.getLastName());
       statement.setBoolean(4, updatedStudent.isActive());
       statement.setString(5 ,  updatedStudent.getEmailAddress());
       
       int affected = statement.executeUpdate();
       if( affected == 1 ) return true;
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
       CallableStatement  statement = 
		DatabaseManager.getCallableStatement( 
			sql);
      
      ResultSet result = statement.executeQuery();
      
      
      try{
	  if( result.next() )
		  return result.getInt( 1 ) ;
      }
      finally{
	  result.close();
      }
      
      return 0;
      
   }
}
