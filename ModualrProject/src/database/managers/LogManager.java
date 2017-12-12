package database.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.bean.log.CertificateLog;
import database.bean.log.Log;
import database.bean.log.LogType;
import database.bean.log.ModuleLog;
import database.bean.log.PaymentLog;
import database.bean.log.RegisterLog;
import database.bean.log.StudentLog;

public final class LogManager
{
    @SuppressWarnings("unchecked")
    public static <T extends Log>  T[] getLog( Class<T> logClass, LogType logType, int startIndex) throws SQLException
    {
	switch( logClass.getSimpleName() ){
	    case "CertificateLog":
		return  (T[]) getCertificateLog(logType, startIndex ) ;
	    case "ModuleLog":
		return  (T[]) getModuleLog(logType,startIndex ) ;
	    case "PaymentLog":
		return  (T[]) getPaymentLog(logType,startIndex ) ;
	    case "RegisterLog":
		return  (T[]) getRegisterLog(logType,startIndex ) ;
	    case "StudentLog":
		return  (T[]) getStudentLog(logType,startIndex ) ;
	}
	return null;
    }

    private static CertificateLog[] getCertificateLog(LogType logType, int startIndex) throws SQLException
    {
	String sql = "SELECT * FROM CertificateLog \n"
		+ "WHERE operationType LIKE ? \n"
		+ "ORDER BY dateOfOperation DESC \n"
		+ "LIMIT ?, 30 ;";
	
	ResultSet result = null;
	List<CertificateLog> list = new ArrayList<>(30);
	try(PreparedStatement stmt = 
		DatabaseManager.getPreparedStatement(sql, logType.getSqlTypeCode(), startIndex))
	{
	    result = stmt.executeQuery();
	    CertificateLog certLog = null;
	    while( result.next()){
		certLog = new CertificateLog(result.getDate("dateOfOperation" ),
			result.getString("certificateName"), result.getString("operationType"));
		list.add(certLog);
	    }
	}
	finally{
	    if( result!=null) result.close();
	}
	return list.toArray( new CertificateLog[list.size()] );
    }

    private static PaymentLog[] getPaymentLog(LogType logType, int startIndex) throws SQLException
    {
	String  sql = "SELECT * FROM PaymentLog "
		+ "WHERE operationType LIKE ? "
		+ "ORDER BY dateOfOperation DESC " 
		+ "LIMIT ?, 30";

	ResultSet result = null;
	List<PaymentLog> list = new ArrayList<>(30);
	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, 
		logType.getSqlTypeCode(), startIndex))
	{
	    result = stmt.executeQuery();
	    PaymentLog payLog = null;
	    while( result.next()){
		payLog = new PaymentLog(result.getInt("regId"), 
			result.getString("operationType"),
			result.getDate("dateofoperation"),
			result.getString("StudentId"), result.getString("moduleName"), 
			result.getDouble("amount"));

		list.add(payLog);
	    }
	}
	finally{
	    if( result!=null) result.close();
	}
	return list.toArray( new PaymentLog[list.size()] );
    }

    private static RegisterLog[] getRegisterLog(LogType logType, int startIndex) throws SQLException
    {
	String  sql = 
		"SELECT * FROM ModuleRegisterLog "
		+ "WHERE operationType LIKE ? "
		+ "ORDER BY DateOfOperation DESC "
		+ "LIMIT ?, 30";

	ResultSet result = null;
	List<RegisterLog> list = new ArrayList<>(30);
	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, logType.getSqlTypeCode(), startIndex))
	{
	    result = stmt.executeQuery();
	    RegisterLog regLog = null;
	    while( result.next()){
		regLog = new RegisterLog(result.getInt("regid"), result.getString("operationType"),
			result.getDate("DateOfOperation"), 
			result.getString("StudentId"), result.getString("ModuleName"));
		list.add(regLog);
	    }
	}
	finally{
	    if( result!=null) result.close();
	}
	return list.toArray( new RegisterLog[list.size()] );
    }

    private static StudentLog[] getStudentLog(LogType logType, int startIndex) throws SQLException
    {
	String  sql = "SELECT * FROM StudentLog "
		+ "WHERE operationType LIKE ? "
		+ "ORDER BY dateOfOperation DESC "
		+ "LIMIT ?, 30";

	ResultSet result = null;
	List<StudentLog> list = new ArrayList<>(30);
	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, logType.getSqlTypeCode(), startIndex))
	{
	    result = stmt.executeQuery();
	    StudentLog studLog = null;
	    while( result.next()){
		studLog = new StudentLog(result.getString("StudentId"),
			result.getString("operationType"),
			result.getDate("dateOfOperation"));
		list.add(studLog);
	    }
	}
	finally{
	    if( result!=null) result.close();
	}
	return list.toArray( new StudentLog[list.size()] );
    }

    private static ModuleLog[] getModuleLog(LogType logType, int startIndex) throws SQLException
    {
	String  sql = String.format("SELECT * FROM ModuleLog "
		+ "WHERE operationType LIKE ?"
		+ "ORDER BY dateOfOperation DESC "
		+ "LIMIT ?, 30", logType.getSqlTypeCode(), startIndex);

	ResultSet result = null;
	List<ModuleLog> list = new ArrayList<>(30);
	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql, logType.getSqlTypeCode(), startIndex))
	{
	    result = stmt.executeQuery();
	    ModuleLog modLog = null;
	    while( result.next()){
		modLog = new ModuleLog(result.getString("moduleName"),
			result.getString("operationType"),
			result.getDate("dateOfOperation"));
		list.add(modLog);
	    }
	}
	finally{
	    if( result!=null) result.close();
	}
	return list.toArray( new ModuleLog[list.size()] );
    }
}
