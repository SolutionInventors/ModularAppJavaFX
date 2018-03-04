package database.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import database.bean.student.AspiringStudent;
import database.bean.student.AspiringStudentData;
import database.bean.student.Student;

public class AspiringStudentManager
{
    public static Map<AspiringStudent, AspiringStudentData> getAspiringStudents(int startIndex) throws SQLException{
	String sql = "SELECT * FROM aspiringStudent "
		+ "LIMIT ?, 30"; 
	
	Map<AspiringStudent, AspiringStudentData> map = new HashMap<>(); 
	ResultSet result = null ; 
	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, startIndex ) ){
	    result =  stmt.executeQuery(); 
	    
	    while(result.next()){
		
		String firstName = result.getString("firstName"); 
		String lastName = result.getString("lastName"); 
		
		AspiringStudent aspStudent =  
			new AspiringStudent
			(
				result.getInt("id"), firstName, 
				lastName, result.getString("title"),
				result.getDate("DateOfBirth"), result.getString("PlaceOfBirth"), 
				result.getString("Religion"), result.getString("PermanentAddress"),
				result.getString("CurrentAddress"), result.getString("email"), 
				result.getString("StateOfOrigin"),result.getString("Country"), 
				result.getString("gender"), 
				Student.getImageFromStream(firstName + "  " +
					lastName,result.getBinaryStream("image")),
				result.getString("highestQualification"), 
				result.getString("currentWorkPlace"), 
				result.getString("courseRead"), 
				result.getInt("YearsExperience"), 
				result.getString("LastInstituteAttended")

			);
		AspiringStudentData aspData = getData(aspStudent); 
		map.put(aspStudent, aspData); 
			
	    }
	}finally{
	    if(result!=null ) result.close();
	}
	return null ;
    }

    public static AspiringStudentData getData(AspiringStudent aspStudent ) 
    {
	int aspID = aspStudent.getId(); 
	AspiringStudentData studData = new AspiringStudentData(aspID);
	
	studData.setEducation(EducationManager.getEducationInfo(aspStudent) );
	studData.setExperiences(ExperienceManager.getExpriences(aspStudent));
	studData.setMeansOfDiscovery(DiscoveryManager.getDiscoveryMeans(aspStudent) );
	studData.setPhoneNumbers(PhoneManager.getPhoneNumber(aspStudent) );
	
	return studData; 
    }

    /**
     * Deletes an aspiring student from the database
     * @param id the id in the aspiring student table
     * @return {@code true} when a aspiring student was deleted
     * @throws SQLException
     */
    public static boolean delete(int id) throws SQLException
    {
	String sql = "DELETE FROM aspiringStudent WHERE id = ? "; 
	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, id) ){
	    int affected = stmt.executeUpdate(); 
	    return affected > 0; 
	}
    }	
    
    
}
