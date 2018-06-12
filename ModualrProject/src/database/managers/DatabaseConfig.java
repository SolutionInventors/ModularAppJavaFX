package database.managers;

public class DatabaseConfig
{
    private final String USERNAME;
    private final String PASSWORD;
    private final String DATABASE_URL;
    private static DatabaseConfig INSTANCE;
    public static boolean noDbConfig = false;
    
    private DatabaseConfig(){
	if("PRODUCTION".equals(System.getenv("JAVA_ENV"))){
	    USERNAME = "prod";
	    PASSWORD = "pass";
	    DATABASE_URL = "DB";
	} else {
	    if(noDbConfig) DATABASE_URL = "jdbc:mysql://localhost?allowMultiQueries=true";
	    else{
		DATABASE_URL = "jdbc:mysql://localhost/modularappdatabasetest?allowMultiQueries=true";
		    
	    }
	    USERNAME = "iitModularAppAdmin";
	    PASSWORD = "O97G9JN>G=F6O?DHLM86";

	}
    }

    public static DatabaseConfig getInstance(){
	if(INSTANCE == null ){
	    INSTANCE = new DatabaseConfig();
	}
	return INSTANCE;
    }
    public String getUsername()
    {
	return USERNAME;
    }
    
    public String getPassword()
    {
	return PASSWORD;
    }
    
    public String getDatabaseUrl()
    {
	return DATABASE_URL;
    }
    
    
}
