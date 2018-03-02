package database.managers;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import database.bean.ModuleRegister;
import database.bean.student.Student;
import exception.InvalidAdminException;
import utils.ModuleRegisterFilter;
import utils.ValidationType;

public final class ModuleRegisterManager
{
    /**
     * This registers a Student for a {@code ModuleTabTable}. The {@code ModuleRegister} 
     * object passed as an argument must contain a studentId and moduleName
     * before it can be used to register a {@code Student} for a {@code ModuleTabTable}.<br> 
     * @return {@code true } if the insertion was successful.
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static boolean registerForModule(ModuleRegister modReg ) 
	    throws  SQLException, InvalidAdminException
    {
	boolean modAndStudentExists = 
		StudentManager.exists(modReg.getStudentId()) && 
		ModuleManager.exists(modReg.getModuleName());

	if(modAndStudentExists && modReg.isValid( ValidationType.NEW_BEAN ) && 
		canRegister(modReg) ){
	    try( CallableStatement stmt = DatabaseManager.getCallableStatement
		    ("{call registerForModule(?,?,?)}", modReg.getStudentId(), modReg.getModuleName());)
	    {
		stmt.registerOutParameter(3, Types.DATE);
		if( stmt.executeUpdate() > 0 ) {
		    modReg.setDateRegistered(stmt.getDate(3));
		    return true;
		}
	    }
	}
	return false;
    }



    /**
     * This checks if a {@code ModuleRegister} can be inputed into the database. 
     * A {@code ModuleRegister} can be inserted if it does not exists in the database
     * or if the {@code Student} failed it the first time.  
     * @param modReg the {@code ModuleRegister } to be tested
     * @return {@code true } if the module Register can be inserted into the database.
     * @throws SQLException
     * @throws InvalidAdminException
     */
    public static boolean canRegister(ModuleRegister modReg) throws SQLException, InvalidAdminException{
	String sql = 
		"Select COUNT(IF(UCASE(result) ='PASS', 1, NULL)) AS numPassed,  " + 
			"	COUNT(IF(result,NULL, 1)) AS registeredButNoResult FROM module_Register as reg " +  
			"WHERE reg.studentId = ? AND	 reg.moduleName = ? ";


	ResultSet result = null;
	if( modReg.isValid( ValidationType.NEW_BEAN )){
	    try( CallableStatement stmt = DatabaseManager.getCallableStatement
		    (sql, modReg.getStudentId(), modReg.getModuleName());)
	    {
		result = stmt.executeQuery();

		if(result.next()) return result.getInt(1) == 0 && result.getInt(2) == 0 ;

	    }finally{
		if(result!=null) result.close();
	    }
	}
	return false;
    }
    public static double getTotalPriceForModule(int regID) throws SQLException
    {
	ModuleRegister modReg = ModuleRegisterManager.getModRegById(regID);
	double totalPrice = modReg.getTotalPriceForModule();
	return totalPrice;
    }

    
    
    /**
     * 
     * @param regID
     * @return
     * @throws SQLException
     */
    public static ModuleRegister getModRegById( int regID ) throws SQLException{
	String sql = 
		"SELECT id, DateRegistered, stud.image as Image,"
		+ " CONCAT(stud.firstName, ' ' , stud.lastName) as StudentName, "
		+ " ModuleName, StudentId, BookingStatus, AttendanceStatus, "
			+ "totalPriceForModule, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId " +
			"WHERE reg.id = ? " ;
	ResultSet result = null;
	try( PreparedStatement statement = DatabaseManager.getPreparedStatement
		(sql, regID))
	{
	    ModuleRegister modReg = null;
	    result = statement.executeQuery();
	    if(result.next()){
		String studentID = result.getString("StudentId");
		File image = 
			Student.getImageFromStream(studentID, result.getBinaryStream("image"));
		modReg =  new ModuleRegister(image, 
			result.getString("StudentName"), 
			result.getString("ModuleName"),
			result.getString("StudentId"), 
			result.getBoolean("BookingStatus"),
			result.getBoolean("AttendanceStatus"),
			result.getDouble("totalPriceForModule" ), 
			result.getString("Result"));
		modReg.setDateRegistered( result.getDate("DateRegistered"));
		modReg.setPaymentStatus(result.getBoolean("Paid"));
		return modReg;
	    }

	}
	finally{
	    if( result !=null ) result.close();
	}
	return null;


    }

    public static boolean bookModule( int modRegId, String studId , String moduleName) 
	    throws SQLException, InvalidAdminException{

	ModuleRegister modReg = getModRegById(modRegId);

	if( modReg!= null && !modReg.hasBooked() && modReg.paymentComplete() ){
	    String sql  = "{call bookModule(?,?, ?) }";
	    try( CallableStatement statement = DatabaseManager.getCallableStatement
		    (sql, modRegId, studId, moduleName))
	    {
		if( statement.executeUpdate() > 0 ) return true;
	    }
	}
	return false;
    }


    public static boolean setAttendanceForModule( int modRegId, String studId, String moduleName ) 
	    throws SQLException, InvalidAdminException
    {

	ModuleRegister modReg = getModRegById(modRegId);
	if( modReg != null &&  modReg.hasBooked() && modReg.getResult() == null){
	    String sql  = "{call attendModule(?,?,?) }";
	    try( CallableStatement statement = DatabaseManager.getCallableStatement
		    (sql, modRegId, studId, moduleName))
	    {
		if( statement.executeUpdate() > 0 ) return true;
	    }
	}
	return false;
    }


