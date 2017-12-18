package database.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.bean.log.CertificateLog;
import database.bean.log.CertificateRegisterLog;
import database.bean.log.Log;
import database.bean.log.TransactionType;
import database.bean.log.ModularClassLog;
import database.bean.log.ModuleLog;
import database.bean.log.ModuleRegisterLog;
import database.bean.log.PaymentLog;
import database.bean.log.StudentLog;

public final class LogManager
{
    @SuppressWarnings("unchecked")
    public static <T extends Log>  T[] getLog( Class<T> logClass, TransactionType transactionType, int startIndex) throws SQLException
    {
	switch( logClass.getSimpleName() ){
	    case "CertificateLog":
		return  (T[]) getCertificateLog(transactionType, startIndex ) ;
	    case "ModuleLog":
		return  (T[]) getModuleLog(transactionType,startIndex ) ;
	    case "PaymentLog":
		return  (T[]) getPaymentLog(transactionType,startIndex ) ;
	    case "ModuleRegisterLog":
		return  (T[]) getModuleRegisterLog(transactionType,startIndex ) ;
	    case "CertificateRegisterLog":
		return  (T[]) getCertificateRegisterLog(transactionType,startIndex ) ;
	    case "ModularClassLog":
		return  (T[]) getModularClassLog(transactionType,startIndex ) ;
	    case "StudentLog":
		return  (T[]) getStudentLog(transactionType,startIndex ) ;
		
	}
	return null;
    }

   

    private static CertificateLog[] getCertificateLog(TransactionType transactionType, int startIndex) throws SQLException
    {
	String sql = "SELECT * FROM CertificateLog \n"
		+ "WHERE operationType LIKE ? \n"
		+ "ORDER BY dateOfOperation DESC \n"
		+ "LIMIT ?, 30 ;";
	
	ResultSet result = null;
	List<CertificateLog> list = new ArrayList<>(30);
	try(PreparedStatement stmt = 
		DatabaseManager.getPreparedStatement(sql, transactionType.getSqlTypeCode(), startIndex))
	{
	    result = stmt.executeQuery();
	    CertificateLog certLog = null;
	    while( result.next()){
		certLog = new CertificateLog(result.getDate("DateOfOperation"), 
			TransactionType.getOPerationType(result.getString("operationType") ),
			result.getString("newCertificateName"), result.getString("oldCertificateName") 	);
		
		list.add(certLog);
	    }
	}
	finally{
	    if( result!=null) result.close();
	}
	return list.toArray( new CertificateLog[list.size()] );
    }

