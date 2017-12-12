package database.bean.log;

public enum LogType
{
    INSERT, UPDATE, DELETE, ALL;
    
    public static LogType getLogType(String type){
	switch( type.toUpperCase() ){
	    case "INSERT":
		return LogType.INSERT;
	    case "DELETE":
		return LogType.DELETE;
	    case "UPDATE":
		return LogType.UPDATE;
	}
	return LogType.ALL;
    }
    
    public String getSqlTypeCode(){
	switch( toString() ){
	    case "ALL":
		return "%";
	}
	return toString();
    }
}
