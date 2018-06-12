package database.managers;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	    
	    String sql =
		    "SELECT  module.amountPerUnit FROM module  "
		    + "	WHERE module.name = ? "
		    + "INTO @price; "
		    + "SELECT module.units FROM module "
		    + "WHERE module.name = modName INTO @units; "
		    + "INSERT INTO `module_register`( `dateRegistered`, "
		    + "`moduleName`, `AttendanceStatus`, `studentId`, "
		    + "`bookingStatus`, `amountPerUnit`, `result`, "
		    + "`NumberOfUnits`) "
		    + "VALUES (NOW(),modName,false,?,false, @price "
		    + ",null, @units);";


	    try( PreparedStatement stmt = DatabaseManager.getPreparedStatement
		    (sql ,modReg.getModuleName(), modReg.getStudentId());)
	    {
		
		if( stmt.executeUpdate() > 0 ) {
		    String dataQuery = ""
		    	+ "SELECT dateRegistered, numberOfUnits, id; "
		    	+ "WHERE id = LAST_INSERT_ID();"; 
		    ResultSet result = 
			    DatabaseManager.getPreparedStatement(dataQuery)
		    		.executeQuery();
		    modReg.setDateRegistered(result.getDate(1));
		    modReg.setNumberOfUnits(result.getInt(2));
		    modReg.setId(result.getInt(3));
		    result.close(); 
		    
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
		""
		+ "Select COUNT(IF(UCASE(result) ='PASS', 1, NULL)) AS numPassed,  " + 
			"COUNT(IF(result,NULL, 1)) AS registeredButNoResult FROM module_Register as reg " +  
			"WHERE UCASE(reg.studentId) = UCASE(?) AND	 UCASE(reg.moduleName) = UCASE(?) ";


	ResultSet result = null;
	if( modReg.isValid( ValidationType.NEW_BEAN )){
	    try( PreparedStatement stmt = DatabaseManager.getPreparedStatement
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
	double totalPrice = modReg.getAmountPerUnit() * modReg.getNumberOfUnits();
	
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
		"SELECT id,numberOfUnits,  DateRegistered, stud.image as Image,"
			+ " CONCAT(stud.firstName, ' ' , stud.lastName) as StudentName, "
			+ " ModuleName, StudentId, BookingStatus, AttendanceStatus, "
			+ "amountPerUnit, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId " +
			"WHERE UCASE(reg.id) = UCASE(?) " ;
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
			result.getDouble("amountPerUnit" ),
			result.getInt("numberOfUnits"), 
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

    public static boolean bookModule( int modRegId, boolean status ) 
	    throws SQLException, InvalidAdminException{

	ModuleRegister modReg = getModRegById(modRegId);

	if( modReg!= null  && modReg.paymentComplete() ){
	    String sql = ""
	    	+ " UPDATE module_register as reg "
	    	+ "SET reg.BookingStatus = ? "
	    	+ "WHERE  reg.id = ?;";
	    try( PreparedStatement statement = DatabaseManager.getPreparedStatement
		    (sql, status, modRegId))
	    {
		if( statement.executeUpdate() > 0 ) return true;
	    }
	}
	return false;
    }


    public static boolean setAttendance( int modRegId,  boolean attended ) 
	    throws SQLException, InvalidAdminException
    {

	ModuleRegister modReg = getModRegById(modRegId);
	if( modReg != null &&  modReg.hasBooked() && !modReg.hasResult()){
	   String sql  = ""
	    	+ " UPDATE module_register as reg "
	    	+ "SET reg.AttendanceStatus = ? "
	    	+ "WHERE  reg.id = ?; ";
	    try( PreparedStatement statement = DatabaseManager.getPreparedStatement
		    (sql,  attended, modRegId))
	    {
		if( statement.executeUpdate() > 0 ) return true;
	    }
	}
	return false;
    }


    public static boolean setResultForModule( int modRegId, String result ) throws SQLException, InvalidAdminException{

	ModuleRegister modReg = getModRegById(modRegId);
	result = result.toUpperCase().matches("P|PASSED") ? "Pass" : result;
	result = result.toUpperCase().matches("F|FAILED") ? "Fail" : result;

	if( modReg != null &&  modReg.hasAttended() && 
		(result.toLowerCase().equals("pass") || result.toLowerCase().equals("fail")))
	{
	    String sql  = ""
	    	+ "UPDATE module_register as reg  "
	    	+ "SET reg.Result = UCASE(?), reg.AttendanceStatus = 1 "
	    	+ "WHERE reg.id = ? ; ";
	    try( PreparedStatement statement = DatabaseManager.getPreparedStatement
		    (sql,result, modRegId))
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
		"SELECT id, numberOfUnits, DateRegistered, stud.image as Image, ModuleName, "
			+ " CONCAT(stud.firstName, ' ' , stud.lastName) as StudentName,StudentId, BookingStatus, AttendanceStatus, "
			+ "amountPerUnit, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId "
			+ "WHERE UCASE(ModuleName) like UCASE(?) " +
			"ORDER BY dateRegistered " ;
		break;

	    case REG_ID:
		sql = 
		"SELECT id, numberOfUnits, DateRegistered, stud.image as Image, "
			+ "CONCAT(stud.firstName, ' ' , stud.lastName)as StudentName,ModuleName, StudentId, BookingStatus, AttendanceStatus, "
			+ "amountPerUnit, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId "
			+ "WHERE UCASE(id) like UCASE(?) " +
			"ORDER BY dateRegistered ";
		break;
	    default:
		sql = 
		"SELECT id, numberOfUnits, DateRegistered, stud.image as Image, ModuleName,"
			+ "CONCAT(stud.firstName, ' ' , stud.lastName) as StudentName, StudentId, BookingStatus, AttendanceStatus, "
			+ "amountPerUnit, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId "
			+ "WHERE UCASE(StudentId) LIKE UCASE(?)  " +
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
		"SELECT id, numberOfUnits, DateRegistered, stud.image as Image, ModuleName, "
			+ "	CONCAT(stud.firstName, ' ' , stud.lastName) as StudentName, StudentId, BookingStatus, AttendanceStatus, "
			+ "amountPerUnit, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId "
			+ "WHERE BookingStatus = 1 " +
			"ORDER BY dateRegistered "+ 
			"LIMIT ? , 30" ;
		break;
	    case COMPLETED_MODULES:
		sql = 
		"SELECT id, numberOfUnits, DateRegistered, stud.image as Image, "
			+ "	CONCAT(stud.firstName, ' ' , stud.lastName) as studentName"
			+ " , ModuleName, StudentId, BookingStatus, AttendanceStatus, "
			+ "amountPerUnit, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId "
			+ "WHERE result is not null " +
			"ORDER BY dateRegistered "+ 
			"LIMIT ? , 30" ;
		break;

	    default:
		sql = 
		"SELECT id, numberOfUnits, DateRegistered, stud.image as Image, "
			+ " CONCAT(stud.firstName, ' ' , stud.lastName) as StudentName, ModuleName, StudentId, BookingStatus, AttendanceStatus, "
			+ "amountPerUnit, Result , isPaymentComplete( reg.id ) as 'Paid' "
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
			result.getDouble("amountPerUnit"),
			result.getInt("numberOfUnits"), 
			result.getString("Result"));
		modReg.setDateRegistered( result.getDate("DateRegistered"));
		modReg.setId(result.getInt("id"));
		modReg.setPaymentStatus(PaymentManager.isPaymentComplete(modReg.getId()));
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
		"SELECT id,numberOfUnits,  DateRegistered, stud.image as Image, ModuleName, StudentId, "
			+ " CONCAT(stud.firstName, ' ' , stud.lastName) as StudentName, BookingStatus, AttendanceStatus, "
			+ "amountPerUnit, Result , isPaymentComplete( reg.id ) as 'Paid' "
			+ "FROM module_register as reg " + 
			"JOIN student as stud "
			+ "ON stud.id_card_number = reg.studentId " +
			"ORDER BY dateRegistered "+ 
			"LIMIT ? , 30" ;
	return getModuleRegisteredHelper(sql, startIndex);
    }

}
