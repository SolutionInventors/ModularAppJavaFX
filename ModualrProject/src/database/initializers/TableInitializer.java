package database.initializers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.managers.ConnectionManager;
import database.managers.DatabaseConfig;
import database.managers.DatabaseManager;

public class TableInitializer {

    public static void createTables() throws FileNotFoundException{
	DatabaseConfig.noDbConfig = true;
        File initFile = new File("sql/initTable.sql");
        try(
        	InputStreamReader reader = new InputStreamReader(new FileInputStream(initFile));
                BufferedReader buffReader = new BufferedReader(reader);
        ){
            StringBuilder builder = new StringBuilder(2000);
            String data;
	    while( (data = buffReader.readLine()) != null){
	       builder.append(data);
	       builder.append("\r\n");
	    }
	    String sql = builder.toString();
	    PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql);
	    ConnectionManager.setAutoCommiting(false);
	    System.out.println("Initialising the database....");
	    System.out.println(stmt.executeUpdate());
	    System.out.println("Successfully initialised the database!!!");
	    ConnectionManager.setAutoCommiting(true);
	    stmt.close();
	}
	catch (IOException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	catch (SQLException e)
	{
	   System.err.println("An error occured while initialising DB");
	    e.printStackTrace();
	}
        finally{
            ConnectionManager.close();
        }
        
        
    }
    
    public static void main(String[] args) throws FileNotFoundException{
	createTables();
    }

}