    public static boolean setResultForModule( int modRegId, String studId, String moduleName, String result ) throws SQLException, InvalidAdminException{

	ModuleRegister modReg = getModRegById(modRegId);
	result = result.toUpperCase().matches("P|PASSED") ? "Pass" : result;
	result = result.toUpperCase().matches("F|FAILED") ? "Fail" : result;

	if( modReg != null &&  modReg.hasAttended() && 
		(result.toLowerCase().equals("pass") || result.toLowerCase().equals("fail")))
	{
	    String sql  = "{call setResult(?,?,?, ?) }";
	    try( CallableStatement statement = DatabaseManager.getCallableStatement
		    (sql, modRegId, studId, moduleName, result))
	    {
		if( statement.executeUpdate() > 0 ) return true;
	    }
	}
	return false;
    }

    
    public static ModuleRegister[] search(ModuleRegisterFilter filter, String searchValue) throws SQLException{
	String sql ;
	
	switch(filter){
	    case MODULE_NAME:
		sql = 
		"SELECT id, DateRegistered, stud.image as Image, ModuleName, "
		+ " CONCAT(stud.firstName, ' ' , stud.lastName) as StudentName,StudentId, BookingStatus, AttendanceStatus, "
			+ "totalPriceForModule, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId "
			+ "WHERE ModuleName like ?" +
			"ORDER BY dateRegistered " ;
		break;
	    
	    case REG_ID:
		sql = 
		"SELECT id, DateRegistered, stud.image as Image, "
		+ "CONCAT(stud.firstName, ' ' , stud.lastName)as StudentName,ModuleName, StudentId, BookingStatus, AttendanceStatus, "
			+ "totalPriceForModule, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId "
			+ "WHERE id like ? " +
			"ORDER BY dateRegistered ";
		break;
	    default:
		sql = 
		"SELECT id, DateRegistered, stud.image as Image, ModuleName,"
		+ "CONCAT(stud.firstName, ' ' , stud.lastName) as StudentName, StudentId, BookingStatus, AttendanceStatus, "
			+ "totalPriceForModule, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId "
			+ "WHERE StudentId LIKE ? " +
			"ORDER BY dateRegistered ";
		break;
	}
	return getModuleRegisteredHelper(sql, "%"+ searchValue + "%");
    }

    public static ModuleRegister[] getModuleRegisters(ModuleRegisterFilter filter, int startIndex) throws SQLException{
	String sql; 
	switch(filter){
	    case BOOKED_MODULES:
		sql = 
		"SELECT id, DateRegistered, stud.image as Image, ModuleName, "
		+ "	CONCAT(stud.firstName, ' ' , stud.lastName) as StudentName, StudentId, BookingStatus, AttendanceStatus, "
			+ "totalPriceForModule, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId "
			+ "WHERE BookingStatus = 1 " +
			"ORDER BY dateRegistered "+ 
			"LIMIT ? , 30" ;
		break;
	    case COMPLETED_MODULES:
		sql = 
		"SELECT id, DateRegistered, stud.image as Image, "
		+ "	CONCAT(stud.firstName, ' ' , stud.lastName) as studentName"
		+ " , ModuleName, StudentId, BookingStatus, AttendanceStatus, "
			+ "totalPriceForModule, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId "
			+ "WHERE result is not null " +
			"ORDER BY dateRegistered "+ 
			"LIMIT ? , 30" ;
		break;
	    
	    default:
		sql = 
		"SELECT id, DateRegistered, stud.image as Image, "
		+ " CONCAT(stud.firstName, ' ' , stud.lastName) as StudentName, ModuleName, StudentId, BookingStatus, AttendanceStatus, "
			+ "totalPriceForModule, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId "+
			"ORDER BY dateRegistered "+ 
			"LIMIT ? , 30" ;
		break;
	    
	}
	
	return getModuleRegisteredHelper(sql, startIndex);
    }
    
    private static ModuleRegister[] getModuleRegisteredHelper(String sql, Object... args) throws SQLException{
	ResultSet result = null;
	ArrayList<ModuleRegister> list = new ArrayList<>(30);
	try( PreparedStatement stmt =
		DatabaseManager.getPreparedStatement(sql, args); )
	{
	    result = stmt.executeQuery();
	    ModuleRegister modReg = null;
	    File image = null ;
	    while( result.next()){
		String studentID = result.getString("StudentID"); 
		image = Student.getImageFromStream(studentID, result.getBinaryStream("image"));
		
		modReg = new ModuleRegister(image,
			result.getString("StudentName"), 
			result.getString("ModuleName"),
			result.getString("StudentId"), result.getBoolean("BookingStatus"),
			result.getBoolean("AttendanceStatus"), 
			result.getDouble("totalPriceForModule"), 
			result.getString("Result"));
		modReg.setDateRegistered( result.getDate("DateRegistered"));
		modReg.setId(result.getInt("id"));
		list.add(modReg);
	    }
	}
	finally{
	    if( result!=null) result.close();
	}
	return list.toArray(new ModuleRegister[list.size()] );
    }
    
    public static ModuleRegister[] getRegisteredModules( int startIndex ) throws SQLException{
	String sql = 
		"SELECT id, DateRegistered, stud.image as Image, ModuleName, StudentId, "
		+ " CONCAT(stud.firstName, ' ' , stud.lastName) as StudentName, BookingStatus, AttendanceStatus, "
			+ "totalPriceForModule, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId " +
			"ORDER BY dateRegistered "+ 
			"LIMIT ? , 30" ;
	return getModuleRegisteredHelper(sql, startIndex);
    }
	
}