    private static PaymentLog[] getPaymentLog(TransactionType transactionType, int startIndex) throws SQLException
    {
	String  sql = "SELECT * FROM PaymentLog "
		+ "WHERE operationType LIKE ? "
		+ "ORDER BY dateOfOperation DESC " 
		+ "LIMIT ?, 30";

	ResultSet result = null;
	List<PaymentLog> list = new ArrayList<>(30);
	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, 
		transactionType.getSqlTypeCode(), startIndex))
	{
	    result = stmt.executeQuery();
	    PaymentLog payLog = null;
	    while( result.next()){
		payLog = new PaymentLog(result.getDate("dateofoperation"),
			TransactionType.getOPerationType(result.getString("operationType") ),
			result.getInt("regId"),
			result.getString("StudentId"),
			result.getString("moduleName"), 
			result.getString("BankName"), result.getDouble("amount"));
		
		list.add(payLog);
	    }
	}
	finally{
	    if( result!=null) result.close();
	}
	return list.toArray( new PaymentLog[list.size()] );
    }

    private static ModuleRegisterLog[] getModuleRegisterLog(TransactionType transactionType, int startIndex) throws SQLException
    {
	String  sql = 
		"SELECT * FROM ModuleRegisterLog "
		+ "WHERE operationType LIKE ? "
		+ "ORDER BY DateOfOperation DESC "
		+ "LIMIT ?, 30";

	ResultSet result = null;
	List<ModuleRegisterLog> list = new ArrayList<>(30);
	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql,
		transactionType.getSqlTypeCode(), startIndex))
	{
	    result = stmt.executeQuery();
	    ModuleRegisterLog regLog = null;
	    while( result.next()){
		regLog = new ModuleRegisterLog(result.getDate("DateOfOperation"),
			TransactionType.getOPerationType(result.getString("operationType") ),
			result.getInt("regid"),
			result.getString("StudentId"),result.getString("oldModuleName"), 
			result.getString("NewModuleName"), result.getBoolean("oldBookingStatus" ),
			result.getBoolean("newBookingStatus"),result.getBoolean("OldAttendanceStatus"), 
			result.getBoolean("NewAttendanceStatus"), 
			result.getString("oldResult"), result.getString("newResult"));
		list.add(regLog);
	    }
	}
	finally{
	    if( result!=null) result.close();
	}
	return list.toArray( new ModuleRegisterLog[list.size()] );
    }

    private static StudentLog[] getStudentLog(TransactionType transactionType, int startIndex) throws SQLException
    {
	String  sql = "SELECT * FROM StudentLog "
		+ "WHERE operationType LIKE ? "
		+ "ORDER BY dateOfOperation DESC "
		+ "LIMIT ?, 30";

	ResultSet result = null;
	List<StudentLog> list = new ArrayList<>(30);
	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, transactionType.getSqlTypeCode(), startIndex))
	{
	    result = stmt.executeQuery();
	    StudentLog studLog = null;
	    while( result.next()){
		studLog = new StudentLog(result.getDate("dateOfOperation"), 
			TransactionType.getOPerationType(result.getString("operationType") ),
			result.getString("OldID"), result.getString("newID"),
			result.getString("PrevCertificateIssued"),
			result.getString("NewCertificateIssued"),
			result.getBoolean("PrevActiveStatus"),result.getBoolean("NewActiveStatus"),
			result.getString("PrevEmail"), result.getString("NewEmail"));
		list.add(studLog);
	    }
	}
	finally{
	    if( result!=null) result.close();
	}
	return list.toArray( new StudentLog[list.size()] );
    }

    private static ModuleLog[] getModuleLog(TransactionType transactionType, int startIndex) throws SQLException
    {
	String  sql = String.format("SELECT * FROM ModuleLog "
		+ "WHERE operationType LIKE ?"
		+ "ORDER BY dateOfOperation DESC "
		+ "LIMIT ?, 30", transactionType.getSqlTypeCode(), startIndex);

	ResultSet result = null;
	List<ModuleLog> list = new ArrayList<>(30);
	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, transactionType.getSqlTypeCode(), startIndex))
	{
	    result = stmt.executeQuery();
	    ModuleLog modLog = null;
	    while( result.next()){
		modLog = new ModuleLog(result.getDate("dateOfOperation"),
			TransactionType.getOPerationType(result.getString("operationType") ),
			result.getString("OldModuleName"),
			result.getString("NewModuleName"));
		list.add(modLog);
	    }
	}
	finally{
	    if( result!=null) result.close();
	}
	return list.toArray( new ModuleLog[list.size()] );
    }
    
    private static ModularClassLog[] getModularClassLog(TransactionType transactionType, int startIndex) throws SQLException
    {
	String  sql = String.format("SELECT * FROM Modular_Class_Log "
		+ "WHERE operationType LIKE ?"
		+ "ORDER BY dateOfOperation DESC "
		+ "LIMIT ?, 30", transactionType.getSqlTypeCode(), startIndex);

	ResultSet result = null;
	List<ModularClassLog> list = new ArrayList<>(30);
	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, transactionType.getSqlTypeCode(), startIndex))
	{
	    result = stmt.executeQuery();
	    ModularClassLog classLog = null;
	    while( result.next()){
		classLog= new ModularClassLog(result.getDate("dateOfOperation"), 
			TransactionType.getOPerationType(result.getString("operationType") ), 
			result.getString("NewModuleName"),result.getString("OldModuleName"));
		
		list.add(classLog);
	    }
	}
	finally{
	    if( result!=null) result.close();
	}
	return list.toArray( new ModularClassLog[list.size()] );
    }

    private static CertificateRegisterLog[] getCertificateRegisterLog(TransactionType transactionType, int startIndex) throws SQLException
    {
	String  sql = String.format("SELECT * FROM CertificateRegisterLog "
		+ "WHERE operationType LIKE ?"
		+ "ORDER BY dateOfOperation DESC "
		+ "LIMIT ?, 30", transactionType.getSqlTypeCode(), startIndex);

	ResultSet result = null;
	List<CertificateRegisterLog> list = new ArrayList<>(30);
	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, transactionType.getSqlTypeCode(), startIndex))
	{
	    result = stmt.executeQuery();
	    CertificateRegisterLog certRegLog = null;
	    while( result.next()){
		certRegLog = new CertificateRegisterLog(result.getDate("dateOfOperation"),
			TransactionType.getOPerationType(result.getString("operationType") ),
			result.getString("oldCertificateName"), result.getString("newCertificateName"), 
			result.getString("OldModuleName"), result.getString("NewModuleName"));
		list.add(certRegLog);
	    }
	}
	finally{
	    if( result!=null) result.close();
	}
	return list.toArray( new CertificateRegisterLog[list.size()] );
    }
    
}
