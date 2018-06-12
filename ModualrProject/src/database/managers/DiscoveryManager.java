package database.managers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import database.bean.student.AspiringStudent;
import database.bean.student.MeanOfDiscovery;
import database.bean.student.Student;
import exception.InvalidAdminException;
import utils.ValidationType;

/**
 * 
 * @author Chidiebere
 *
 */
public final class DiscoveryManager
{
    public static boolean insert( MeanOfDiscovery means) 
	    throws SQLException, InvalidAdminException
    {
	if( means.isValid(ValidationType.NEW_BEAN )){
	    String sql = 
		    "INSERT INTO `means_of_discovery`(`StudentID`, `Means`)  "
		    + "VALUES (?,? );";
	    
	    try( PreparedStatement statement =  
		    DatabaseManager.getPreparedStatement
		    (sql, means.getStudentId(), means.getMeans()) ; )
	    {
		int affected = statement.executeUpdate();
		if( affected >0 ) return true ;
	    }
	}
	return false;
    }

    /**
     * 
     * @param oldDisc
     * @param newMeans
     * @return
     * @throws InvalidAdminException
     * @throws SQLException
     */
    public static boolean update(MeanOfDiscovery oldDisc, MeanOfDiscovery newMeans) 
	    throws  InvalidAdminException, SQLException
    {
	if( !DatabaseManager.validateAdmin() ) throw new InvalidAdminException();
	//Ensures that the two objects are valid and that the both have the same 
	//student id card number
	if( ( newMeans.isValid(ValidationType.NEW_BEAN ) &&
		oldDisc.isValid(ValidationType.EXISTING_BEAN)&& 
		oldDisc.getStudentId().equals( newMeans.getStudentId()) ))
	{
	    String sql = 
		    "UPDATE means_of_discovery SET `Means` = ? "
	    	+ " where StudentID = ? AND Means = ?;";
	
   
	    try( PreparedStatement statement =  
		    DatabaseManager.getPreparedStatement
		    (sql,  newMeans.getMeans(), oldDisc.getStudentId(),oldDisc.getMeans()); )
	    {
		int affected = statement.executeUpdate();
		if( affected >0 ) return true ;
	    }
	}
	return false;
    }

    public static MeanOfDiscovery[] getDiscoveryMeans(Student student) throws SQLException, InvalidAdminException
    {
	String sql = 
		"SELECT means from means_of_discovery "
		+ "WHERE studentId = ? ";
	
	return getDiscoveryMeansHelper(sql, student.getIdCardNumber());
    }

    public static MeanOfDiscovery[] getDiscoveryMeans(AspiringStudent aspStudent) throws SQLException
    {
	String sql = 
		"SELECT means from aspiringmeans "
		+ "WHERE aspID = ? ";
	
	return getDiscoveryMeansHelper(sql, String.valueOf(aspStudent.getId()));
    }
    
    public static MeanOfDiscovery[] getDiscoveryMeansHelper(String sql, String id) throws SQLException
    {
	List<MeanOfDiscovery> list = new LinkedList<>();

	ResultSet result =  null;
	try( PreparedStatement statement =  DatabaseManager.getPreparedStatement
		(sql, id); )
	{
	    
	    result = statement.executeQuery();
	    while( result.next() ){
		MeanOfDiscovery mean = new 
			MeanOfDiscovery(id, result.getString(1) );
		list.add( mean );
	    }
	}
	finally{
	    if( result != null ) result.close();
	}

	return list.toArray( new MeanOfDiscovery[ list.size()] );
    }
}
